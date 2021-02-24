package Principal.Ventanas;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author PANCHO
 */
public class AdminPrincipalController {

    @FXML
    private Button consultaMensual;
    @FXML
    private Button adminUsuarios;
    @FXML
    private Button cerrarSesion;

    //BOTONES
    //Apretando el boton para consultar la planilla mensual
    @FXML
    private void apretarConsulta(ActionEvent event) {
        System.out.println("Abriendo ventana de viajes mensuales");

        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            //Cargo el archivo fxml de la ventana de administrador de viajes
            FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("adminViajes.fxml"));
            Parent root3 = (Parent) FXMLLoader.load();
            Stage ventanaAdminViajes = new Stage();
            ventanaAdminViajes.setScene(new Scene(root3));

            //La hago bonita
            ventanaAdminViajes.getIcons().add(new Image("file:src/imagenes/nube.png"));
            ventanaAdminViajes.setTitle("Viajes Mensuales");
            ventanaAdminViajes.initStyle(StageStyle.UNDECORATED);

            System.out.println("Iniciando la ventana de viajes mensuales...");

            //Inicio la ventana
            ventanaAdminViajes.show();

            System.out.println("Ventana de viajes mensuales abierta correctamente!");
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Apretando el boton para administrar Usuarios
    @FXML
    private void apretarAdminUsuarios(ActionEvent event) {
        System.out.println("Abriendo ventana de Administrar usuarios");

        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            //Cargo el archivo fxml de la ventana de administrador de usuarios
            FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("adminUsuarios.fxml"));
            Parent root4 = (Parent) FXMLLoader.load();
            Stage ventanaUsuarios = new Stage();
            ventanaUsuarios.setScene(new Scene(root4));

            //La hago bonita
            ventanaUsuarios.getIcons().add(new Image("file:src/imagenes/nube.png"));
            ventanaUsuarios.setTitle("Administrador de Usuarios");
            ventanaUsuarios.initStyle(StageStyle.UNDECORATED);

            System.out.println("Iniciando la ventana de administrador de usuarios...");

            //Inicio la ventana
            ventanaUsuarios.show();

            System.out.println("Ventana de administrador de usuarios abierta correctamente!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Apretando el boton para cerrar sesion y volver al login
    @FXML
    private void apretarCerrarSesionA(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        System.out.println("Cerrando ventana de administrador");
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }

}
