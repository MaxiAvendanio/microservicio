package domain.port.out;

import domain.model.Mascota;

import java.util.List;

public interface MascotaRepositoryPort {
    List<Mascota> findAll();
    Mascota findById(Long id);
    List<Mascota> findDuenioById(Long duenioId);
    Mascota save(Mascota mascota);
    void deleteById(Long id);
}
