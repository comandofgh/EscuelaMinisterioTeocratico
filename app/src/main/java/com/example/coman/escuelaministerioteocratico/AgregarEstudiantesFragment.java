package com.example.coman.escuelaministerioteocratico;


import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import util.TextChangedListener;

public class AgregarEstudiantesFragment extends DialogFragment {

    private EditText txtNombre, txtApellido;
    private RadioGroup rdgSexo;
    private RadioButton rbtHombre, rbtMujer;
    private Button cancelar, guardar;
    private boolean nombrewhatch;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_agrega_estudiante, null);
        builder.setView(v);

        inicializarComponentesUI(v);

        return builder.create();
    }

    private void inicializarComponentesUI(View v) {

        txtNombre = (EditText) v.findViewById(R.id.etxtnombre);
        txtApellido = (EditText) v.findViewById(R.id.etxtapellido);
        rdgSexo = (RadioGroup) v.findViewById(R.id.rdgsexo);
        rbtHombre = (RadioButton) v.findViewById(R.id.rbthombre);
        rbtMujer = (RadioButton) v.findViewById(R.id.rbtmujer);
        cancelar = (Button) v.findViewById(R.id.btncancelaragregaest);
        guardar = (Button) v.findViewById(R.id.btnguardaragregaest);

        rbtHombre.setChecked(true);
        guardar.setEnabled(false);

        //<editor-fold desc="metodos para verificar se escribio algo">
        txtNombre.addTextChangedListener(new TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    if (txtApellido.length() == 0) {
                        guardar.setEnabled(false);
                    } else {
                        guardar.setEnabled(true);
                    }

                } else {
                    guardar.setEnabled(false);
                }
            }
        });

        txtApellido.addTextChangedListener(new TextChangedListener(){

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    if (txtNombre.length() == 0){
                        guardar.setEnabled(false);
                    }else{
                        guardar.setEnabled(true);
                    }
                }else{
                    guardar.setEnabled(false);
                }
            }
        });
        //</editor-fold>

        cancelar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                }
        );

        guardar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        limpiarCampos();
                    }
                }
        );
    }

    private void limpiarCampos() {
        txtNombre.getText().clear();
        txtApellido.getText().clear();

        txtNombre.requestFocus();

    }

}
