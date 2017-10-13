package com.victorsaico.shareddrawer.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.victorsaico.shareddrawer.R;
import com.vstechlab.easyfonts.EasyFonts;

public class HomeFragment extends Fragment {
private TextView txtbienvenido,home_fullname;
    private SharedPreferences sharedPreferences;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        txtbienvenido = (TextView) view.findViewById(R.id.txtbienvenido);
        home_fullname = (TextView) view.findViewById(R.id.home_fullname);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        home_fullname.setText(sharedPreferences.getString("fullname", null));
        String valor = sharedPreferences.getString("preferencitexto", null);
        if(valor != null){
            if(valor.equals("1")){
                changeTexto1();
            }else if (valor.equals("2")) {
                changeTexto2();
            }else if(valor.equals("3")){
                changeTexto3();
            }
        }else {
            Toast.makeText(getActivity(), "ninguna fuente seleccionada", Toast.LENGTH_SHORT).show();
        }
        return view;
    }
    public void changeTexto1(){
        txtbienvenido.setTypeface(EasyFonts.droidSerifBoldItalic(getContext()));
        home_fullname.setTypeface(EasyFonts.droidSerifBoldItalic(getContext()));

    }
    public void changeTexto2(){
        txtbienvenido.setTypeface(EasyFonts.caviarDreams(getContext()));
        home_fullname.setTypeface(EasyFonts.caviarDreams(getContext()));

    }
    public void changeTexto3(){
        txtbienvenido.setTypeface(EasyFonts.robotoBlackItalic(getContext()));
        home_fullname.setTypeface(EasyFonts.robotoBlackItalic(getContext()));
    }
}
