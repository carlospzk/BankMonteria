/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankmonteria;

import java.util.Date;

/**
 *
 * @author sergi
 */
public class RepresentanteLegal extends Persona{
  private int telefonoRepresentante;

  //Constructor vacio
  public RepresentanteLegal() {
  }

  //Constructor super con parametros
  public RepresentanteLegal(int identificacion, String nombre, String apellido, Date fechaNacimiento, String direccion, int telefonoRepresentante) {
    super(identificacion, nombre, apellido, fechaNacimiento, direccion);
    this.telefonoRepresentante = telefonoRepresentante;
  }

  //Getters y Setters
  public int getTelefonoRepresentante() {
    return telefonoRepresentante;
  }

  public void setTelefonoRepresentante(int telefonoRepresentante) {
    this.telefonoRepresentante = telefonoRepresentante;
  }

  public void setIdentificacion(int identificacion) {
    this.identificacion = identificacion;
  }

  //Polimorfismo
  @Override
  public String toString() {
    return "RepresentanteLegal{" + "telefonoRepresentante=" + telefonoRepresentante + '}';
  }

    
    
}
