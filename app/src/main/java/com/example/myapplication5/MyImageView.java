package com.example.myapplication5;

import android.content.Context;
import android.util.AttributeSet;

import android.view.MotionEvent;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class MyImageView extends AppCompatImageView {
    Cube c;

    DrawingSettings ds;
    public void setCube(Cube c) {
        this.c = c;
    }
    Point2D start;
    double distance=0;
    public boolean onTouchEvent(MotionEvent me){
        super.onTouchEvent(me);
        if (me.getPointerCount()>1) {

            if (me.getActionMasked()==me.ACTION_POINTER_DOWN)

                distance=new Point2D(
                        me.getX(0)-me.getX(1),
                        me.getY(0)-me.getY(1)
                ).getDistance();
            if (me.getActionMasked()==me.ACTION_MOVE){

                if(distance!=0){
                    double new_distance=new Point2D(
                            me.getX(0)-me.getX(1),
                            me.getY(0)-me.getY(1)
                    ).getDistance();
                    ds.setZoom(new_distance/distance);
                    distance=new_distance;
                    invalidate();
                }

            }

            return true;
        }
        if (me.getActionMasked()==me.ACTION_DOWN)

            start=new Point2D(me.getX(),me.getY());

        if (me.getActionMasked()==me.ACTION_MOVE){

            if (start!=null){
                Point2D delta=new Point2D(me.getX()-start.x(),me.getY()-start.y());
                if (false){
                    c.move(delta.x()/20,delta.y()/20);
                } else if (true) {
                    c.rotate(-delta.x()/200,-delta.y()/200);
                } else if (true) {

                }

            }
            start=new Point2D(me.getX(),me.getY());
        }
        this.invalidate();
        return true;
    }
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    TextView tv;
    public void setTextView(TextView tv) {
        this.tv=tv;
    }

    public void setDrawingSettings(DrawingSettings drawingSettings) {
        this.ds = drawingSettings;
    }


}
