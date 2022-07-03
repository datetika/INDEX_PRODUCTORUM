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
import com.dev.mrvazguen.indexproductorum.databinding.FragmentRegistrarBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class RegistrarFragment extends Fragment {
FragmentRegistrarBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CORREO = "Correo";

    Usuari user;
    private String mParam1;
    private String mParam2;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_registrar, container, false);
        View v = inflater.inflate(R.layout.fragment_registrar, container, false);
        binding = FragmentRegistrarBinding.bind(v);

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

                Log.e("User_alta",correo+" | "+pasword+" | "+nombre+" | ");
            }

        });

        return  binding.getRoot();
    }

    private boolean isValidat(Usuari  user) {
        return !user.getEmail().isEmpty() && !user.getPasword().isEmpty()&& !user.getPasword().isEmpty();
    }

    private  void donarAlta(Usuari user, View v) {

        FirebaseConection.getmAuth().createUserWithEmailAndPassword(user.getEmail(), user.getPasword())
                .addOnCompleteListener(this.getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseConection.settUser(FirebaseConection.getmAuth().getCurrentUser());
                            Navigation.findNavController(v).navigate(
                                    R.id.action_registrarFragment_to_listaArticuloFragment);
                            Toast.makeText(getContext(),"Sighn in with successful ",Toast.LENGTH_SHORT);


                        } else {

                            Toast.makeText(getContext(),"No hemos podido registrarse",Toast.LENGTH_SHORT);

                            Toast.makeText(getContext(),"SignIn error !!!",Toast.LENGTH_SHORT);
                        }
                    }
                });

    }
}