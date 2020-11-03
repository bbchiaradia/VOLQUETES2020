package frgp.utn.edu.com.volquetes.swipview.Cliente.Reportes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import frgp.utn.edu.com.volquetes.R;

public class reportes_principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button btn_irReporte1 = (Button) findViewById(R.id.btn_reporte1);

        btn_irReporte1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent abrir = new Intent(reportes_principal.this, reporte1.class);
                startActivity(abrir);
            }
        });

        Button btn_irReporte2 = (Button) findViewById(R.id.btn_reporte2);

        btn_irReporte2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent abrir = new Intent(reportes_principal.this, reporte2.class);
                startActivity(abrir);
            }
        });

        Button btn_irReporte3 = (Button) findViewById(R.id.btn_reporte3);

        btn_irReporte3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent abrir = new Intent(reportes_principal.this, reporte3.class);
                startActivity(abrir);
            }
        });

    }

}
