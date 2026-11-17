package adapter.in.web.dto;

import domain.model.EstadoTurno;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurnoResponseDTO {
    @Schema(description = "Identificador único del turno", example = "1")
    private Long id;
    @Schema(description = "Fecha del turno", example = "2026-09-25")
    private LocalDateTime fecha;
    @Schema(description = "Motivo de la consulta", example = "Control anual")
    private String motivo;
    @Schema(description = "Estado actual del turno", example = "PENDIENTE")
    private EstadoTurno estado;
    @Schema(description = "Nombre de la mascota del turno", example = "Negro")
    private String nombreMascota;
    @Schema(description = "ID de la mascota del turno", example = "1")
    private Long idMascota;
    @Schema(description = "ID del veterinario del turno", example = "1")
    private Long idVeterinario;
    @Schema(description = "Nombre del veterinario del turno", example = "Ana")
    private String nombreVeterinario;
}