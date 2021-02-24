package Principal.Ventanas;

import Principal.Entidades.ControladorPersona;
import Principal.Entidades.ControladorViaje;
import Principal.Entidades.Viaje;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
 * @author PANCHO
 */
public class ModificarRegistroController {

    @FXML
    private Label eNumero;
    @FXML
    private DatePicker eFecha;
    @FXML
    private Button confirmarEdicion;
    @FXML
    private TextField eOrigen;
    @FXML
    private TextField eDestino;
    @FXML
    private TextField eTipoNota;
    @FXML
    private TextField eNota;
    @FXML
    private TextField eCantidadKm;
    @FXML
    private TextField eDniChofer;
    @FXML
    private TextField eIdVehiculo;
    @FXML
    private TextField eHoraSalida;
    @FXML
    private TextField eHoraRegreso;
    @FXML
    private TextField eImporte;
    @FXML
    private Button botonCancelar;

    private Viaje viaje;

    @FXML
    public void initialize(Viaje viaje) {
        this.viaje = viaje;
        eNumero.setText(Integer.toString(viaje.getIdViaje()));
        //FECHA
        eFecha.setValue(LocalDate.now());
        eOrigen.setText(viaje.getOrigen());
        eDestino.setText(viaje.getDestino());
        eTipoNota.setText(viaje.getTipoNota());
        eNota.setText(viaje.getNota());
        eCantidadKm.setText(Integer.toString(viaje.getCantidadKm()));
        eDniChofer.setText(Integer.toString(viaje.getDniChofer()));
        eIdVehiculo.setText(viaje.getIdAuto());
        eHoraSalida.setText(viaje.getHoraSalida());
        eHoraRegreso.setText(viaje.getHoraRegreso());
        eImporte.setText(Float.toString(viaje.getImporte()));

    }

    @FXML
    private void apretarConfirmarEdicion(ActionEvent event) throws IOException {
        System.out.println("Editando registro...");

        ControladorViaje controlBd = new ControladorViaje();
        if (controlBd.buscarPorIdSQL(Integer.parseInt(eNumero.getText())) == null || Integer.parseInt(eNumero.getText()) == viaje.getIdViaje()) {
            controlBd.borrarDatos(viaje.getIdViaje());
            viaje.setFecha(java.sql.Date.valueOf(eFecha.getValue()));
            viaje.setOrigen(eOrigen.getText());
            viaje.setDestino(eDestino.getText());
            viaje.setTipoNota(eTipoNota.getText());
            viaje.setNota(eNota.getText());
            viaje.setCantidadKm(Integer.parseInt(eCantidadKm.getText()));
            viaje.setDniChofer(Integer.parseInt(eDniChofer.getText()));
            viaje.setIdAuto(eIdVehiculo.getText());
            viaje.setHoraSalida(eHoraSalida.getText());
            viaje.setHoraRegreso(eHoraRegreso.getText());
            viaje.setImporte(Float.parseFloat(eImporte.getText()));

            controlBd.insertarSQL(viaje);
        }else {
                JOptionPane.showMessageDialog(null, "El ID ya siendo utilizado");
        }
        System.out.println("Registro editado correctamente!!");
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("adminViajes.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }

    @FXML
    private void apretarCancelar(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("adminViajes.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();  
    }

}
