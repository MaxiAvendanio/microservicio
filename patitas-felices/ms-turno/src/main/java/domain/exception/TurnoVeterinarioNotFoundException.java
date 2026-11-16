package domain.exception;

public class TurnoVeterinarioNotFoundException extends RuntimeException {
    public TurnoVeterinarioNotFoundException() {
        super("El veterinario no tiene turnos asignados");
    }
}
