package com.example.myapplication5;

import android.widget.ImageView;

/***
 * Eine Klasse, die die generellen Einstellungen zur Zeichnung verwaltet.
 * (Ich war mir unsicher, in welcher Klasse das jeweilige Callback des Views
 * implementiert wird)
 */
public class DrawingSettings {

    private double zoom;
    private ImageView view;

    private byte mode = 0;
    public Point2D offset = new Point2D(0, 0);
    public static final byte ROTATE = 1;
    public static final byte MOVE = 2;
    public static final byte MOVE_SIDE = 3;

    public void setMode(byte i) {
        mode = i;
    }

    public byte getMode() {
        return mode;
    }

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
