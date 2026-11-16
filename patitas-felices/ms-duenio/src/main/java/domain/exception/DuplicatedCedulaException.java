package domain.exception;

public class DuplicatedCedulaException extends RuntimeException {
    public DuplicatedCedulaException(String cedula) {

        super("Ya existe un duenio con esa cedula " + cedula);
    }
}
