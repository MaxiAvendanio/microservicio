package application;

import domain.exception.MascotaNotFoundException;
import domain.model.Mascota;
import domain.port.in.MascotaService;
import domain.port.out.MascotaRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepositoryPort mascotaRepository;

    @Override
    public List<Mascota> findAll() {
        return mascotaRepository.findAll();
    }

    @Override
    public Mascota findById(Long id) {
        Mascota mascota = mascotaRepository.findById(id);
        if (mascota == null) {
            throw new MascotaNotFoundException(id);
        }
        return mascota;
    }

    @Override
    public List<Mascota> findDuenioById(Long duenioId) {
        return mascotaRepository.findDuenioById(duenioId);
    }

    @Override
    @Transactional
    public Mascota save(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    @Transactional
    public Mascota update(Long id, Mascota mascota) {
        Mascota existente = findById(id);
        existente.setNombre(mascota.getNombre());
        existente.setEspecie(mascota.getEspecie());
        existente.setRaza(mascota.getRaza());
        existente.setFechaNacimiento(mascota.getFechaNacimiento());
        existente.setDuenioId(mascota.getDuenioId());
        return mascotaRepository.save(existente);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Mascota mascota = mascotaRepository.findById(id);
        if (mascota == null) {
            throw new MascotaNotFoundException(id);
        }
        mascotaRepository.deleteById(id);
    }

    @Override
    public List<Mascota> findByDuenioId(Long duenioId) {
        return mascotaRepository.findByDuenioId(duenioId);
    }

}
