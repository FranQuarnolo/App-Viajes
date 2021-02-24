package Principal;

import Principal.Entidades.ControladorPersona;
import Principal.Ventanas.LoginController;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;

/**
 *
 * @author Bruce Wayne
 */
public class MiApp extends Application {

    private Stage ventana;
    public MiApp(){
    }

    @Override
    public void start(Stage ventanaPrincipal) {
        this.ventana = ventanaPrincipal;
        this.ventana.setTitle("Aperture Lab");
        System.out.println("La aplicacion se inicio correctamente");
        System.out.println("Abriendo Login...");
        //ABRE LA APP
        mostrarLogin();
        System.out.println("Login abierto correctamente!");
    }
//"file:resources/images/address_book_32.png"
    //Muestra la pantalla para registrarse o loguearse
    public void mostrarLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MiApp.class.getResource("Ventanas/login.fxml"));
            AnchorPane login = (AnchorPane) loader.load();
            Scene scene = new Scene(login);
            ventana.setScene(scene);
            //Lo hago bonito
            ventana.initStyle(StageStyle.UNDECORATED);
            ventana.getIcons().add(new Image("file:src/imagenes/logoAA.png"));

            LoginController controller = loader.getController();
            controller.setMiApp(this);
            ventana.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
        ControladorPersona controladorP1 = new ControladorPersona();
        if (controladorP1.crearTabla()) {
            System.out.println("La tabla se creo CORRECTAMENTE");
        }
    }
}
