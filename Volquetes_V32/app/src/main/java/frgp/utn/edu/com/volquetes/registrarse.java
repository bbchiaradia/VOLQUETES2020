package frgp.utn.edu.com.volquetes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import frgp.utn.edu.com.volquetes.conexion.DataMainActivityRegistrarse;

public class registrarse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrarse);
        final String msj_toast="Error \n" ;

        Button btn_guardar = (Button)findViewById(R.id.btn_guardar);
        final EditText txtNombre = (EditText)findViewById(R.id.nombre_usuario_nuevo);
        final EditText txtContrasena = (EditText) findViewById(R.id.contrasena_registrarse);
        final EditText txtContrasenaConfirmar = (EditText) findViewById(R.id.confirmar_contrasena_registrarse);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msj_toast = "";
                if (txtNombre.getText().toString().isEmpty()) {
                    msj_toast = msj_toast + "Campo NOMBRE vacío \n";
                }
                if (txtContrasena.getText().toString().isEmpty()) {
                    msj_toast = msj_toast + "Campo CONTRASEÑA vacío \n";
                }
                if (txtContrasenaConfirmar.getText().toString().isEmpty()) {
                    msj_toast = msj_toast + "Campo CONFIRMAR CONTRASEÑA vacío \n";
                }
                if  (!txtContrasena.getText().toString().equals(txtContrasenaConfirmar.getText().toString())){
                    msj_toast = msj_toast  + "Las contraseñas no coinciden";
                }
                if (msj_toast.isEmpty()){
                   new DataMainActivityRegistrarse(registrarse.this).execute(txtNombre.getText().toString(), txtContrasena.getText().toString(), txtContrasenaConfirmar.getText().toString());
                }else{
                    Toast.makeText((getApplicationContext()), msj_toast, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
