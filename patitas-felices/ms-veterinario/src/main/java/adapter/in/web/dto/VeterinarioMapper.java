package adapter.in.web.dto;


import domain.model.Veterinario;

public final class VeterinarioMapper {

    private VeterinarioMapper() {
    }

    public static Veterinario toEntity(VeterinarioRequestDTO veterinarioDto) {
        Veterinario veterinario = new Veterinario();
        veterinario.setNombre(veterinarioDto.getNombre());
        veterinario.setApellido(veterinario.getApellido());
        veterinario.setEmail(veterinario.getEmail());
        veterinario.setMatricula(veterinarioDto.getMatricula());
        veterinario.setEspecialidad(veterinarioDto.getEspecialidad());
        return veterinario;
    }


    public static VeterinarioResponseDTO toResponse(Veterinario veterinario) {
        return new VeterinarioResponseDTO(
                veterinario.getId(),
                veterinario.getNombre(),
                veterinario.getApellido(),
                veterinario.getEmail(),
                veterinario.getMatricula(),
                veterinario.getEspecialidad()
        );
    }
}
