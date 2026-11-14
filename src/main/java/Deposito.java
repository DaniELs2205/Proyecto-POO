import java.math.BigDecimal;

public class Deposito extends Movimiento {
    public Deposito(BigDecimal monto) { super(monto); }

    @Override
    public String getDescripcion() { return "Deposito"; }
}
