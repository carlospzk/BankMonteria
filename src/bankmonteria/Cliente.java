
package bankmonteria;

import java.util.Date;

/**
 *
 * @author sergio
 */
public class Cliente extends Persona{
  private String tipoCuenta;
  private String estadoCuenta;

  //Constructor super vacio
  public Cliente() {
    super();
  }

  //Constructor super con parametros
  public Cliente(String identificacion, String nombre, String apellido, Date fechaNacimiento, String direccion, String tipoCuenta, String estadoCuenta) {
    super(identificacion, nombre, apellido, fechaNacimiento, direccion);
    this.tipoCuenta = tipoCuenta;
    this.estadoCuenta = estadoCuenta;
  }


  //Getters y Setters
  public String getTipoCuenta() {
    return tipoCuenta;
  }

  public void setTipoCuenta(String tipoCuenta) {
    this.tipoCuenta = tipoCuenta;
  }

  public String getEstadoCuenta() {
    return estadoCuenta;
  }

  public void setEstadoCuenta(String estadoCuenta) {
    this.estadoCuenta = estadoCuenta;
  }

  public void setIdentificacion(String identificacion) {
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
    return "Cliente{" + "identificacion=" + identificacion + ", nombres=" + nombre + ", apellidos=" + apellido + ", fechaNacimiento=" + fechaNacimiento + ", direccion=" + direccion + ", tipoCuenta=" + tipoCuenta + ", estadoCuenta=" + estadoCuenta + '}';
}
  


  





    






    
}
