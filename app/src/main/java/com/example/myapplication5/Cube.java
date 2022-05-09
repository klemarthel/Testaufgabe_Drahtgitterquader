package com.example.myapplication5;

/***
 * Repraesentiert den Wuerfel.
 */
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

    /***
     *
     * @return die Kanten des Wuerfels.
     */
    public Edge[] getEdges(){
        return edges;
    }

    /***
     *
     * @return die Punkte des Wuerfels abgebildet auf 2d.
     */
    @Override
    public Point2D[] projectTo2D() {

        return new Point2D[0];
    }

    /***
     * Verschiebt den Wuerfel.
     * @param x verschiebe den Wuerfel um x auf der x Achse im 3d Raum.
     * @param y verschiebe den Wuerfel um y auf der y Achse im 3d Raum.
     */
    public void move(double x,double y){
        for (Point3D p:
                points) {
            p.move(x,y,0);

        }
    }

    /**
     * Rotiert die Punkte des Wuerfels um (0,0,0).
     * @param x im Bogenmass, aendert XZ, Rotation erfolgt an y-Achse
     * @param y im Bogenmass, aendert YZ, Rotation erfolgt an x-Achse
     */
    public void rotate(double x,double y){
        for (Point3D p:
             points) {
            p.rotateXZ(x);
            p.rotateYZ(y);
        }
    }
}
