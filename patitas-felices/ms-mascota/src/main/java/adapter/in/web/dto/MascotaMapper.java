package adapter.in.web.dto;


import domain.model.Mascota;

public final class MascotaMapper {

    private MascotaMapper(){

    }

    public static Mascota toEntity(MascotaRequestDTO mascotaDto) {
        Mascota mascota = new Mascota();
        mascota.setNombre(mascotaDto.getNombre());
        mascota.setEspecie(mascotaDto.getEspecie());
        mascota.setRaza(mascotaDto.getRaza());
        mascota.setFechaNacimiento(mascotaDto.getFechaNacimiento());
        mascota.setDuenioId(mascotaDto.getIdDuenio());
        return mascota;
    }


    public static MascotaResponseDTO toResponse(Mascota mascota) {
        return new MascotaResponseDTO(
                mascota.getId(),
                mascota.getNombre(),
                mascota.getEspecie(),
                mascota.getRaza(),
                mascota.getFechaNacimiento(),
                mascota.getDuenioId()
        );
    }

}
