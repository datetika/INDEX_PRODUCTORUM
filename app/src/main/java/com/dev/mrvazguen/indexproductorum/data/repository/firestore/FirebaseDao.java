package com.dev.mrvazguen.indexproductorum.data.repository.firestore;

import com.dev.mrvazguen.indexproductorum.data.model.Articulo;
import com.dev.mrvazguen.indexproductorum.data.repository.iFirestoreNotification;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

//Generic abstract class
public abstract class FirebaseDao <T> {
    protected  static   final   DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference();
    protected String tableName;
    public  FirebaseDao(){

    }
    public FirebaseDao(String tableName) {

    }


    public abstract void append(Object articulo, iFirestoreNotification iErrorMesgage);

    /**
     *  lee en firestore todos los datos
     * @param list  list of object
     * @param tipoClase
     * @return retorn todos los valores
     */
    public  abstract void read(ArrayList<T> list, Object tipoClase);
}
