package com.example.myapplication5;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * Klasse, die die den Wuerfel zeichnende Komponente repraesentiert.
 */
public class MyImageView extends AppCompatImageView {
    private Cube c;

    private DrawingSettings ds;
    private Face face;

    public void setCube(Cube c) {
        this.c = c;
    }

    private Point2D start;
    private double distance = 0;
    private Point3D direction;

    public boolean onTouchEvent(MotionEvent me) {
        super.onTouchEvent(me);
        if (me.getPointerCount() > 1) {

            if (me.getActionMasked() == me.ACTION_POINTER_DOWN)

                distance = new Point2D(
                        me.getX(0) - me.getX(1),
                        me.getY(0) - me.getY(1)
                ).getDistance();
            if (me.getActionMasked() == me.ACTION_MOVE) {

                if (distance != 0) {
                    double new_distance = new Point2D(
                            me.getX(0) - me.getX(1),
                            me.getY(0) - me.getY(1)
                    ).getDistance();
                    ds.setZoom(new_distance / distance);
                    distance = new_distance;
                    invalidate();
                }

            }

            return true;
        }
        if (me.getActionMasked() == me.ACTION_DOWN) {

            start = new Point2D(me.getX(), me.getY());
            if (ds.getMode() == DrawingSettings.MOVE_SIDE) {
                Face[] faces = c.getFaces();
                Point2D t = new Point2D(
                        (me.getX() - ds.offset.x()) / ds.getZoom(),
                        (me.getY() - ds.offset.y()) / ds.getZoom()
                );
                face = faces[0];
                /*double min_distance=face.sumDistance(start);
                for (int i = 1; i <faces.length; i++) {
                    double tmp=faces[i].sumDistance(start);
                    if (tmp<min_distance){
                        min_distance=tmp;
                        face=faces[i];
                    }
                }*/
                for (Face f : c.getFaces()
                ) {
                    if (f.inBounds(new Point2D(
                            (me.getX() - ds.offset.x()) / ds.getZoom(),
                            (me.getY() - ds.offset.y()) / ds.getZoom()
                    ))) {
                        if (face != null) {
                            if (f.higher(face))
                                face = f;
                        } else {
                            face = f;
                        }
                    }
                }
            }
        }
        if (me.getActionMasked() == me.ACTION_MOVE) {

            if (start != null) {
                Point2D delta = new Point2D(me.getX() - start.x(), me.getY() - start.y());
                switch (ds.getMode()) {
                    case DrawingSettings.ROTATE:
                        c.rotate(-delta.x() / 200, -delta.y() / 200);
                        break;
                    case DrawingSettings.MOVE:
                        c.move(delta.x() / 20, delta.y() / 20);
                        break;
                    case DrawingSettings.MOVE_SIDE:
                        if (face != null) {
                            if (delta.x() > 0)
                                face.move(delta.getDistance() / 10000);
                            else
                                face.move(-delta.getDistance() / 10000);
                        }

                        break;

                }

            }
            start = new Point2D(me.getX(), me.getY());
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


    public void setDrawingSettings(DrawingSettings drawingSettings) {
        this.ds = drawingSettings;
    }


}
