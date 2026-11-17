package adapter.in.web.dto;


import domain.model.Turno;

public final class TurnoMapper {
    private TurnoMapper(){

    }

    public static Turno toEntity(TurnoRequestDTO turnoDTO) {
        Turno turno = new Turno();
        turno.setFecha(turnoDTO.getFecha());
        turno.setMascotaId(turnoDTO.getIdMascota());
        turno.setVeterinarioId(turnoDTO.getIdVeterinario());
        turno.setMotivo(turnoDTO.getMotivo());
        return turno;
    }


    public static TurnoResponseDTO toResponse(Turno turno) {
        return new TurnoResponseDTO(
                turno.getId(),
                turno.getFecha(),
                turno.getMotivo(),
                turno.getEstado(),
                turno.getObservaciones(),
                turno.getMascotaId(),
                turno.getVeterinarioId(),
                turno.getMotivo()
        );
    }
}
