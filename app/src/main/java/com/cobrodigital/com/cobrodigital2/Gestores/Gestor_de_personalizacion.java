package com.cobrodigital.com.cobrodigital2.Gestores;

import android.content.Context;
import android.database.Cursor;
import android.os.StrictMode;

import com.cobrodigital.com.cobrodigital2.Factory.pagadorFactory;
import com.cobrodigital.com.cobrodigital2.Model.Pagador;
import com.cobrodigital.com.cobrodigital2.Model.Personalizacion;
import com.cobrodigital.com.cobrodigital2.core.CobroDigital;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * Created by ariel on 20/10/16.
 */

public class Gestor_de_personalizacion {
        public static boolean set_estructura_clientes(Context context){
            try{
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                String[]campos=null;
                if(CobroDigital.webservice.webservice_pagador.consultar_estructura_pagadores()=="1"){
                    campos=new String[CobroDigital.webservice.obtener_datos().size()];
                    Vector<String> estructura=CobroDigital.webservice.obtener_datos();
                    int i=0;
                    for (String campo:estructura) {
                        campos[i]=campo;
                        i++;
                    }
                }
                Pagador pagador=new Pagador();
                pagador.set_campos_variables(campos);
                pagadorFactory factory=new pagadorFactory(context);
                factory.guardar(pagador);
                return true;

            }catch (Exception e){
                System.out.println("error");
                System.out.println(e.getStackTrace());
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.out.println(e);
                return false;
            }
        }
        public static String[] get_estructura_clientes(Context context) throws SQLException, ClassNotFoundException {
            Pagador pagador;
            pagadorFactory factory=new pagadorFactory(context);
            List<Pagador> pagadores=factory.select();
            if(pagadores.size()<1){
                return null;
            }
            System.out.println(pagadores.toString());
            pagador=pagadores.get(0);
            return pagador.get_campos_variables();
        }
        public static void obtener_estructura_clientes(Context context ){
            try {
                CobroDigital.personalizacion.estructura = get_estructura_clientes(context);
                System.out.println(CobroDigital.personalizacion.estructura);
            }catch (SQLException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
}
