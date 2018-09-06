package com.github.moreno.stephania.githubuser.rest;

import com.github.moreno.stephania.githubuser.models.Component;
import com.github.moreno.stephania.githubuser.utils.ConstantUtil;
import com.github.moreno.stephania.githubuser.utils.StringUtil;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import java.io.IOException;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

/**
 * Clase que realiza peticion al servidor
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class RestClient {

    /**
     * Interfaz de tipo {@link ApiInterface}
     */
    private static ApiInterface mApiInterface;

    /**
     * Metodo que realiza peticion al web service
     * @return Informcion del usuario
     */
    public static ApiInterface getClient() {

        if(mApiInterface == null){

            OkHttpClient okClient = new OkHttpClient();
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(ConstantUtil.BASEURL)
                    .addConverter(String.class, new StringUtil())
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mApiInterface = client.create(ApiInterface.class);

        }
        return mApiInterface;
    }

    /**
     * Interfaz que contiene parte de la url
     */
    public interface ApiInterface {

        @Headers("User-Agent: Retrofit2.0Tutorial-App")
        @GET("/search/repositories")
        Call<Component> getUsersNamedTom(@Query("q") String name);
    }

}

