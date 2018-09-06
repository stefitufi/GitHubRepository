package com.github.moreno.stephania.githubuser.view.desing;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.github.moreno.stephania.githubuser.R;

/**
 * Actividad que contiene los contoles personalizados
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class ControlCustom {

    /**
     * Método para personalizar el control Toast
     * @param mContext
     *          Contexto de la actividad
     * @param mToastVw
     *          Toast cumtom
     * @param mMensaje
     *          Mensaje a mostrar en el toast
     */
    public static void showFullyCustomToast(Context mContext, View mToastVw, String mMensaje)
    {
        TextView text = mToastVw.findViewById(R.id.customToastText);
        text.setText(mMensaje);
        // Initiate the Toast instance.
        Toast toast = new Toast(mContext.getApplicationContext());
        // Set custom view in toast.
        toast.setView(mToastVw);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0,0);
        toast.show();
    }

}
