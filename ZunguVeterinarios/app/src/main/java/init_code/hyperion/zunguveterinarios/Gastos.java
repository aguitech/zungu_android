package init_code.hyperion.zunguveterinarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Gastos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos);
    }

    public void goMenu(View v){
        Intent i = new Intent(Gastos.this, Menu.class);
        startActivity(i);
    }

    public void goAgregar(View v){
        Intent i = new Intent(Gastos.this, Agregar_gastos.class);
        startActivity(i);
    }

    public void goBack(View v){
        finish();
    }
}
