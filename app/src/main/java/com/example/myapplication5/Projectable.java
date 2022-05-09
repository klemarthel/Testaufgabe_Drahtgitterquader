package com.example.myapplication5;

/***
 * Interface fuer Klassen, die von 3d auf 2d-Ebene abgebildet werden koennen.
 */
public interface Projectable {
    public Point2D[] projectTo2D();
}
