package com.dev.mrvazguen.indexproductorum.data.repository.firestore;

import androidx.annotation.NonNull;

import com.dev.mrvazguen.indexproductorum.data.model.Articulo;
import com.dev.mrvazguen.indexproductorum.data.repository.iFirestoreNotification;
import com.dev.mrvazguen.indexproductorum.utils.GlobarArgs;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author VM
 * @see  <a href="https://github.com/UOC-Android/index_productorum.git">githubURL</a>
 * @version  07/2022
 */

public class FirestoreDB  extends FirebaseDao {
    Object clase ;
    public final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public  FirestoreDB(Object claseBase){
        this.clase = claseBase;
    }

    /**
     *
     * @param clase tipo de clase que trabajaremos
     * @param transactionNotification Listener que  norifica al UI
     */
    @Override
    public void append(Object clase, iFirestoreNotification transactionNotification) {
        db.collection(GlobarArgs.GLOBAL_Collection)
                .document(GlobarArgs.ARTICULO_DOCUMENTO)
                .collection(GlobarArgs.ARTICULO_COLLECTION)
                .add(clase)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        transactionNotification.OnSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.w(TAG, "Error adding document", e);
                        transactionNotification.OnFailure();
                    }
                });
    }

    @Override
    public  ArrayList<Object> read(String collrectionPath, Object objeto) {

        final ArrayList<FirestoreDB> query = new ArrayList<>();
        ArrayList<Object>lista = new ArrayList<>();
        CollectionReference userRef = db.collection(collrectionPath);

        userRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            private Object firestoreDB;

            @Override
            public void onSuccess(QuerySnapshot snapshot) {
              snapshot.toObjects(FirestoreDB.class);


              for(DocumentChange doc:snapshot.getDocumentChanges())
                    switch (doc.getType()){
                        case ADDED:
                            Map<String,Object> documents = doc.getDocument().getData();
                            //Object[] fieldArray = documents.entrySet().toArray();//All field of firestore document
                            lista.add(documents);
                            break;
                        case REMOVED:

                            break;
                        case MODIFIED:

                            break;
                    }

            }


        });
        return lista;
    }
}
