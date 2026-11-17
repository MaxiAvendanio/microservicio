package adapter.in.web;


import adapter.in.web.dto.MascotaMapper;
import adapter.in.web.dto.MascotaRequestDTO;
import adapter.in.web.dto.MascotaResponseDTO;
import domain.model.Mascota;
import domain.port.in.MascotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Mascotas", description = "CRUD - clinica veterinaria-Mascota")
@RestController
@RequestMapping("/api/mascota")
@RequiredArgsConstructor
public class MascotaController {

    private final MascotaService mascotaService;

    @Operation(
            summary = "Registro de nuevas mascotas",
            description = "Crea una nueva mascota asociada a un duenio existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Mascota creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Los datos son invalidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "No existe el duenio indicado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Ya existe una mascota con ese nombre para el duenio",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping
    public ResponseEntity<MascotaResponseDTO> registrarMascota(@Valid @RequestBody MascotaRequestDTO mascotaRequestDto) {
        Mascota mascota = mascotaService.save(MascotaMapper.toEntity(mascotaRequestDto));
        return ResponseEntity.ok(MascotaMapper.toResponse(mascota));
    }

    @Operation(summary = "Modifica una mascota existente", description = "Actualiza los datos de la mascota con el ID indicado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mascota modificada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Los datos son invalidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "No existe mascota con ese ID",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<MascotaResponseDTO> modificarMascota(@Parameter(description = "ID de la mascota", example = "1") @PathVariable Long id,
                                                               @Valid @RequestBody MascotaRequestDTO mascotaRequestDto) {
        Mascota mascotaActulizada = mascotaService.update(id, MascotaMapper.toEntity(mascotaRequestDto));
        return ResponseEntity.ok(MascotaMapper.toResponse(mascotaActulizada));
    }

    @Operation(summary = "Elimina una mascota", description = "Borra la mascota con el ID indicado")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Mascota eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "No existe mascota con ese ID",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@Parameter(description = "ID de la mascota", example = "1") @PathVariable Long id) {
        mascotaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Busca mascota por ID", description = "Devuelve los datos de la mascota con el ID indicado; sino existe devuelve 404")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mascota encontrada"),
            @ApiResponse(responseCode = "404", description = "No existe mascota con ese ID",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<MascotaResponseDTO> buscarPorId(@Parameter(description = "ID de la mascota", example = "1") @PathVariable Long id) {
        Mascota mascota = mascotaService.findById(id);
        return ResponseEntity.ok(MascotaMapper.toResponse(mascota));
    }

    @Operation(summary = "Lista todas las mascotas", description = "Devuelve la lista completa de mascotas registradas")
    @GetMapping
    public ResponseEntity<List<MascotaResponseDTO>> listarTodos() {
        List<MascotaResponseDTO> response = mascotaService.findAll().stream().map(MascotaMapper::toResponse).toList();
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Lista mascotas de un duenio", description = "Devuelve todas las mascotas asociadas al duenio indicado")
    @GetMapping("/duenio/{duenioId}")
    public ResponseEntity<List<MascotaResponseDTO>> listarPorDuenio(@Parameter(example = "2") @PathVariable Long duenioId) {
        return ResponseEntity.ok(
                mascotaService.findDuenioById(duenioId).stream()
                        .map(MascotaMapper::toResponse)
                        .toList()
        );
    }
}