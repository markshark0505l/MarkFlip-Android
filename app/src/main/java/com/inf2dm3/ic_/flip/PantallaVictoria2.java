package com.inf2dm3.ic_.flip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ik_2dm3 on 11/11/2016.
 */


public class PantallaVictoria2 extends AppCompatActivity {
    private TextView TxtTiempo;
    private CharSequence Tiempo;
    private Button btnRejugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutvictoria2);
        Bundle extras = getIntent().getExtras();
        Tiempo = extras.getCharSequence("T");
        TxtTiempo = (TextView) findViewById(R.id.txtTiempo2);
        TxtTiempo.setText(Tiempo);
        btnRejugar = (Button) findViewById(R.id.btnReJugar2);
        btnRejugar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(PantallaVictoria2.this,GameConfig.class));
            }
        });
    }

}
