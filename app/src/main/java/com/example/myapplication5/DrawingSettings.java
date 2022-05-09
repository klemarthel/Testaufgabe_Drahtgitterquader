package com.example.myapplication5;

import android.widget.ImageView;

public class DrawingSettings {
    double zoom;
    ImageView view;
    boolean active[]=new boolean[]{false,false,false,
            false,false,false};
    byte mode=1;
    public static final byte ROTATE=1;
    public static final byte MOVE=2;
    public static final byte MOVE_SIDE=3;
    public void setMode(byte i){
        mode =i;
    }
    public byte getMode(){return mode;}
    public DrawingSettings(double zoom, ImageView view) {
        this.zoom = zoom;
        this.view = view;
    }

    public ImageView getView() {
        return view;
    }

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom *= zoom;
    }
}
