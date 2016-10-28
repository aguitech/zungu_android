package init_code.hyperion.zunguveterinarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Agregar_gastos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_gastos);
    }

    public void goMenu(View v){
        Intent i = new Intent(Agregar_gastos.this, Menu.class);
        startActivity(i);
    }

    public void goGastos(View v){
        Intent i = new Intent(Agregar_gastos.this, Gastos.class);
        startActivity(i);
    }

    public void goAgregar(View v){
        Intent i = new Intent(Agregar_gastos.this, Gastos.class);
        startActivity(i);
    }

    public void goCancelar(View v){
        Intent i = new Intent(Agregar_gastos.this, Gastos.class);
        startActivity(i);
    }

    public void goBack(View v){
        finish();
    }
}
