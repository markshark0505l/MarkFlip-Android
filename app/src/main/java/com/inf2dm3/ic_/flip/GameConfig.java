package com.inf2dm3.ic_.flip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class GameConfig extends AppCompatActivity {

    private SeekBar seekX, seekY, seekTr;
    private TextView tvX, tvY, tvTr;
    private RadioButton rbCol, rbNum;
    private CheckBox cbS, cbV;
    private Button btnJugar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_config);

        seekX = (SeekBar) findViewById(R.id.seekX);
        seekY = (SeekBar) findViewById(R.id.seekY);
        seekTr = (SeekBar) findViewById(R.id.seekTr);
        tvX = (TextView) findViewById(R.id.tvX);
        tvY = (TextView) findViewById(R.id.tvY);
        tvTr = (TextView) findViewById(R.id.tvTr);
        rbCol = (RadioButton) findViewById(R.id.rbCol);
        rbNum = (RadioButton) findViewById(R.id.rbNum);
        cbS = (CheckBox) findViewById(R.id.cbSo);
        cbV = (CheckBox) findViewById(R.id.cbVb);
        btnJugar = (Button) findViewById(R.id.btnJugar);


        rbCol.setText(getString(R.string.rbColores));
        rbNum.setText(getString(R.string.rbNumeros));
        cbS.setText(getString(R.string.Sonido));
        cbV.setText(getString(R.string.Vibracion));
        tvX.setText(getString(R.string.lblseekX)+ ": 3");
        tvY.setText(getString(R.string.lblseekY)+ " 3");
        tvTr.setText(getString(R.string.lblTR)+ " 2");

        seekX.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onStartTrackingTouch (SeekBar seekBar){}
            @Override
            public void onStopTrackingTouch (SeekBar seekBar){}
            @Override
            public void onProgressChanged (SeekBar seekBar, int progress, boolean fromuser){
                updateX(seekBar.getProgress());

            }});
            seekY.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
                @Override
                public void onStartTrackingTouch (SeekBar seekBar){}
                @Override
                public void onStopTrackingTouch (SeekBar seekBar){}
                @Override
                public void onProgressChanged (SeekBar seekBar, int progress, boolean fromuser){
                    updateY(seekBar.getProgress());

                }
            });
                seekTr.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
                    @Override
                    public void onStartTrackingTouch (SeekBar seekBar){}
                    @Override
                    public void onStopTrackingTouch (SeekBar seekBar){}
                    @Override
                    public void onProgressChanged (SeekBar seekBar, int progress, boolean fromuser){
                        updateTramas(seekBar.getProgress());

                    }
        });
    }

    protected void updateX(int progress){
        int x = progress + 3;
        tvX.setText(getString(R.string.lblseekX) + ": " + x);

    }

    protected void updateY(int progress){

        tvY.setText(getString(R.string.lblseekY) + ": " + (progress + 3));
    }

    protected void updateTramas(int progress){

        tvTr.setText(getString(R.string.lblTR) + "" + (progress + 2));
    }


    public void onclick_jugar(View view){
        Intent i = new Intent(this, GameField.class);

        i.putExtra("x", seekX.getProgress());
        i.putExtra("y", seekY.getProgress());
        i.putExtra("tr", seekTr.getProgress());

        if (rbCol.isChecked()) {
            i.putExtra("mode", "colores");
        }else{
            i.putExtra("mode", "numeros");
        }

        if (cbS.isChecked()){

            i.putExtra("sonido" , true);
        }else{
            i.putExtra("sonido", false);
        }

        if (cbV.isChecked()){

            i.putExtra("vibracion" , true);
        }else{
            i.putExtra("vibracion", false);
        }
        startActivity(i);
    }


}
