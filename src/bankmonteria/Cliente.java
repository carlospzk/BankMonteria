
package bankmonteria;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author sergio
 */
public class Cliente extends Persona {
  private String tipoCuenta;
  private int numeroCuenta;
  private boolean estadoCuenta;
  private double saldo;

  // Constructor vacio
  public Cliente() {
    super();
  }

  // Constructor super vacio
  // public Cliente(int i, String nombreCliente, String apellidoCliente, Date fechaNacimientoCliente,
  //     String direccionCliente, String tipoCuentaCliente, String tipoCuentaClienteDescription, int numeroCuentaCliente,
  //     boolean isEstadoCuentaCliente, double saldoInicialCliente) {
  //   super();
  // }

  // Constructor super con parametros
  public Cliente(int identificacion, String nombre, String apellido, Date fechaNacimiento, String direccion,
      String tipoCuenta, int numeroCuenta, boolean estadoCuenta, double saldo) {
    super(identificacion, nombre, apellido, fechaNacimiento, direccion);
    this.tipoCuenta = tipoCuenta;
    this.numeroCuenta = numeroCuenta;
    this.estadoCuenta = estadoCuenta;
    this.saldo = saldo;
  }

  // Getters y Setters
  public String getTipoCuenta() {
    return tipoCuenta;
  }

  public void setTipoCuenta(String tipoCuenta) {
    this.tipoCuenta = tipoCuenta;
  }

  public int getNumeroCuenta() {
    return numeroCuenta;
  }

  public void setNumeroCuenta(int numeroCuenta) {
    this.numeroCuenta = numeroCuenta;
  }

  public boolean getEstadoCuenta() {
    return estadoCuenta;
  }

  public void setEstadoCuenta(boolean estadoCuenta) {
    this.estadoCuenta = estadoCuenta;
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

  public void setIdentificacion(int identificacion) {
    this.identificacion = identificacion;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  // Actualizar datos del cliente (informacion personal)
  public void actualizarDatosCliente(String nombre, String apellido, Date fechaNacimiento, String direccion) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaNacimiento = fechaNacimiento;
    this.direccion = direccion;
  }


  public String formatClient() {
    // "Cliente: " + nombreCliente + " " + cliente.getApellido() + "\n" +
    //             "Identificacion: " + identificacionCliente + "\n" +
    //             "Fecha de nacimiento: " + fechaNacimientoCliente + "\n" +
    //             "Edad: " + edad + " años, " + edadMeses + " meses, " + edadDias + " dias, " + edadHoras + " horas, "
    //             + edadMinutos + " minutos, " + edadSegundos + " segundos\n" +
    //             "Direccion: " + direccionCliente + "\n" +
    //             "Tipo de cuenta: " + tipoCuentaClienteDescription + "\n" +

    String tipoCuenta = "";
    String estadoCuenta = "";

    if (this.tipoCuenta.equals("1")) {
      tipoCuenta = "Ahorros";
    } else if (this.tipoCuenta.equals("2")) {
      tipoCuenta = "Corriente";
    } else {
      tipoCuenta = "NA";
    }

    if (this.estadoCuenta == true) {
      estadoCuenta = "Activa";
    } else {
      estadoCuenta = "Inactiva";
    }

    return "Cliente: " + this.nombre + " " + this.apellido + "\n" + "Identificación: " + this.identificacion + "\n"
        + "Fecha de nacimiento: " + this.fechaNacimiento + "\n" + "Dirección: " + this.direccion + "\n" + "Tipo de cuenta: "
        + tipoCuenta + "\n" + "Numero de cuenta: " + this.numeroCuenta + "\n" + "Cuenta: " + estadoCuenta + "\n"
        + "Saldo: " + this.saldo + "\n";
  }

  public static Cliente obtenerConIdentificacionCliente(int identificacion, ArrayList<Cliente> clientes) {
      for (Cliente cliente : clientes) {
        if (cliente.identificacion == identificacion) {
          return cliente;
        }
      }
      return null;
    }

  public static Cliente obtenerNombreCliente(String nombre, ArrayList<Cliente> clientes) {
    for (Cliente cliente : clientes) {
      if (cliente.nombre.equals(nombre)) {
        return cliente;
      }
    }
    return null;
  }

  // Polimorfismo
  @Override
  public String toString() {
    return super.toString() + "Cliente {" + "tipoCuenta = " + tipoCuenta + ", numeroCuenta = " + numeroCuenta
        + ", estadoCuenta = " + estadoCuenta + ", saldo = " + saldo + '}';
  }

}
