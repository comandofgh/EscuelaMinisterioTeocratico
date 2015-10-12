package util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;

public class EstudianteReceiver extends BroadcastReceiver {
    public static final int ESTUDIANTE_AGREGADO = 1;
    public static final int ESTUDIANTE_ELIMINADO = 2;
    public static final int ESTUDIANTE_ACTUALIZADO = 3;

    private final OrmLiteBaseActivity1<DatabaseHelper> activity;
    private final ArrayAdapter<Estudiante> adapter;

    public EstudianteReceiver(ArrayAdapter<Estudiante> adapter, OrmLiteBaseActivity1<DatabaseHelper> activity) {
        this.adapter = adapter;
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int operacion = intent.getIntExtra("operacion", -1);
        switch (operacion){
            case ESTUDIANTE_AGREGADO: agregarEstudiante(intent); break;
            case ESTUDIANTE_ELIMINADO: eliminarEstudiante(intent); break;
            case ESTUDIANTE_ACTUALIZADO: actualizarEstudiante(intent); break;
        }

    }

    private void agregarEstudiante(Intent intent) {
        Estudiante estudiante =(Estudiante) intent.getSerializableExtra("datos");
        if (activity != null) {
            DatabaseHelper helper = activity.getHelper();
            RuntimeExceptionDao<Estudiante, Integer> dao = helper.getEstudianteRuntimeDAO();
            dao.create(estudiante);
        }
        adapter.add(estudiante);
    }

    private void eliminarEstudiante(Intent intent) {
        ArrayList<Estudiante> lista = (ArrayList<Estudiante>) intent.getSerializableExtra("datos");
        if (activity != null) {
            DatabaseHelper helper = activity.getHelper();
            RuntimeExceptionDao<Estudiante, Integer> dao = helper.getEstudianteRuntimeDAO();
            dao.delete(lista);
        }
        for (Estudiante c : lista) {
            adapter.remove(c);
        }
    }

    private void actualizarEstudiante(Intent intent) {
        Estudiante estudiante = (Estudiante) intent.getSerializableExtra("datos");
        if (activity != null) {
            DatabaseHelper helper = activity.getHelper();
            RuntimeExceptionDao<Estudiante, Integer> dao = helper.getEstudianteRuntimeDAO();
            dao.update(estudiante);
        }
        int posicion = adapter.getPosition(estudiante);
        adapter.insert(estudiante, posicion);
    }
}
