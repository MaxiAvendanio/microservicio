package domain.port.out;

import domain.model.Turno;

import java.util.List;

public interface TurnoRepositoryPort {
    List<Turno> findAll();
    Turno findById(Long id);
    List<Turno> findByVeterinarioId(Long veterinarioId);
    List<Turno> findByMascotaId(Long mascotaId);
    Turno save(Turno turno);
}
