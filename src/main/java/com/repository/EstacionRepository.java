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

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    private List<Estacion> executeQuery(String query, Object... params) {
        List<Estacion> estaciones = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Seteamos los parámetros dinámicamente (no importa si es int o string)
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

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


    public List<Estacion> getAllEstaciones() {
        return executeQuery("SELECT * FROM estaciones");
    }

    public List<Estacion> getEstacionById(int id) {
        return executeQuery("SELECT * FROM estaciones WHERE id = ?", id);
    }

    public List<Estacion> getEstacionByCode(String code) {
        return executeQuery("SELECT * FROM estaciones WHERE LOWER(codigo) LIKE LOWER(?)","%" + code + "%");
    }

    public List<Estacion> getEstacionesByLikeName(String name) {
        return executeQuery("SELECT * FROM estaciones WHERE LOWER(descripcion) LIKE LOWER(?)", "%" + name + "%");
    }

    public List<Estacion> getEstacionByProvincia(String prov) {
        return executeQuery("SELECT * FROM estaciones WHERE LOWER(provincia) LIKE LOWER(?)", "%" + prov + "%");
    }

    public List<Estacion> getEstacionByPoblacion(String pobl) {
        return executeQuery("SELECT * FROM estaciones WHERE LOWER(poblacion) LIKE LOWER(?)", "%" + pobl + "%");
    }

    public List<Estacion> getEstacionByDireccion(String direccion) {
        return executeQuery("SELECT * FROM estaciones WHERE LOWER(direccion) LIKE LOWER(?)", "%" + direccion + "%");
    }

    // --- MAPPER (Sigue igual) ---
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