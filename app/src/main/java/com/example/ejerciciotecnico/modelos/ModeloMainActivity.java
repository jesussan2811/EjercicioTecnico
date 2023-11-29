package com.example.ejerciciotecnico.modelos;

import com.example.ejerciciotecnico.MainActivity;
import com.example.ejerciciotecnico.entidades.ModeloArticulos;
import com.example.ejerciciotecnico.entidades.ModeloClases;
import com.example.ejerciciotecnico.entidades.ModeloDepartamentos;
import com.example.ejerciciotecnico.entidades.ModeloFamilias;
import com.example.ejerciciotecnico.entidades.RespuestaApi;
import com.example.ejerciciotecnico.servicios.APIinterface;
import com.example.ejerciciotecnico.servicios.RetrifitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModeloMainActivity {
    MainActivity mainActivity;

    public ModeloMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void consultarArticulo(int id){
        APIinterface apiInterface = RetrifitInstance.getRetrofitInstance("http://192.168.100.9:4040/").create(APIinterface.class);
        Call<List<ModeloArticulos>> call = apiInterface.consultarArticulo(id);
        call.enqueue(new Callback<List<ModeloArticulos>>() {
            @Override
            public void onResponse(Call<List<ModeloArticulos>> call, Response<List<ModeloArticulos>> response) {
                List<ModeloArticulos> articulos = new ArrayList<>();
                articulos = (List<ModeloArticulos>) response.body();
                ModeloArticulos articulo = articulos.get(0);
                mainActivity.llenarArticulo(articulo);
            }

            @Override
            public void onFailure(Call<List<ModeloArticulos>> call, Throwable t) {
                System.out.println("fallo al consultar");
                System.out.println(t.getMessage());
            }
        });
    }

    public void insert_update_articulos(ModeloArticulos articulo){
        APIinterface apiInterface = RetrifitInstance.getRetrofitInstance("http://192.168.100.9:4040/").create(APIinterface.class);
        Call<ModeloArticulos> call = apiInterface.insertUpdateArticulo(articulo);
        call.enqueue(new Callback<ModeloArticulos>() {
            @Override
            public void onResponse(Call<ModeloArticulos> call, Response<ModeloArticulos> response) {
                ModeloArticulos respuesta = (ModeloArticulos) response.body();
                if(respuesta != null)
                    mainActivity.mostrarRespuesta("se ha dado de alta");
                else
                    mainActivity.mostrarRespuesta("no se dio de alta");

            }

            @Override
            public void onFailure(Call<ModeloArticulos> call, Throwable t) {
                System.out.println("fallo al Capturar");
            }
        });
    }

    public void eliminarArticulo(int id){
        APIinterface apiInterface = RetrifitInstance.getRetrofitInstance("http://192.168.100.9:4040/").create(APIinterface.class);
        Call<RespuestaApi> call = apiInterface.eliminarArticulo(id);
        call.enqueue(new Callback<RespuestaApi>() {
            @Override
            public void onResponse(Call<RespuestaApi> call, Response<RespuestaApi> response) {
                RespuestaApi respuesta = (RespuestaApi) response.body();
                mainActivity.respuesta(respuesta);
            }

            @Override
            public void onFailure(Call<RespuestaApi> call, Throwable t) {
                System.out.println("fallo al eliminar");
            }
        });
    }

    public void getDepartamentos(){
        APIinterface apiInterface = RetrifitInstance.getRetrofitInstance("http://192.168.100.9:4040/").create(APIinterface.class);
        Call<List<ModeloDepartamentos>> call = apiInterface.getDepartamentos();
        call.enqueue(new Callback<List<ModeloDepartamentos>>() {
            //Aqui se obtiene la respuesta del servicio
            @Override
            public void onResponse(Call<List<ModeloDepartamentos>> call, Response<List<ModeloDepartamentos>> response) {
                ArrayList<ModeloDepartamentos> departamentos = new ArrayList<>();
                departamentos = (ArrayList<ModeloDepartamentos>) response.body();
                //se manda a llamar metodo para mostrar los resultados del servicio
                mainActivity.obtenerDepartamentos(departamentos);
            }

            @Override
            public void onFailure(Call<List<ModeloDepartamentos>> call, Throwable t) {
                System.out.println("no funciono");
            }
        });
    }
    public void getClases(){
        APIinterface apiInterface = RetrifitInstance.getRetrofitInstance("http://192.168.100.9:4040/").create(APIinterface.class);
        Call<List<ModeloClases>> call = apiInterface.getClases();
        call.enqueue(new Callback<List<ModeloClases>>() {
            //Aqui se obtiene la respuesta del servicio
            @Override
            public void onResponse(Call<List<ModeloClases>> call, Response<List<ModeloClases>> response) {
                ArrayList<ModeloClases> clases = new ArrayList<>();
                clases = (ArrayList<ModeloClases>) response.body();
                //se manda a llamar metodo para mostrar los resultados del servicio
                mainActivity.obtenerClases(clases);
            }

            @Override
            public void onFailure(Call<List<ModeloClases>> call, Throwable t) {
                System.out.println("no funciono");
            }
        });
    }
    public void getFamilias(){
        APIinterface apiInterface = RetrifitInstance.getRetrofitInstance("http://192.168.100.9:4040/").create(APIinterface.class);
        Call<List<ModeloFamilias>> call = apiInterface.getFamilias();
        call.enqueue(new Callback<List<ModeloFamilias>>() {
            //Aqui se obtiene la respuesta del servicio
            @Override
            public void onResponse(Call<List<ModeloFamilias>> call, Response<List<ModeloFamilias>> response) {
                ArrayList<ModeloFamilias> familias = new ArrayList<>();
                familias = (ArrayList<ModeloFamilias>) response.body();
                //se manda a llamar metodo para mostrar los resultados del servicio
                mainActivity.obtenerFamilias(familias);
            }

            @Override
            public void onFailure(Call<List<ModeloFamilias>> call, Throwable t) {
                System.out.println("no funciono");
            }
        });
    }
    public void getArticulos(){
        APIinterface apiInterface = RetrifitInstance.getRetrofitInstance("http://192.168.100.9:4040/").create(APIinterface.class);
        Call<List<ModeloArticulos>> call = apiInterface.getArticulos();
        call.enqueue(new Callback<List<ModeloArticulos>>() {
            //Aqui se obtiene la respuesta del servicio
            @Override
            public void onResponse(Call<List<ModeloArticulos>> call, Response<List<ModeloArticulos>> response) {
                List<ModeloArticulos> articulos = new ArrayList<>();
                articulos = (List<ModeloArticulos>) response.body();
                //se manda a llamar metodo para mostrar los resultados del servicio
                mainActivity.obtenerArticulos(articulos);
            }

            @Override
            public void onFailure(Call<List<ModeloArticulos>> call, Throwable t) {
                System.out.println("no funciono");
                System.out.println(t.getMessage());
            }
        });
    }
}
