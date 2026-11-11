import java.math.BigDecimal;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    private CuentaBancaria cuenta;
    private final Label saldo = new Label();
    private final Label mensaje = new Label();
    private final TextArea historial = new TextArea();
    private final TextField monto = new TextField();

    @Override
    public void start(Stage ventana) {
        TextField titular = new TextField();
        titular.setPromptText("Nombre ficticio del titular");
        TextField inicial = new TextField("500.00");
        Button crear = new Button("Crear cuenta");
        HBox datos = new HBox(10, titular, inicial, crear);
        HBox.setHgrow(titular, Priority.ALWAYS);

        monto.setPromptText("Monto, por ejemplo 150.50");
        monto.setMaxWidth(260);
        Button depositar = new Button("Depositar");
        Button retirar = new Button("Retirar");
        Button consultar = new Button("Consultar saldo");
        Button ver = new Button("Ver historial");
        Button salir = new Button("Salir");
        HBox operaciones = new HBox(10, depositar, retirar, consultar, ver);
        VBox banco = new VBox(12, saldo, monto, operaciones,
                new Label("Movimientos registrados"), historial);
        banco.setDisable(true);
        historial.setEditable(false);
        historial.setWrapText(true);
        historial.setPrefRowCount(10);
        saldo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        Label titulo = new Label("Banco UVG");
        titulo.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
        mensaje.setWrapText(true);

        crear.setOnAction(evento -> {
            try {
                cuenta = new CuentaBancaria(titular.getText(), leerMonto(inicial.getText()));
                banco.setDisable(false);
                datos.setDisable(true);
                actualizar();
                mostrarMensaje("Cuenta creada para " + cuenta.getTitular(), false);
                monto.requestFocus();
            } catch (IllegalArgumentException e) {
                mostrarMensaje(e.getMessage(), true);
            }
        });
        depositar.setOnAction(evento -> operar(true));
        retirar.setOnAction(evento -> operar(false));
        consultar.setOnAction(evento -> {
            actualizar();
            mostrarMensaje("Titular: " + cuenta.getTitular() + " | Saldo: Q" + cuenta.getSaldo(), false);
        });
        ver.setOnAction(evento -> {
            actualizar();
            mostrarMensaje("Historial actualizado: " + cuenta.getMovimientos().length + " movimientos.", false);
        });
        salir.setOnAction(evento -> ventana.close());

        VBox contenido = new VBox(16, titulo,
                new Label("Simulacion academica - datos ficticios"),
                new Label("Titular y saldo inicial (Q)"), datos, banco, mensaje, salir);
        contenido.setPadding(new Insets(24));
        contenido.setStyle("-fx-background-color: #f4f7fa; -fx-font-size: 14px;");
        VBox.setVgrow(historial, Priority.ALWAYS);
        VBox.setVgrow(banco, Priority.ALWAYS);
        ventana.setTitle("Banco UVG - Depositos y retiros");
        ventana.setScene(new Scene(contenido, 740, 580));
        ventana.setMinWidth(740);
        ventana.setMinHeight(580);
        ventana.show();
    }

    private BigDecimal leerMonto(String texto) {
        String valor = texto.trim();
        if (!valor.matches("[+-]?[0-9]+(\\.[0-9]{1,2})?")) {
            throw new IllegalArgumentException("Escriba un numero con maximo dos decimales, por ejemplo 150.50.");
        }
        return new BigDecimal(valor);
    }

    private void operar(boolean esDeposito) {
        try {
            BigDecimal cantidad = leerMonto(monto.getText());
            if (esDeposito) {
                cuenta.depositar(cantidad);
            } else {
                cuenta.retirar(cantidad);
            }
            actualizar();
            mostrarMensaje((esDeposito ? "Deposito" : "Retiro") + " registrado correctamente.", false);
            monto.clear();
            monto.requestFocus();
        } catch (IllegalArgumentException e) {
            mostrarMensaje(e.getMessage(), true);
        }
    }

    private void actualizar() {
        saldo.setText("Saldo disponible: Q" + cuenta.getSaldo().toPlainString());
        historial.setText(formatearArreglo(cuenta.getMovimientos()));
    }

    // Metodo generico: cada objeto utiliza su propia representacion toString.
    public static <T> String formatearArreglo(T[] datos) {
        if (datos.length == 0) { return "Todavia no hay movimientos."; }
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < datos.length; i++) {
            texto.append("[").append(i + 1).append("] ").append(datos[i]).append("\n");
        }
        return texto.toString();
    }

    private void mostrarMensaje(String texto, boolean error) {
        mensaje.setText(texto);
        mensaje.setStyle(error ? "-fx-text-fill: #b42318;" : "-fx-text-fill: #17683b;");
    }

    public static void main(String[] args) { launch(args); }
}
