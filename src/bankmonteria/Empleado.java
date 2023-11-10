
package bankmonteria;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author sergio
 */
public class Empleado extends Persona {
  private boolean turno;
  private Cargo cargo;

  // Constructor super vacio
  public Empleado() {
    super();
  }

  // Constructor super con parametros
  public Empleado(int identificacion, String nombre, String apellido, Date fechaNacimiento, String direccion,
      Cargo cargo, boolean turno) {
    super(identificacion, nombre, apellido, fechaNacimiento, direccion);
    this.cargo = cargo;
    this.turno = turno;
  }

  // Getters y Setters
  public String getCargo() {
    return cargo.getCode();
  }

  public void setCargo(Cargo cargo) {
    this.cargo = cargo;
  }

  public boolean isTurno() {
    return turno;
  }

  public void setTurno(boolean turno) {
    this.turno = turno;
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

  public boolean getTurno() {
    return turno;
  }

  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public static Empleado obtenerConIdentificacionEmpleado(int identificacion, ArrayList<Empleado> empleados) {
    for (Empleado empleado : empleados) {
      if (empleado.getIdentificacion() == identificacion) {
        return empleado;
      }
    }
    return null;
  }

  public static Empleado obtenerNombreEmpleado(String nombre, String apellido, ArrayList<Empleado> empleados) {
    for (Empleado empleado : empleados) {
      if (empleado.getNombre().equals(nombre) && empleado.getApellido().equals(apellido)) {
        return empleado;
      
      }
    }
    return null;
  }

  public static Empleado obtenerCajeroConTurno(ArrayList<Empleado> empleados) {
    for (Empleado empleado : empleados) {
      if (empleado.getTurno() == true && empleado.cargo.getCode().equals("2")) {
        return empleado;
      }
    }
    return null;
  }



  public static Empleado obtenerAsesorConTurno(ArrayList<Empleado> empleados) {
    for (Empleado empleado : empleados) {
      if (empleado.getTurno() == true && empleado.cargo.getCode().equals("1")) {
        return empleado;
      }
    }
    return null;
  }
  
  @Override
  public String toString() {


    String turnoString = (turno) ? "En turno" : "No en turno";

    return super.toString() + "cargo =" + cargo.getDescription() + ", turno = " + turnoString;
}
}
