package domain.exception;

public class MascotaNotFoundException extends RuntimeException {
    public MascotaNotFoundException(Long id) {

        super("No se encontró una mascota con el id " + id);
    }
}
