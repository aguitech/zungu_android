package init_code.hyperion.zunguveterinarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Politicas_de_privacidad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politicas_de_privacidad);
    }

    public void irAnterior(View view) {
        Intent i = new Intent(Politicas_de_privacidad.this, Cuenta.class);
        startActivity(i);
    }

    public void goMenu(View v){
        Intent i = new Intent(Politicas_de_privacidad.this, Menu.class);
        startActivity(i);
    }
}
