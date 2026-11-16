package domain.exception;

public class DuplicatedEmailException extends RuntimeException {
    public DuplicatedEmailException(String email) {

        super("Ya existe un duenio con ese email " + email);
    }
}
