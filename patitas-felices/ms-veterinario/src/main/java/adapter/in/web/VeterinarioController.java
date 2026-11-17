package adapter.in.web;


import adapter.in.web.dto.VeterinarioMapper;
import adapter.in.web.dto.VeterinarioRequestDTO;
import adapter.in.web.dto.VeterinarioResponseDTO;
import domain.model.Veterinario;
import domain.port.in.VeterinarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Veterinarios", description = "CRUD - clinica veterinaria-Veterinario")
@RestController
@RequestMapping("/api/veterinario")
@RequiredArgsConstructor
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    @Operation(summary = "Registro de nuevos veterinarios", description = "Crea un nuevo veterinario. La matricula debe tener el formato MP-XXXX")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Veterinario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Los datos son invalidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Matricula ya registrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping
    public ResponseEntity<VeterinarioResponseDTO> registrarVeterinario(@Valid @RequestBody VeterinarioRequestDTO veterinarioRequestDTO) {
        Veterinario veterinario = veterinarioService.save(VeterinarioMapper.toEntity(veterinarioRequestDTO));
        return ResponseEntity.ok(VeterinarioMapper.toResponse(veterinario));
    }

    @Operation(summary = "Busca veterinario por ID", description = "Devuelve los datos del veterinario con el ID indicado; sino existe devuelve 404")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veterinario encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe veterinario con ese ID",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<VeterinarioResponseDTO> buscarPorId(@Parameter(example = "1") @PathVariable Long id) {
        Veterinario veterinario = veterinarioService.findById(id);
        return ResponseEntity.ok(VeterinarioMapper.toResponse(veterinario));
    }

    @Operation(summary = "Lista todos los veterinarios", description = "Devuelve la lista completa de veterinarios registrados")
    @GetMapping
    public ResponseEntity<List<VeterinarioResponseDTO>> listarTodos() {
        List<VeterinarioResponseDTO> response = veterinarioService.findAll().stream().map(VeterinarioMapper::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Modifica un veterinario existente", description = "Actualiza los datos del veterinario con el ID indicado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veterinario modificado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Los datos son invalidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "No existe veterinario con ese ID",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<VeterinarioResponseDTO> modificarVeterinario(@Parameter(example = "1") @PathVariable Long id,
                                                                       @Valid @RequestBody VeterinarioRequestDTO veterinarioRequestDTO) {
        Veterinario veterinarioActulizado = veterinarioService.update(id, VeterinarioMapper.toEntity(veterinarioRequestDTO));
        return ResponseEntity.ok(VeterinarioMapper.toResponse(veterinarioActulizado));
    }

    @Operation(summary = "Elimina un veterinario", description = "Borra el veterinario con el ID indicado")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Veterinario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No existe veterinario con ese ID",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVeterinario(@Parameter(example = "1") @PathVariable Long id) {
        veterinarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}