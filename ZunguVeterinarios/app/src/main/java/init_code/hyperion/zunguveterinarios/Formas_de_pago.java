package init_code.hyperion.zunguveterinarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Formas_de_pago extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formas_de_pago);
    }

    public void goMenu(View v){
        Intent i = new Intent(Formas_de_pago.this, Menu.class);
        startActivity(i);
    }

    public void goCuenta(View v){
        Intent i = new Intent(Formas_de_pago.this, Cuenta_pago.class);
        startActivity(i);
    }

    public void goTarjeta(View v){
        Intent i = new Intent(Formas_de_pago.this, Anuncios_tarjeta.class);
        startActivity(i);
    }

    public void goPay(View v){
        Intent i = new Intent(Formas_de_pago.this, Anuncios_paypal.class);
        startActivity(i);
    }

    public void goSpei(View v){
        Intent i = new Intent(Formas_de_pago.this, Anuncios_spei.class);
        startActivity(i);
    }

    public void goBack(View v){
        finish();
    }
}
