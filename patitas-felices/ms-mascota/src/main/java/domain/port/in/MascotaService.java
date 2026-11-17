package domain.port.in;

import domain.model.Mascota;

import java.util.List;

public interface MascotaService {
    List<Mascota> findAll();
    Mascota findById(Long id);
    List<Mascota> findDuenioById(Long duenioId);
    Mascota save(Mascota mascota);
    Mascota update(Long id, Mascota mascota);
    void deleteById(Long id);
    List<Mascota> findByDuenioId(Long duenioId);
}
