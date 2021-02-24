/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal.Ventanas;

import Principal.BD.ControladorBaseDatosA;
import Principal.Entidades.ControladorViaje;
import Principal.Entidades.Viaje;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author PANCHO
 */
public class AdminNuevoRegistroController implements Initializable {

    private boolean clickRegistrarViaje = false;
    
    @FXML
    private Label numeroViaje;
    @FXML
    private DatePicker tFecha;
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
    private TextField tDniChofer;
    @FXML
    private TextField tIdVehiculo;
    @FXML
    private TextField sHoraSalida;
    @FXML
    private TextField sHoraRegreso;
    @FXML
    private TextField sImporte;
    @FXML
    private Button registrarViaje;
    @FXML
    private Button botonCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ControladorBaseDatosA ct = new ControladorBaseDatosA();
        int valor = ct.obtenerUltimoId();
        numeroViaje.setText(Integer.toString(valor));
    }    
    public boolean siClickeoOk() {
        return clickRegistrarViaje;
    }
    
    public boolean siEsInvalidoNuevoRegistro() {
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
        numeroViaje.setText(Integer.toString(valor));
    }
    
    //BOTONES
    //Apretar boton de registrar
    @FXML
    private void apretarRegistrar(ActionEvent event) {
        ControladorViaje controladorV = new ControladorViaje();
        ControladorBaseDatosA id = new ControladorBaseDatosA();
        
        if (siEsInvalidoNuevoRegistro()) {
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

    //Apretar boton cancelar
    @FXML
    private void apretarCancelar(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        System.out.println("Adiossss!"); 
    }
    
}
