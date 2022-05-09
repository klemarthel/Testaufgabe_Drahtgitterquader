package com.example.myapplication5;

import androidx.annotation.NonNull;

/**
 * Repraesentiert einen 2d-Punkt bzw. Vektor.
 */
public class Point2D extends Point{
    public double getDistance(){
        return Math.sqrt(x()*x()+y()*y());
    }
    public Point2D(double x,double y){
        coordinates=new double[]{x,y};
    }
    public double scalar(Point2D p){
        return this.x()*p.x()+this.y()*p.y();
    }
    public Point2D(Point2D p1,Point2D p2) {
        coordinates=new double[]{
                p1.x()-p2.x(),
                p1.y()-p2.y()};
    }

    public double x(){
        return coordinates[0];
    }
    public double y(){
        return coordinates[1];
    }

    @NonNull
    @Override
    public String toString() {
        return "("+x()+","+y()+")";
    }
}
