# Banco UVG con botones

Proyecto academico de banco ficticio con JavaFX. Saldo, depositos, retiros,
historial, encapsulacion, herencia, polimorfismo y metodo generico.

## Integrantes
- [Completar nombre 1]
- [Completar nombre 2]

## Ejecutar
1. Instalar JDK 21 o superior y Apache Maven. Tener conexion a Internet para la primera descarga de dependencias.
2. Descomprimir y abrir esta carpeta completa en VS Code, donde esta pom.xml.
3. Abrir Terminal > New Terminal y ejecutar:

```sh
mvn clean javafx:run
```

Si no se reconoce mvn, verificar la instalacion de Maven y su PATH y reiniciar VS Code.
Para diagnosticar: `java -version`, `javac -version` y `mvn -version`.
Maven debe utilizar el JDK 21 o superior. No copiar solo Main.java al proyecto anterior:
este proyecto configura JavaFX mediante pom.xml. No necesita FXML.

## Uso
Escribir titular ficticio y saldo inicial; pulsar Crear cuenta. Ingresar un monto
y pulsar Depositar o Retirar. El saldo e historial se actualizan automaticamente.
Consultar saldo y Ver historial muestran los datos actuales. Salir cierra la ventana.
Solo permite una cuenta por ejecucion. Al cerrar, los datos se pierden.

## Pruebas que deben realizar
- Crear cuenta con Q500; depositar 200 y retirar 150: Q550.
- Retirar 900: rechazar sin modificar saldo ni historial.
- Depositar hola, 0, -10 y 1.001: rechazar sin agregar movimientos.
- Consultar saldo e historial: dos operaciones validas.
- Registrar mas de diez operaciones: el arreglo se amplia sin perderlas.
- Salir y volver a abrir: datos reiniciados.

## Estructura
Main crea los controles y conecta setOnAction con las operaciones de CuentaBancaria.
CuentaBancaria conserva saldo privado y Movimiento[] iniciado con diez posiciones.
Deposito y Retiro heredan de Movimiento y sobrescriben getDescripcion.
Al mostrar los movimientos, toString usa getDescripcion de cada especializacion.
formatearArreglo(T[]) es el metodo generico que transforma el arreglo en texto.
Se usa BigDecimal para dinero y try/catch para errores de entrada y de negocio.

## GitHub
Usar el repositorio compartido. Cada integrante debe revisar y adaptar su parte,
trabajar en su rama, hacer al menos dos commits claros y abrir un Pull Request
revisado por el companero. Integrar la version probada en main y dar acceso al docente.
No subir target, bin ni archivos .class. Completar nombres y Uso de IA antes de entregar.

## Uso de IA
Se utilizo ChatGPT para preparar la interfaz JavaFX, adaptar la estructura y proponer
pruebas. Se conservaron las reglas de cuenta compartidas por el estudiante.
El asistente no pudo ejecutar la ventana JavaFX en su entorno; las pruebas visuales
se deben realizar en el equipo de los integrantes.
- Integrante 1: [Completar que reviso, cambio y probo realmente].
- Integrante 2: [Completar que reviso, cambio y probo realmente].
