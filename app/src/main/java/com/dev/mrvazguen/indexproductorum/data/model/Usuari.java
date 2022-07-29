package com.dev.mrvazguen.indexproductorum.data.model;

import androidx.annotation.AttrRes;

import com.google.firebase.firestore.Exclude;

import java.util.ArrayList;


public class Usuari {
    String nombre;
    @Exclude
    String email;
    @Exclude
    String pasword;

    public Usuari(){}

    public Usuari(String nombre){
        this();
    }

    public  Usuari(String email,String nombre){
        this();
        this.nombre = nombre;
        this.email = email;
    }

    public  Usuari(String nombre,String email,String pasword) {
        this(email,nombre);
        this.pasword= pasword;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getPasword() {
        return pasword;
    }
    @Exclude
    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

}
