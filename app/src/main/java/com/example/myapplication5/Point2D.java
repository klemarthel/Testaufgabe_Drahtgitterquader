package com.example.myapplication5;

import androidx.annotation.NonNull;

public class Point2D extends Point{
    public double getDistance(){
        return Math.sqrt(x()*x()+y()*y());
    }
    public Point2D(double x,double y){
        coordinates=new double[]{x,y};
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
