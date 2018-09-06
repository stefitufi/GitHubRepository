package com.github.moreno.stephania.githubuser.rest;

import com.github.moreno.stephania.githubuser.models.Items_;
import com.github.moreno.stephania.githubuser.utils.ConstantUtil;
import com.github.moreno.stephania.githubuser.utils.StringUtil;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.util.List;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Clase que realiza peticion al servidor
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class RestClientRepository {

    /**
     * Interfaz de tipo {@link RestClientRepository.ApiInterface}
     */
    private static RestClientRepository.ApiInterface mApiInterface;

    /**
     * Metodo que realiza peticion al web service
     * @return Informcion del usuario
     */
    public static RestClientRepository.ApiInterface getClientRepo() {

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
            mApiInterface = client.create(RestClientRepository.ApiInterface.class);

        }
        return mApiInterface;
    }

    /**
     * Interfaz que contiene parte de la url
     */
    public interface ApiInterface {
        @GET("/repos/{first}/{second}/pulls")
        Call<List<Items_>> getRepository(@Path("first") String first, @Path("second") String second);
    }
}
