# Banco UVG

Proyecto final de Programación Orientada a Objetos de la Universidad del Valle de Guatemala, Campus Altiplano.

**Opción B: Banco digital básico.**

Aplicación de escritorio desarrollada en Java con JavaFX para administrar una cuenta ficticia mediante botones.

## Integrantes

- William Bedardo Ajanel Chumil.
- Daniel Pachac.

## Funciones

- Crear una cuenta con titular y saldo inicial.
- Depositar cantidades positivas.
- Retirar dinero cuando exista saldo suficiente.
- Consultar el saldo y el historial de movimientos.
- Rechazar entradas no numéricas, montos inválidos y retiros sin fondos.
- Cerrar la aplicación mediante el botón Salir.

Los datos se guardan en memoria durante la ejecución. Al cerrar la aplicación, se pierden la cuenta y los movimientos registrados.

## Requisitos

- JDK 21 o superior.
- Apache Maven.
- Visual Studio Code con Extension Pack for Java.
- Conexión a Internet para descargar las dependencias la primera vez.

JavaFX está configurado mediante Maven en el archivo `pom.xml`.

## Cómo ejecutar

1. Descargar el repositorio desde **Code → Download ZIP**.
2. Extraer el archivo ZIP.
3. Abrir Visual Studio Code.
4. Seleccionar **File → Open Folder** y abrir la carpeta que contiene `pom.xml`.
5. Esperar a que se carguen el proyecto Java y sus dependencias.
6. Abrir `src/main/java/Inicio.java`.
7. Pulsar **Run** encima del método `main`.

También se puede ejecutar desde la terminal, dentro de la carpeta del proyecto:

```sh
mvn clean javafx:run
```

## Cómo utilizar el banco

1. Ingresar un titular ficticio y un saldo inicial.
2. Pulsar **Crear cuenta**.
3. Escribir el monto de la operación.
4. Pulsar **Depositar** o **Retirar**.
5. Utilizar **Consultar saldo** y **Ver historial** para consultar la cuenta.
6. Pulsar **Salir** para cerrar la aplicación.

Los montos deben escribirse sin el símbolo Q y con punto decimal, por ejemplo `150.50`.

## Organización del código

| Clase | Responsabilidad |
|---|---|
| `Inicio` | Iniciar la aplicación desde Run en VS Code. |
| `Main` | Crear la ventana y conectar los botones con las operaciones. |
| `CuentaBancaria` | Administrar el saldo, las validaciones y el arreglo de movimientos. |
| `Movimiento` | Definir el tipo general de las operaciones bancarias. |
| `Deposito` | Representar un depósito. |
| `Retiro` | Representar un retiro. |

## Conceptos de programación utilizados

### Encapsulación

Los atributos de la cuenta son privados. El saldo se modifica mediante los métodos `depositar()` y `retirar()`, que aplican las validaciones correspondientes.

### Herencia y polimorfismo

`Deposito` y `Retiro` heredan de `Movimiento` y sobrescriben el método `getDescripcion()`.

El arreglo almacena ambas especializaciones mediante el tipo general `Movimiento`. Al mostrar cada objeto, su método `toString()` utiliza la descripción correspondiente.

### Arreglos

El historial utiliza un arreglo `Movimiento[]` con diez espacios iniciales. Cuando se llena, se amplía para conservar las operaciones anteriores y permitir nuevos movimientos.

### Método genérico

El método `formatearArreglo(T[] datos)` recorre el arreglo y genera el texto que se muestra en el historial.

### Validaciones y excepciones

Se utiliza `try/catch` para controlar errores sin cerrar abruptamente el programa. Se rechazan:

- Titulares vacíos.
- Saldos iniciales negativos.
- Depósitos y retiros iguales o menores que cero.
- Montos con más de dos decimales.
- Entradas no numéricas.
- Retiros superiores al saldo disponible.

### Manejo de dinero

Se utiliza `BigDecimal` para representar los montos con precisión decimal.

## Pruebas realizadas

Las siguientes pruebas se realizaron con un saldo inicial de Q500.00:

| Prueba | Resultado observado |
|---|---|
| Depositar Q200 | Saldo actualizado a Q700.00. |
| Retirar Q150 | Saldo actualizado a Q550.00. |
| Intentar retirar Q900 | Operación rechazada por fondos insuficientes. |
| Ingresar texto como monto | Entrada rechazada con mensaje de validación. |
| Intentar depositar −10 | Operación rechazada por monto negativo. |
| Consultar saldo | Se mostró Q550.00. |
| Consultar historial | Se mostraron el depósito de Q200 y el retiro de Q150. |

Los intentos rechazados conservaron el saldo y no agregaron movimientos al historial.

## Alcance

El programa es una simulación académica. No utiliza cuentas reales, conexiones bancarias ni bases de datos. La información se conserva únicamente mientras la aplicación está abierta.

## Uso de IA

Se utilizó ChatGPT como apoyo para generar y adaptar partes del código, resolver errores y configurar JavaFX. Se probó el funcionamiento de depósitos, retiros, historial y validaciones.
