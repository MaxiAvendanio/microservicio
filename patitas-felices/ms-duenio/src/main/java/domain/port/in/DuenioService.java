package domain.port.in;

import domain.model.Duenio;

import java.util.List;
import java.util.Optional;

//Puerta de entrada. Define QUE se puede hacer con los duenios y no COMO
public interface DuenioService {

    List<Duenio> findAll();
    Duenio save(Duenio duenio);
    Duenio update(Long id, Duenio duenio);
    void deleteById(Long id);
    Duenio findById(Long id);
    Optional<Duenio> findByEmail(String email);
    Optional<Duenio> findByNombreIgnoreCase(String name);
    Optional<Duenio> findByNombreAndApellidoIgnoreCase(String name, String apellido);
    boolean existsByEmail(String email);
    boolean existsByCedula(String cedula);
}
