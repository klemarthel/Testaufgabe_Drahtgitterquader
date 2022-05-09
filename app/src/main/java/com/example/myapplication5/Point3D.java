package com.example.myapplication5;


import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Point3D extends Point implements Projectable{
    public double distance(Point3D p3){
        double dx=this.x()-p3.x();
        double dy=this.y()-p3.y();
        double dz=this.z()-p3.z();
        return Math.sqrt(dx*dx+dy*dy+dz*dz);
    }
    final double[] old_coordinates;
    public Point3D(double x,double y,double z){
        coordinates=new double[]{x,y,z};
        old_coordinates=new double[]{x,y,z};
    }
    public void rotateXZ(double x){
        double x_1=-sin(x)*coordinates[2]+cos(x)*coordinates[0];
        double z_1=cos(x)*coordinates[2]+sin(x)*coordinates[0];

        coordinates[0]=x_1;

        coordinates[2]=z_1/*+z_2*/;
    }
    public void rotateYZ(double x){
        double x_1=-sin(x)*coordinates[2]+cos(x)*coordinates[1];
        double z_1=cos(x)*coordinates[2]+sin(x)*coordinates[1];
        //double y_2=sin(z)*old_coordinates[2]+cos(z)*old_coordinates[1];
        //double z_2=cos(z)*old_coordinates[2]-sin(z)*old_coordinates[1];
        coordinates[1]=x_1;
        //coordinates[1]=y_2;
        coordinates[2]=z_1/*+z_2*/;
    }
    public void move(double x,double y,double z){
        coordinates[0]+=x;
        coordinates[1]+=y;
        coordinates[2]+=z;
    }
    public double x(){return coordinates[0];}
    public double y(){return coordinates[1];}
    public double z(){return coordinates[2];}
    public Point2D[] projectTo2D(){

        return new Point2D[]{
                new Point2D(
                        x()+0.5*z(),
                        y()+0.5*z()
                )
        };
    }
}
