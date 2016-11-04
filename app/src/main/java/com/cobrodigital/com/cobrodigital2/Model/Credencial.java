package com.cobrodigital.com.cobrodigital2.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.cobrodigital.com.cobrodigital2.Factory.credencialFactory;
import com.cobrodigital.com.cobrodigital2.Gestores.Gestor_de_cifrado;
import com.cobrodigital.com.cobrodigital2.Gestores.Gestor_de_personalizacion;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by Ariel on 6/09/16.
 */
@DatabaseTable(tableName = "Credencial")
public class Credencial {
    private static boolean PRUEBA=false;
    @DatabaseField(id=true,index = true)
    private int Id_credencial;
    @DatabaseField(unique = true,dataType = DataType.BYTE_ARRAY)
    private byte[] sid;
    @DatabaseField(unique = true,dataType = DataType.BYTE_ARRAY)
    private byte[] IdComercio;
    protected Context context;
    public Credencial(String IdComercio,String sid,Context context) {
        set_IdComercio(IdComercio);
        set_sid(sid);
    }
    public Credencial(){}
    public Credencial(Context context){
        this.context=context;
    }
    public int getId_credencial() {
        return Id_credencial;
    }
    public void setId_credencial(int id_credencial) {
        Id_credencial = id_credencial;
    }
    public void set_IdComercio(String IdComercio){
        Gestor_de_cifrado cifrado=new Gestor_de_cifrado();
        try {
            this.IdComercio=cifrado.cifra(IdComercio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String get_IdComercio() throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException {
        Gestor_de_cifrado cifrado=new Gestor_de_cifrado();
        return cifrado.descifra(this.IdComercio);
    }
    public void set_sid(String sid){
        Gestor_de_cifrado cifrado=new Gestor_de_cifrado();
        try {
            this.sid=cifrado.cifra(sid);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public String get_sid() throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException {
        Gestor_de_cifrado cifrado=new Gestor_de_cifrado();
        return cifrado.descifra(sid);
    }

    public Credencial obtenerCredencial() throws SQLException, ClassNotFoundException, NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        credencialFactory factory=new credencialFactory(context);
        List<Credencial> Credenciales=(List<Credencial>)factory.select();
        if(Credenciales.size()>0)
            for (Credencial credencial:Credenciales) {
                this.setId_credencial(credencial.getId_credencial());
                this.set_IdComercio(credencial.get_IdComercio());
                this.set_sid((credencial.get_sid()));

            }
        if(PRUEBA){
            this.setId_credencial(1);
            this.set_IdComercio("FL662997");
            this.set_sid("ABZ0ya68K791phuu76gQ5L662J6F2Y4j7zqE2Jxa3Mvd22TWNn4iip6L9yq");
        }
        if(this.IdComercio==null || this.sid==null)
            return null;
        return this;
    }
    public boolean BorrarCredencial() throws SQLException, ClassNotFoundException {
        credencialFactory factory=new credencialFactory(this.context);
        if(factory.delete(this))
            return true;
        return false;
    }

}
