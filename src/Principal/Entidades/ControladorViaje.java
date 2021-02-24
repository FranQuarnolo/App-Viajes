package Principal.Entidades;

import Principal.BD.ControladorBaseDatosA;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorViaje extends ControladorBaseDatosA {

    private String borrarTablaSQL = "DROP TABLE IF EXISTS viaje";

    private String crearTablaSQL = "CREATE TABLE IF NOT EXISTS `viaje`(`idViaje` int DEFAULT NULL, `fecha` date(50) DEFAULT NULL, `origen` varchar(50) DEFAULT NULL, `destino` varchar(50) DEFAULT NULL, `tipoNota` varchar(50) DEFAULT NULL,`nota` varchar(100) DEFAULT NULL, `cantidadKM` int DEFAULT NULL, `dniChofer` int DEFAULT NULL, `idAuto` varchar(50) DEFAULT NULL,  `horaSalida` varchar(50) DEFAULT NULL, `horaRegreso` varchar(50) DEFAULT NULL, `importe` float DEFAULT NULL, PRIMARY KEY(`idViaje`))";
    private String insertarSQL = "INSERT INTO `viaje`(`idViaje`, `fecha`, `origen`, `destino`, `tipoNota`,`nota`,`cantidadKm`,`dniChofer`,`idAuto`,`horaSalida`,`horaRegreso`,`importe`) VALUES(%s, '%s', '%s', '%s', '%s', '%s',%s, %s, '%s', '%s', '%s', %s)";
    private String buscarPorIdSQL = "SELECT * FROM viaje WHERE idViaje = %s";
    private String buscarTodosSQL = "SELECT * FROM viaje";
    private String actualizarSQL = "UPDATE `viaje` SET `nombre`='Fran' WHERE dni=41108505 ";
    private String eliminarSQL = "DELETE FROM `viaje` WHERE `idViaje`=%s";

    public ControladorViaje() {
        super();
    }

    public boolean borrarDatos(int dni) {
        try {
            String SQL = String.format(eliminarSQL, dni);
            Statement sentencia = getConexion().createStatement();
            sentencia.execute(SQL);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorViaje.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ControladorViaje.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ControladorViaje.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean insertarSQL(Viaje v) {
        try {
            //String insertarSQL = "INSERT INTO `productos`(`id`, `nombre`, `descripcion`, `precio`, `marca`) VALUES("+p.getId()+",'"+p.getNombre()+"', '%s', %s, '%s')"; // falta terminar
            String SQL = String.format(insertarSQL, v.getIdViaje(), v.getFecha(), v.getOrigen(), v.getDestino(), v.getTipoNota(), v.getNota(), v.getCantidadKm(), v.getDniChofer(), v.getIdAuto(), v.getHoraSalida(), v.getHoraRegreso(), v.getImporte());
            Statement sentencia = getConexion().createStatement();
            sentencia.execute(SQL);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorViaje.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ControladorViaje.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public Viaje buscarPorIdSQL(int dni) {

        try {
            String SQL = String.format(buscarPorIdSQL, dni);
            PreparedStatement sentencia = getConexion().prepareStatement(SQL);
            ResultSet rs = sentencia.executeQuery(SQL);

            if (rs != null) {
                while (rs.next()) {
                    Viaje viajeNuevo = new Viaje();
                    viajeNuevo.setIdViaje(rs.getInt(1));
                    viajeNuevo.setFecha(rs.getDate(2));
                    viajeNuevo.setOrigen(rs.getString(3));
                    viajeNuevo.setDestino(rs.getString(4));
                    viajeNuevo.setTipoNota(rs.getString(5));
                    viajeNuevo.setNota(rs.getString(6));
                    viajeNuevo.setCantidadKm(rs.getInt(7));
                    viajeNuevo.setDniChofer(rs.getInt(8));
                    viajeNuevo.setIdAuto(rs.getString(9));
                    viajeNuevo.setHoraSalida(rs.getString(10));
                    viajeNuevo.setHoraRegreso(rs.getString(11));
                    viajeNuevo.setImporte(rs.getFloat(12));
              
                    return viajeNuevo;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControladorViaje.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

//    public List<Persona> buscarTodos() {
//        try {
//            String SQL = buscarTodosSQL;
//            Statement sentencia = getConexion().createStatement();
//            ResultSet rs = sentencia.executeQuery(SQL);
//
//            List<Persona> productos = new ArrayList<>();
//
//            if (rs != null) {
//                while (rs.next()) {
//                    Persona prodNuevo = new Persona();
//                    prodNuevo.setNombre(rs.getString(1));
//                    prodNuevo.setApellido(rs.getString(2));
//                    prodNuevo.setDni(rs.getInt(3));
//                    prodNuevo.setTelefono(rs.getInt(4));
//                    prodNuevo.setUsuario(rs.getString(5));
//                    prodNuevo.setContrasena(rs.getString(6));
//
//                    productos.add(prodNuevo);
//                }
//
//            }
//            return productos;
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ControladorViaje.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }

}
