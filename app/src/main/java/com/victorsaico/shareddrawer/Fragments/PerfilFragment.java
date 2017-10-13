package com.victorsaico.shareddrawer.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.victorsaico.shareddrawer.Activities.MyPreferencesActivity;
import com.victorsaico.shareddrawer.R;
import com.vstechlab.easyfonts.EasyFonts;


public class PerfilFragment extends Fragment {
    // SharedPreferences
    private SharedPreferences sharedPreferences;
    private TextView usernameText,informacion_del_usuario,texto_password,titulo_contrasena;
    private TextView nameText;
    private ImageView btncambiarcontra;

    public PerfilFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        usernameText =  view.findViewById(R.id.texto_email);
        nameText = view.findViewById(R.id.texto_nombre);
        informacion_del_usuario = view.findViewById(R.id.informacion_del_usuario);
        texto_password = view.findViewById(R.id.texto_password);
        titulo_contrasena = view.findViewById(R.id.titulo_contrasena);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        btncambiarcontra = (ImageView) view.findViewById(R.id.icono_indicador_derecho);
        btncambiarcontra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(getActivity(), MyPreferencesActivity.class);
                startActivity(intent);
            }
        });
        imprimirValores();
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
    public void imprimirValores(){
        usernameText.setText(sharedPreferences.getString("username", null));
        nameText.setText(sharedPreferences.getString("fullname", null));
    }
    public void changeTexto1(){
        usernameText.setTypeface(EasyFonts.droidSerifBoldItalic(getContext()));
        nameText.setTypeface(EasyFonts.droidSerifBoldItalic(getContext()));
        informacion_del_usuario.setTypeface(EasyFonts.droidSerifBoldItalic(getContext()));
        texto_password.setTypeface(EasyFonts.droidSerifBoldItalic(getContext()));
        titulo_contrasena.setTypeface(EasyFonts.droidSerifBoldItalic(getContext()));
    }
    public void changeTexto2(){
        usernameText.setTypeface(EasyFonts.caviarDreams(getContext()));
        nameText.setTypeface(EasyFonts.caviarDreams(getContext()));
        informacion_del_usuario.setTypeface(EasyFonts.caviarDreams(getContext()));
        texto_password.setTypeface(EasyFonts.caviarDreams(getContext()));
        titulo_contrasena.setTypeface(EasyFonts.caviarDreams(getContext()));
    }
    public void changeTexto3(){
        usernameText.setTypeface(EasyFonts.robotoBlackItalic(getContext()));
        nameText.setTypeface(EasyFonts.robotoBlackItalic(getContext()));
        informacion_del_usuario.setTypeface(EasyFonts.robotoBlackItalic(getContext()));
        texto_password.setTypeface(EasyFonts.robotoBlackItalic(getContext()));
        titulo_contrasena.setTypeface(EasyFonts.robotoBlackItalic(getContext()));
    }
    }

