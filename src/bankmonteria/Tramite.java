
package bankmonteria;

import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author sergio
 */
public class Tramite {
    private int idTramite;
    private String tipoTramite;
    private double valorTramite;
    private LocalDate fechaTramite;
    private LocalTime horaTramite;
    private Cliente cliente;
    private Empleado empleado;

    //Constructor vacío
    public Tramite() {
    }

    //Constructor con parámetros
    public Tramite(String tipoTramite, double valorTramite, Cliente cliente, Empleado empleado) {
        // generate number random for idTramite
        this.idTramite = (int) (Math.random() * 10000 + 1);
        this.tipoTramite = tipoTramite;
        this.valorTramite = valorTramite;
        this.fechaTramite = LocalDate.now();
        this.horaTramite = LocalTime.now();
        this.cliente = cliente;
        this.empleado = empleado;
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

    public LocalDate getFechaTramite() {
        return fechaTramite;
    }

    public LocalTime getHoraTramite() {
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

    public void setFechaTramite(LocalDate fechaTramite) {
        this.fechaTramite = fechaTramite;
    }

    public void setHoraTramite(LocalTime horaTramite) {
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

    public static ArrayList<Tramite> obtenerConTipoTramite(String tipo, ArrayList<Tramite> tramites) {

        ArrayList<Tramite> tramitesEncontrados = new ArrayList<>();

        for (Tramite tramite : tramites) {
            if (tramite.tipoTramite.equals(tipo)) {
                tramitesEncontrados.add(tramite);
            }
        }

        return tramitesEncontrados;
    }

    public String formatTramite() {

        return empleado.getIdentificacion()+ " -> " + cliente.getIdentificacion() + " -> " + cliente.getNumeroCuenta() + " -> " + valorTramite;
    }



    //Polimorfismo
    @Override
    public String toString() {
        return "Tramite{" + "idTramite =" + idTramite + ", tipoTramite =" + tipoTramite + ", valorTramite=" + valorTramite + ", fechaTramite=" + fechaTramite + ", horaTramite=" + horaTramite + '}';
    }
}
