package com.example.coman.escuelaministerioteocratico;


import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import util.Estudiante;
import util.EstudianteReceiver;
import util.TextChangedListener;

public class AgregarEstudiantesFragment extends DialogFragment {

    private EditText txtNombre, txtApellido;
    private RadioButton rbtHombre;
    private Button btnguardar;

    private List<Estudiante> estudiante = new ArrayList<Estudiante>();


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
        rbtHombre = (RadioButton) v.findViewById(R.id.rbthombre);
        Button btncancelar = (Button) v.findViewById(R.id.btncancelaragregaest);
        btnguardar = (Button) v.findViewById(R.id.btnguardaragregaest);

        rbtHombre.setChecked(true);
        btnguardar.setEnabled(false);

        //<editor-fold desc="metodos para verificar texto">
        txtNombre.addTextChangedListener(new TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    if (txtApellido.length() == 0) {
                        btnguardar.setEnabled(false);
                    } else {
                        btnguardar.setEnabled(true);
                    }

                } else {
                    btnguardar.setEnabled(false);
                }
            }
        });

        txtApellido.addTextChangedListener(new TextChangedListener() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    if (txtNombre.length() == 0) {
                        btnguardar.setEnabled(false);
                    } else {
                        btnguardar.setEnabled(true);
                    }
                } else {
                    btnguardar.setEnabled(false);
                }
            }
        });
        //</editor-fold>

        //<editor-fold desc="onClickListener">
        btnguardar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        agregarEstudiante(
                                txtNombre.getText().toString(),
                                txtApellido.getText().toString(),
                                rbtHombre.isChecked()
                        );
                        String mesg = String.format("%s ha sido agregado a la lista", txtNombre.getText());
                        Toast.makeText(v.getContext(),mesg , Toast.LENGTH_SHORT).show();
//                        btnguardar.setEnabled(false);
//                        limpiarCampos(); TODO: ES NECESARIO?
                        dismiss();
                    }
                }
        );

        btncancelar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                }
        );
        //</editor-fold>

    }

    private void agregarEstudiante(String nombre, String apellido, boolean sexo) {
        Estudiante nuevo = new Estudiante(nombre, apellido, sexo);
        Intent intent = new Intent("listaestudiantes");
        intent.putExtra("operacion", EstudianteReceiver.ESTUDIANTE_AGREGADO);
        intent.putExtra("datos", nuevo);
        getActivity().sendBroadcast(intent);

    }

    private void limpiarCampos() {
        txtNombre.getText().clear();
        txtApellido.getText().clear();

        txtNombre.requestFocus();

    }

}
