package domain.port.out;

import domain.model.Veterinario;

import java.util.List;

public interface VeterinarioRepositoryPort {
    List<Veterinario> findAll();
    Veterinario findById(Long id);
    List<Veterinario> findByEspecialidad(String especialidad);
    Veterinario save(Veterinario veterinario);
    void deleteById(Long id);
}
