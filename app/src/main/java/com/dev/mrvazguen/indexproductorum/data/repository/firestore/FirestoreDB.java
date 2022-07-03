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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author VM
 * @see  <a href="https://github.com/UOC-Android/index_productorum.git">githubURL</a>
 * @version  07/2022
 */

public class FirestoreDB<T>  extends FirebaseDao {
     private  String collrectionPath;
    public final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public  FirestoreDB( String collectionPath){
         this.collrectionPath = collectionPath;
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
    public static <T> T convertInstanceOfObject(Object o) {
        try {
            T rv = (T)o;
            return rv;
        } catch(java.lang.ClassCastException e) {
            return null;
        }
    }
    @Override
    public void read(ArrayList lista, Object tipoClase) {

        CollectionReference userRef = db.collection(collrectionPath);

        userRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            private Object firestoreDB;

            @Override
            public void onSuccess(QuerySnapshot snapshot) {
               //List lista =  snapshot.toObjects(FirestoreDB.class);

                for (DocumentChange doc : snapshot.getDocumentChanges())
                    switch (doc.getType()) {
                        case ADDED:
                            Map<String, T> documents = (Map<String, T>) doc.getDocument().getData();
                            //Object[] fieldArray = documents.entrySet().toArray();//All field of firestore document

                            lista.add( documents);
                            break;
                        case REMOVED:
                            String id = doc.getDocument().getId();
                            int currentPosition = -1;
                            for (int i = 0; i < lista.size(); i++) {
                                if (id.equals(lista.get(i))) {
                                    currentPosition = i;
                                    break;
                                }
                            }
                            lista.remove(currentPosition);
                            break;
                        case MODIFIED:

                            break;
                    }

            }

        });
    }


}
