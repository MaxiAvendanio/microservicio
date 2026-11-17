package domain.port.in;

import domain.model.Turno;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TurnoService {
    List<Turno> findAll();
    Turno findById(Long id);
    List<Turno> findByVeterinarioIdAndFecha(Long veterinarioId, LocalDate fecha);
    List<Turno> findByVeterinarioId(Long veterinarioId);
    List<Turno> findByMascotaId(Long mascotaId);
    Turno save(Turno turno);
    Turno cancel(Long id);
    Turno update(Long id, Turno datos);
}
