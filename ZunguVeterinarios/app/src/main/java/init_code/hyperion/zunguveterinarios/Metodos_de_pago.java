package init_code.hyperion.zunguveterinarios;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Metodos_de_pago extends AppCompatActivity {

    private String _url;
    public static final String idu = "idu";
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodos_de_pago);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }
    public void irPagoPaypal(View view) {
        Intent i = new Intent(Metodos_de_pago.this, Anuncios_paypal.class);
        startActivity(i);
    }
    public void irPagoTDC(View view) {
        Intent i = new Intent(Metodos_de_pago.this, Anuncios_tarjeta.class);
        startActivity(i);
    }
    public void irPagoSpei(View view) {
        Intent i = new Intent(Metodos_de_pago.this, Anuncios_spei.class);
        startActivity(i);
    }
}
