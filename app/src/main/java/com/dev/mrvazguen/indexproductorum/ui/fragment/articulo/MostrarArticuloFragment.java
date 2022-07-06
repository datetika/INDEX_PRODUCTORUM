package com.dev.mrvazguen.indexproductorum.ui.fragment.articulo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.mrvazguen.indexproductorum.R;
import com.dev.mrvazguen.indexproductorum.utils.GlobarArgs;


public class MostrarArticuloFragment extends Fragment {



    public MostrarArticuloFragment() {
        // Required empty public constructor
    }

    public static MostrarArticuloFragment newInstance(String param1, String param2) {
        MostrarArticuloFragment fragment = new MostrarArticuloFragment();
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
       String nombre= getArguments().getString(GlobarArgs.articuloEnum.nombre.toString());
       String descripcon= getArguments().getString(GlobarArgs.articuloEnum.descripcion.toString());
       Double precio= getArguments().getDouble(GlobarArgs.articuloEnum.precio.toString());
       String categoria= getArguments().getString(GlobarArgs.articuloEnum.categoria.toString());
       View view = inflater.inflate(R.layout.fragment_mostrar_articulo, container, false);
        // Inflate the layout for this fragment
        ((TextView) view.findViewById(R.id.tv_NomArticle)).setText(nombre);
        ((TextView) view.findViewById(R.id.tv_Descripcion)).setText(descripcon);
        ((TextView) view.findViewById(R.id.tv_Categoria)).setText(categoria);
        ((TextView) view.findViewById(R.id.tv_Precio)).setText(String.valueOf(precio));
        return view;
    }
}