package com.cobrodigital.com.cobrodigital2.Gestores;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by ariel on 14/10/16.
 */

public class Gestor_de_mensajes_usuario extends Toast {
    public static Context context=null;
    public Gestor_de_mensajes_usuario(Context context) {
        super(context);
        this.context = context;
    }
    public static void mensaje(String mensaje, Context context){
        Toast toast = Toast.makeText(context,mensaje,Toast.LENGTH_SHORT);
        toast.show();
    }
    public static void mensaje(String mensaje){
        Toast toast = Toast.makeText(context,mensaje,Toast.LENGTH_SHORT);
        toast.show();
    }

}