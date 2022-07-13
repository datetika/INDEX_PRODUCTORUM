package com.dev.mrvazguen.indexproductorum.data.repository.firestore.manager;

import androidx.annotation.NonNull;

import com.dev.mrvazguen.indexproductorum.data.model.User;
import com.dev.mrvazguen.indexproductorum.data.model.Usuari;
import com.dev.mrvazguen.indexproductorum.data.repository.iFirestoreNotification;
import com.dev.mrvazguen.indexproductorum.utils.GlobarArgs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;

public class UserManagerDB   {
    iFirestoreNotification notification ;
    public  UserManagerDB(){}
    public  UserManagerDB( iFirestoreNotification notification ){
        this.notification= notification;
    }
    public void addUserPermision(Usuari user, String idUsuari ){
        Map<String, Object> docUser = new HashMap<>();
        Map<String, Object> nestedUserPermision = new HashMap<>();
        nestedUserPermision.put("Usuari_Permis",user);

        docUser.put("permisosLeer", nestedUserPermision);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(GlobarArgs.DB_USERS)
                .add(docUser).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        notification.OnSuccess();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        notification.OnFailure();
                    }
                });


    }

    /**
     * Add user in DB
     * @param user
     */
    public void addUser(Usuari user ){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
         User newUser = new User("Vazguen","usuari@gmail.com","id");

        db.collection("users").document("usuari@gmail.com").set(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    //DB access permission
    public  void tablaPermisionAdd(String userEmail,String idUserActual){

    }


    public  void close(){

        FirebaseAuth.getInstance().signOut();
    }

}
