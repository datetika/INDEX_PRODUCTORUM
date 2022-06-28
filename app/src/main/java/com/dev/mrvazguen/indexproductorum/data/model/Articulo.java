package com.dev.mrvazguen.indexproductorum.data.model;

public class Articulo {
    public String getNombre() {
        return nombre;
    }

    private  String nombre;

    public  Articulo(String nombre){
        this.nombre = nombre;
    }
}
