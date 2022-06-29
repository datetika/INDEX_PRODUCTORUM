package com.dev.mrvazguen.indexproductorum.ui.fragment.articulo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.dev.mrvazguen.indexproductorum.R;
import com.dev.mrvazguen.indexproductorum.databinding.FragmentAgregarArticuloBinding;
import com.dev.mrvazguen.indexproductorum.databinding.FragmentLoginBinding;

public class AgregarArticuloFragment extends Fragment {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapterCategoria;
    FragmentAgregarArticuloBinding binding;

    public AgregarArticuloFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ///region adapter of Spinner categoria
        adapterCategoria = ArrayAdapter.createFromResource(this.getActivity().getApplicationContext(),
                R.array.categoria_array, android.R.layout.simple_spinner_item);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ///endregion
        


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_agregar_articulo, container, false);
        View v = inflater.inflate(R.layout.fragment_agregar_articulo, container, false);

        binding = FragmentAgregarArticuloBinding.bind(v);

        ///region spiner categoria
        spinner =  binding.spinnerCategoria;
        spinner.setAdapter(adapterCategoria);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("Spinner categoria",parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("Spinner categoria","default_value");
            }
        });
        ///endregion

        return  binding.getRoot();
    }
}