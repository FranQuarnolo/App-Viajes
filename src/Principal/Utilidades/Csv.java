package Principal.Utilidades;

import Principal.BD.ControladorBaseDatosA;
import Principal.Entidades.Viaje;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.ObservableList;

/**
 *
 * @author PANCHO
 */
public class Csv {

    public void exportarCsv(ObservableList<Viaje> listaViaje) throws SQLException {
        ControladorBaseDatosA cp2 = new ControladorBaseDatosA();

//        ObservableList<Viaje> archivoViajes;
        Statement ps = cp2.getConexion().createStatement();
        ResultSet rs = ps.executeQuery("SELECT * FROM viaje");
        if (rs.next()) {

            listaViaje.add(new Viaje(rs.getInt("idViaje"), rs.getDate("fecha"), rs.getString("origen"),
                    rs.getString("destino"), rs.getString("tipoNota"), rs.getString("nota"),
                    rs.getInt("cantidadKM"), rs.getInt("dniChofer"), rs.getString("idAuto"),
                    rs.getString("horaSalida"), rs.getString("horaRegreso"), rs.getFloat("importe")));

        }
        String outputFile = "C:/Users/Andres/Desktop/ArchivoViajes.csv";
        boolean siYaExiste = new File(outputFile).exists();

        if (siYaExiste) {
            File ArchivoViajes = new File(outputFile);
            ArchivoViajes.delete();
        }

        try {

            CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
            
            csvOutput.write("idViaje");
            csvOutput.write("Fecha");
            csvOutput.write("Origen");
            csvOutput.write("Destino");
            csvOutput.write("TipoNota");
            csvOutput.write("Nota");
            csvOutput.write("KM");
            csvOutput.write("DNIChofer");
            csvOutput.write("IDAuto");
            csvOutput.write("HoraSalida");
            csvOutput.write("HoraRegreso");
            csvOutput.write("Importe");
            csvOutput.endRecord();

            for (Viaje v : listaViaje) {

                csvOutput.write(Integer.toString(v.getIdViaje()));
                csvOutput.write(v.getFecha().toString());
                csvOutput.write(v.getOrigen());
                csvOutput.write(v.getDestino());
                csvOutput.write(v.getTipoNota());
                csvOutput.write(v.getNota());
                csvOutput.write(Integer.toString(v.getCantidadKm()));
                csvOutput.write(Integer.toString(v.getDniChofer()));
                csvOutput.write(v.getIdAuto());
                csvOutput.write(v.getHoraSalida());
                csvOutput.write(v.getHoraRegreso());
                csvOutput.write(Float.toString(v.getImporte()));

                csvOutput.endRecord();
            }

            csvOutput.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
