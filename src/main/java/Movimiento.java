import java.math.BigDecimal;

// Tipo general: cada especializacion describe su propia operacion.
public abstract class Movimiento {
    private final BigDecimal monto;

    protected Movimiento(BigDecimal monto) {
        if (monto == null || monto.signum() <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo.");
        }
        this.monto = monto;
    }

    public BigDecimal getMonto() { return monto; }
    public abstract String getDescripcion();

    @Override
    public String toString() {
        return getDescripcion() + " de Q" + monto.toPlainString();
    }
}
