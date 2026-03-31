package com.repository;

import com.model.Estacion;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EstacionRepository {

    private final String url = "jdbc:mysql://db-renfe:3306/renfe";
    private final String user = "root";
    private final String pass = "root";

    public List<Estacion> getAllEstaciones() {
        List<Estacion> estaciones = new ArrayList<>();
        String query = "SELECT * FROM estaciones";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                estaciones.add(mapResultSetToEstacion(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estaciones;
    }

    public Estacion getEstacionByName(String name) {
        String query = "SELECT * FROM estaciones WHERE descripcion = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToEstacion(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Estacion> getEstacionesByLikeName(String name) {
        List<Estacion> estaciones = new ArrayList<>();
        String query = "SELECT * FROM estaciones WHERE LOWER(descripcion) LIKE LOWER(?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + name + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    estaciones.add(mapResultSetToEstacion(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estaciones;
    }

    private Estacion mapResultSetToEstacion(ResultSet rs) throws SQLException {
        Estacion estacion = new Estacion();
        estacion.setId(rs.getInt("id"));
        estacion.setCodigo(rs.getString("codigo"));
        estacion.setNombre(rs.getString("descripcion"));
        estacion.setLatitud(rs.getDouble("latitud"));
        estacion.setLongitud(rs.getDouble("longitud"));
        estacion.setDireccion(rs.getString("direccion"));
        estacion.setPoblacion(rs.getString("poblacion"));
        estacion.setProvincia(rs.getString("provincia"));
        return estacion;
    }
}