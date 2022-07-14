package com.dev.mrvazguen.indexproductorum.ui.fragment.articulo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.dev.mrvazguen.indexproductorum.R;
import com.dev.mrvazguen.indexproductorum.data.model.Articulo;
import com.dev.mrvazguen.indexproductorum.data.repository.firestore.manager.ArticuloManagerDB;
import com.dev.mrvazguen.indexproductorum.data.repository.iFirestoreNotification;
import com.dev.mrvazguen.indexproductorum.databinding.FragmentAgregarArticuloBinding;
import com.dev.mrvazguen.indexproductorum.utils.GlobarArgs;

import java.util.ArrayList;

public class AgregarArticuloFragment extends Fragment {
    private static final String TAG = "AgregarArticuloFragment";
    Spinner spinner;
    ArrayAdapter<CharSequence> adapterCategoria;
    FragmentAgregarArticuloBinding binding;
    ArticuloManagerDB firestorePersistence;

    public AgregarArticuloFragment() {
        // Required empty public constructor
      firestorePersistence = new ArticuloManagerDB(GlobarArgs.DB_SHOPING);
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

        ///region Buto agregar
        binding.btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String>categoria = new ArrayList<>();
                categoria.add(binding.spinnerCategoria.getSelectedItem().toString());

                String nombre = binding.editTextTextNombre.getText().toString();
                String descripcion =binding.editTextDescripcion.getText().toString() ;
                String precio =  binding.edittextPrecio.getText().toString();

                if(precio.isEmpty())
                    precio="0";

                Articulo articulo = new Articulo(nombre,descripcion,categoria,Double.parseDouble(precio));

               if(! validarDatos(articulo)){
                   Toast.makeText(AgregarArticuloFragment.this.getActivity().getApplicationContext(),"Campos no son validos !!!",Toast.LENGTH_SHORT);
               }
               else{
                // interface to notificate  state of apend method
                iFirestoreNotification iErrorMesgage= new iFirestoreNotification() {
                    @Override
                    public boolean OnSuccess() {
                       // Log.e(TAG,"Agregado articulo con exito");
                        //Navega al fragment anterior
                        Navigation.findNavController(v).navigate(R.id.listaArticuloFragment);
                         return  true;
                    }

                    @Override
                    public void OnFailure() {
                        Log.e(TAG,"hemos encontrado error al agregar articulo");
                        Toast.makeText(AgregarArticuloFragment.this.getActivity(),"hemos encontrado error al agregar articulo",Toast.LENGTH_SHORT);

                    }
                };

                firestorePersistence.append(articulo, iErrorMesgage);
            }}
        });
        ///endregion
        return  binding.getRoot();
    }

    private boolean validarDatos(Articulo articulo) {


        if(articulo.getNombre().isEmpty()){
            binding.editTextTextNombre.startAnimation(shakeError());
        }

        return  !articulo.getNombre().isEmpty() && articulo.getNombre().length()>3;
    }

    private void closefragment() {
        /*
        FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.agregarArticuloFragment, ListaArticuloFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("name") // name can be null
                .commit();

         */

    }
    public TranslateAnimation shakeError() {
        TranslateAnimation shake = new TranslateAnimation(0, 10, 0, 0);
        shake.setDuration(500);
        shake.setInterpolator(new CycleInterpolator(7));
        return shake;
    }
}