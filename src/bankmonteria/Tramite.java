
package bankmonteria;

import java.util.Date;

/**
 *
 * @author sergio
 */
public class Tramite {
    private int idTramite;
    private String tipoTramite;
    private double valorTramite;
    private Date fechaTramite;
    private Date horaTramite;

    //Constructor vacío
    public Tramite() {
    }

    //Constructor con parámetros
    public Tramite(int idTramite, String tipoTramite, double valorTramite, Date fechaTramite, Date horaTramite) {
        this.idTramite = idTramite;
        this.tipoTramite = tipoTramite;
        this.valorTramite = valorTramite;
        this.fechaTramite = fechaTramite;
        this.horaTramite = horaTramite;
    }

    //Métodos getters y setters
    public int getIdTramite() {
        return idTramite;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public double getValorTramite() {
        return valorTramite;
    }

    public Date getFechaTramite() {
        return fechaTramite;
    }

    public Date getHoraTramite() {
        return horaTramite;
    }

    public void setIdTramite(int idTramite) {
        this.idTramite = idTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public void setValorTramite(double valorRetiro) {
        this.valorTramite = valorRetiro;
    }

    public void setFechaTramite(Date fechaTramite) {
        this.fechaTramite = fechaTramite;
    }

    public void setHoraTramite(Date horaTramite) {
        this.horaTramite = horaTramite;
    }

    // Tramite consignacion

    public void consignacion(int valorTramite) {
        this.valorTramite = valorTramite;
    }


    // Tramite retiro

    public void retiro(int valorTramite) {
        this.valorTramite = valorTramite;
    }

    // Valor Tramite

    public void valorTramite(int valorTramite) {
        this.valorTramite = valorTramite;
    }



    //Polimorfismo
    @Override
    public String toString() {
        return "Tramite{" + "idTramite =" + idTramite + ", tipoTramite =" + tipoTramite + ", valorTramite=" + valorTramite + ", fechaTramite=" + fechaTramite + ", horaTramite=" + horaTramite + '}';
    }







    
    
}
