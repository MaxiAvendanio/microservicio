package domain.port.in;

import domain.model.Veterinario;

import java.util.List;

public interface VeterinarioService {
    List<Veterinario> findAll();
    Veterinario findById(Long id);
    List<Veterinario> findByEspecialidad(String especialidad);
    Veterinario save(Veterinario veterinario);
    Veterinario update(Long id, Veterinario veterinario);
    void deleteById(Long id);
}
