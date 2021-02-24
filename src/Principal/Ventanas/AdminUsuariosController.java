package Principal.Ventanas;

import Principal.BD.ControladorBaseDatosA;
import Principal.Entidades.ControladorPersona;
import Principal.Entidades.Persona;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author PANCHO
 */
public class AdminUsuariosController extends ControladorBaseDatosA implements Initializable {

    private Persona persona;
    @FXML
    private Button atras;
    @FXML
    private Button actualizar;
    @FXML
    private TableView<Persona> listado;
    @FXML
    private TableColumn<Persona, String> lNombre;
    @FXML
    private TableColumn<Persona, String> lApellido;
    @FXML
    private TableColumn<Persona, Integer> lDni;
    @FXML
    private TableColumn<Persona, Integer> lTelefono;

    private ObservableList<Persona> listaUsuarios;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaUsuarios = FXCollections.observableArrayList();
        ControladorBaseDatosA dbz = new ControladorBaseDatosA();
        dbz.llenarUsuarios(dbz.getConexion(), listaUsuarios);
        listado.setItems(listaUsuarios);
        lNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        lApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        lDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        lTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    }

    //BOTONES
    //Apretar nuevo
    @FXML
    private void apretarNuevo(ActionEvent event) {
        System.out.println("Abriendo ventana de Registro...");
        try {
            //Cargo el archivo fxml de la ventana de registro
            FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("registro.fxml"));
            Parent root2 = (Parent) FXMLLoader.load();
            Stage ventanaRegistro = new Stage();
            ventanaRegistro.setScene(new Scene(root2));

            //La hago bonita
            ventanaRegistro.getIcons().add(new Image("file:src/imagenes/registro.png"));
            ventanaRegistro.setTitle("Registro");
            ventanaRegistro.initStyle(StageStyle.UNDECORATED);

            System.out.println("Iniciando la ventana de registro...");
            //Inicio la ventana

            ventanaRegistro.show();
            System.out.println("Ventana de registro abierta correctamente!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Apretar refresh
    @FXML
    private void apretarRefrescar(ActionEvent event) {
        listaUsuarios = FXCollections.observableArrayList();
        ControladorBaseDatosA dbz = new ControladorBaseDatosA();
        dbz.llenarUsuarios(dbz.getConexion(), listaUsuarios);
        listado.setItems(listaUsuarios);
        lNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        lApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        lDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        lTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    }

    //Apretar Eliminar Usuario
    @FXML
    private void apretarEliminar(ActionEvent event) {
        ControladorPersona controlador = new ControladorPersona();
        int selectedIndex = listado.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            controlador.borrarDatos(lDni.getCellData(selectedIndex));
            listado.getItems().remove(selectedIndex);
        } else {
            // Nada seleccionado
            Alert alerta1 = new Alert(Alert.AlertType.ERROR);
            alerta1.setTitle("Error");
            alerta1.setHeaderText("Error");
            alerta1.setContentText("Selecciona algun elemento de la lista");
            alerta1.showAndWait();

        }
    }

    //Apretar Editar Usuario
    @FXML
    private void apretarModificar(ActionEvent event) {
        
        System.out.println("Abriendo ventana para modificar...");
        try { 
            Persona selectedPerson = listado.getSelectionModel().getSelectedItem();
            if (selectedPerson != null) {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            //Cargo el archivo fxml de la ventana de registro
            FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("modificarUsuario.fxml"));
            Parent root5 = (Parent) FXMLLoader.load();
            Stage ventanaRegistro = new Stage();
            ventanaRegistro.setScene(new Scene(root5));

            //La hago bonita
            ventanaRegistro.getIcons().add(new Image("file:src/imagenes/editar.png"));
            ventanaRegistro.setTitle("Modificar");
            ventanaRegistro.initStyle(StageStyle.UNDECORATED);

            System.out.println("Iniciando la ventana de modificar...");
            //Inicio la ventana
            ModificarUsuarioController control = FXMLLoader.getController();
            
            control.initialize(selectedPerson);
            
            ventanaRegistro.show();
            System.out.println("Ventana de modificar abierta correctamente!");
            }else {
            // Si no se selecciona nada
            Alert alerta1 = new Alert(Alert.AlertType.ERROR);
            alerta1.setTitle("Error");
            alerta1.setHeaderText("Error");
            alerta1.setContentText("Selecciona algun elemento de la lista");
            alerta1.showAndWait();

        }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Apretar boton atras
    @FXML
    private void apretarBotonAtras(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("adminPrincipal.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }

}
