package init_code.hyperion.zunguveterinarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Citas_solicitudes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_solicitudes);
    }

    public void goMenu(View v){
        Intent i = new Intent(Citas_solicitudes.this, Menu.class);
        startActivity(i);
    }

    public void goCitas(View v){
        Intent i = new Intent(Citas_solicitudes.this, Citas.class);
        startActivity(i);
    }

    public void goCalendario(View v){
        Intent i = new Intent(Citas_solicitudes.this, Citas_calendario.class);
        startActivity(i);
    }

    public void goBack(View v){
        finish();
    }
}
