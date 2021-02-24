/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal.Ventanas;

import Principal.Entidades.ControladorPersona;
import Principal.Entidades.Persona;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author PANCHO
 */
public class ModificarUsuarioController {

    @FXML
    private TextField eNombre;
    @FXML
    private TextField eApellido;
    @FXML
    private TextField eDni;
    @FXML
    private TextField eTelefono;
    @FXML
    private TextField eUsuario;
    @FXML
    private TextField eContrasena;
    @FXML
    private Button botonCancelar;
    @FXML
    private Button botonGuardar;

    private Persona persona;


    @FXML
    public void initialize(Persona persona) {
        this.persona = persona;
        eNombre.setText(persona.getNombre());
        eApellido.setText(persona.getApellido());
        eDni.setText(Integer.toString(persona.getDni()));
        eTelefono.setText(Integer.toString(persona.getTelefono()));
        eUsuario.setText(persona.getUsuario());
        eContrasena.setText(persona.getContrasena());
    }

    //BOTONES
    //Apretar boton Guardar
    @FXML
    private void apretarGuardar(ActionEvent event) throws IOException {
        System.out.println("Editando usuario...");
        if (siLoIngresadoEsInvalido()) {
            ControladorPersona controladorBd = new ControladorPersona();
            if (controladorBd.buscarPorDniSQL(Integer.parseInt(eDni.getText())) == null || Integer.parseInt(eDni.getText()) == persona.getDni()) {

                controladorBd.borrarDatos(persona.getDni());

                persona.setNombre(eNombre.getText());
                persona.setApellido(eApellido.getText());
                persona.setDni(Integer.parseInt(eDni.getText()));
                persona.setTelefono(Integer.parseInt(eTelefono.getText()));
                persona.setUsuario(eUsuario.getText());
                persona.setContrasena(eContrasena.getText());

                controladorBd.insertarSQL(persona);
            } else {
                JOptionPane.showMessageDialog(null, "El ID ya siendo utilizado");
            }
        }
        System.out.println("Usuario editado correctamente!!");
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("adminUsuarios.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
        
    }

    //Apretar cancelar
    @FXML
    private void apretarCancelar(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("adminPrincipal.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }

    private boolean siLoIngresadoEsInvalido() {
        String errorMessage = "";

        if (eNombre.getText() == null || eNombre.getText().length() == 0) {
            errorMessage += "Nombre no valido!\n";
        }
        if (eApellido.getText() == null || eApellido.getText().length() == 0) {
            errorMessage += "Apellido Invalido!\n";
        }
        if (eDni.getText() == null || eDni.getText().length() == 0) {
            errorMessage += "Codigo postal invalido!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(eDni.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Codigo postal invalido!!\n";
            }
        }
        if (eTelefono.getText() == null || eTelefono.getText().length() == 0) {
            errorMessage += "Tel no valida!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(eTelefono.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Tel invalido!!\n";
            }
        }

        if (eUsuario.getText() == null || eUsuario.getText().length() == 0) {
            errorMessage += "Usuario inv!\n";
        }

        if (eContrasena.getText() == null || eContrasena.getText().length() == 0) {
            errorMessage += "Contra no valida!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Muestra el mensaje de error
//            Dialogs.create()
//                    .title("Campos invalidos")
//                    .masthead("Porfavor corriga los campos invalidos")
//                    .message(errorMessage)
//                    .showError();
            return false;
        }
    }

}
