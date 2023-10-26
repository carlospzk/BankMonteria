
package bankmonteria;

import java.util.Date;

/**
 *
 * @author sergio
 */
public class Cliente extends Persona{
  private String tipoCuenta;
  private int numeroCuenta;
  private boolean estadoCuenta;
  private double saldo;
  
  //Constructor super vacio
  public Cliente() {
    super();
  }

  //Constructor super con parametros
  public Cliente(int identificacion, String nombre, String apellido, Date fechaNacimiento, String direccion, String tipoCuenta, int numeroCuenta, boolean estadoCuenta, double saldo) {
    super(identificacion, nombre, apellido, fechaNacimiento, direccion);
    this.tipoCuenta = tipoCuenta;
    this.numeroCuenta = numeroCuenta;
    this.estadoCuenta = estadoCuenta;
    this.saldo = saldo;
  }
  


  //Getters y Setters
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


  @Override
  public String toString() {
    return super.toString() + "Cliente{" + "tipoCuenta=" + tipoCuenta + ", numeroCuenta=" + numeroCuenta + ", estadoCuenta=" + estadoCuenta + ", saldo=" + saldo + '}';
  }
  
  


  





    






    
}
