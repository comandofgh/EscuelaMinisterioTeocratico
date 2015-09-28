package com.example.coman.escuelaministerioteocratico;

import android.app.Fragment;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import util.Estudiante;
import util.EstudianteListAdapter;
import util.EstudianteReceiver;

public class ListaEstudiantesFragment extends Fragment {

    private ArrayAdapter<Estudiante> adapter;
    private EstudianteReceiver receiver;
    private ListView estudiantListview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lista_estudiantes, container, false);
        inicializarComponentes(v);
        setHasOptionsMenu(true);//Habilita el ActionBar de este fragment
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        receiver = new EstudianteReceiver(adapter);
        getActivity().registerReceiver(receiver, new IntentFilter("listaestudiantes"));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }

    private void inicializarComponentes(View v) {
        estudiantListview = (ListView) v.findViewById(R.id.listViewEstudiantes);
        adapter = new EstudianteListAdapter(getActivity(), new ArrayList<Estudiante>());
        //Notificaciones para el adapter y actualize listview
        adapter.setNotifyOnChange(true);
        estudiantListview.setAdapter(adapter);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu); //TODO: este menu ya es inflado en main, esta duplicado
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_eliminar_estudiante: eliminarEstudiante(item); return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    private void eliminarEstudiante(MenuItem item) {
        SparseBooleanArray array = estudiantListview.getCheckedItemPositions();
        ArrayList<Estudiante> seleccion = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            //Posicion del estudiante dentro del adaptador
            int posicion = array.keyAt(i);
            if (array.valueAt(i)) seleccion.add(adapter.getItem(posicion));
            Intent intent = new Intent("listaestudiantes");
            intent.putExtra("operacion", EstudianteReceiver.ESTUDIANTE_ELIMINADO);
            intent.putExtra("datos", seleccion);
            getActivity().sendBroadcast(intent);
            estudiantListview.clearChoices();
        }
    }

}
