package com.example.coman.escuelaministerioteocratico;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
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

import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;

import util.DatabaseHelper;
import util.Estudiante;
import util.EstudianteListAdapter;
import util.EstudianteReceiver;
import util.OrmLiteBaseActivity1;

public class ListaEstudiantesFragment extends Fragment {

    private ArrayAdapter<Estudiante> adapter;
    private EstudianteReceiver receiver;
    private ListView estudiantListview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lista_estudiantes, container, false);
        try {
            inicializarComponentes(v);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setHasOptionsMenu(true);//Habilita el ActionBar de este fragment
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        receiver = new EstudianteReceiver(adapter, getOrmLiteBaseActivity());
        getActivity().registerReceiver(receiver, new IntentFilter("listaestudiantes"));

    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }

    private void inicializarComponentes(View v) throws SQLException {
        estudiantListview = (ListView) v.findViewById(R.id.listViewEstudiantes);
        adapter = new EstudianteListAdapter(getActivity(), new ArrayList<Estudiante>());
        OrmLiteBaseActivity1<DatabaseHelper> activity = getOrmLiteBaseActivity();
        if (activity != null) {
            DatabaseHelper helper = activity.getHelper();
            QueryBuilder<Estudiante, Integer> dao = helper.getEstudianteRuntimeDAO().queryBuilder(); //construimos un Builder
            dao.orderBy("nombre", true); //ordenamos alfabeticamente por nombre
            adapter.addAll(dao.query());

//  Notificaciones para el adapter y actualize listview
            adapter.setNotifyOnChange(true);
            estudiantListview.setFastScrollEnabled(true);  //TODO:deseleccionar checbox
            estudiantListview.setAdapter(adapter);

        }
    }

    private OrmLiteBaseActivity1<DatabaseHelper> getOrmLiteBaseActivity() {
        Activity activity = getActivity();
        if (activity instanceof OrmLiteBaseActivity1)
            return (OrmLiteBaseActivity1<DatabaseHelper>) activity;
        return null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_eliminar_estudiante:
                String mensaje = "Esta seguro de eliminar Estudiante seleccionado?";
                confirmarAccion(item, mensaje);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void confirmarAccion(final MenuItem item, String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.ic_action_warning).setTitle("Confirmar operacion");
        builder.setMessage(mensaje);
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                eliminarEstudiante(item);
            }
        });
        builder.setNegativeButton("NO", null);
        builder.show();
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
