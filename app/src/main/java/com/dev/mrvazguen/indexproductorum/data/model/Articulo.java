package com.dev.mrvazguen.indexproductorum.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.Exclude;

import java.util.ArrayList;

public class Articulo   {

    ///region Fields
    @Exclude
    public String id;
    private  String nombre;
    private  String descripcion;
    private ArrayList<String>categoria;
    private  double precio;
    ///endregion

    ///region Constructor
    public  Articulo(){};
    public  Articulo(String nombre){
        this.nombre = nombre;
    }
    public Articulo(String nombre, String descripcion, ArrayList<String> categoria, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
    }
    ///endregion



    ///region Getter & Setter
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<String> getCategoria() {
        return categoria;
    }

    public void setCategoria(ArrayList<String> categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }



    public String getNombre() {
        return nombre;
    }
    ///endregion

    @Override
    public String toString() {
        return "Articulo{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", categoria=" + categoria +
                ", precio=" + precio +
                '}';
    }


}
