package frgp.utn.edu.com.volquetes.swipview.Cliente.Reportes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import frgp.utn.edu.com.volquetes.R;
import frgp.utn.edu.com.volquetes.conexion.DataMainActivityReporteConsultarStock;

public class reporte1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte1);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }


    public void  consultarStockVolquetes(View view){

        Button btn_consultar_stock = (Button) findViewById(R.id.btn_buscar_rpt_stock_volquete);
        final EditText txt_disponible = (EditText) findViewById(R.id.TXT_DISPONIBLES);
        final EditText txt_reservados = (EditText) findViewById(R.id.TXT_RESERVADOS);

        new DataMainActivityReporteConsultarStock(reporte1.this, txt_disponible, txt_reservados ).execute();

/*
        btn_consultar_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msj_toast_alta = "";
                new DataMainActivityReporteConsultarStock(reporte1.this, txt_disponible, txt_reservados ).execute();

            }
        });
*/

    }


}
