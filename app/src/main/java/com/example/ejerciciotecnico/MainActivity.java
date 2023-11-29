package com.example.ejerciciotecnico;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ejerciciotecnico.entidades.ModeloArticulos;
import com.example.ejerciciotecnico.entidades.ModeloClases;
import com.example.ejerciciotecnico.entidades.ModeloDepartamentos;
import com.example.ejerciciotecnico.entidades.ModeloFamilias;
import com.example.ejerciciotecnico.entidades.RespuestaApi;
import com.example.ejerciciotecnico.modelos.ModeloMainActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<ModeloDepartamentos> listaDepartamentos;
    ArrayList<ModeloClases> listaClases;
    ArrayList<ModeloFamilias> listaFamilias;
    List<ModeloArticulos> listaArticulos;
    ModeloArticulos miArticulo;

    EditText txtSku;
    EditText txtArticulo;
    EditText txtMarca;
    EditText txtModelo;
    EditText txtStock;
    EditText txtCantidad;
    EditText txtFechaAlta;
    EditText txtFechaBaja;

    CheckBox cbDescontinuado;

    Spinner spinDepartamento;
    Spinner spinClase;
    Spinner spinFamilia;

    Button btnBuscar;
    Button btnLimpiar;
    Button btnEditar;
    Button btnEliminar;
    Button btnAgregar;

    ModeloMainActivity modeloMainActivity;

    //private KeyListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSku = (EditText) findViewById(R.id.EditText_Sku);
        txtArticulo = (EditText) findViewById(R.id.EditText_Articulo);
        txtMarca = (EditText) findViewById(R.id.EditText_Marca);
        txtModelo = (EditText) findViewById(R.id.EditText_Modelo);
        txtStock = (EditText) findViewById(R.id.EditText_Stock);
        txtCantidad = (EditText) findViewById(R.id.EditText_Cantidad);
        txtFechaAlta = (EditText) findViewById(R.id.EditText_FechaAlta);
        txtFechaBaja = (EditText) findViewById(R.id.EditText_FechaBaja);

        cbDescontinuado = (CheckBox) findViewById(R.id.checkbox_Descontinuado);

        spinDepartamento = (Spinner) findViewById(R.id.spinner_Departamento);
        spinClase = (Spinner) findViewById(R.id.spinner_Clase);
        spinFamilia = (Spinner) findViewById(R.id.spinner_Familia);

        btnBuscar = (Button) findViewById(R.id.Buscar);
        btnLimpiar = (Button) findViewById(R.id.Limpiar);
        btnEditar = (Button) findViewById(R.id.Editar);
        btnEliminar = (Button) findViewById(R.id.Eliminar);
        btnAgregar = (Button) findViewById(R.id.Agregar);

        modeloMainActivity = new ModeloMainActivity(this);

        modeloMainActivity.getArticulos();

        miArticulo= new ModeloArticulos();
        listaArticulos = new ArrayList<ModeloArticulos>();

        bloquear_campos();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sku = txtSku.getText().toString();
                String articulo = txtArticulo.getText().toString();
                String marca = txtMarca.getText().toString();
                String modelo = txtModelo.getText().toString();
                String stock =  txtStock.getText().toString();
                String cantidad = txtCantidad.getText().toString();
                String fechaAlta = txtFechaAlta.getText().toString();
                String fechaBaja = txtFechaBaja.getText().toString();
                //String descontinuado = cbDescontinuado.get

            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtSku.setText("");
                txtArticulo.setText("");
                txtMarca.setText("");
                txtModelo.setText("");
                txtStock.setText("");
                txtCantidad.setText("");
                txtFechaAlta.setText("");
                txtFechaBaja.setText("");

                bloquear_campos();
            }
        });
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txSku = txtSku.getText().toString();
                System.out.println(txSku);
                if(txSku.length() == 6)
                {
                    int skuBuscar = Integer.parseInt(txSku);
                    modeloMainActivity.consultarArticulo(skuBuscar);
                    //modeloMainActivity.getArticulos();
                    //System.out.println(listaArticulos.get(0).getSKU());
                    //System.out.println(miArticulo.getSKU());
                    if (skuBuscar == miArticulo.getSKU())
                    {
                        System.out.println("el articulo ya existe");
                        txtSku.setText(miArticulo.getSKU());
                        txtArticulo.setText(miArticulo.getARTICULO());
                        txtMarca.setText(miArticulo.getMARCA());
                        txtModelo.setText(miArticulo.getMODELO());
                        txtStock.setText(miArticulo.getSTOCK());
                        txtCantidad.setText(miArticulo.getCANTIDAD());
                        txtFechaAlta.setText(miArticulo.getFECHAALTA());
                        txtFechaBaja.setText(miArticulo.getFECHABAJA());
                        desbloqueo_campos_encontrado();
                    }else{
                        System.out.println("el articulo no existe");
                        desbloqueo_campos_noencontrado();
                    }
                    desbloquear_campos();
                }else{
                    Toast.makeText(getBaseContext(),"el Sku debe tener 6 digitos",Toast.LENGTH_LONG).show();
                }
            }
        });



    }
    public void bloquear_campos(){
        txtArticulo.setFocusable(false);
        txtArticulo.setEnabled(false);
        txtArticulo.setCursorVisible(false);
        txtArticulo.setBackgroundColor(Color.LTGRAY);

        txtMarca.setFocusable(false);
        txtMarca.setEnabled(false);
        txtMarca.setCursorVisible(false);
        txtMarca.setBackgroundColor(Color.LTGRAY);

        txtModelo.setFocusable(false);
        txtModelo.setEnabled(false);
        txtModelo.setCursorVisible(false);
        txtModelo.setBackgroundColor(Color.LTGRAY);

        txtStock.setFocusable(false);
        txtStock.setEnabled(false);
        txtStock.setCursorVisible(false);
        txtStock.setBackgroundColor(Color.LTGRAY);

        txtCantidad.setFocusable(false);
        txtCantidad.setEnabled(false);
        txtCantidad.setCursorVisible(false);
        txtCantidad.setBackgroundColor(Color.LTGRAY);

        txtFechaAlta.setFocusable(false);
        txtFechaAlta.setEnabled(false);
        txtFechaAlta.setCursorVisible(false);
        txtFechaAlta.setBackgroundColor(Color.LTGRAY);

        txtFechaBaja.setFocusable(false);
        txtFechaBaja.setEnabled(false);
        txtFechaBaja.setCursorVisible(false);
        txtFechaBaja.setBackgroundColor(Color.LTGRAY);

        cbDescontinuado.setFocusable(false);
        cbDescontinuado.setEnabled(false);
        cbDescontinuado.setCursorVisible(false);
        cbDescontinuado.setBackgroundColor(Color.LTGRAY);

        spinDepartamento.setFocusable(false);
        spinDepartamento.setEnabled(false);

        spinClase.setFocusable(false);
        spinClase.setEnabled(false);

        spinFamilia.setFocusable(false);
        spinFamilia.setEnabled(false);

        btnEditar.setEnabled(false);
        btnEditar.setBackgroundColor(Color.LTGRAY);

        btnEliminar.setEnabled(false);
        btnEliminar.setBackgroundColor(Color.LTGRAY);

        btnAgregar.setEnabled(false);
        btnAgregar.setBackgroundColor(Color.LTGRAY);
    }

    public void desbloquear_campos() {
        txtArticulo.setFocusable(true);
        txtArticulo.setEnabled(true);
        txtArticulo.setCursorVisible(true);
        txtArticulo.setBackgroundColor(Color.TRANSPARENT);

        txtMarca.setFocusable(true);
        txtMarca.setEnabled(true);
        txtMarca.setCursorVisible(true);
        txtMarca.setBackgroundColor(Color.TRANSPARENT);

        txtModelo.setFocusable(true);
        txtModelo.setEnabled(true);
        txtModelo.setCursorVisible(true);
        txtModelo.setBackgroundColor(Color.TRANSPARENT);

        txtStock.setFocusable(true);
        txtStock.setEnabled(true);
        txtStock.setCursorVisible(true);
        txtStock.setBackgroundColor(Color.TRANSPARENT);

        txtCantidad.setFocusable(true);
        txtCantidad.setEnabled(true);
        txtCantidad.setCursorVisible(true);
        txtCantidad.setBackgroundColor(Color.TRANSPARENT);

        txtFechaAlta.setFocusable(true);
        txtFechaAlta.setEnabled(true);
        txtFechaAlta.setCursorVisible(true);
        txtFechaAlta.setBackgroundColor(Color.TRANSPARENT);

        txtFechaBaja.setFocusable(true);
        txtFechaBaja.setEnabled(true);
        txtFechaBaja.setCursorVisible(true);
        txtFechaBaja.setBackgroundColor(Color.TRANSPARENT);

        /*cbDescontinuado.setFocusable(true);
        cbDescontinuado.setEnabled(true);
        cbDescontinuado.setCursorVisible(true);
        cbDescontinuado.setBackgroundColor(Color.TRANSPARENT);*/

        spinDepartamento.setFocusable(true);
        spinDepartamento.setEnabled(true);

        spinClase.setFocusable(true);
        spinClase.setEnabled(true);

        spinFamilia.setFocusable(true);
        spinFamilia.setEnabled(true);
    }
    public void desbloqueo_campos_encontrado() {
        cbDescontinuado.setFocusable(true);
        cbDescontinuado.setEnabled(true);
        cbDescontinuado.setCursorVisible(true);
        cbDescontinuado.setBackgroundColor(Color.TRANSPARENT);

        btnEditar.setEnabled(true);
        btnEditar.setBackgroundColor(Color.parseColor("#9C27B0"));

        btnEliminar.setEnabled(true);
        btnEliminar.setBackgroundColor(Color.parseColor("#9C27B0"));

    }
    public void desbloqueo_campos_noencontrado() {
        cbDescontinuado.setFocusable(true);
        cbDescontinuado.setEnabled(true);
        cbDescontinuado.setCursorVisible(true);
        cbDescontinuado.setBackgroundColor(Color.TRANSPARENT);

        btnAgregar.setEnabled(true);
        btnAgregar.setBackgroundColor(Color.parseColor("#9C27B0"));
    }

    public void obtenerDepartamentos(ArrayList<ModeloDepartamentos> departamentos){
        listaDepartamentos = departamentos;
    }
    public void obtenerClases(ArrayList<ModeloClases> clases){
        listaClases = clases;
    }
    public void obtenerFamilias(ArrayList<ModeloFamilias> familias){
        listaFamilias = familias;
    }
    public void obtenerArticulos(List<ModeloArticulos> articulos){
        listaArticulos = articulos;
        System.out.println(listaArticulos.get(0).getARTICULO());
    }
    public void llenarArticulo(ModeloArticulos articulo){
        miArticulo = articulo;
    }
    public void mostrarRespuesta(String respuesta){
        Toast.makeText(this,respuesta,Toast.LENGTH_LONG).show();
    }
    public void respuesta(RespuestaApi respuestaApi){
        Toast.makeText(this,respuestaApi.mensaje,Toast.LENGTH_LONG).show();
    }
}