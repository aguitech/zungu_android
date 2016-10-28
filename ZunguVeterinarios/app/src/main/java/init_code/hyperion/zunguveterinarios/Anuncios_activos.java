package init_code.hyperion.zunguveterinarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Anuncios_activos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncios_activos);
    }

    public void goMenu(View v){
        Intent i = new Intent(Anuncios_activos.this, Menu.class);
        startActivity(i);
    }

    public void goPromocionar(View v){
        Intent i = new Intent(Anuncios_activos.this, Anuncios.class);
        startActivity(i);
    }

    public void goBack(View v){
        finish();
    }
}
