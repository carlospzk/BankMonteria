/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankmonteria;

/**
 *
 * @author sergi
 */
public class Cargo {
  protected String code;
  protected String description;

  public Cargo(String code, String description) {
    this.code = code;
    this.description = description;
  }

  public String getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public String toString() {
    return "Cargo{" + "code=" + code + ", description=" + description + '}';
  }

  public static boolean validateCargoConCode(String cargoEmpleado, Cargo[] cargos) {
    return false;

    
  }







    
}
