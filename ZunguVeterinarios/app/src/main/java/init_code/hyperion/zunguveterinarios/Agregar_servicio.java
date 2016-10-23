package init_code.hyperion.zunguveterinarios;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Agregar_servicio extends AppCompatActivity {

    private EditText txtNombreServicio;
    private EditText txtCostoServicio;
    private EditText txtDuracionServicio;
    private EditText txtDescripcionServicio;
    private EditText txtCapacidadServicio;
    public Button btnAgregar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_servicio);

        /*
        EditText txtEmail = (EditText)findViewById(R.id.txtEmail);

        EditText txtNombreServicio = (EditText)findViewById(R.id.txtNombreServicio);
    */

        //EditText txtNombreServicio = (EditText)findViewById(R.id.txtNombreServicio);
        EditText txtNombreServicio = (EditText)findViewById(R.id.txtNombreServicio);
        /*
        EditText txtCostoServicio = (EditText)findViewById(R.id.txtCostoServicio);
        EditText txtDuracionServicio = (EditText)findViewById(R.id.txtDuracionServicio);
        EditText txtDescripcionServicio = (EditText)findViewById(R.id.txtDescripcionServicio);
        EditText txtCapacidadServicio = (EditText)findViewById(R.id.txtCapacidadServicio);
        */
        Button btnAgregar = (Button)findViewById(R.id.btnAgregar);

        /*
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, "Hola", duration);
        //Toast toast = Toast.makeText(context, txtNombreServicio.getText(), duration);
        toast.show();
        */

        //btnAgregar.setOnClickListener();




        btnAgregar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, "Hola2", duration);
                toast.show();
            }
        });



    }



    /*
    btnAgregar.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v){
            //Intent i = new Intent();
            //i.putExtra("Nombre", "Mi nombre es Hector");

            //i.setClass(MainActivity.this, PantallaActivity.class);
            //i.setClass(MainActivity.this, RegistroActivity.class);
            //i.setClass(RegistroActivity.this, BlogActivity.class);


            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, "Hola", duration);
            toast.show();

            //i.setClass(RegistroActivity.this, LoginActivity.class);
            //startActivity(i);
        }

    });
    */

}
