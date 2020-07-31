package com.inf2dm3.ic_.flip;

import android.content.Context;
import android.widget.Button;

/**
 * Created by admin on 29/09/2016.
 */

public class TileView extends Button {

    //coordenadas
    public int x=0;
    public int y=0;
    //tramas a mostrar
    private int index =0;
    // max tramas
    private int topElements =0;

    public TileView(Context context, int x, int y, int index, int topElements, int background){
        super(context);
        this.x=x;
        this.y=y;
        this.index=index;
        this.topElements=topElements;
        this.setBackgroundResource(background);
    }

    public int getNewIndex(){
        index ++;
        if(index==topElements)index=0;
        return index;
    }

}
