package com.cobrodigital.com.cobrodigital2.Gestores;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cobrodigital.com.cobrodigital2.Modulos.Boletas.Boletas;
import com.cobrodigital.com.cobrodigital2.Pagador;
import com.cobrodigital.com.cobrodigital2.R;
import com.cobrodigital.com.cobrodigital2.Modulos.Transacciones.Transacciones;
import com.cobrodigital.com.cobrodigital2.core.Configuracion;

/**
 * Created by ariel on 16/01/17.
 */

public class Gestor_de_navegacion {
    Context context;
    public Gestor_de_navegacion(Context context){
        this.context=context;
    }
    public boolean navegar (int id){
        // Handle navigation view item clicks here.
        if (id == R.id.nav_loggin) {
            if (!Gestor_de_credenciales.esta_asociado()) {
                this.escanear(context);
            }
            else{
                Gestor_de_mensajes_usuario.mensaje("Usted ya esta asociado a una cuenta CobroDigital.",context);
            }
        } else if (id == R.id.nav_transacciones) {
            this.OnClickListarTransacciones(context);
        }  else if (id == R.id.nav_tools) {
            this.Configurar(context);

        } else if (id == R.id.nav_compartir) {

        }
        else if(id==R.id.nav_boleta){
            this.generar_boleta(context);
        }


        return true;
    }

    public static void OnClickListarTransacciones(Context context) {
        Intent transacciones = new Intent(context, Transacciones.class);
        context.startActivity(transacciones);
    }
    public static void escanear(Context context) {
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        ((AppCompatActivity)context).startActivityForResult(intent, 0);
    }
    public static void Configurar(Context context) {
        Intent configuracion = new Intent(context, Configuracion.class);
        context.startActivity(configuracion);
    }
    public void OnClickPagadorNew(View view) {
        Intent pagador = new Intent(context, Pagador.class);
        context.startActivity(pagador);
    }
    public static void generar_boleta(Context context){
        ((AppCompatActivity)context).startActivity(new Intent(context,Boletas.class));
    }

}