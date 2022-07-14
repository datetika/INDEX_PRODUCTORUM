package com.dev.mrvazguen.indexproductorum.ui.fragment.articulo.sharedUser;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.mrvazguen.indexproductorum.R;
import com.dev.mrvazguen.indexproductorum.data.repository.firestore.manager.UserManagerDB;
import com.dev.mrvazguen.indexproductorum.databinding.FragmentSharedUserBinding;

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

        binding.btnSearchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManagerDB userManagerDB = new UserManagerDB();
                if(userManagerDB.existUserInTableUser(binding.tvSearchUserByEmail.getText().toString())){
                    //Add user in permision of shoping list
                }

            }
        });

        return binding.getRoot();//View

    }
}