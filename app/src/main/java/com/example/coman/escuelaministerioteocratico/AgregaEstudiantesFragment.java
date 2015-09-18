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

import util.TextChangedListener;


public class AgregaEstudiantesFragment extends Fragment implements View.OnClickListener {

    private EditText txtNombre, txtApellido;
    private RadioGroup rdgSexo;
    private RadioButton rbtHombre, rbtMujer;
    private Button btnGuardar, btnCancelar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_agrega_estudiante, container, false);
        inicializarComponentesUI(rootView);
        return rootView;
    }

    private void inicializarComponentesUI(final View view) {
        txtNombre = (EditText) view.findViewById(R.id.etxtnombre);
        txtApellido = (EditText) view.findViewById(R.id.etxtapellido);
        rdgSexo = (RadioGroup) view.findViewById(R.id.rdgsexo);
        rbtHombre = (RadioButton) view.findViewById(R.id.rbthombre);
        rbtMujer = (RadioButton) view.findViewById(R.id.rbtmujer);

        btnCancelar = (Button) view.findViewById(R.id.btncancelar);

        //verifica que el campo txtNombre no este vacio
        txtNombre.addTextChangedListener(new TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence secuencia, int start, int before, int count) {
                Button btnGuardar = (Button) view.findViewById(R.id.btnguardar);
                btnGuardar.setEnabled(!secuencia.toString().trim().isEmpty());
            }
        });
    }

    @Override
    public void onClick(View view){
//        if(  view == btnGuardar ){
//            agregarEstudiante(
//                    txtNombre.getText().toString(),
//                    txtApellido.getText().toString(),
//                    rbtHombre.isChecked(),
//                    rbtMujer.isChecked();
//                    );
//            String mesg = String.format("%s ha sido agregado a la lista", txtNombre.getText());
//            makeText(this, mesg, Toast.LENGTH_LONG).show();
//            btnGuardar.setEnabled(false);
//            limpiarCampos();
//
//        }else if (view == btnCancelar){
////            TODO:faltan metodos para matar fragment
//            limpiarCampos();
//        }

    }

//    private void agregarEstudiante(String nombre, String apellido, boolean hombre, boolean mujer) {
////        TODO:Crear adaptador para sqlite
//    }

//    private void limpiarCampos() {
//        txtNombre.getText().clear();
//        txtApellido.getText().clear();
//        rdgSexo.clearCheck();
//
//        txtNombre.requestFocus();
//    }


}
