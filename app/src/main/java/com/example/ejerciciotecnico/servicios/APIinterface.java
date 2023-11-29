package com.example.ejerciciotecnico.servicios;

import com.example.ejerciciotecnico.entidades.ModeloArticulos;
import com.example.ejerciciotecnico.entidades.ModeloClases;
import com.example.ejerciciotecnico.entidades.ModeloDepartamentos;
import com.example.ejerciciotecnico.entidades.ModeloFamilias;
import com.example.ejerciciotecnico.entidades.RespuestaApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIinterface {

    @Headers("Content-Type: application/json")

    //OBTENER ARTICULO
    @GET("/articulos/{id}")
    Call<List<ModeloArticulos>>consultarArticulo(@Path("id") int id);

    //INSERTAR Y ACTUALIZAR ARTICULO
    @POST("/addArticulo")
    Call<ModeloArticulos> insertUpdateArticulo(@Body ModeloArticulos Articulo);

    //ELIMINAR UN ARTICULO
    @DELETE("/articulos/delete/{id}")
    Call<RespuestaApi>eliminarArticulo(@Path("id") int id);

    //OBTENER DEPARTAMENTOS
    @GET("/allDepartamentos")
    Call<List<ModeloDepartamentos>>getDepartamentos();

    //OBTENER CLASES
    @GET("/allClases")
    Call<List<ModeloClases>>getClases();

    //OBTENER FAMILIAS
    @GET("/allFamilias")
    Call<List<ModeloFamilias>>getFamilias();

    //OBTENER ARTICULOS
    @GET("/allArticulos")
    Call<List<ModeloArticulos>>getArticulos();
}