 package com.example.estudiante.municipiosnarino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.estudiante.municipiosnarino.datosAPI.SitesService;
import com.example.estudiante.municipiosnarino.models.Municipio;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

 public class MainActivity extends AppCompatActivity {

     private Retrofit retrofit;
     public static  final String TAG = "datosColombia";

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         retrofit=new Retrofit.Builder()
                 .baseUrl("https://www.datos.gov.co/resource/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();

         obtenerdatos();

     }

     public void obtenerdatos() {

         SitesService servicio = retrofit.create(SitesService.class);
         Call<List<Municipio>> sitiorespuestacall = servicio.obtenerlistadesitios();
         sitiorespuestacall.enqueue(new Callback<List<Municipio>>() {
             @Override
             public void onResponse(Call<List<Municipio>> call, Response<List<Municipio>> response) {
                 if (response.isSuccessful()) { //SI LLEGAN DATOS
                     List lista = response.body();
                     for (int i = 0; i < lista.size(); i++) {
                         Municipio m = (Municipio) lista.get(i);
                         Log.i(TAG, "Nombre: " + m.getNombre_municipio() + " Alcade: " + m.getNombre_alcalde());
                     }
                 } else {
                     Log.e(TAG, "OnResponse: " + response.errorBody());
                 }
             }

             @Override
             public void onFailure(Call<List<Municipio>> call, Throwable t) {

             }
         });

     }}
