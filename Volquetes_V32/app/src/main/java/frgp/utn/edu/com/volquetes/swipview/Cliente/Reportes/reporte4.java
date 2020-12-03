package frgp.utn.edu.com.volquetes.swipview.Cliente.Reportes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import frgp.utn.edu.com.volquetes.R;
import frgp.utn.edu.com.volquetes.conexion.DataMainActivityReporte4ClienteReservasMonto;

public class reporte4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte4);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }



    public void  BuscarClienteReporte4(View view){
        final EditText codigo_cliente_reserva = (EditText) findViewById(R.id.codigo_cliente_listar_reporte4);
        Button btn_buscar = (Button) findViewById(R.id.btn_buscar_cliente_listar_reporte4);
        final ListView ListaReserva =(ListView) findViewById(R.id.ListaClientesReservasReporte4);
        final EditText CantidadReservas = (EditText) findViewById(R.id.TXT_MONTO_RESERVAS_CLIENTES);

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msj_toast_alta = "";

                if (codigo_cliente_reserva.getText().toString().isEmpty()) {
                    msj_toast_alta = msj_toast_alta + "Campo CODIGO vacío \n";
                }
                if (msj_toast_alta.isEmpty()){
                    new DataMainActivityReporte4ClienteReservasMonto(reporte4.this, ListaReserva, CantidadReservas).execute(codigo_cliente_reserva.getText().toString());
                }else{
                    Toast.makeText((getApplicationContext()), msj_toast_alta, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



}
