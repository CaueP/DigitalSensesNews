package com.cauep.digitalsensesnews.utils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Classe genérica para criação de serviços para Retrofit
 * @author Cauê Garcia Polimanti
 * @version 1.0
 * Created on 5/27/2017.
 */
public class ServiceGenerator {
    private static final String BASE_URL = Constants.HTTP.API_URL;

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    // constrói o serviço do retrofit
    private static Retrofit retrofit = builder.build();

    private static HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();

    /**
     * Método utilizado para criar um novo serviço de acordo com a classe de interface recebida
     * @param serviceClass Classe de interface que deseja-se utilizar para criar um novo serviço
     * @param <S> Serviço criado para retorno
     * @return Retorna uma classe de serviço para consumo da API
     */
    public static <S> S createService(
            Class<S> serviceClass) {
        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }

        return retrofit.create(serviceClass);
    }
}
