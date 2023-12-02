package com.example.ejerciciotecnico;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<ModeloDepartamentos> listaDepartamentos;
    ArrayList<ModeloClases> listaClases;
    ArrayList<ModeloFamilias> listaFamilias;
    List<ModeloArticulos> listaArticulos;
    ModeloArticulos miArticulo;
    ModeloArticulos unArticulo;

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
    Button btnBaja;
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
        btnBaja = (Button) findViewById(R.id.Baja);
        btnEliminar = (Button) findViewById(R.id.Eliminar);
        btnAgregar = (Button) findViewById(R.id.Agregar);

        modeloMainActivity = new ModeloMainActivity(this);

        listaDepartamentos = new ArrayList<>();
        listaClases = new ArrayList<>();
        listaFamilias = new ArrayList<>();

        modeloMainActivity.getArticulos();
        modeloMainActivity.getDepartamentos();
        modeloMainActivity.getClases();
        modeloMainActivity.getFamilias();

        /*ArrayAdapter<ModeloDepartamentos> adapterDepartamento = new ArrayAdapter<>(getApplicationContext(), androidx.constraintlayout.widget.R.layout
                .support_simple_spinner_dropdown_item, listaDepartamentos);
        ArrayAdapter<ModeloClases> adapterClase = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout
                .support_simple_spinner_dropdown_item, listaClases);
        ArrayAdapter<ModeloFamilias> adapterFamilia = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout
                .support_simple_spinner_dropdown_item, listaFamilias);

        spinDepartamento.setAdapter(adapterDepartamento);
        spinClase.setAdapter(adapterClase);
        spinFamilia.setAdapter(adapterFamilia);*/

        //miArticulo= new ModeloArticulos();
        listaArticulos = new ArrayList<ModeloArticulos>();

        bloquear_campos();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tsku = txtArticulo.getText().toString();
                int sku = Integer.parseInt(tsku);

                String articulo = txtArticulo.getText().toString();
                String marca = txtMarca.getText().toString();
                String modelo = txtModelo.getText().toString();
                String fechaAlta = txtFechaAlta.getText().toString();
                String fechaBaja = txtFechaBaja.getText().toString();

                String tstock =  txtStock.getText().toString();
                int stock =  Integer.parseInt(tstock);
                String tcantidad = txtCantidad.getText().toString();
                int cantidad = Integer.parseInt(tcantidad);

                int descontinuado = 0;
                if(cbDescontinuado.isChecked()) {
                    descontinuado = 1;
                }
                String tDepartamento = spinDepartamento.getSelectedItem().toString().substring(0,1);
                String tClase = spinClase.getSelectedItem().toString().substring(0,2);
                String tFamilia = spinFamilia.getSelectedItem().toString().substring(0,3);

                int departamento = Integer.parseInt(tDepartamento);
                int clase = Integer.parseInt(tClase);
                int familia = Integer.parseInt(tFamilia);

                if (comprobarDatos(sku,articulo,marca,modelo,tDepartamento,tClase,tFamilia,fechaAlta,tstock,tcantidad,descontinuado,fechaBaja)){
                    ModeloArticulos nvoArticulo = new ModeloArticulos(sku,articulo,marca,modelo,departamento,clase,familia,fechaAlta,stock,cantidad,descontinuado,fechaBaja);
                    Toast.makeText(getBaseContext(),"tu regisro esta correcto Felicidades",Toast.LENGTH_LONG).show();
                }
                modeloMainActivity.getArticulos();
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

                cbDescontinuado.setChecked(false);

                btnBuscar.setEnabled(true);
                btnBuscar.setBackgroundColor(Color.parseColor("#673AB7"));

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
                    btnBuscar.setEnabled(false);
                    btnBuscar.setBackgroundColor(Color.LTGRAY);

                    int skuBuscar = Integer.parseInt(txSku);

                    ArrayAdapter<String> adapterDepartamento = new ArrayAdapter<>(getApplicationContext(), android.R.layout
                            .simple_spinner_dropdown_item, obtenerArrayNombresDepa(listaDepartamentos));
                    ArrayAdapter<String> adapterClase = new ArrayAdapter<>(getApplicationContext(), android.R.layout
                            .simple_spinner_dropdown_item, obtenerArrayNombresClas(listaClases));
                    ArrayAdapter<String> adapterFamilia = new ArrayAdapter<>(getApplicationContext(), android.R.layout
                            .simple_spinner_dropdown_item, obtenerArrayNombresFam(listaFamilias));

                    spinDepartamento.setAdapter(adapterDepartamento);
                    spinClase.setAdapter(adapterClase);
                    spinFamilia.setAdapter(adapterFamilia);

                    //modeloMainActivity.getArticulos();
                    //System.out.println(listaArticulos.get(0).getSKU());
                    //System.out.println(miArticulo.getSKU());
                    if (buscarEnListaArticulos(skuBuscar))
                    {
                        System.out.println("el articulo ya existe");
                        txtSku.setText(unArticulo.getSKU()+"");
                        txtArticulo.setText(unArticulo.getARTICULO());
                        txtMarca.setText(unArticulo.getMARCA());
                        txtModelo.setText(unArticulo.getMODELO());
                        txtStock.setText(unArticulo.getSTOCK()+"");
                        txtCantidad.setText(unArticulo.getCANTIDAD()+"");
                        txtFechaAlta.setText(unArticulo.getFECHAALTA());
                        txtFechaBaja.setText(unArticulo.getFECHABAJA());

                        boolean descontinuado = false;
                        if (unArticulo.getDESCONTINUADO() == 1){descontinuado = true;}
                        cbDescontinuado.setChecked(descontinuado);

                        int positionDepa = posicionDepa(unArticulo.getDEPARTAMENTO());
                        spinDepartamento.setSelection(positionDepa);

                        int positionClas = posicionClas(unArticulo.getCLASE());
                        spinClase.setSelection(positionClas);

                        int positionFam = posicionFam(unArticulo.getFAMILIA());
                        spinFamilia.setSelection(positionFam);

                        desbloqueo_campos_encontrado();
                    }else{
                        System.out.println("el articulo no existe");
                        long ahora = System.currentTimeMillis();
                        Date fecha = new Date(ahora);
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        String salida = df.format(fecha);
                        txtFechaAlta.setText(salida);
                        txtFechaBaja.setText("1900-01-01");
                        desbloqueo_campos_noencontrado();
                    }
                    desbloquear_campos();
                }else{
                    Toast.makeText(getBaseContext(),"el Sku debe tener 6 digitos",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnBaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

        btnBaja.setEnabled(false);
        btnBaja.setBackgroundColor(Color.LTGRAY);

        btnEliminar.setEnabled(false);
        btnEliminar.setBackgroundColor(Color.LTGRAY);

        btnAgregar.setEnabled(false);
        btnAgregar.setBackgroundColor(Color.LTGRAY);
        btnAgregar.setText("UPLOAD");
    }

    public void desbloquear_campos() {
        txtArticulo.setFocusable(true);
        txtArticulo.setEnabled(true);
        txtArticulo.setCursorVisible(true);
        txtArticulo.setTextIsSelectable(true);
        txtArticulo.setBackgroundColor(Color.TRANSPARENT);

        txtMarca.setFocusable(true);
        txtMarca.setEnabled(true);
        txtMarca.setCursorVisible(true);
        txtMarca.setTextIsSelectable(true);
        txtMarca.setBackgroundColor(Color.TRANSPARENT);

        txtModelo.setFocusable(true);
        txtModelo.setEnabled(true);
        txtModelo.setCursorVisible(true);
        txtModelo.setTextIsSelectable(true);
        txtModelo.setBackgroundColor(Color.TRANSPARENT);

        txtStock.setFocusable(true);
        txtStock.setEnabled(true);
        txtStock.setCursorVisible(true);
        txtStock.setTextIsSelectable(true);
        txtStock.setBackgroundColor(Color.TRANSPARENT);

        txtCantidad.setFocusable(true);
        txtCantidad.setEnabled(true);
        txtCantidad.setCursorVisible(true);
        txtCantidad.setTextIsSelectable(true);
        txtCantidad.setBackgroundColor(Color.TRANSPARENT);

       /* txtFechaAlta.setFocusable(true);
        txtFechaAlta.setEnabled(true);
        txtFechaAlta.setCursorVisible(true);
        txtFechaAlta.setBackgroundColor(Color.TRANSPARENT);

        txtFechaBaja.setFocusable(true);
        txtFechaBaja.setEnabled(true);
        txtFechaBaja.setCursorVisible(true);
        txtFechaBaja.setBackgroundColor(Color.TRANSPARENT);*/

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

        btnBaja.setEnabled(true);
        btnBaja.setBackgroundColor(Color.parseColor("#673AB7"));

        btnEliminar.setEnabled(true);
        btnEliminar.setBackgroundColor(Color.parseColor("#673AB7"));

        btnAgregar.setEnabled(true);
        btnAgregar.setBackgroundColor(Color.parseColor("#673AB7"));
        btnAgregar.setText("EDIT");
    }
    public void desbloqueo_campos_noencontrado() {
        /*cbDescontinuado.setFocusable(true);
        cbDescontinuado.setEnabled(true);
        cbDescontinuado.setCursorVisible(true);
        cbDescontinuado.setBackgroundColor(Color.TRANSPARENT);*/

        btnAgregar.setEnabled(true);
        btnAgregar.setBackgroundColor(Color.parseColor("#673AB7"));
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
    public boolean buscarEnListaArticulos(int sku){
        int i = 0;
        int skuLista;

        do {
            skuLista = listaArticulos.get(i).getSKU();
            if (sku == skuLista) {
                unArticulo = listaArticulos.get(i);
                return true;
            }
            i++;
        }while (i < listaArticulos.size());

        return false;
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


    public ArrayList<String> obtenerArrayNombresDepa(ArrayList<ModeloDepartamentos> aux){
        ArrayList<String> arreglo = new ArrayList<>();
        for (int i = 0; i < aux.size(); i++) {
            arreglo.add(aux.get(i).getDEPARTAMENTOID() + ". " +
                    aux.get(i).getDEPARTAMENTONAME());
        }
        return arreglo;
    }
    public ArrayList<String> obtenerArrayNombresClas(ArrayList<ModeloClases> aux){
        ArrayList<String> arreglo = new ArrayList<>();
        for (int i = 0; i < aux.size(); i++) {
            arreglo.add(aux.get(i).getCLASEID() + ". " +
                    aux.get(i).getCLASENAME());
        }
        return arreglo;
    }
    public ArrayList<String> obtenerArrayNombresFam(ArrayList<ModeloFamilias> aux){
        ArrayList<String> arreglo = new ArrayList<>();
        for (int i = 0; i < aux.size(); i++) {
            arreglo.add(aux.get(i).getFAMILIAID() + ". " +
                    aux.get(i).getFAMILIANAME());
        }
        return arreglo;
    }
    public int posicionDepa(int id){
        int pos = 0;
        do
        {
            if (id == listaDepartamentos.get(pos).getDEPARTAMENTOID()){
                return pos;
            }
            pos++;
        }while(pos < listaDepartamentos.size());
        return pos;
    }
    public int posicionClas(int id){
        int pos = 0;
        do
        {
            if (id == listaClases.get(pos).getCLASEID()){
                return pos;
            }
            pos++;
        }while(pos < listaClases.size());
        return pos;
    }
    public int posicionFam(int id){
        int pos = 0;
        do
        {
            if (id == listaFamilias.get(pos).getFAMILIAID()){
                return pos;
            }
            pos++;
        }while(pos < listaFamilias.size());
        return pos;
    }
    public boolean comprobarDatos(int csku,String carticulo,String cmarca,String cmodelo,String cdepartamento,String cclase,String cfamilia,String cfechaAlta,String cstock,String ccantidad,int cdescontinuado,String cfechaBaja){
        if(csku < 100000){ Toast.makeText(getBaseContext(),"el Sku debe tener 6 digitos",Toast.LENGTH_LONG).show(); return false;}
        if(carticulo.length() < 1){mensajeTextoVacio(); return false;}
        if(cmarca.length() < 1){mensajeTextoVacio(); return false;}
        if(cmodelo.length() < 1){mensajeTextoVacio(); return false;}
        if(cdepartamento.length() < 1){Toast.makeText(getBaseContext(),"el departamento esta vacio",Toast.LENGTH_LONG).show(); return false;}
        if(cdepartamento.equals(cclase.substring(0,1))){Toast.makeText(getBaseContext(),"la clase no coincide con el departamento",Toast.LENGTH_LONG).show(); return false;}
        if(cclase.equals(cfamilia.substring(0,2))){Toast.makeText(getBaseContext(),"la familia no coincide con la clase",Toast.LENGTH_LONG).show(); return false;}
        if(cfechaAlta.length() < 1){Toast.makeText(getBaseContext(),"la fecha de alta se encuentra vacia",Toast.LENGTH_LONG).show(); return false;}
        if(cfechaBaja.length() < 1){Toast.makeText(getBaseContext(),"la fecha de baja esta vacia",Toast.LENGTH_LONG).show(); return false;}
        if(cstock.length() < 1){mensajeTextoVacio(); return false;}
        if(ccantidad.length() < 1){mensajeTextoVacio(); return false;}
        int cdstock=Integer.parseInt(cstock),cdcantidad=Integer.parseInt(ccantidad);
        if(cdstock < cdcantidad){Toast.makeText(getBaseContext(),"la cantidad no puede ser mayor al stock",Toast.LENGTH_LONG).show(); return false;}
        if(cdescontinuado > 1 || cdescontinuado < 0){Toast.makeText(getBaseContext(),"hay un error en el campo descontinuado",Toast.LENGTH_LONG).show(); return false;}

        return true;
    }
    public void mensajeTextoVacio(){
        Toast.makeText(getBaseContext(),"todos los campos de texto deben ser llenados",Toast.LENGTH_LONG).show();
    }
}