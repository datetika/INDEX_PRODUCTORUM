package com.dev.mrvazguen.indexproductorum.ui.fragment.articulo.sharedUser;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.dev.mrvazguen.indexproductorum.R;
import com.dev.mrvazguen.indexproductorum.data.model.Usuari;
import com.dev.mrvazguen.indexproductorum.data.repository.firestore.manager.UserManagerDB;
import com.dev.mrvazguen.indexproductorum.data.repository.iFirebaseResult;
import com.dev.mrvazguen.indexproductorum.data.repository.iFirestoreNotification;
import com.dev.mrvazguen.indexproductorum.databinding.FragmentSharedUserBinding;
import com.dev.mrvazguen.indexproductorum.ui.fragment.PurchaseConfirmationDialogFragment;
import com.dev.mrvazguen.indexproductorum.utils.GlobarArgs;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SharedUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SharedUserFragment extends Fragment {

   FragmentSharedUserBinding binding ;

    public SharedUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SharedUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SharedUserFragment newInstance(String param1, String param2) {
        SharedUserFragment fragment = new SharedUserFragment();

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
       // return inflater.inflate(R.layout.fragment_shared_user, container, false);
        binding = FragmentSharedUserBinding.inflate(inflater, container, false);

        ///region Shared user Spinner cargar los datos
        final List<String> list = new ArrayList<String>();
        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, list);

        binding.spinnerSharedUserList.setAdapter(adp1);

        ///endregion




        binding.btnSearchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManagerDB userManagerDB = new UserManagerDB();
                String email =binding.tvSearchUserByEmail.getText().toString()==null?"": binding.tvSearchUserByEmail.getText().toString();

                    if (!email.isEmpty())
                     userManagerDB.findUserByEmail(email, new iFirebaseResult() {

                         @Override
                         public void OnSuccess(Object usuari) {
                             Usuari usuariAux = (Usuari) usuari;
                             if(list.contains(usuariAux.getNombre()))
                                 Toast.makeText(getActivity(), "Usuari   existe en la lista", Toast.LENGTH_SHORT);
                             else if( usuariAux.getNombre().compareTo(GlobarArgs.NOM_USUARI_ACTUAL)==0){
                                 Toast.makeText(getActivity(), "Usuario selecionado es el usuario actual", Toast.LENGTH_SHORT);
                             }
                             else{
                                 list.add(usuariAux.getNombre());
                                 //TODO agregar documento de permiso en db_USERS/userID
                             }
                         }

                         @Override
                        public void OnFailure(String errorMSG) {
                            Toast.makeText(getActivity(), "Usuari no existe", Toast.LENGTH_SHORT);
                        }
                    });

                    else
                        Log.d("SHARED_USER_FRAGMENT", "camp Shared user email es vacio");
                }
        });

        return binding.getRoot();//View

    }
}