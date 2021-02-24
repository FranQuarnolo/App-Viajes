package Principal.BD;

import Principal.Entidades.Persona;
import Principal.Entidades.Viaje;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

public class ControladorBaseDatosA {

    private String nombreBaseDatos = "mybasefinal";
    private String urlServidor = "jdbc:mysql://localhost:3306/" + nombreBaseDatos + "?useUnicode=true&useJDBCCompiantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String usuario = "root";
    private String password = "";
    private Connection conexion;

    public ControladorBaseDatosA() {
        try {
            //Driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Nombre del servidor. localhost:3306 es la ruta y el puerto de la conexión MySQL
            //El root es el nombre de usuario por default. No hay contraseña
            //Se inicia la conexión
            conexion = DriverManager.getConnection(urlServidor, usuario, password);

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } finally {
            //JOptionPane.showMessageDialog(null, "Conexión Exitosa");
            System.out.println("Conexion a la BD exitosa!!");
        }

    }

    public Connection getConexion() {
        return conexion;
    }

    //Metodo para obtener el usuario y contraseña de la base de datos
    public int login(String usuario, String contrasena) {
        int resultado = 0;
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM persona WHERE usuario='" + usuario + "' AND contrasena='" + contrasena + "'");
            if (rs.next()) {
                resultado = 1;
            } else {
                resultado = 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Error: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
        return resultado;

    }

    //Metodo para hacer el nro de viajes autoIncremental
    public int idViajeAutoIncrementable() {
        int id = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ControladorBaseDatosA db = new ControladorBaseDatosA();
        try {
            ps = db.getConexion().prepareStatement("SELECT MAX(idViaje) FROM viaje");
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Error: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {

            }
        }
        return id;
    }

    //Metodo para cargar la lista de viajes
    public void llenarViajes(Connection connection, ObservableList<Viaje> listaViaje) {
        try {
            Statement ps = connection.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM viaje");
            while (rs.next()) {
                listaViaje.add(new Viaje(rs.getInt("idViaje"), rs.getDate("fecha") /*rs.getObject("fecha", Date.class)*/, rs.getString("origen"),
                        rs.getString("destino"), rs.getString("tipoNota"), rs.getString("nota"), rs.getInt("cantidadKM"),
                        rs.getInt("dniChofer"), rs.getString("idAuto"), rs.getString("horaSalida"), rs.getString("horaRegreso"),
                        rs.getFloat("importe")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Error: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo para cargar la lista de usuarios
    public void llenarUsuarios(Connection connection, ObservableList<Persona> listaUsuarios) {
        try {
            Statement ps = connection.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM persona");
            while (rs.next()) {
                listaUsuarios.add(new Persona(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("dni"), rs.getInt("telefono"), rs.getString("usuario"), rs.getString("contrasena")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Error: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo para obtener el ultimo id del registro guardado
    public int obtenerUltimoId() {
        int ultimoValor = 0;
        try {
//            PreparedStatement stmtr = conexion.prepareStatement("SELECT * FROM viaje ORDER BY idViaje DESC");
            PreparedStatement stmtr = conexion.prepareStatement("SELECT MAX(`idViaje`)+1 FROM `viaje`");
            ResultSet rsr = stmtr.executeQuery();
            if (rsr.next()) {
                ultimoValor = rsr.getInt(1);
            }
            stmtr.close();
            rsr.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ultimoValor;
    }
    
    //Metodo para obtener el importe total de los viajes
    public float obtenerTotalImporte(){
        float totalImporte = 0;
        try {
            PreparedStatement stmtr = conexion.prepareStatement("SELECT SUM(`importe`) FROM `viaje` ");
            ResultSet rsr = stmtr.executeQuery();
            if (rsr.next()) {
                totalImporte = rsr.getFloat(1);
            }
            stmtr.close();
            rsr.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalImporte;
    }  
    
}