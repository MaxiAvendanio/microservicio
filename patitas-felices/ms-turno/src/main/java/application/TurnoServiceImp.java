package application;

import domain.exception.*;
import domain.model.EstadoTurno;
import domain.model.Turno;
import domain.port.in.TurnoService;
import domain.port.out.MascotaServicePort;
import domain.port.out.TurnoRepositoryPort;
import domain.port.out.VeterinarioServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnoServiceImp implements TurnoService {
    private final TurnoRepositoryPort turnoRepository;
    private final MascotaServicePort mascotaService;
    private final VeterinarioServicePort veterinarioService;
    @Override
    public List<Turno> findAll() {
        return turnoRepository.findAll();
    }

    @Override
    public Turno findById(Long id) {
        Turno turno = turnoRepository.findById(id);
        if(turno == null){
            throw new TurnoNotFoundException(id);
        }
        return turno;
    }

    @Override
    public List<Turno> findByVeterinarioId(Long veterinarioId) {
        List<Turno> turnoPorVeterinario = turnoRepository.findByVeterinarioId(veterinarioId);
        if(turnoPorVeterinario == null)
        {
            throw new TurnoVeterinarioNotFoundException();
        }
        return turnoPorVeterinario;
    }

    @Override
    public List<Turno> findByMascotaId(Long mascotaId) {
        List<Turno> turnoPorMascota = turnoRepository.findByMascotaId(mascotaId);
        if(turnoPorMascota == null)
        {
            throw new TurnoMascotaNotFoundException();
        }
        return turnoPorMascota;
    }

    @Override
    @Transactional
    public Turno save(Turno turno) {
        if(!mascotaService.existsById(turno.getMascotaId())){
            throw new MascotaNotFoundException(turno.getId());
        }

        if(!veterinarioService.existsById(turno.getVeterinarioId())){
            throw new VeterinarioNotFoundException(turno.getId());
        }

        turno.setEstado(EstadoTurno.PENDIENTE);
        return turnoRepository.save(turno);
    }

    @Override
    @Transactional
    public Turno cancel(Long id) {
        Turno turno = turnoRepository.findById(id);
        if(turno == null){
            throw new TurnoNotFoundException(id);
        }
        turno.setEstado(EstadoTurno.CANCELADO);
        return turnoRepository.save(turno);
    }
}
