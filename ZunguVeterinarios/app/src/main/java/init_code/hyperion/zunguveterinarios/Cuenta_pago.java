package init_code.hyperion.zunguveterinarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Cuenta_pago extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta_pago);
    }

    public void goMenu(View v){
        Intent i = new Intent(Cuenta_pago.this, Menu.class);
        startActivity(i);
    }

    public void goForma(View v){
        Intent i = new Intent(Cuenta_pago.this, Formas_de_pago.class);
        startActivity(i);
    }

    public void goEditar(View v){
        Intent i = new Intent(Cuenta_pago.this, Cuenta_activa.class);
        startActivity(i);
    }

    public void goBack(View v){
        finish();
    }
}
