
package bankmonteria;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author sergio
 */
public class Empleado extends Persona {
  private boolean turno;
  private String cargo;

  // Constructor super vacio
  public Empleado() {
    super();
  }

  // Constructor super con parametros
  public Empleado(int identificacion, String nombre, String apellido, Date fechaNacimiento, String direccion,
      String cargo, String cargoEmpleadoDescription, boolean turno) {
    super(identificacion, nombre, apellido, fechaNacimiento, direccion);
    this.cargo = cargo;
    this.turno = turno;
  }

  // Getters y Setters
  public String getCargo() {
    return cargo;
  }

  public void setCargo(String cargo) {
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

  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public static Empleado obtenerConIdentificacionEmpleado(int identificacion, ArrayList<Empleado> empleados) {
    for (Empleado empleado : empleados) {
      if (empleado.identificacion == identificacion) {
        return empleado;
      }
    }
    return null;
  }

  public static Empleado obtenerNombreEmpleado(String nombre, ArrayList<Empleado> empleados) {
    for (Empleado empleado : empleados) {
      if (empleado.nombre.equals(nombre)) {
        return empleado;
      }
    }
    return null;
  }

  public static Empleado obtenerAsesorConTurno(ArrayList<Empleado> empleados) {
    for (Empleado empleado : empleados) {
      if (empleado.turno == true && empleado.cargo.equals("1")) {
        return empleado;
      }
    }
    return null;
  }
  
  @Override
  public String toString() {

    String cargoString = "";

    if (cargo.equals("1")) {
      cargoString = "Asesor";
    } else if (cargo.equals("2")) {
      cargoString = "Cajero";
    } else {
      cargoString = "NA";
    }


    String turnoString = (turno) ? "En turno" : "No en turno";

    return super.toString() + "cargo =" + cargoString + ", turno = " + turnoString;
}
}
