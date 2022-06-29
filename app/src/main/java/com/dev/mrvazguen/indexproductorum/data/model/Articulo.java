package com.dev.mrvazguen.indexproductorum.data.model;

import java.util.ArrayList;

public class Articulo {
    private  String nombre;
    private  String descripcion;
    private ArrayList<String>categoria;
    private  double precio;

    public  Articulo(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
