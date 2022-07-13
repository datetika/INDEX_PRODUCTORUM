package com.dev.mrvazguen.indexproductorum.data.repository.firestore;

import com.dev.mrvazguen.indexproductorum.data.model.Articulo;
import com.dev.mrvazguen.indexproductorum.data.repository.iFirestoreNotification;
import com.dev.mrvazguen.indexproductorum.data.repository.iTaskNotification;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

//Generic abstract class
public abstract class FirebaseDao <E> {
    protected  static   final   DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference();
    protected String tableName;
    public  FirebaseDao(){

    }
    public FirebaseDao(String tableName) {

    }


    public abstract void append(E articulo, iFirestoreNotification iErrorMesgage);

    /**
     *  lee en firestore todos los datos en tiempo real
     * @param list  list of object
     * @param tipoClase
     * @return retorn todos los valores
     */
    public  abstract void readLiveDate(ArrayList<E> list, Object tipoClase);
    public abstract  void   read( iTaskNotification notificationEstat) ;

    public abstract  void readRealtimeListener( iTaskNotification notificationEstat);
    public abstract  boolean deleteDocument(String nomCollectionAbsolutPath, String documentFieldName);
}
