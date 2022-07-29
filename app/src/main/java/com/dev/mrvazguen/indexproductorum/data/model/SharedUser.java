package com.dev.mrvazguen.indexproductorum.data.model;

public class SharedUser {
    String nombre;
    String email;
    public SharedUser(String nombre) {
        this.nombre = nombre;
    }

    public SharedUser(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
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

    @Override
    public String toString() {
        return "SharedUser{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
