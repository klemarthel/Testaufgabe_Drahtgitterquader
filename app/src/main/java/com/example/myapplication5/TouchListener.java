package com.example.myapplication5;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Urspruenglich fuer Rotationen des Wuerfels verwendet,
 * dann durch Erweiterung von ImageView via MyImageview ersetzt.
 */
public class TouchListener implements GestureDetector.OnGestureListener{
    Cube c;
    DrawingSettings drawingSettings;
    public TouchListener(Cube c,DrawingSettings drawingSettings) {
        this.c = c;
        this.drawingSettings=drawingSettings;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        double x=0;
        double y=0;
        if (Math.abs(velocityX)>Math.abs(velocityY)){
            if (velocityX>0)
                x=-0.1;//(velocityX/drawingSettings.getView().getWidth())*2*Math.PI;
            else
                x=0.1;

        }else {
            if (velocityY>0)
                y=-0.1;//(velocityX/drawingSettings.getView().getWidth())*2*Math.PI;
            else
                y=0.1;


        }

        c.rotate(x,
                y
        );
        return false;
    }


}
