package com.dev.mrvazguen.indexproductorum.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseConection    {

    private  static  FirebaseAuth mAuth;
    private  static  FirebaseUser firebaseUser;
    private  final String nombreProyecto="";

    public static void  crearConection(){
        if(mAuth==null){
            mAuth = FirebaseAuth.getInstance();
            firebaseUser = mAuth.getCurrentUser();
        }

    }

    public static   boolean closeConection(){
        return  true;
    }


    public static FirebaseUser getUser(){
        return firebaseUser;
    }
    public static void settUser( FirebaseUser firebaseUser){

        crearConection();
        firebaseUser = firebaseUser;
    }
    public  static  FirebaseAuth getmAuth(){
        crearConection();
        return  mAuth;
    }
}
