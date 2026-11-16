package domain.port.out;


import domain.model.Duenio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DuenioRepositoryPort {
    //buscar por nombre HQL
    Optional<Duenio> findByNombreIgnoreCase(String nombre);
    Optional<Duenio> findByEmail(String email);
    Optional<Duenio> findByNombreAndApellidoIgnoreCase(String nombre, String apellido);
    boolean existsByCedula(String cedula);
    boolean existsByEmail(String cedula);
    List<Duenio> findAll();
    Duenio save(Duenio duenio);
    Duenio findById(Long id);
    void deleteById(Long id);
}
