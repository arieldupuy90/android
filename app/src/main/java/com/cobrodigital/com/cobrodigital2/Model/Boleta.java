package com.cobrodigital.com.cobrodigital2.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ariel on 14/10/16.
 */

public class Boleta extends Model {
    private String id_tabla="Nro_boleta";
    private String nro_boleta;
    private String identificador;
    private String campo_a_buscar;
    private String concepto;
    private String fecha_1;
    private String importe_1;
    private String modelo;
    private String fecha_2;
    private String importe_2;
    private String fecha_3;
    private String importe_3;
    private String unique=ID_Tabla;

    public String getNro_boleta() {
        return nro_boleta;
    }

    public void setNro_boleta(String nro_boleta) {
        this.nro_boleta = nro_boleta;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getCampo_a_buscar() {
        return campo_a_buscar;
    }

    public void setCampo_a_buscar(String campo_a_buscar) {
        this.campo_a_buscar = campo_a_buscar;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getFecha_1() {
        return fecha_1;
    }

    public void setFecha_1(String fecha_1) {
        this.fecha_1 = fecha_1;
    }

    public String getImporte_1() {
        return importe_1;
    }

    public void setImporte_1(String importe_1) {
        this.importe_1 = importe_1;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFecha_2() {
        return fecha_2;
    }

    public void setFecha_2(String fecha_2) {
        this.fecha_2 = fecha_2;
    }

    public String getImporte_2() {
        return importe_2;
    }

    public void setImporte_2(String importe_2) {
        this.importe_2 = importe_2;
    }

    public String getFecha_3() {
        return fecha_3;
    }

    public void setFecha_3(String fecha_3) {
        this.fecha_3 = fecha_3;
    }

    public String getImporte_3() {
        return importe_3;
    }

    public void setImporte_3(String importe_3) {
        this.importe_3 = importe_3;
    }

    public String getid_tabla(){
        return this.id_tabla;
    }
    public int getId() {
        return Integer.parseInt(nro_boleta);
    }

    public void setId(String id) {
        this.nro_boleta= id;
    }
    public Boleta(Context contexto) {
        super(contexto);
    }
    public Boleta leerBoleta(JSONObject dato ){
        try{
            this.setId((String) dato.get("Nro_boleta"));
            this.setIdentificador((String) dato.get("identificador"));
            this.setCampo_a_buscar((String) dato.get("campo_a_buscar"));
            this.setConcepto((String) dato.get("concepto"));
            this.setFecha_1((String) dato.get("fecha_1"));
            this.setImporte_1((String) dato.get("importe_1"));
            this.setModelo((String) dato.get("modelo"));
            this.setFecha_2((String) dato.get("fecha_2"));
            this.setImporte_2((String) dato.get("importe_2"));
            this.setFecha_3((String) dato.get("fecha_3"));
            this.setImporte_3((String) dato.get("importe_3"));
        } catch (JSONException e1) {
            System.out.println(e1.getMessage());
            return null;
        }
        return this;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        super.onCreate(sqLiteDatabase);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        super.onUpgrade(sqLiteDatabase, i, i1);
    }

    @Override
    public String getunique() {
        return unique;
    }
}
