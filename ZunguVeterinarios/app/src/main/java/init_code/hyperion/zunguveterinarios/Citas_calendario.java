package init_code.hyperion.zunguveterinarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Citas_calendario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_calendario);
    }

    public void goMenu(View v){
        Intent i = new Intent(Citas_calendario.this, Menu.class);
        startActivity(i);
    }

    public void goCitas(View v){
        Intent i = new Intent(Citas_calendario.this, Citas.class);
        startActivity(i);
    }

    public void goBack(View v){
        finish();
    }
}
