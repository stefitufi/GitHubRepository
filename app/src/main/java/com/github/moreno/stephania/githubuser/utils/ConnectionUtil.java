package com.github.moreno.stephania.githubuser.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Clase que contiene las validaciones de conexión
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class ConnectionUtil {

    /**
     * Método que válida si el dispositivo tiene conexión a internet
     * @param mContext
     * @return
     */
    public static String validateConnection(Context mContext)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                mContext.getSystemService(mContext.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        String mValida;
        if (networkInfo != null && networkInfo.isConnected())
        {
            mValida = ConstantUtil.ON_CONNECTION;

        } else {
            mValida = ConstantUtil.OFF_CONNECTION;
        }
        return mValida;
    }
}
