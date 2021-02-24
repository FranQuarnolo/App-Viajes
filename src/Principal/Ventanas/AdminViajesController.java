package Principal.Ventanas;

import Principal.BD.ControladorBaseDatosA;
import Principal.Entidades.ControladorViaje;
import Principal.Entidades.Viaje;
import Principal.Utilidades.Csv;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author PANCHO
 */
public class AdminViajesController extends ControladorBaseDatosA implements Initializable {

    private Viaje viaje;
    @FXML
    private Button atras;
    @FXML
    private Button refresh;
    @FXML
    private Label total;
    @FXML
    private TableView<Viaje> lista;
    @FXML
    private TableColumn<Viaje, Integer> idColumna;
    @FXML
    private TableColumn<Viaje, Date> fechaColumna;
    @FXML
    private TableColumn<Viaje, String> origenColumna;
    @FXML
    private TableColumn<Viaje, String> destinoColumna;
    @FXML
    private TableColumn<Viaje, String> tipoNotaColumna;
    @FXML
    private TableColumn<Viaje, String> notaColumna;
    @FXML
    private TableColumn<Viaje, Integer> kmColumna;
    @FXML
    private TableColumn<Viaje, Integer> dniChoferColumna;
    @FXML
    private TableColumn<Viaje, String> idAutoColumna;
    @FXML
    private TableColumn<Viaje, String> hSalidaColumna;
    @FXML
    private TableColumn<Viaje, String> hRegresoColumna;
    @FXML
    private TableColumn<Viaje, Float> importe;

    private ObservableList<Viaje> listaViaje;

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        listaViaje = FXCollections.observableArrayList();
        ControladorBaseDatosA db = new ControladorBaseDatosA();
        db.llenarViajes(db.getConexion(), listaViaje);

        lista.setItems(listaViaje);

        idColumna.setCellValueFactory(new PropertyValueFactory<>("idViaje"));
        //ACA AL CARGAR LA FECHA DESDE LA BASE DE DATOS ME RESTA UN DIA

        fechaColumna.setCellValueFactory(new PropertyValueFactory<>("fecha"));
//      fechaColumna.setCellValueFactory(convertirLocalDateAdate(new PropertyValueFactory<>("fecha")));

        origenColumna.setCellValueFactory(new PropertyValueFactory<>("origen"));
        destinoColumna.setCellValueFactory(new PropertyValueFactory<>("destino"));
        tipoNotaColumna.setCellValueFactory(new PropertyValueFactory<>("tipoNota"));
        notaColumna.setCellValueFactory(new PropertyValueFactory<>("nota"));
        kmColumna.setCellValueFactory(new PropertyValueFactory<>("cantidadKm"));
        dniChoferColumna.setCellValueFactory(new PropertyValueFactory<>("dniChofer"));
        idAutoColumna.setCellValueFactory(new PropertyValueFactory<>("idAuto"));
        hSalidaColumna.setCellValueFactory(new PropertyValueFactory<>("horaSalida"));
        hRegresoColumna.setCellValueFactory(new PropertyValueFactory<>("horaRegreso"));
        importe.setCellValueFactory(new PropertyValueFactory<>("importe"));

        ControladorBaseDatosA ct = new ControladorBaseDatosA();
        float importeTotal = ct.obtenerTotalImporte();
        total.setText(Float.toString(importeTotal));

    }

    //BOTONES
    //Apretar boton para nuevo registro
    @FXML
    private void apretarNuevoRegistro(ActionEvent event) throws IOException {
        System.out.println("Abriendo ventana Nuevo registro...");
        FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("adminNuevoRegistro.fxml"));
        Parent root5 = (Parent) FXMLLoader.load();
        Stage ventanaNuevoRegistro = new Stage();
        ventanaNuevoRegistro.setScene(new Scene(root5));

        //Lo hago bonito
        ventanaNuevoRegistro.setTitle("Nuevo Registro");
        ventanaNuevoRegistro.getIcons().add(new Image("file:src/imagenes/disco.png"));
        ventanaNuevoRegistro.initStyle(StageStyle.UNDECORATED);

        //Se inicia la Pantalla
        ventanaNuevoRegistro.show();
    }

    //Apretar Actualizar
    @FXML
    private void actualizarListado(ActionEvent event) {
        listaViaje = FXCollections.observableArrayList();
        ControladorBaseDatosA db = new ControladorBaseDatosA();
        db.llenarViajes(db.getConexion(), listaViaje);

        lista.setItems(listaViaje);

        idColumna.setCellValueFactory(new PropertyValueFactory<>("idViaje"));
        //ACA AL CARGAR LA FECHA DESDE LA BASE DE DATOS ME RESTA UN DIA

        fechaColumna.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        origenColumna.setCellValueFactory(new PropertyValueFactory<>("origen"));
        destinoColumna.setCellValueFactory(new PropertyValueFactory<>("destino"));
        tipoNotaColumna.setCellValueFactory(new PropertyValueFactory<>("tipoNota"));
        notaColumna.setCellValueFactory(new PropertyValueFactory<>("nota"));
        kmColumna.setCellValueFactory(new PropertyValueFactory<>("cantidadKm"));
        dniChoferColumna.setCellValueFactory(new PropertyValueFactory<>("dniChofer"));
        idAutoColumna.setCellValueFactory(new PropertyValueFactory<>("idAuto"));
        hSalidaColumna.setCellValueFactory(new PropertyValueFactory<>("horaSalida"));
        hRegresoColumna.setCellValueFactory(new PropertyValueFactory<>("horaRegreso"));
        importe.setCellValueFactory(new PropertyValueFactory<>("importe"));

        ControladorBaseDatosA ct = new ControladorBaseDatosA();
        float importeTotal = ct.obtenerTotalImporte();
        total.setText(Float.toString(importeTotal));
    }

    //Apretar boton para eliminar un registro
    @FXML
    private void apretarEliminarRegistro(ActionEvent event) {
        ControladorViaje controladorV = new ControladorViaje();
        int selectedIndex = lista.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            controladorV.borrarDatos(idColumna.getCellData(selectedIndex));
            lista.getItems().remove(selectedIndex);
        } else {
            // Nada seleccionado
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("Error");
            alerta2.setHeaderText("Error");
            alerta2.setContentText("Selecciona algun elemento de la lista");
            alerta2.showAndWait();

        }
    }

    //Apretar boton de modificar algun registro
    @FXML
    private void apretarModificarRegistro(ActionEvent event) {
        
        System.out.println("Abriendo ventana para modificar...");
        try {
            Viaje selectedViaje = lista.getSelectionModel().getSelectedItem();
            if (selectedViaje != null) {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                //Cargo el archivo fxml de la ventana de registro
                FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("modificarRegistro.fxml"));
                Parent root12 = (Parent) FXMLLoader.load();
                Stage ventanaEditarRegistro = new Stage();
                ventanaEditarRegistro.setScene(new Scene(root12));

                //La hago bonita
                ventanaEditarRegistro.getIcons().add(new Image("file:src/imagenes/editar.png"));
                ventanaEditarRegistro.setTitle("Modificar");
                ventanaEditarRegistro.initStyle(StageStyle.UNDECORATED);

                System.out.println("Iniciando la ventana de modificar...");
                //Inicio la ventana
                ModificarRegistroController contro2 = FXMLLoader.getController();

                contro2.initialize(selectedViaje);

                ventanaEditarRegistro.show();
                System.out.println("Ventana de modificar abierta correctamente!");
            } else {
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

    //Apretar boton para mostrar estadisticas
    @FXML
    private void apretarMostrarEstadisticas(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        System.out.println("Abriendo ventana de Estadisticas...");
        FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("estadisticasViajes.fxml"));
        Parent root6 = (Parent) FXMLLoader.load();
        Stage ventanaEstadisticas = new Stage();
        ventanaEstadisticas.setScene(new Scene(root6));

        //Lo hago bonito
        ventanaEstadisticas.setTitle("Estadisticas");
        ventanaEstadisticas.getIcons().add(new Image("file:src/imagenes/estatus.png"));
        ventanaEstadisticas.initStyle(StageStyle.UNDECORATED);
        
        EstadisticasViajesController controlador = FXMLLoader.getController();
        controlador.setPersonaData(listaViaje);
        //Se inicia la Pantalla
        ventanaEstadisticas.show();

    }

    //Apretar boton exportar
    @FXML
    private void apretarExportar(ActionEvent event) throws SQLException {
        Csv xp = new Csv();
        xp.exportarCsv(listaViaje);
        System.out.println("Archivo exportado correctamente!!");
        JOptionPane.showMessageDialog(null, "El archivo se exporto Correctamente");
    }

    //Apretar boton atras
    @FXML
    private void apretarAtras(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("adminPrincipal.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }
}
