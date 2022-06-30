package com.dev.mrvazguen.indexproductorum.data.repository;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

/**

 * Esta clase define  consulta de base de datos utilizando patron de diseno DAO

 * @author: GuestVM

 * @version: 29/06/2022/A

 * @see <a href = "https://firebase.google.com/docs/firestore/quickstart" /> Documentacion basica </a>

 */


public class FirestorePersistence  implements  persistenciaDAO{

   public final FirebaseFirestore db = FirebaseFirestore.getInstance();

   @Override
   public void create(String nombreCollecion, Map<String, Object> dato) {
      db.collection(nombreCollecion).add(dato).
               addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                  @Override
                  public void onSuccess(DocumentReference documentReference) {

                  }
               })
              .addOnFailureListener(new OnFailureListener() {
                 @Override
                 public void onFailure(@NonNull Exception e) {

                 }
              });
   }

   @Override
   public void delete() {

   }

   @Override
   public void update() {

   }

   @Override
   public void read() {

   }

   public  void agregar(String collection,Object dato){

   }

   public  void close(){

   }
}
