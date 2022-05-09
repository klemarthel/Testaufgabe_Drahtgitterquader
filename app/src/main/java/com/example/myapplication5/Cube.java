package com.example.myapplication5;

/***
 * Repraesentiert den Wuerfel.
 */
public class Cube implements Projectable {
    private Face[] faces;
    private Edge[] edges;
    private Point3D[] points;

    public Cube() {
        this.points = new Point3D[]{
                new Point3D(-10, -10, -10),
                new Point3D(10, -10, -10),
                new Point3D(-10, 10, -10),
                new Point3D(10, 10, -10),
                new Point3D(-10, -10, 10),
                new Point3D(10, -10, 10),
                new Point3D(-10, 10, 10),
                new Point3D(10, 10, 10)
        };

        this.edges = new Edge[]{
                new Edge(points[0], points[1]),//0
                new Edge(points[2], points[3]),//1
                new Edge(points[4], points[5]),//2
                new Edge(points[6], points[7]),//3

                new Edge(points[0], points[2]),//4
                new Edge(points[1], points[3]),//5
                new Edge(points[4], points[6]),//6
                new Edge(points[5], points[7]),//7

                new Edge(points[0], points[4]),//8
                new Edge(points[1], points[5]),//9
                new Edge(points[2], points[6]),//10
                new Edge(points[3], points[7])//11

        };
        this.faces = new Face[]{
                new Face(edges[0], edges[4], edges[5], edges[1]),
                new Face(edges[0], edges[8], edges[9], edges[2]),
                new Face(edges[4], edges[10], edges[8], edges[6]),

                new Face(edges[5], edges[9], edges[11], edges[7]),
                new Face(edges[1], edges[10], edges[11], edges[3]),
                new Face(edges[2], edges[6], edges[7], edges[3]),
        };
    }

    /***
     *
     * @return die Kanten des Wuerfels.
     */
    public Edge[] getEdges() {
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
    public void move(double x, double y) {
        for (Point3D p :
                points) {
            p.move(x, y, 0);

        }
    }

    /**
     * Rotiert die Punkte des Wuerfels um (0,0,0).
     *
     * @param x im Bogenmass, aendert XZ, Rotation erfolgt an y-Achse
     * @param y im Bogenmass, aendert YZ, Rotation erfolgt an x-Achse
     */
    public void rotate(double x, double y) {
        for (Point3D p :
                points) {
            p.rotateXZ(x);
            p.rotateYZ(y);
        }
    }

    public Face[] getFaces() {
        return faces;
    }
}
