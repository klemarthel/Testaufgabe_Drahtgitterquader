package com.example.myapplication5;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import com.google.android.material.chip.Chip;

/***
 * Repraesentiert die Oberflaeche der App.
 */
public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private DrawingSettings ds;
    private Chip move;
    private Chip rotate;//=findViewById(R.id.chipMove);
    private Chip move_side;

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (buttonView.getId() == move.getId()) {
            if (isChecked) {
                ds.setMode(DrawingSettings.MOVE);
                move_side.setChecked(false);
                rotate.setChecked(false);
            }

        } else if (buttonView.getId() == rotate.getId()) {
            if (isChecked) {
                ds.setMode(DrawingSettings.ROTATE);
                move_side.setChecked(false);
                move.setChecked(false);
            }
        } else if (buttonView.getId() == move_side.getId()) {
            if (isChecked) {
                ds.setMode(DrawingSettings.MOVE_SIDE);
                move.setChecked(false);
                rotate.setChecked(false);
            }
        }
    }


    private Cube e = new Cube();

    /**
     * Zeichnung des Wuerfels.
     */
    class CubeDrawing extends Drawable {


        private float mapXtoCanvas(double x) {
            return (float) (x * ds.getZoom() + ds.offset.x());
        }

        private float mapYtoCanvas(double x) {
            return (float) (x * ds.getZoom() + ds.offset.y());
        }

        @Override
        public void draw(@NonNull Canvas canvas) {
            ds.offset = new Point2D(getBounds().centerX(), getBounds().centerY());
            Paint p = new Paint();
            p.setARGB(255, 255, 255, 255);
            Paint p2 = new Paint();
            p2.setARGB(255, 0, 0, 0);
            canvas.drawRect(0, 0, getBounds().right, getBounds().bottom, p2);
            Edge[] pts = e.getEdges();
            for (Edge pt : pts) {
                Point2D[] start_stop = pt.projectTo2D();

                Point2D start = start_stop[0];
                Point2D stop = start_stop[1];
                canvas.drawLine(mapXtoCanvas(start.x()), mapYtoCanvas(start.y()), mapXtoCanvas(stop.x()), mapYtoCanvas(stop.y()), p);
            }

        }

        @Override
        public void setAlpha(int i) {

        }

        @Override
        public void setColorFilter(@Nullable ColorFilter colorFilter) {

        }

        @Override
        public int getOpacity() {
            return PixelFormat.OPAQUE;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyImageView miw = findViewById(R.id.myImageView);
        //miw.set

        (move = findViewById(R.id.chipMove)).setOnCheckedChangeListener(this);
        (rotate = findViewById(R.id.chipRotate)).setOnCheckedChangeListener(this);
        (move_side = findViewById(R.id.chipMoveSide)).setOnCheckedChangeListener(this);
        ds = new DrawingSettings(10, miw);
        miw.setDrawingSettings(ds);
        miw.setImageDrawable(new CubeDrawing());
        this.setTitle("Testaufgabe Drahtgitterquader");

        miw.setCube(e);

    }

}