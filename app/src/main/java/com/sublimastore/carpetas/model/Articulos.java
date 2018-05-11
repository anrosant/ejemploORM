package com.sublimastore.carpetas.model;

import com.orm.SugarRecord;

/**
 * Created by Anny on 05/03/2018.
 */

public class Articulos extends SugarRecord {
    private String nombre;
    private String tipo;
    private double precio;
    private int cantidad;
    private String descripcion;
    private String dirImagen;

    public Articulos(){ }
    public Articulos(String nombre, String tipo, double precio, int cantidad, String descripcion,
                     String dirImagen) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.dirImagen = dirImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDirImagen() {
        return dirImagen;
    }

    public void setDirImagen(String dirImagen) {
        this.dirImagen = dirImagen;
    }
}
