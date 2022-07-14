package com.dev.mrvazguen.indexproductorum.data.repository.firestore.manager;

import androidx.annotation.NonNull;

import com.dev.mrvazguen.indexproductorum.data.model.Usuari;
import com.dev.mrvazguen.indexproductorum.data.repository.iFirestoreNotification;
import com.dev.mrvazguen.indexproductorum.utils.GlobarArgs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class UserManagerDB   {
    private static  final  String DEFAULT_USER_NAME="default_user";
    iFirestoreNotification notification ;
    private  FirebaseFirestore db = FirebaseFirestore.getInstance();
    public  UserManagerDB(){}
    public  UserManagerDB( iFirestoreNotification notification ){
        this.notification= notification;
    }
    public void addUserPermision(Usuari user, String idUsuari ){
        Map<String, Object> docUser = new HashMap<>();
        Map<String, Object> nestedUserPermision = new HashMap<>();
        nestedUserPermision.put("Usuari_Permis",user);

        docUser.put("permisosLeer", nestedUserPermision);


        db.collection(GlobarArgs.DB_USERS_LIST)
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
    public void addUserTable(Usuari user , iFirestoreNotification iFirestoreNotification){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(GlobarArgs.DB_USER_COLLECTION).document(user.getEmail()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                    iFirestoreNotification.OnSuccess();
                else
                    iFirestoreNotification.OnFailure();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                  iFirestoreNotification.OnFailure();
            }
        });
    }

    //DB access permission
    public  void tablaPermisionAdd(String userEmail,String idUserActual){

    }

    /**
     * Find user in table @GlobarArgs.DB_USER_COLLECTION and asing in @GlobarArgs.NOM_USUARI_ACTUAL
     * @param emailUsuariActual
     */
    public  void findUserByEmail(String emailUsuariActual){
        db= FirebaseFirestore.getInstance();
        CollectionReference colREF = db.collection(GlobarArgs.DB_USER_COLLECTION);


        colREF.whereEqualTo("email",emailUsuariActual).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Usuari usuari = new Usuari();
              if(task.isSuccessful()){
                  for (QueryDocumentSnapshot document : task.getResult()){
                      if ( (usuari =document.toObject(Usuari.class)).getEmail().equals(emailUsuariActual)){
                          GlobarArgs.NOM_USUARI_ACTUAL = usuari.getNombre();
                          break;
                      }
                  }
              }
            }
        });
    }


    public  void close(){


    }

    public boolean existUserInTableUser(String userEmail) {
        return false;
    }
}
