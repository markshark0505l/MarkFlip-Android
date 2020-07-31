package com.inf2dm3.ic_.flip;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by admin on 29/09/2016.
 */

public class GameField extends AppCompatActivity {

    private int topTileX=3;
    private int topTileY=3;
    private int topElements=2;
    boolean hasSound = false;
    boolean hasVibration= false;
    private Vibrator vibratorService = null;
    private MediaPlayer mp = null;

    private static final int[] colors = new int[]{
            R.drawable.ic_1c,
            R.drawable.ic_2c,
            R.drawable.ic_3c,
            R.drawable.ic_4c,
            R.drawable.ic_5c,
            R.drawable.ic_6c,
            R.drawable.ic_7c,
            R.drawable.ic_8c,
            R.drawable.ic_9c

    };

    private static final int[] numbers = new int[]{
            R.drawable.ic_1n,
            R.drawable.ic_2n,
            R.drawable.ic_3n,
            R.drawable.ic_4n,
            R.drawable.ic_5n,
            R.drawable.ic_6n,
            R.drawable.ic_7n,
            R.drawable.ic_8n,
            R.drawable.ic_9n

    };

    private int []pictures = null;

    private Chronometer chr = null;
    private TextView clickTxt = null;
    private LinearLayout l1 = null;

    //los ids de las celdas
    //para recuperarlos
    private  int ids[][]=null;
    //indices background
    //para la agilizacion de final de partida
    private int values[][] = null;

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_field);

        chr = (Chronometer) findViewById(R.id.chr);
        clickTxt = (TextView) findViewById(R.id.clicksTxt);
        l1 = (LinearLayout) findViewById(R.id.fieldLandscape);
        l1.removeAllViews();
        Bundle extras = getIntent().getExtras();
        topTileX = extras.getInt("x")+3;
        topTileY = extras.getInt("y")+3;
        topElements = extras.getInt("tr")+2;

        chr.start();
        if("numeros".equals(extras.getString("mode"))){
            pictures=numbers;

        }else {

            pictures=colors;
        }


        hasSound=extras.getBoolean("sonido");
        hasVibration=extras.getBoolean("vibracion");

        vibratorService = (Vibrator) (getSystemService(Service.VIBRATOR_SERVICE));
        mp = MediaPlayer.create(this, R.raw.rana);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels/topTileX;
        int height = (dm.heightPixels-180)/topTileY;

        ids = new int[topTileX][topTileY];
        values = new int[topTileX][topTileY];

        //Crear RANDOMs de 0 hasta topElements
        Random r = new Random(System.currentTimeMillis());
        int tilePictureToShow = r.nextInt(topElements);
        int ident=0;

        for (int i=0;i<topTileY;i++){
            LinearLayout l2 = new LinearLayout(this);
            l2.setOrientation(LinearLayout.HORIZONTAL);

            for(int j=0;j<topTileX;j++){
                tilePictureToShow = r.nextInt(topElements);

                values[j][i] = tilePictureToShow;
                TileView tv = new TileView(this, j, i, tilePictureToShow, topElements, pictures[tilePictureToShow]);
                ident ++;
                tv.setId(ident);
                ids[j][i] = ident;

                tv.setHeight(height);
                tv.setWidth(width);

                tv.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View vista){
                        hasClick(((TileView)vista).x, ((TileView)vista).y);
                    }
                });

                l2.addView(tv);
            }
            l1.addView(l2);
        }
    }
    public void  hasClick (int x , int y) {
        if (hasSound) mp.start();
        if (hasVibration) vibratorService.vibrate(100);

        changeView(x,y);
        if(x==0&&y==0){
            changeView(0,1);
            changeView(1,0);
            changeView(1,1);
        }else if(x==topTileX-1&&y==0){
            changeView(topTileX-2,0);
            changeView(topTileX-1,1);
            changeView(topTileX-2,1);
        }else if(x==topTileX-1&&y==topTileY-1){
            changeView(topTileX-2,topTileY-1);
            changeView(topTileX-2,topTileY-2);
            changeView(topTileX-1, topTileY-2);
        }else if(x==0&&y==topTileY-1){
            changeView(0,topTileY-2);
            changeView(1,topTileY-2);
            changeView(1,topTileY-1);
        }else if(y==0){
            changeView(x,y+1);
            changeView(x+1,y);
            changeView(x-1,y);
        }else if(x==0){
            changeView(x,y-1);
            changeView(x+1,y);
            changeView(x,y+1);
        }
        else if(x==topTileX-1){
            changeView(topTileX-2,y);
           changeView(topTileX-1,y-1);
            changeView(topTileX-1,y+1);
        }
        else if(y==topTileY-1){
            changeView(x,topTileY-2);
            changeView(x-1,topTileY-1);
            changeView(x+1,topTileY-1);
        }else{
            changeView(x,y-1);
            changeView(x,y+1);
            changeView(x-1,y);
            changeView(x+1,y);

        }
        checkFinish();


    }


        private void changeView(int x, int y){
            TileView tt = (TileView) findViewById(ids[x][y]);
            //Cojemor indice
            int newIndex=tt.getNewIndex();
            //meter a values
            values[x][y]=newIndex;
            tt.setBackgroundResource(pictures[newIndex]);
            tt.invalidate();
        }
        public void checkFinish(){
            boolean iguales = true;
            int semilla = values[0][0];

            for (int i=0; i<topTileX; i++){
                for(int j=0; j<topTileY; j++){
                    if (values[i][j]!=semilla){iguales=false;}



                }
            }
            if (iguales == true){
                chr.stop();
                CharSequence Tiempo;
                Tiempo=chr.getText();
                String txt = iguales + "";
                Toast.makeText(this, "JUEGO COMPLETADO", Toast.LENGTH_SHORT).show();
                if(topElements==2){
                    Intent i = new Intent(this, PantallaVictoria.class);
                    i.putExtra("T", Tiempo);
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(this, PantallaVictoria2.class);
                    i.putExtra("T", Tiempo);
                    startActivity(i);
                }


            }
        }
}
