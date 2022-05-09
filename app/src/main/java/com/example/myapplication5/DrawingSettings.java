package com.example.myapplication5;

import android.widget.ImageView;

/***
 * Eine Klasse, die die generellen Einstellungen zur Zeichnung verwaltet.
 * (Ich war mir unsicher, in welcher Klasse das jeweilige Callback des Views
 * implementiert wird)
 */
public class DrawingSettings {
    /***
     *
     * @return Seitenflaeche gewaehlt, ja/nein
     */
    public boolean sideSelected(){
        for(boolean b: active){
            if (b)
                return true;
        }
        return false;
    }
    private double zoom;
    private ImageView view;
    boolean active[]=new boolean[]{false,false,false,
            false,false,false};

    /**
     *
     * @param i Index der Seitenflaeche.
     * @return Ist die Seitenfllaeche gewaehlt?
     */
    public boolean isEdgeActive(int i){
        return active[i];
    }

    /**
     * Waehle vier Kanten der Seitenflaeche.
     * @param i
     * @param j
     * @param k
     * @param l
     */
    public void setActive(int i,int j,int k, int l){
        active[i]=active[j]=active[k]=active[l]=true;
    }
    public void setInactive(){
        active=new boolean[]{false,false,false,false,false,false};
    }
    private byte mode=1;

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
