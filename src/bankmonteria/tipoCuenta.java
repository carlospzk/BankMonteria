
package bankmonteria;

/**
 *
 * @author sergio
 */
public class tipoCuenta {
  protected String nombreCuenta;  
  protected int numeroCuenta;
  protected double saldo;
  protected String tipoCuenta;

  public tipoCuenta(String nombreCuenta, int numeroCuenta, double saldo, String tipoCuenta) {
    this.nombreCuenta = nombreCuenta;
    this.numeroCuenta = numeroCuenta;
    this.saldo = saldo;
    this.tipoCuenta = tipoCuenta;
  }

  public String getNombreCuenta() {
    return nombreCuenta;
  }

  public int getNumeroCuenta() {
    return numeroCuenta;
  }

  public double getSaldo() {
    return saldo;
  }

  public String getTipoCuenta() {
    return tipoCuenta;
  }

  public void setNombreCuenta(String nombreCuenta) {
    this.nombreCuenta = nombreCuenta;
  }

  public void setNumeroCuenta(int numeroCuenta) {
    this.numeroCuenta = numeroCuenta;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

  public void setTipoCuenta(String tipoCuenta) {
    this.tipoCuenta = tipoCuenta;
  }

  @Override
  public String toString() {
    return "tipoCuenta{" + "nombreCuenta=" + nombreCuenta + ", numeroCuenta=" + numeroCuenta + ", saldo=" + saldo + ", tipoCuenta=" + tipoCuenta + '}';
  }

  public static boolean validateTipoCuenta(String tipoCuenta, tipoCuenta[] tipoCuentas) {
    return false;

    
  }
  
    
}
