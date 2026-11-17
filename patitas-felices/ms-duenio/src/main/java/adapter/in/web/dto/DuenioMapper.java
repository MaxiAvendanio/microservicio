package adapter.in.web.dto;


import domain.model.Duenio;

public final class DuenioMapper {
    private DuenioMapper(){}

    //Pasar dto al dominio
    public static Duenio toEntity(DuenioRequestDTO duenioRequestDTO){
        Duenio duenio = new Duenio();
        duenio.setNombre(duenioRequestDTO.getNombre());
        duenio.setApellido(duenioRequestDTO.getApellido());
        duenio.setCedula(duenioRequestDTO.getCedula());
        duenio.setTelefono(duenioRequestDTO.getTelefono());
        duenio.setEmail(duenioRequestDTO.getEmail());

        return duenio;
    }

    public static DuenioResponseDTO toResponse(Duenio duenio){
        return new DuenioResponseDTO(
                duenio.getId(),
                duenio.getNombre(),
                duenio.getApellido(),
                duenio.getCedula(),
                duenio.getTelefono(),
                duenio.getEmail()
        );
    }
}
