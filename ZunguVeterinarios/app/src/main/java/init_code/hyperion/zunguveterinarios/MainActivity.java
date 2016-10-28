package init_code.hyperion.zunguveterinarios;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Intent i = new Intent(MainActivity.this, SlideUno.class);
                //Intent i = new Intent(MainActivity.this, Agregar_servicio.class);
                //Intent i = new Intent(MainActivity.this, Agregar_servicio.class);

                //Intent i = new Intent(MainActivity.this, Servicios.class);
                //Intent i = new Intent(MainActivity.this, Anuncios_spei.class);
                //Intent i = new Intent(MainActivity.this, Agregar_producto.class);
                //Intent i = new Intent(MainActivity.this, Editar_producto.class);
                //Intent i = new Intent(MainActivity.this, Cliente_solicitar_pago.class);
                //Intent i = new Intent(MainActivity.this, Cliente_perfil.class);
                //Intent i = new Intent(MainActivity.this, Registrar_mascota.class);
                //Intent i = new Intent(MainActivity.this, Agregar_ms_adopcion.class);
                //Intent i = new Intent(MainActivity.this, Editar_establecimiento.class);
                //Intent i = new Intent(MainActivity.this, Eliminar_cuenta.class);
                //Intent i = new Intent(MainActivity.this, Cambiar_contrasena.class);
                //Intent i = new Intent(MainActivity.this, Preview_establecimiento.class);
                Intent i = new Intent(MainActivity.this, Agregar_servicio.class);
                //Intent i = new Intent(MainActivity.this, Agregar_servicio.class);
                startActivity(i);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    public void onBackPressed(){

    }
}