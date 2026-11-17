package domain.port.out;

import domain.model.Turno;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TurnoRepositoryPort {
    List<Turno> findAll();
    Turno findById(Long id);
    List<Turno> findByVeterinarioId(Long veterinarioId);
    List<Turno> findByMascotaId(Long mascotaId);
    Turno save(Turno turno);
    boolean isTurnoAvailable(Long veterinarioId, LocalDate fecha);
    List<Turno> findByVeterinarioIdAndFecha(Long veterinarioId, LocalDateTime desde, LocalDateTime hasta);
}
