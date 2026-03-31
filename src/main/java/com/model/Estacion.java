package com.model;

public class Estacion {
    private int id;
    private String codigo;
    private String nombre;
    private double latitud;
    private double longitud;
    private String direccion;
    private String poblacion;
    private String provincia;

    public Estacion() {}

    // Constructor con parámetros
    public Estacion(int id, String codigo, String nombre, double latitud, double longitud,
                    String direccion, String poblacion, String provincia) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.provincia = provincia;
    }

    // GETTERS
    public int getId() { return id; }
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getLatitud() { return latitud; }
    public double getLongitud() { return longitud; }
    public String getDireccion() { return direccion; }
    public String getPoblacion() { return poblacion; }
    public String getProvincia() { return provincia; }

    // SETTERS
    public void setId(int id) { this.id = id; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setLatitud(double latitud) { this.latitud = latitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setPoblacion(String poblacion) { this.poblacion = poblacion; }
    public void setProvincia(String provincia) { this.provincia = provincia; }
}