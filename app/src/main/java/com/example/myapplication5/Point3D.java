package com.example.myapplication5;


import androidx.annotation.Nullable;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
/**
 * Repraesentiert einen 3d-Punkt bzw. Vektor.
 */
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
    public Point3D(Point3D p1,Point3D p2){
        double x=p1.x()-p2.x();
        double y=p1.y()-p2.y();
        double z=p1.z()-p2.z();
        coordinates=new double[]{x,y,z};
        old_coordinates=new double[]{x,y,z};
    }

    /**
     * Rotiert an der Y-Achse, aendert x,z
     * Formel aus:
     * https://en.wikipedia.org/wiki/Three-dimensional_rotation_operator#Rotation_around_an_axis
     * @param x
     */
    public void rotateXZ(double x){
        double x_1=-sin(x)*coordinates[2]+cos(x)*coordinates[0];
        double z_1=cos(x)*coordinates[2]+sin(x)*coordinates[0];

        coordinates[0]=x_1;

        coordinates[2]=z_1/*+z_2*/;
    }
    /**
     * Rotiert an der X-Achse, aendert y,z
     * Formel aus:
     * https://en.wikipedia.org/wiki/Three-dimensional_rotation_operator#Rotation_around_an_axis
     * @param x
     */
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

    /**
     * Projeziert den Punkt auf 2d.
     * Dies scheint die einfachste Variante zusein,
     * das zu tun, und soll fuer diese App reichen.
     * So sind wir bereits im Geometrie Unterricht
     * in der Schule vorgegangen.
     * Es scheinen noch andere Moeglichkeiten zu existieren,
     * eine davon wurde in GPU-Programmierung angesprochen
     * (als urspruengliche Motivation zum Entwurf von GPUs)
     * https://en.wikipedia.org/wiki/3D_projection#Orthographic_projection
     * @return 2d-Punkt
     */
    public Point2D[] projectTo2D(){

        return new Point2D[]{
                new Point2D(
                        x()+0.5*z(),
                        y()+0.5*z()
                )
        };
    }


    public boolean equals(Point3D p) {
        for (int i = 0; i < 3; i++) {
            if(this.coordinates[i]!=p.coordinates[i]){
                return false;
            }
        }
        return true;
    }

}
