import java.math.BigDecimal;

public class Retiro extends Movimiento {
    public Retiro(BigDecimal monto) { super(monto); }

    @Override
    public String getDescripcion() { return "Retiro"; }
}
