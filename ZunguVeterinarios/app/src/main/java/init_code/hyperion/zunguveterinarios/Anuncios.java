package init_code.hyperion.zunguveterinarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Anuncios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncios);
    }

    public void goMenu(View v){
        Intent i = new Intent(Anuncios.this, Menu.class);
        startActivity(i);
    }

    public void goActivos(View v){
        Intent i = new Intent(Anuncios.this, Anuncios_activos.class);
        startActivity(i);
    }

    public void goBack(View v){
        finish();
    }
}
