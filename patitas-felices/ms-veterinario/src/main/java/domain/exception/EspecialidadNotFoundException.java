package domain.exception;

public class EspecialidadNotFoundException extends RuntimeException {
    public EspecialidadNotFoundException(String especialidad) {
        super("No se encontro la especialidad " + especialidad);
    }
}
