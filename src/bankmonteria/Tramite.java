
package bankmonteria;

import java.util.Date;

/**
 *
 * @author sergio
 */
public class Tramite {
    private int idTramite;
    private String tipoTramite;
    private int valorTramite;
    private Date fechaTramite;
    private Date horaTramite;

    //Constructor vacío
    public Tramite() {
    }

    //Constructor con parámetros
    public Tramite(int idTramite, String tipoTramite, int valorTramite, Date fechaTramite, Date horaTramite) {
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

    public int getValorTramite() {
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

    public void setValorTramite(int valorTramite) {
        this.valorTramite = valorTramite;
    }

    public void setFechaTramite(Date fechaTramite) {
        this.fechaTramite = fechaTramite;
    }

    public void setHoraTramite(Date horaTramite) {
        this.horaTramite = horaTramite;
    }

    //Polimorfismo
    @Override
    public String toString() {
        return "Tramite{" + "idTramite=" + idTramite + ", tipoTramite=" + tipoTramite + ", valorTramite=" + valorTramite + ", fechaTramite=" + fechaTramite + ", horaTramite=" + horaTramite + '}';
    }







    
    
}
