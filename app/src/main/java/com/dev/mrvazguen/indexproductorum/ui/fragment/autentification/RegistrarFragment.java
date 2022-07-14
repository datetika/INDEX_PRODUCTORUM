package com.dev.mrvazguen.indexproductorum.ui.fragment.autentification;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dev.mrvazguen.indexproductorum.R;

import com.dev.mrvazguen.indexproductorum.data.model.Usuari;
import com.dev.mrvazguen.indexproductorum.data.repository.FirebaseConection;
import com.dev.mrvazguen.indexproductorum.data.repository.firestore.manager.UserManagerDB;
import com.dev.mrvazguen.indexproductorum.data.repository.iFirestoreNotification;
import com.dev.mrvazguen.indexproductorum.databinding.FragmentRegistrarBinding;
import com.dev.mrvazguen.indexproductorum.utils.GlobarArgs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrarFragment extends Fragment {
FragmentRegistrarBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CORREO = "Correo";
    private UserManagerDB userManagerDB;
    Usuari user;
    private String mParam1;
    private String mParam2;
    FirebaseAuth mAuth ;
    public RegistrarFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static RegistrarFragment newInstance(String nombre, String email,String pasword) {
        RegistrarFragment fragment = new RegistrarFragment();
        Bundle args = new Bundle();
         
        //args.putString(ARG_CORREO,nombre);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseConection.getmAuth();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        userManagerDB = new UserManagerDB();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_registrar, container, false);
        View v = inflater.inflate(R.layout.fragment_registrar, container, false);
        binding = FragmentRegistrarBinding.bind(v);
        mAuth = FirebaseConection.getmAuth();
        binding.btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String correo =binding.editTextTextCorreo.getText().toString();
                String pasword = binding.editTextTextPassword.getText().toString();
                String nombre = binding.editTextTextNombre.getText().toString();
                Usuari user = new Usuari(nombre, correo,pasword);

                if(isValidat(user)){
                    donarAlta(user, v);


                }
                else
                {
                    Toast.makeText(getActivity(), "Datos no son validos", Toast.LENGTH_SHORT).show();
                    Log.d("RegistrarActividad","dato no son correctos");
                }
                Log.e("User_alta",correo+" | "+pasword+" | "+nombre+" | ");
            }

        });

        return  binding.getRoot();
    }

    private static boolean isValidat(Usuari  user) {
        return !user.getEmail().isEmpty() && !user.getPasword().isEmpty()&& !user.getPasword().isEmpty();
    }



    private  void donarAlta(Usuari user, View v) {


        Boolean hasRegisterUser=false;
        if(mAuth==null){
            Log.d("Firebase_Autentification","mAut null");
            mAuth = FirebaseConection.getmAuth();
        }

        UserManagerDB userManagerDB = new UserManagerDB();
        iFirestoreNotification iFirestoreNotification = new iFirestoreNotification () {

            @Override
            public boolean OnSuccess() {
                GlobarArgs.USER_ID= FirebaseConection.getmAuth().getCurrentUser().getUid();
                GlobarArgs.CORREO_USUARIO = user.getEmail();
                GlobarArgs.NOM_USUARI_ACTUAL = user.getNombre();//Asignamos el nombre de usuario
                Log.d("USER_MANAGER_FIRESTORE","hemos podido dar alta al usuario : "+ user.getNombre());
                return true;
            }

            @Override
            public void OnFailure() {
                Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT);
                Log.d("USER_MANAGER_FIRESTORE","No hemos podido dar alta al usuario : ");
            }
        };
        userManagerDB.addUserTable(user,iFirestoreNotification);



        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPasword())
                .addOnCompleteListener(this.getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            //TODO assign user id in global args
                            GlobarArgs.USER_ID= FirebaseConection.getmAuth().getCurrentUser().getUid();
                            GlobarArgs.CORREO_USUARIO =user.getEmail();
                            GlobarArgs.NOM_USUARI_ACTUAL = user.getNombre();
                            Log.d("USER_REGISTER",GlobarArgs.USER_ID);

                            Toast.makeText(getActivity(),"Sighn in with successful ",Toast.LENGTH_SHORT);

                            if(iFirestoreNotification.OnSuccess())
                                Navigation.findNavController(v).navigate(
                                        R.id.action_registrarFragment_to_listaArticuloFragment);
                            else
                                Log.d("USER_REGISTER","No hemos podido crear la tabla de usuario");

                        } else {

                            Toast.makeText(getActivity(),"SignIn error !!!",Toast.LENGTH_SHORT);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(),e.toString(),Toast.LENGTH_SHORT);
                    }
                });


        /*
        FirebaseUser userFirebase = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = EmailAuthProvider
                .getCredential(user.getEmail(), user.getPasword());


        userFirebase.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //TODO assign user id in global args
                GlobarArgs.USER_ID= FirebaseConection.getmAuth().getCurrentUser().getUid();
                GlobarArgs.CORREO_USUARIO = FirebaseConection.getUser().getEmail();


                UserManagerDB userManagerDB = new UserManagerDB();
                String email =FirebaseConection.getmAuth().getCurrentUser().getEmail();
                iFirestoreNotification iFirestoreNotification = new iFirestoreNotification () {

                    @Override
                    public void OnSuccess() {
                        Log.d("USER_MANAGER_FIRESTORE","hemos podido dar alta al usuario : "+ user.getNombre());
                    }

                    @Override
                    public void OnFailure() {
                        Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT);
                        Log.d("USER_MANAGER_FIRESTORE","No hemos podido dar alta al usuario : ");
                    }
                };
                userManagerDB.addUser(user,iFirestoreNotification);
                Log.d("USER_REGISTER",GlobarArgs.USER_ID);


                Toast.makeText(getActivity(),"Sighn in with successful ",Toast.LENGTH_SHORT);


                Navigation.findNavController(v).navigate(
                        R.id.action_registrarFragment_to_listaArticuloFragment);
            }
        });



         */

    }

    @Override
    public void onStop() {
        super.onStop();
        GlobarArgs.USER_ID = mAuth.getUid();
    }
}