package com.example.coman.escuelaministerioteocratico;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import util.DatabaseHelper;
import util.OrmLiteBaseActivity1;

public class MainActivity extends OrmLiteBaseActivity1<DatabaseHelper> implements View.OnClickListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarUI();

    }

    private void inicializarUI() {
        //Comprobamos cual layout se esta cargando segun el tamano de la pantalla
        View view = findViewById(R.id.navigation_drawer_layout);
        String viewTag = String.valueOf(view.getTag());
        Log.d(getClass().getSimpleName(),String.format("Layout:%s", viewTag));

        if (viewTag.equals("phone")) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            FloatingActionButton fba = (FloatingActionButton) findViewById(R.id.fab);

            setSupportActionBar(toolbar);
            fba.setOnClickListener(this);

            actionBar = getSupportActionBar();
            assert actionBar != null; //esto arregla nullexception
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);

            drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);

            NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
            if (navigationView != null) {
                setupNavigationDrawerContent(navigationView);
            }

            setupNavigationDrawerContent(navigationView);
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_drawer_estudiantes:
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_programa:
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_calificacion:
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_backup:
                                menuItem.setChecked(true);
                                Toast.makeText(MainActivity.this, "Launching " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_ayuda:
                                menuItem.setChecked(true);
                                Toast.makeText(MainActivity.this, menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_opinion:
                                menuItem.setChecked(true);
                                Toast.makeText(MainActivity.this, menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                        }
                        return true;
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                // creamos instancia de AgregarEstudiantesFragment()
                DialogFragment newFragment = new AgregarEstudiantesFragment();
                // mostramos
                newFragment.show(getFragmentManager(),"crear_est");
                break;
        }


    }
}
