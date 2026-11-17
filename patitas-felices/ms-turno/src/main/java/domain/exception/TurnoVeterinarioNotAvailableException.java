package domain.exception;

public class TurnoVeterinarioNotAvailableException extends RuntimeException {
    public TurnoVeterinarioNotAvailableException() {
        super("El veterinario ya tiene un turno esa fecha");
    }
}
