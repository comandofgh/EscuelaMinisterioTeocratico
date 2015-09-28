package util;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.coman.escuelaministerioteocratico.R;

import java.util.List;

public class EstudianteListAdapter extends ArrayAdapter<Estudiante>{

    private Activity ctx;

    public EstudianteListAdapter(Activity context, List<Estudiante> estudiantes){
        super(context, R.layout.listview_estudiante, estudiantes);
        this.ctx = context;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if( view == null)
            view = ctx.getLayoutInflater().inflate(R.layout.listview_estudiante, parent, false);
        Estudiante actual = this.getItem(position);
        inicializarCamposDeTexto(view, actual);
        return view;
    }

    private void inicializarCamposDeTexto(View view, Estudiante actual) {
        TextView textview = (TextView) view.findViewById(R.id.viewNombre);
        textview.setText(actual.getNombre());
        textview = (TextView) view.findViewById(R.id.viewApellido);
        textview.setText(actual.getApellido());

    }
}
