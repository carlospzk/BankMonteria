
package bankmonteria;

/**
 *
 * @author sergio
 */
public class TipoCuenta {
  private String codigo;
  private String descripcion;

  public TipoCuenta(String codigo, String descripcion) {
    this.codigo = codigo;
    this.descripcion = descripcion;
  }

  public String getCodigo() {
    return codigo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  @Override
  public String toString() {
    return "tipoCuenta{" + "codigo=" + codigo + ", descripcion=" + descripcion + '}';
  }
  

  

    
}
