package com.example.myapplication5;

/***
 * Repraesentiert eine Kante.
 */
public class Edge implements Projectable {
    private Point3D start;
    private Point3D end;

    public Point3D start() {
        return start;
    }

    public Point3D end() {
        return end;
    }

    public Edge(Point3D start, Point3D end) {
        this.start = start;
        this.end = end;
    }


    @Override
    public Point2D[] projectTo2D() {
        return new Point2D[]{start.projectTo2D()[0],end.projectTo2D()[0]};
    }
}
