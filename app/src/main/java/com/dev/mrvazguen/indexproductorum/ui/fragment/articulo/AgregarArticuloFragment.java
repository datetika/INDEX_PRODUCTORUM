package com.dev.mrvazguen.indexproductorum.ui.fragment.articulo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.mrvazguen.indexproductorum.R;
import com.dev.mrvazguen.indexproductorum.databinding.FragmentAgregarArticuloBinding;
import com.dev.mrvazguen.indexproductorum.databinding.FragmentLoginBinding;

public class AgregarArticuloFragment extends Fragment {

    FragmentAgregarArticuloBinding binding;

    public AgregarArticuloFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_agregar_articulo, container, false);
        View v = inflater.inflate(R.layout.fragment_agregar_articulo, container, false);

        binding = FragmentAgregarArticuloBinding.bind(v);

        return  binding.getRoot();
    }
}