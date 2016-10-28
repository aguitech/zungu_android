package init_code.hyperion.zunguveterinarios;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //int value = sharedpreferences.getInt("idu", 0);

        //TextView txtNombre = (TextView)findViewById(R.id.txtNombre);
        //txtNombre.setText(String.valueOf(value));
    }

    public void cerrar(View v){
        finish();
    }

    public void goHome(View v){
        Intent i = new Intent(Menu.this, Home.class);
        startActivity(i);
    }

    public void goPerfil(View v){
        Intent i = new Intent(Menu.this, Principal.class);
        startActivity(i);
    }

    public void goCuenta(View v){
        Intent i = new Intent(Menu.this, Cuenta.class);
        startActivity(i);
    }

    public void goTienda(View v){
        Intent i = new Intent(Menu.this, Tienda.class);
        startActivity(i);
    }

    public void goInventario(View v){
        Intent i = new Intent(Menu.this, Inventario.class);
        startActivity(i);
    }

    public void goEstadisticas(View v){
        Intent i = new Intent(Menu.this, Servicio.class);
        startActivity(i);
    }

    public void goAnuncios(View v){
        Intent i = new Intent(Menu.this, Anuncios.class);
        startActivity(i);
    }

    public void goGastos(View v){
        Intent i = new Intent(Menu.this, Gastos.class);
        startActivity(i);
    }

    public void goFormas(View v){
        Intent i = new Intent(Menu.this, Servicio.class);
        startActivity(i);
    }
}
