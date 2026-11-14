import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class CuentaBancaria {
    private final String titular;
    private BigDecimal saldo;
    private Movimiento[] movimientos = new Movimiento[10];
    private int cantidadMovimientos;

    public CuentaBancaria(String titular, BigDecimal saldoInicial) {
        if (titular == null || titular.trim().isEmpty()) {
            throw new IllegalArgumentException("El titular es obligatorio.");
        }
        BigDecimal inicial = validarDinero(saldoInicial);
        if (inicial.signum() < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }
        this.titular = titular.trim();
        this.saldo = inicial;
    }

    private BigDecimal validarDinero(BigDecimal monto) {
        if (monto == null) {
            throw new IllegalArgumentException("Ingrese un monto.");
        }
        try {
            return monto.setScale(2, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Use como maximo dos decimales.");
        }
    }

    private BigDecimal validarMonto(BigDecimal monto) {
        BigDecimal valido = validarDinero(monto);
        if (valido.signum() <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero.");
        }
        return valido;
    }

    public void depositar(BigDecimal monto) {
        BigDecimal valido = validarMonto(monto);
        registrar(new Deposito(valido));
        saldo = saldo.add(valido);
    }

    public void retirar(BigDecimal monto) {
        BigDecimal valido = validarMonto(monto);
        if (valido.compareTo(saldo) > 0) {
            throw new IllegalArgumentException("Fondos insuficientes.");
        }
        registrar(new Retiro(valido));
        saldo = saldo.subtract(valido);
    }

    private void registrar(Movimiento movimiento) {
        // Se conserva el arreglo y se amplia para no perder operaciones.
        if (cantidadMovimientos == movimientos.length) {
            movimientos = Arrays.copyOf(movimientos, movimientos.length * 2);
        }
        movimientos[cantidadMovimientos++] = movimiento;
    }

    public String getTitular() { return titular; }
    public BigDecimal getSaldo() { return saldo; }

    public Movimiento[] getMovimientos() {
        // Una copia protege el arreglo interno de cambios externos.
        return Arrays.copyOf(movimientos, cantidadMovimientos);
    }
}
