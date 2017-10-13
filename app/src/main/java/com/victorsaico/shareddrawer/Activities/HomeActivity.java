package com.victorsaico.shareddrawer.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.victorsaico.shareddrawer.Fragments.HomeFragment;
import com.victorsaico.shareddrawer.Fragments.PerfilFragment;
import com.victorsaico.shareddrawer.R;

import cn.pedant.SweetAlert.SweetAlertDialog;
public class HomeActivity extends AppCompatActivity{
    private DrawerLayout drawerLayout;
    private TextView txtfullname;
    private static final int REGISTER_FORM_REQUEST = 100;
    private static final String TAG = HomeActivity.class.getSimpleName();
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Setear Toolbar como action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, android.R.string.ok, android.R.string.cancel);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if(navigationView != null ){
            prepararDrawer(navigationView);
            seleccionarItem(navigationView.getMenu().getItem(0));
        }
        txtfullname = (TextView) navigationView.getHeaderView(0).findViewById(R.id.menu_fullname);
        imprimirDrawer();

    }
    private void imprimirDrawer(){
      sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
       String fullname = sharedPreferences.getString("fullname", null);
       txtfullname.setText(fullname);
   }
    private void prepararDrawer(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                seleccionarItem(menuItem);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }
    private void seleccionarItem(MenuItem itemDrawer){
        Fragment fragmentoGenerico = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (itemDrawer.getItemId()){
            case R.id.nav_home:
                fragmentoGenerico = new HomeFragment();
                break;
            case R.id.nav_perfil:
                fragmentoGenerico = new PerfilFragment();
                break;
            case R.id.nav_settings:
                showUpdate();
                break;
            case R.id.nav_logout:
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Desea salir?")
                        .setContentText("")
                        .setCancelText("Si, pero vuelvo")
                        .setConfirmText("Cancelar")
                        .showContentText(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();
                                logout();
                            }
                        })
                        .show();
                break;
        }
        if (fragmentoGenerico != null){
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content, fragmentoGenerico)
                    .commit();
        }
        setTitle(itemDrawer.getTitle());
    }

    private void logout(){
        // remove from SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean success = editor.putBoolean("islogged", false).commit();
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // Option open drawer
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showUpdate(){
        Intent intent = new Intent(HomeActivity.this, MyPreferencesActivity.class);
        startActivity(intent);
    }

}
