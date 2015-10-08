package com.example.primera.pruebapelota;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    FrameLayout miPanel;
    ImageView miPelota;
    Button btnInicio;
    Button btnParar;
    // Mis variables
    Timer timer;
    int x;
    int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        miPanel = (FrameLayout)findViewById(R.id.panelJuego);
        miPelota = (ImageView)findViewById(R.id.miPelota);
        btnInicio = (Button)findViewById(R.id.btnIniciar);
        btnParar = (Button)findViewById(R.id.btnParar);
        //
        Inicializador();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void Inicializador() {
        x = 3;
        y = 2;
        miPelota.setX(0);
        miPelota.setY(0);
        btnParar.setEnabled(false);
        btnInicio.setEnabled(true);
    }

    // Manejador del evento Click INICIO
    public void InicioJuego(View v){
        x = 3;
        y = 2;
        btnInicio.setEnabled(false);
        btnParar.setEnabled(true);
        MoverPelota(this);
    }

    // Manejador del evento Click PARAR
    public void PararJuego(View v){
        Inicializador(); // Reestablece valores iniciales
        timer.cancel(); // Para el temporizador
    }

    // Mueve la pelota cada X segundos
    public void MoverPelota(final Activity act){
        //Timer cada 0.01 segundo
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                act.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Este código siempre corre en el hilo UI, por lo tanto es seguro modificar elementos UI.
                        Movimiento();
                    }
                });
            }
        }, 0, 10);
    }

    private void Movimiento() {
        // Lados
        if(miPelota.getX() < 0 || (miPelota.getX()+miPelota.getWidth()) > miPanel.getWidth()){
            x = -x;
        }
        // Arriba y abajo
        if (miPelota.getY() < 0 || (miPelota.getY()+miPelota.getHeight()) > miPanel.getHeight()){
            y = -y;
        }

        // Suma x e y a la posición atual de la pelota
        miPelota.setX(miPelota.getX() + x);
        miPelota.setY(miPelota.getY() + y);
    }

}
