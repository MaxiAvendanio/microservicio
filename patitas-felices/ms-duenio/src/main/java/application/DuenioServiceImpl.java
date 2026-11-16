package application;


import domain.exception.DuenioNotFoundException;
import domain.exception.DuplicatedCedulaException;
import domain.exception.DuplicatedEmailException;
import domain.model.Duenio;
import domain.port.in.DuenioService;
import domain.port.out.DuenioRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DuenioServiceImpl implements DuenioService {
    @Autowired
    private final DuenioRepositoryPort duenioRepository;
//    @Autowired
//    private final DuenioMapper duenioMapper;



    @Override
    public List<Duenio> findAll() {
        return duenioRepository.findAll();
    }

    @Override
    @Transactional
    public Duenio save(Duenio duenio) {
        if(duenioRepository.existsByCedula(duenio.getCedula())){
            throw new DuplicatedCedulaException(duenio.getCedula());
        }
        return duenioRepository.save(duenio);
    }

    @Override
    @Transactional
    public Duenio update(Long id, Duenio duenio) {
        Duenio duenioExistente = duenioRepository.findById(id);

        if (duenioExistente == null) throw new DuenioNotFoundException(id);

        duenioExistente.setNombre(duenio.getNombre());
        duenioExistente.setApellido(duenio.getApellido());
        duenioExistente.setTelefono(duenio.getTelefono());
        duenioExistente.setCedula(duenio.getCedula());

        return duenioRepository.save(duenioExistente);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Duenio duenioExistente = duenioRepository.findById(id);

        if (duenioExistente == null) throw new DuenioNotFoundException(id);

        duenioRepository.deleteById(id);
    }

    @Override
    public Duenio findById(Long id) {
        Duenio duenioExistente = duenioRepository.findById(id);

        if (duenioExistente == null) throw new DuenioNotFoundException(id);

        return duenioExistente;
    }

    @Override
    public Optional<Duenio> findByEmail(String email) {
        Optional<Duenio> emailExistente = duenioRepository.findByEmail(email);

        if(emailExistente.isEmpty()) throw new DuplicatedEmailException(email);

        return emailExistente;
    }

    @Override
    public Optional<Duenio> findByNombreIgnoreCase(String name) {
        Optional<Duenio> nameExists = duenioRepository.findByNombreIgnoreCase(name);

        if(nameExists.isEmpty()) throw new DuplicatedEmailException(name);

        return nameExists;
    }

    @Override
    public Optional<Duenio> findByNombreAndApellidoIgnoreCase(String name, String apellido) {
        Optional<Duenio> nameAndApellidoExists = duenioRepository.findByNombreAndApellidoIgnoreCase(name, apellido);

        if(nameAndApellidoExists.isEmpty()) throw new DuplicatedEmailException(name);

        return nameAndApellidoExists;
    }

    @Override
    public boolean existsByEmail(String email) {
        return  duenioRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByCedula(String cedula) {
        return duenioRepository.existsByCedula(cedula);
    }

}