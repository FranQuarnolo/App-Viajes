package Principal.Ventanas;

import Principal.BD.ControladorBaseDatosA;
import Principal.Entidades.ControladorViaje;
import Principal.Entidades.Persona;
import Principal.Entidades.Viaje;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.dateTime;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Bruce Wayne
 */
public class PrincipalController extends ControladorBaseDatosA implements Initializable {

    private Viaje viaje;
    private Persona persona;
    private Stage ventana;
    private boolean clickRegistrarViaje = false;

    //Constructor
    public PrincipalController() {

    }

    //Datos fx 
    @FXML
    private Label numero;
    @FXML
    private TextField tOrigen;
    @FXML
    private TextField tDestino;
    @FXML
    private TextField tTipoNota;
    @FXML
    private TextField tNota;
    @FXML
    private TextField tCantidadKm;
    @FXML
    private TextField tIdVehiculo;
    @FXML
    private TextField tDniChofer;
    @FXML
    private TextField sHoraSalida;
    @FXML
    private TextField sHoraRegreso;
    @FXML
    private DatePicker tFecha;
    @FXML
    private TextField sImporte;

    //Botones
    @FXML
    private Button confirmarViaje;
    @FXML
    private Button cerrarSesion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ControladorBaseDatosA ct = new ControladorBaseDatosA();
        int valor = ct.obtenerUltimoId();
        numero.setText(Integer.toString(valor));
    }

    public void valoresInicialesViaje(Viaje viaje) {
        this.viaje = viaje;

        tOrigen.setText(viaje.getOrigen());
        tDestino.setText(viaje.getDestino());
        tTipoNota.setText(viaje.getTipoNota());
        tNota.setText(viaje.getNota());
        tCantidadKm.setText(Integer.toString(viaje.getCantidadKm()));
        tIdVehiculo.setText(viaje.getIdAuto());
        tDniChofer.setText(Integer.toString(viaje.getDniChofer()));
        sImporte.setText(Float.toString(viaje.getImporte()));

        
    }

    public boolean siClickeoOk() {
        return clickRegistrarViaje;
    }

    //Metodo para vaciar los campos ingresados anteriormente
    private void limpiar() {
        tOrigen.setText("");
        tDestino.setText("");
        tTipoNota.setText("");
        tNota.setText("");
        tCantidadKm.setText("");
        tIdVehiculo.setText("");
        tDniChofer.setText("");
        sHoraSalida.setText("");
        sHoraRegreso.setText("");
        sImporte.setText("");
        ControladorBaseDatosA ct = new ControladorBaseDatosA();
        int valor = ct.obtenerUltimoId();
        numero.setText(Integer.toString(valor));
    }

    //Metodo de comprobacion de datos
    public boolean siEsInvalidoPrincipal() {
        String errorMessage = "";

        if (tOrigen.getText() == null || tOrigen.getText().length() == 0) {
            errorMessage += "Origen no valido!\n";
        }
        if (tDestino.getText() == null || tDestino.getText().length() == 0) {
            errorMessage += "Destino Invalido!\n";
        }
        if (tTipoNota.getText() == null || tTipoNota.getText().length() == 0) {
            errorMessage += "Tipo de Nota no valida!\n";
        }

        if (tNota.getText() == null || tNota.getText().length() == 0) {
            errorMessage += "Nota no valida!\n";
        }
        if (tCantidadKm.getText() == null || tCantidadKm.getText().length() == 0) {
            errorMessage += "Cantidad de KM invalida!\n";
        } else {
            try {
                Integer.parseInt(tCantidadKm.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Cantidad de KM invalida!!\n";
            }
        }
        if (tIdVehiculo.getText() == null || tIdVehiculo.getText().length() == 0) {
            errorMessage += "Id del vehiculo no valida!\n";
        }

        if (tDniChofer.getText() == null || tDniChofer.getText().length() == 0) {
            errorMessage += "Dni invalido!\n";
        } else {
            try {
                Integer.parseInt(tDniChofer.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Dni invalido!!\n";
            }
        }

        if (tFecha.getPromptText() == null || tFecha.getPromptText().length() == 0) {
            errorMessage += "Fecha no valida!\n";
        }
        if (sImporte.getText() == null || sImporte.getText().length() == 0) {
            errorMessage += "Dni invalido!\n";
        } else {
            try {
                Integer.parseInt(sImporte.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Importe invalido!!\n";
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    //Botoness
    //Una vez llenado los datos apretando el boton de registrar viaje
    @FXML
    public void apretarRegistrarViaje(ActionEvent event) {
        ControladorViaje controladorV = new ControladorViaje();
        ControladorBaseDatosA id = new ControladorBaseDatosA();

        if (siEsInvalidoPrincipal()) {
            Viaje v1 = new Viaje();

            //Obtengo el metodo de id autoincrementable
            v1.setIdViaje(id.idViajeAutoIncrementable());
            v1.setOrigen(tOrigen.getText());
            v1.setDestino(tDestino.getText());
            v1.setTipoNota(tTipoNota.getText());
            v1.setNota(tNota.getText());
            v1.setCantidadKm(Integer.parseInt(tCantidadKm.getText()));
            v1.setIdAuto(tIdVehiculo.getText());
            v1.setDniChofer(Integer.parseInt(tDniChofer.getText()));
            //Obtengo la fecha del date picker
            v1.setFecha(java.sql.Date.valueOf(tFecha.getValue()));
            //Aca guardo los valores de hora 
            v1.setHoraSalida((sHoraSalida.getText()));
            v1.setHoraRegreso((sHoraRegreso.getText()));
            v1.setImporte(Float.parseFloat(sImporte.getText()));

            clickRegistrarViaje = true;
            JOptionPane.showMessageDialog(null, "Viaje registrado con exito");
            System.out.println("Viaje Registrado correctamente!!");
            controladorV.insertarSQL(v1);
            limpiar();

        } else {
            if (tOrigen.getText().equals("") || tDestino.equals("") || tTipoNota.equals("") || tNota.equals("") || tCantidadKm.equals("") || tIdVehiculo.equals("") || tDniChofer.equals("") || sImporte.equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese algun dato");
                System.out.println("Ingresa algo vivo!");
            }
        }
    }

    //Apretando el boton de cerrar sesion
    @FXML
    public void apretarCerrarSesion(ActionEvent event) throws IOException {
        
        System.out.println("Sesion cerrada con exito!!");
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();

    }
}
