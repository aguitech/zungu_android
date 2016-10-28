package init_code.hyperion.zunguveterinarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Citas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas);
    }

    public void goMenu(View v){
        Intent i = new Intent(Citas.this, Menu.class);
        startActivity(i);
    }

    public void goAgregar(View v){
        Intent i = new Intent(Citas.this, Agregar_cita.class);
        startActivity(i);
    }

    public void goSolicitudes(View v){
        Intent i = new Intent(Citas.this, Citas_solicitudes.class);
        startActivity(i);
    }

    public void goCalendario(View v){
        Intent i = new Intent(Citas.this, Citas_calendario.class);
        startActivity(i);
    }

    public void goBack(View v){
        finish();
    }
}
