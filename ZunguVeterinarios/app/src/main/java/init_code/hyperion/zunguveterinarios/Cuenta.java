package init_code.hyperion.zunguveterinarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Cuenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);
    }
    public void irSuscripcion(View view) {
        Intent i = new Intent(Cuenta.this, Cuenta_suscripcion.class);
        startActivity(i);
    }
    public void irCambiarContrasenia(View view) {
        Intent i = new Intent(Cuenta.this, Cambiar_contrasena.class);
        startActivity(i);
    }
    public void irEliminarCuenta(View view) {
        Intent i = new Intent(Cuenta.this, Eliminar_cuenta.class);
        startActivity(i);
    }
    public void irPoliticaPrivacidad(View view) {
        Intent i = new Intent(Cuenta.this, Politicas_de_privacidad.class);
        startActivity(i);
    }
    public void irContarleAmigo(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Promociona tu establecimiento, consigue más clientes con Zungu App");
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Zungu Veterinarios"));
    }

    public void goMenu(View v){
        Intent i = new Intent(Cuenta.this, Menu.class);
        startActivity(i);
    }

    public void goBack(View v){
        finish();
    }
}
