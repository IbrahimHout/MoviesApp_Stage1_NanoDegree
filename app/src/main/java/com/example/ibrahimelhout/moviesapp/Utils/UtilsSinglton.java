package com.example.ibrahimelhout.moviesapp.Utils;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UtilsSinglton {


    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }

}
