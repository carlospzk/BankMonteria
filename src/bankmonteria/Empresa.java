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
public class Empresa extends Persona{
  private int nit;
  private int telefonoEmpresa;  

  //Constructor vacio
  public Empresa() {
  }

  //Constructor super con parametros
  public Empresa(int identificacion, String nombre, String apellido, Date fechaNacimiento, String direccion, int nit, int telefonoEmpresa) {
    super(identificacion, nombre, apellido, fechaNacimiento, direccion);
    this.nit = nit;
    this.telefonoEmpresa = telefonoEmpresa;
  }



  public int getNit() {
    return nit;
  }

  public int getTelefonoEmpresa() {
    return telefonoEmpresa;
  }

  public void setNit(int nit) {
    this.nit = nit;
  }

  public void setTelefonoEmpresa(int telefonoEmpresa) {
    this.telefonoEmpresa = telefonoEmpresa;
  }

  @Override
  public String toString() {
    return "CuentaCorriente{" + "nit=" + nit + ", telefonoEmpresa=" + telefonoEmpresa + '}';
  }


  
    
}
