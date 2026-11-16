package domain.exception;

public class TurnoMascotaNotFoundException extends RuntimeException {
    public TurnoMascotaNotFoundException() {
        super("La mascota no tiene turnos activos");
    }
}
