package domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private Long mascotaId;

    @Column(nullable = false)
    private Long veterinarioId;

    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoTurno estado;

    private String observaciones;

}
