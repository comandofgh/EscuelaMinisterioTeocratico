package com.example.coman.escuelaministerioteocratico;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class AgregaEstudiantesFragment extends Fragment implements View.OnClickListener {

    private EditText txtnombre, txtapellido;
    private RadioGroup rdgsexo;
    private RadioButton rbthombre, rbtmujer;
    private Button btnagregar, btncancelar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View rootView = inflater.inflate(R.layout.fragment_agrega_estudiante, container, false);
        inicializarComponentesUI(rootView);
        return rootView;
    }

    private void inicializarComponentesUI(View view) {
        txtnombre = (EditText) view.findViewById(R.id.etxtnombre);
        txtapellido = (EditText) view.findViewById(R.id.etxtapellido);
        rdgsexo = (RadioGroup) view.findViewById(R.id.rdgsexo);
        rbthombre = (RadioButton) view.findViewById(R.id.rbthombre);
        rbtmujer = (RadioButton) view.findViewById(R.id.rbtmujer);
        btnagregar = (Button) view.findViewById(R.id.btnguardar);
        btncancelar = (Button) view.findViewById(R.id.btncancelar);

        //TODO:terminar de inicializar, metodo para comprobar entrada de texto en txtnombre

        limpiarCampos();
    }

    private void limpiarCampos() {

    }

    @Override
    public void onClick(View v) {

    }
}
