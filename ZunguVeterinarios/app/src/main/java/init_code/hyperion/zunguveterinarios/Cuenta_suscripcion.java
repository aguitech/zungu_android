package init_code.hyperion.zunguveterinarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Cuenta_suscripcion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta_suscripcion);
    }
    public void irAnterior(View view) {
        Intent i = new Intent(Cuenta_suscripcion.this, Cuenta.class);
        startActivity(i);
    }

    public void goMenu(View v){
        Intent i = new Intent(Cuenta_suscripcion.this, Menu.class);
        startActivity(i);
    }
}
