package Principal.Ventanas;

import Principal.BD.ControladorBaseDatosA;
import Principal.Entidades.ControladorPersona;
import Principal.Entidades.Persona;
import Principal.MiApp;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class RegistroController extends ControladorBaseDatosA{

    @FXML
    private TextField tNombre;
    @FXML
    private TextField tApellido;
    @FXML
    private TextField tDni;
    @FXML
    private TextField tTelefono;
    @FXML
    private TextField tUsuario;
    @FXML
    private PasswordField tContrasena;
    @FXML
    private Button botonAtras;
    @FXML
    private Button botonRegistrarse;

    private MiApp app;
    private Persona persona;
    private boolean clickRegistro = false;

    public RegistroController() {

    }

    public void setApp(MiApp app) {
        this.app = app;
    }
 
    public void valoresIniciales(Persona persona) {
        this.persona = persona;
        tNombre.setText(persona.getNombre());
        tApellido.setText(persona.getApellido());
        tDni.setText(Integer.toString(persona.getDni()));
        tTelefono.setText(Integer.toString(persona.getTelefono()));

        tUsuario.setText(persona.getUsuario());
        tContrasena.setText(persona.getContrasena());
    }

    public boolean siClickeoOk() {
        return clickRegistro;
    }

    //Botoness
    //Una vez llenado los datos apretando el boton de registrarse
    @FXML
    private void apretarRegistrarse(ActionEvent event) {
        ControladorPersona controladorP = new ControladorPersona();

        if (siEsInvalido()) {
            if (controladorP.buscarPorDniSQL(Integer.parseInt(tDni.getText())) == null || persona.getDni() == Integer.parseInt(tDni.getText())) {

                Persona p1 = new Persona();
                
                p1.setNombre(tNombre.getText());                
                p1.setApellido(tApellido.getText());
                p1.setDni(Integer.parseInt(tDni.getText()));
                p1.setTelefono(Integer.parseInt(tTelefono.getText()));
                p1.setUsuario(tUsuario.getText());
                p1.setContrasena(tContrasena.getText());
                
                clickRegistro = true;
               ((Node) (event.getSource())).getScene().getWindow().hide();

                JOptionPane.showMessageDialog(null, "Bienvenido " + p1.getNombre());
                
                controladorP.insertarSQL(p1);
                
                System.out.println("Nuevo usuario registrado con exito!!");
            }
        }

    }

    @FXML
    private void apretarAtras(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        System.out.println("Adiossss!");
    }

    //Comprobando que los datos ingresados sean validos
    public boolean siEsInvalido() {
        String errorMessage = "";

        if (tNombre.getText() == null || tNombre.getText().length() == 0) {
            errorMessage += "Nombre no valido!\n";
        }
        if (tApellido.getText() == null || tApellido.getText().length() == 0) {
            errorMessage += "Apellido Invalido!\n";
        }
        if (tDni.getText() == null || tDni.getText().length() == 0) {
            errorMessage += "DNI no valida!\n";
        }
        if (tTelefono.getText() == null || tTelefono.getText().length() == 0) {
            errorMessage += "Telefono invalido!\n";
        }

        if (tUsuario.getText() == null || tUsuario.getText().length() == 0) {
            errorMessage += "usuario no valido!\n";
        }

        if (tContrasena.getText() == null || tContrasena.getText().length() == 0) {
            errorMessage += "contraseña no valido!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Muestra el mensaje de error
            Alert alerta1 = new Alert(Alert.AlertType.ERROR);
            alerta1.setTitle("Error");
            alerta1.setHeaderText("Tipo de dato incorrecto");
            alerta1.setContentText("Corrija los valores no sea boludo!");
            alerta1.showAndWait();
            return false;
        }
    }
}


