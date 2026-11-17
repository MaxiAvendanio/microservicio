package adapter.in.web;


import adapter.in.web.dto.TurnoMapper;
import adapter.in.web.dto.TurnoRequestDTO;
import adapter.in.web.dto.TurnoResponseDTO;
import domain.model.Turno;
import domain.port.in.TurnoService;
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

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Turnos", description = "CRUD - clinica veterinaria-Turno")
@RestController
@RequestMapping("api/turnos")
@RequiredArgsConstructor
public class TurnoController {

    private final TurnoService turnoService;

    @Operation(
            summary = "Registro de nuevos turnos",
            description = "Crea un nuevo turno. El horario debe estar entre las 08:00 y las 18:00, en punto o y media, y no puede superponerse con otro turno del mismo veterinario"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Turno creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Los datos son invalidos o el horario no es valido",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "No existe la mascota o el veterinario indicado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "El veterinario ya tiene un turno asignado en ese horario",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping
    public ResponseEntity<TurnoResponseDTO> guardarTurno(@Valid @RequestBody TurnoRequestDTO turnoRequestDto) {
        Turno turno = turnoService.save(TurnoMapper.toEntity(turnoRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(TurnoMapper.toResponse(turno));
    }

    @Operation(summary = "Busca turno por ID", description = "Devuelve los datos del turno con el ID indicado; sino existe devuelve 404")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Turno encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe turno con ese ID",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<TurnoResponseDTO> buscarPorId(@Parameter(example = "1") @PathVariable Long id) {
        Turno turno = turnoService.findById(id);
        return ResponseEntity.ok(TurnoMapper.toResponse(turno));
    }

    @Operation(summary = "Lista todos los turnos", description = "Devuelve la lista completa de turnos registrados")
    @GetMapping
    public ResponseEntity<List<TurnoResponseDTO>> listarTodos() {

        List<TurnoResponseDTO> response = turnoService.findAll().stream().map(TurnoMapper::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Lista turnos de una mascota", description = "Devuelve el historial de turnos de la mascota indicada, ordenado por fecha descendente")
    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<List<TurnoResponseDTO>> listarPorMascota(@Parameter(example = "1") @PathVariable Long idMascota) {
        List<TurnoResponseDTO> response = turnoService.findByMascotaId(idMascota).stream()
                .map(TurnoMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Modifica un turno existente", description = "Actualiza los datos del turno con el ID indicado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Turno modificado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Los datos son invalidos o el horario no es valido",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "No existe turno, mascota o veterinario con ese ID",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<TurnoResponseDTO> modificarTurno(@Parameter(example = "1") @PathVariable Long id,
                                                           @Valid @RequestBody TurnoRequestDTO dto) {
        return ResponseEntity.ok(turnoService.modificarEntidad(id, dto));
    }

    @Operation(summary = "Elimina un turno", description = "Borra el turno con el ID indicado")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Turno eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No existe turno con ese ID",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<TurnoResponseDTO> cancelar(@Parameter(example = "1") @PathVariable Long id) {
        Turno turnoCancelado = turnoService.cancel(id);
        return ResponseEntity.ok(TurnoMapper.toResponse(turnoCancelado));
    }

    @Operation(summary = "Lista turnos de un veterinario en una fecha", description = "Devuelve la agenda del veterinario indicado para la fecha especificada")
    @GetMapping("/veterinario/{idVeterinario}")
    public List<TurnoResponseDTO> listarPorVeterinarioYFecha(@Parameter(example = "1") @PathVariable Long idVeterinario,
                                                             @Parameter(example = "2026-09-25") @RequestParam LocalDate fecha) {
        return turnoService.findByVeterinarioIdAndFecha(idVeterinario, fecha).stream().map(TurnoMapper::toResponse).toList();
    }

    @Operation(summary = "Lista turnos por nombre de duenio", description = "Devuelve los turnos de las mascotas del duenio indicado")
    @GetMapping("/duenio/{nombre}")
    public List<TurnoResponseDTO> listarPorDuenio(@Parameter(example = "Maxi") @PathVariable String nombre) {
        return turnoService.listarPorDuenio(nombre);
    }


}