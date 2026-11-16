package application;

import domain.exception.EspecialidadNotFoundException;
import domain.exception.VeterinarioNotFoundException;
import domain.model.Veterinario;
import domain.port.in.VeterinarioService;
import domain.port.out.VeterinarioRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VeterinarioServiceImpl implements VeterinarioService {

    private  final VeterinarioRepositoryPort veterinarioRepository;

    @Override
    public List<Veterinario> findAll() {
        return veterinarioRepository.findAll();
    }

    @Override
    public Veterinario findById(Long id) {
        Veterinario veterinarioExistente = veterinarioRepository.findById(id);
        if(veterinarioExistente == null)
        {
            throw new VeterinarioNotFoundException(id);
        }
        return veterinarioExistente;
    }

    @Override
    public List<Veterinario> findByEspecialidad(String especialidad) {
        List<Veterinario> veterinarioConEspecialidad = veterinarioRepository.findByEspecialidad(especialidad);
        if(veterinarioConEspecialidad == null)
        {
            throw new EspecialidadNotFoundException(especialidad);
        }
        return veterinarioConEspecialidad;
    }

    @Override
    @Transactional
    public Veterinario save(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    @Override
    @Transactional
    public Veterinario update(Long id, Veterinario veterinario) {
        Veterinario veterinarioExistente = veterinarioRepository.findById(id);
        if(veterinarioExistente == null)
        {
            throw new VeterinarioNotFoundException(id);
        }

        veterinarioExistente.setNombre(veterinario.getNombre());
        veterinarioExistente.setApellido(veterinario.getApellido());
        veterinarioExistente.setMatricula(veterinario.getMatricula());
        veterinarioExistente.setEspecialidad(veterinario.getEspecialidad());
        veterinarioExistente.setEmail(veterinario.getEmail());

        return veterinarioRepository.save(veterinarioExistente);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Veterinario veterinarioExistente = veterinarioRepository.findById(id);
        if(veterinarioExistente == null)
        {
            throw new VeterinarioNotFoundException(id);
        }

        veterinarioRepository.deleteById(id);
    }
}
