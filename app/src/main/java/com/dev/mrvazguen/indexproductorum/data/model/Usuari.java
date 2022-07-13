package com.dev.mrvazguen.indexproductorum.data.model;

import androidx.annotation.AttrRes;

import java.util.ArrayList;


public class Usuari {
    String nombre;
    String email;
    String pasword;
    ArrayList<String>idSharedUserArticle;

    public Usuari(){
        idSharedUserArticle= new ArrayList<>();
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

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

}
