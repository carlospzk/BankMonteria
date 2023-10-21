
package bankmonteria;

import java.util.Date;

/**
 *
 * @author sergio
 */
public class Persona {
    protected String identificacion;
    protected String nombre;
    protected String apellido;
    protected Date fechaNacimiento;
    protected String direccion;
    private String id;

    //Constructor vacío
    public Persona() {
    }

    //Constructor con parámetros
    public Persona(String identificacion, String nombre, String apellido, Date fechaNacimiento, String direccion) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
    }

    //Métodos getters y setters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido(){
        return apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setApellido(String apellido){
        this.apellido = apellido;
    }

    public void setFechaNacimiento(Date fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    //Polimorfismo
    @Override
    public String toString() {
        return "Persona{" + "identificacion=" + identificacion + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento + ", direccion=" + direccion + '}';
    }
    





    

    
}
