package com.example.myapplication5;

public class Cube implements Projectable {
    private Edge[] edges;
    private Point3D[] points;

    public Cube() {
        this.points=new Point3D[]{
                new Point3D(-10,-10,-10),
                new Point3D(10,-10,-10),
                new Point3D(-10,10,-10),
                new Point3D(10,10,-10),
                new Point3D(-10,-10,10),
                new Point3D(10,-10,10),
                new Point3D(-10,10,10),
                new Point3D(10,10,10)
        };

        this.edges = new Edge[]{
                new Edge(points[0],points[1]),
                new Edge(points[2],points[3]),
                new Edge(points[4],points[5]),
                new Edge(points[6],points[7]),

                new Edge(points[0],points[2]),
                new Edge(points[1],points[3]),
                new Edge(points[4],points[6]),
                new Edge(points[5],points[7]),

                new Edge(points[0],points[4]),
                new Edge(points[1],points[5]),
                new Edge(points[2],points[6]),
                new Edge(points[3],points[7])

        };
    }
    public Edge[] getEdges(){
        return edges;
    }
    @Override
    public Point2D[] projectTo2D() {

        return new Point2D[0];
    }
    public void move(double x,double y){
        for (Point3D p:
                points) {
            p.move(x,y,0);

        }
    }
    public void rotate(double x,double y){
        for (Point3D p:
             points) {
            p.rotateXZ(x);
            p.rotateYZ(y);
        }
    }
}
