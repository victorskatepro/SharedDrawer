package com.victorsaico.shareddrawer.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.victorsaico.shareddrawer.R;
import com.victorsaico.shareddrawer.Repositories.UserRepository;
import com.victorsaico.shareddrawer.models.User;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    private EditText usernameInput;
    private EditText passwordInput;
    private ProgressBar progressBar;
    private String email,password,fullname;
    private View loginPanel;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameInput = (EditText)findViewById(R.id.username_input);
        passwordInput = (EditText)findViewById(R.id.password_input);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        loginPanel = findViewById(R.id.login_panel);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // username remember


        // islogged remember
        if(sharedPreferences.getBoolean("islogged", false)){
            // Go to Dashboard
         goDashboard();
        }
    }
    public void callLogin(View view){
        loginPanel.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        email = usernameInput.getText().toString();
        password = passwordInput.getText().toString();

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "You must complete these fields", Toast.LENGTH_SHORT).show();
            return;
        }

        //Login logic
        user = UserRepository.login(email, password);
        if(user == null){
            //UserRepository.create(email, email, password);
            showDialog();
            loginPanel.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            return;
        }

        guardarPreferences();
    }
    private void goDashboard(){
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
    public void showDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_regiser_dialog);
        dialog.setTitle("Registrar Usuario");

        final EditText edtfullname = (EditText) dialog.findViewById(R.id.edtfullname);
        Button btnregistrar = (Button) dialog.findViewById(R.id.btnregistrar);
        Button btncancelar = (Button) dialog.findViewById(R.id.btncancelar);

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullname = edtfullname.getText().toString();
                user = UserRepository.create(email, fullname,password);
                guardarPreferences();
                return;
            }
        });
        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
     }
     public void guardarPreferences(){
         Toast.makeText(this, "Welcome " + user.getFullname(), Toast.LENGTH_SHORT).show();

         // Save to SharedPreferences
         SharedPreferences.Editor editor = sharedPreferences.edit();
         boolean success = editor
                 .putString("username", user.getFullname())
                 .putString("fullname",user.getFullname())
                 .putBoolean("islogged", true)
                 .commit();

         // Go to Dashboard
         goDashboard();
     }

}
