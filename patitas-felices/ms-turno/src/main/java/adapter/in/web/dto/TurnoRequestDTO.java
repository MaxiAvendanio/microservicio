package adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurnoRequestDTO {
    @Schema(description = "Fecha del turno, no puede ser pasada", example = "2026-09-25", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "La fecha es obligatoria")
    @FutureOrPresent(message = "Seleccione una fecha valida")
    private LocalDateTime fecha;

    @Schema(description = "Motivo de la consulta", example = "Control anual", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "El motivo es obligatorio")
    private String motivo;

    @Schema(description = "ID de la mascota del turno", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Debe indicar la mascota del turno")
    @Positive(message = "El valor debe ser un número positivo")
    private Long idMascota;

    @Schema(description = "ID del veterinario que atiende el turno", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Debe indicar el veterinario del turno")
    @Positive(message = "El valor debe ser un número positivo")
    private Long idVeterinario;
}