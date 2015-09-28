package util;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class EstudianteReceiver extends BroadcastReceiver {
    public static final int ESTUDIANTE_AGREGADO = 1;
    public static final int ESTUDIANTE_ELIMINADO = 2;
    public static final int ESTUDIANTE_ACTUALIZADO = 3;

    private final ArrayAdapter<Estudiante> adapter;

    public EstudianteReceiver(ArrayAdapter<Estudiante> adapter) {
        this.adapter = adapter;
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
        adapter.add(estudiante);
    }

    private void eliminarEstudiante(Intent intent) {
        ArrayList<Estudiante> lista = (ArrayList<Estudiante>) intent.getSerializableExtra("datos");
        for (Estudiante c : lista) adapter.remove(c);

    }

    private void actualizarEstudiante(Intent intent) {
        Estudiante estudiante = (Estudiante) intent.getSerializableExtra("datos");
        int posicion = adapter.getPosition(estudiante);
        adapter.insert(estudiante, posicion);

    }
}
