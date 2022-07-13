package com.dev.mrvazguen.indexproductorum.ui.fragment.autentification;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.mrvazguen.indexproductorum.R;
import com.dev.mrvazguen.indexproductorum.data.repository.FirebaseConection;

import com.dev.mrvazguen.indexproductorum.databinding.FragmentLoginBinding;
import com.dev.mrvazguen.indexproductorum.utils.GlobarArgs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.regex.Pattern;


public class LoginFragment extends Fragment    {
FragmentLoginBinding binding;
    public LoginFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {

      LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
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

        View v = inflater.inflate(R.layout.fragment_login, container, false);
        binding = FragmentLoginBinding.bind(v);
        // Inflate the layout for this fragment3



        v.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e( "btnLogin","btn Login clicked");

                 loginUser(v);

                 /*
                Navigation.findNavController(v).navigate(
                        R.id.action_loginFragment_to_listaArticuloFragment);
                */
            }

        });


        binding.btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e( "btnRegistrar","btn signIN clicked");
                Navigation.findNavController(v).navigate(
                        R.id.action_loginFragment_to_registrarFragment);
            }
        });

        binding.showPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v.getTag()=="hiden")
                {//Show Password
                     binding.idEdtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                     v.setTag("show");
                }
                else{
                //Hide Password
                    binding.idEdtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    v.setTag("hiden");
                }
            }
        });


        return binding.getRoot();
        /*
        binding = FragmentLoginBinding.inflate(inflater);
        return  binding.getRoot();*/

    }


    ///region TODO validation User date
    public  void loginUser(View v){

        String userMail= binding.idEdtUserName.getText().toString();
        String password = binding.idEdtPassword.getText().toString();

        boolean  login=false;
        if(validarFieldLogin(userMail,password) ) {
            FirebaseConection.getmAuth().signInWithEmailAndPassword(userMail, password)
                    .addOnCompleteListener(  this.getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseConection.settUser(FirebaseConection.getmAuth().getCurrentUser());

                                Navigation.findNavController(v).navigate(
                                        R.id.action_loginFragment_to_listaArticuloFragment);
                                //TODO assign user id in global args
                                GlobarArgs.USER_ID = FirebaseConection.getmAuth().getCurrentUser().getUid() ;
                                GlobarArgs.CORREO_USUARIO = FirebaseConection.getUser().getEmail();

                            } else {
                                // If sign in fails, display a message to the user.


                                String txt = " | Login ha fallado !!! ";
                                Toast.makeText( v.getContext().getApplicationContext(), txt,Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
            }
        else {
            Log.e("Autentificarse", "failes login !!!");
            Toast.makeText(this.getContext().getApplicationContext(), "No hemos posido autentificarse", Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding =null;
    }

    public  boolean validarFieldLogin(String userMail, String pasword){

        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

        return  Pattern.compile(regexPattern).matcher(userMail).matches() && pasword.length()>3;
    }



    ///endregion
}