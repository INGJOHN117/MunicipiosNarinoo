package com.example.estudiante.municipiosnarino.datosAPI;

import com.example.estudiante.municipiosnarino.models.Municipio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by estudiante on 10/12/17.
 */

public interface SitesService {
    @GET("pfet-63uw.json")
    Call<List<Municipio>> obtenerlistadesitios();

}
