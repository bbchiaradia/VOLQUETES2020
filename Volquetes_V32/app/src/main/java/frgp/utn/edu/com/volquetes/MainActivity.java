package frgp.utn.edu.com.volquetes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import frgp.utn.edu.com.volquetes.conexion.DataMainActivityIniciarSesion;
import frgp.utn.edu.com.volquetes.entidad.Usuario;

public class MainActivity extends AppCompatActivity {
    Usuario usuario = new Usuario();
    TextView registrese;
    Button btn_iniciar_sesion;
    EditText nombreUsuario;
    EditText contraseniaUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_sesion);
        registrese = (TextView) findViewById(R.id.registrese);

        registrese.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent abrir = new Intent(MainActivity.this, registrarse.class);
                startActivity(abrir);

            }
        } );

        nombreUsuario = (EditText) findViewById(R.id.nombre_usuario);
        contraseniaUsuario = (EditText) findViewById(R.id.contrasenia_usuario);

            btn_iniciar_sesion = (Button)findViewById(R.id.btn_Iniciar);
            btn_iniciar_sesion.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    if(nombreUsuario.getText().toString().trim().isEmpty() || contraseniaUsuario.getText().toString().trim().isEmpty()){
                        Toast.makeText((getApplicationContext()), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                    }else{
                        new DataMainActivityIniciarSesion(MainActivity.this).execute(nombreUsuario.getText().toString(),contraseniaUsuario.getText().toString());
                    }
                }


            } );

    }
}
