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
import com.dev.mrvazguen.indexproductorum.data.model.Articulo;
import com.dev.mrvazguen.indexproductorum.data.repository.FirebaseConection;
import com.dev.mrvazguen.indexproductorum.data.repository.FirestorePersistence;
import com.dev.mrvazguen.indexproductorum.databinding.FragmentAgregarArticuloBinding;
import com.dev.mrvazguen.indexproductorum.databinding.FragmentLoginBinding;

import java.util.ArrayList;

public class AgregarArticuloFragment extends Fragment {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapterCategoria;
    FragmentAgregarArticuloBinding binding;
    FirestorePersistence firestorePersistence;

    public AgregarArticuloFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firestorePersistence = new FirestorePersistence();
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

        ///region Buto agregar
        binding.btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Articulo articulo = new Articulo("","",new ArrayList<>(),3);
                firestorePersistence.agregar("",articulo);
                firestorePersistence.close();

                closefragment();
                //getActivity().getFragmentManager().beginTransaction().remove(this).commit();

            }
        });
        ///endregion
        return  binding.getRoot();
    }
    private void closefragment() {
        getActivity().getFragmentManager().popBackStack();
       }
}