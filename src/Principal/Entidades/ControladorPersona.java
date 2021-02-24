package Principal.Entidades;

import Principal.BD.ControladorBaseDatosA;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorPersona extends ControladorBaseDatosA {

    private String borrarTablaSQL = "DROP TABLE IF EXISTS persona";

    private String crearTablaSQL = "CREATE TABLE IF NOT EXISTS `persona`(`nombre` varchar(50) DEFAULT NULL, `apellido` varchar(50) DEFAULT NULL, `dni` int DEFAULT NULL, `telefono` int DEFAULT NULL, `usuario` varchar(50) DEFAULT NULL,`contrasena` varchar(25) DEFAULT NULL, PRIMARY KEY(`dni`))";
    private String insertarSQL = "INSERT INTO `persona`(`nombre`, `apellido`, `dni`, `telefono`, `usuario`,`contrasena`) VALUES('%s', '%s', %s, %s, '%s', '%s')";
    private String buscarPorIdSQL = "SELECT * FROM persona WHERE nombre = '%s'";
    private String buscarTodosSQL = "SELECT * FROM persona";
    private String actualizarSQL = "UPDATE `persona` SET `nombre`='Fran' WHERE dni=41108505 ";
    private String eliminarSQL = "DELETE FROM `persona` WHERE `dni`=%s";

    public ControladorPersona() {
        super();
    }

    public boolean borrarDatos(int dni) {
        try {
            String SQL = String.format(eliminarSQL, dni);
            Statement sentencia = getConexion().createStatement();
            sentencia.execute(SQL);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public boolean borrarTabla() {

        try {
            String SQL = borrarTablaSQL;
            Statement sentencia = getConexion().createStatement();
            sentencia.execute(SQL);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean crearTabla() {

        try {
            String SQL = crearTablaSQL;
            Statement sentencia = getConexion().createStatement();
            sentencia.execute(SQL);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean insertarSQL(Persona p) {
        try {
            //String insertarSQL = "INSERT INTO `productos`(`id`, `nombre`, `descripcion`, `precio`, `marca`) VALUES("+p.getId()+",'"+p.getNombre()+"', '%s', %s, '%s')"; // falta terminar
            String SQL = String.format(insertarSQL, p.getNombre(), p.getApellido(), p.getDni(), p.getTelefono(), p.getUsuario(), p.getContrasena());
            Statement sentencia = getConexion().createStatement();
            sentencia.execute(SQL);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean actualizarSQL() {
        try {
            String SQL = String.format(actualizarSQL);
            Statement sentencia = getConexion().createStatement();
            sentencia.execute(SQL);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public Persona buscarPorDniSQL(int dni) {

        try {
            String SQL = String.format(buscarPorIdSQL, dni);
            PreparedStatement sentencia = getConexion().prepareStatement(SQL);
            ResultSet rs = sentencia.executeQuery(SQL);

            if (rs != null) {
                while (rs.next()) {
                    Persona prodNuevo = new Persona();
                    prodNuevo.setNombre(rs.getString(1));
                    prodNuevo.setApellido(rs.getString(2));
                    prodNuevo.setDni(rs.getInt(3));
                    prodNuevo.setTelefono(rs.getInt(4));
                    prodNuevo.setUsuario(rs.getString(5));
                    prodNuevo.setContrasena(rs.getString(6));

                    return prodNuevo;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public List<Persona> buscarTodos() {
        try {
            String SQL = buscarTodosSQL;
            Statement sentencia = getConexion().createStatement();
            ResultSet rs = sentencia.executeQuery(SQL);

            List<Persona> productos = new ArrayList<>();

            if (rs != null) {
                while (rs.next()) {
                    Persona prodNuevo = new Persona();
                    prodNuevo.setNombre(rs.getString(1));
                    prodNuevo.setApellido(rs.getString(2));
                    prodNuevo.setDni(rs.getInt(3));
                    prodNuevo.setTelefono(rs.getInt(4));
                    prodNuevo.setUsuario(rs.getString(5));
                    prodNuevo.setContrasena(rs.getString(6));

                    productos.add(prodNuevo);
                }

            }
            return productos;

        } catch (SQLException ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
