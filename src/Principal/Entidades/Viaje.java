package Principal.Entidades;

import java.sql.Date;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Viaje {
    private IntegerProperty idViaje;
    private Date fecha;
    private StringProperty origen;
    private StringProperty destino;
    private StringProperty tipoNota;
    private StringProperty nota;
    private IntegerProperty cantidadKm;
    private IntegerProperty dniChofer;
    private StringProperty idAuto;
    private StringProperty horaSalida;
    private StringProperty horaRegreso;
    private FloatProperty importe;

    public Viaje() {
      
        this.idViaje = new SimpleIntegerProperty();
        this.fecha = fecha;
        this.origen = new SimpleStringProperty("");
        this.destino = new SimpleStringProperty("");
        this.tipoNota = new SimpleStringProperty("");
        this.nota = new SimpleStringProperty("");
        this.cantidadKm = new SimpleIntegerProperty();
        this.dniChofer = new SimpleIntegerProperty();
        this.idAuto = new SimpleStringProperty("");
        this.horaSalida = new SimpleStringProperty("");
        this.horaRegreso = new SimpleStringProperty();
        this.importe = new SimpleFloatProperty();
        

    }
    
    public Viaje(Integer idViaje,Date fecha,String origen,String destino, String tipoNota,String nota,Integer cantidadKm,Integer dniChofer,String idAuto,String horaSalida,String horaRegreso, Float importe){
        this.idViaje = new SimpleIntegerProperty(idViaje);
        this.fecha = fecha;
        this.origen = new SimpleStringProperty(origen);
        this.destino = new SimpleStringProperty(destino);
        this.tipoNota = new SimpleStringProperty(tipoNota);
        this.nota = new SimpleStringProperty(nota);
        this.cantidadKm = new SimpleIntegerProperty(cantidadKm);
        this.dniChofer = new SimpleIntegerProperty(dniChofer);
        this.idAuto = new SimpleStringProperty(idAuto);
        this.horaSalida = new SimpleStringProperty(horaSalida);
        this.horaRegreso = new SimpleStringProperty(horaRegreso);
        this.importe = new SimpleFloatProperty(importe);
    }

    public int getIdViaje() {
        return idViaje.get();
    }

    public void setIdViaje(int idViaje) {
        this.idViaje.set(idViaje);
    }

    public IntegerProperty idViajeProperty() {
        return idViaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getOrigen() {
        return origen.get();
    }

    public void setOrigen(String origen) {
        this.origen.set(origen);
    }

    public StringProperty origenProperty() {
        return origen;
    }

    public String getDestino() {
        return destino.get();
    }

    public void setDestino(String destino) {
        this.destino.set(destino);
    }

    public StringProperty destinoProperty() {
        return destino;
    }

    public String getTipoNota() {
        return tipoNota.get();
    }

    public void setTipoNota(String tipoNota) {
        this.tipoNota.set(tipoNota);
    }

    public StringProperty tipoNotaProperty() {
        return tipoNota;
    }

    public String getNota() {
        return nota.get();
    }

    public void setNota(String nota) {
        this.nota.set(nota);
    }

    public StringProperty notaProperty() {
        return nota;
    }

    public int getCantidadKm() {
        return cantidadKm.get();
    }

    public void setCantidadKm(int cantidadKm) {
        this.cantidadKm.set(cantidadKm);
    }

    public IntegerProperty cantidadKmProperty() {
        return cantidadKm;
    }

    public int getDniChofer() {
        return dniChofer.get();
    }

    public void setDniChofer(int dniChofer) {
        this.dniChofer.set(dniChofer);
    }

    public IntegerProperty dniChoferProperty() {
        return dniChofer;
    }

    public String getIdAuto() {
        return idAuto.get();
    }

    public void setIdAuto(String idAuto) {
        this.idAuto.set(idAuto);
    }

    public StringProperty idAutoProperty() {
        return idAuto;
    }

    public String getHoraSalida() {
        return horaSalida.get();
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida.set(horaSalida);
    }

    public StringProperty horaSalidaProperty() {
        return horaSalida;
    }

    public String getHoraRegreso() {
        return horaRegreso.get();
    }

    public void setHoraRegreso(String HoraRegreso) {
        this.horaRegreso.set(HoraRegreso);
    }

    public StringProperty HoraRegresoProperty() {
        return horaRegreso;
    }

     public float getImporte() {
        return importe.get();
    }

    public void setImporte(float Importe) {
        this.importe.set(Importe);
    }

    public FloatProperty ImporteProperty() {
        return importe;
    }

}
