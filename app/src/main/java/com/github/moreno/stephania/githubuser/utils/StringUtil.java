package com.github.moreno.stephania.githubuser.utils;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;
import retrofit.Converter;

/**
 * Clase String para manejo de métodos
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class StringUtil implements Converter<String> {

    /**
     * Meotodo manejo de web service
     * @param body
     * @return
     * @throws IOException
     */
    @Override
    public String fromBody(ResponseBody body) throws IOException {
        return body.toString();
    }

    /**
     * Meotodo manejo de web service
     * @param value
     * @return
     */
    @Override
    public RequestBody toBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    /**
     * Metodo que convierte el primer caracter en mayuscula
     * @param word
     *          Palabra a acfectar
     * @return
     *          Palabra con primera letra en mayuscula
     */
    public static String capitalize(String word){
        return Character.toUpperCase(word.charAt(0)) + word.substring(1);
    }

    /**
     * Metodo que convierte el primer caracter en mayuscula
     * @param string
     *          Palabra a convertir
     * @return
     *          Palabra con primera letra en mayúscula
     */
    public static String firstCapitalize(String string) {
        if (string == null || string.isEmpty()) {
            return string;
        } else {
            return string.substring(0, 1).toUpperCase() + string.substring(1);
        }
    }
}
