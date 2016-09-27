package com.cobrodigital.com.cobrodigital2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cobrodigital.com.cobrodigital2.Clases.Loading_screen;
import com.cobrodigital.com.cobrodigital2.Model.Credencial;
import com.cobrodigital.com.cobrodigital2.Services.serviceBoot;
import com.cobrodigital.com.cobrodigital2.core.CobroDigital;
import com.cobrodigital.com.cobrodigital2.core.Configuracion;
import com.cobrodigital.com.cobrodigital2.core.LectorQR;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        12);
            }
        }
        super.onCreate(savedInstanceState);
       this.ejecutar();
    }
    //clicks de botones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //al seleccionar esta opcion que valla a un menu de configuracion.
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            if (CobroDigital.credencial == null) {
              this.escanear();
            }
        } else if (id == R.id.nav_gallery) {
            this.OnClickListarTransacciones(findViewById(R.layout.activity_transacciones));
            return true;

        }  else if (id == R.id.nav_manage) {
            this.Configurar();

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void Configurar() {
        Intent configuracion = new Intent(this, Configuracion.class);
        startActivity(configuracion);

    }
    public void onClickEscanear(View view){
        this.escanear();
    }
    public void OnClickListarTransacciones(View View) {
        Intent transacciones = new Intent(getApplicationContext(), Transacciones.class);
        Loading_screen screen = new Loading_screen(MainActivity.this);
        screen.execute(transacciones);
    }
    private void escanear() {
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent, 0);
    }
    public void OnClickPagadorNew(View view) {
        Intent pagador = new Intent(getApplicationContext(), Pagador.class);
        startActivity(pagador);
    }
    private void ejecutar(){
        Credencial credencial= new Credencial(getApplicationContext());
        credencial.onCreate(credencial.getWritableDatabase());
        CobroDigital.credencial = credencial.obtenerCredencial();
        setContentView(R.layout.activity_main);
        if(CobroDigital.credencial!=null){
            credencial.set(); //revisar que no se hagan updates al cuete
            View cuenta=findViewById(R.id.textView7);
            ((ViewGroup) cuenta.getParent()).removeView(cuenta);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        serviceBoot sb=new serviceBoot(this.getApplicationContext());
        sb.startTimer();
    }
    @Override
    //capturo el resultado del scanner
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                LectorQR lectorQR=new LectorQR(getApplicationContext());
                try{
                  CobroDigital.credencial=lectorQR.leer(contents);
                }catch (JSONException e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }

                CobroDigital.credencial.set();

            } else if (resultCode == RESULT_CANCELED) {
                Toast toast = Toast.makeText(this, "La operación fué cancelada",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();
            }
        }
        ejecutar();
        Toast toast = Toast.makeText(getApplicationContext(),"Usuario loggeado correctamente.",Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ejecutar();
    }
    static public Context getContext(){
        MainActivity activity=new MainActivity();
        return activity.getBaseContext();
    }
}
