package com.dev.mrvazguen.indexproductorum.ui.fragment.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.mrvazguen.indexproductorum.R;
import com.dev.mrvazguen.indexproductorum.databinding.FragmentMainBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    private FragmentMainBinding binding;
    public MainFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {

        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Activity","Main Fragment");
      //binding =   FragmentMainBinding.inflate(this.getLayoutInflater());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Binding (Vincular vista con codigo)
       binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        binding.btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Navigation","LoginFragment");
                Navigation.findNavController(v).navigate(
                        R.id.action_mainFragment_to_loginFragment);
            }
        });

        return view;
        //return inflater.inflate(R.layout.fragment_main, container, false);

    }




}