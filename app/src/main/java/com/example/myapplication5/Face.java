package com.example.myapplication5;

/**
 * Repraesentiert eine Seitenflaeche.
 */
public class Face {
    private Edge[] edges;
    private Point3D Direction;

    /**
     * Erzeugt eine Oberflaeche, und berechnet den Orthogonalenvektor.
     *
     * @param edge0
     * @param edge1
     * @param edge2
     * @param edge3
     */
    public Face(Edge edge0, Edge edge1, Edge edge2, Edge edge3) {
        this.edges = new Edge[]{edge0, edge1, edge2, edge3};
        getDirection();

    }

    public boolean higher(Face f) {
        double x = 0;
        for (Edge e : this.edges
        ) {
            x += e.start().z();
            x += e.end().z();
        }
        double f_x = 0;
        for (Edge e : f.edges
        ) {
            f_x += e.start().z();
            f_x += e.end().z();
        }
        return x > f_x;
    }

    public Point3D getDirection() {
        Point3D p[] = new Point3D[]{
                new Point3D(edges[0].start(), edges[0].end()),
                new Point3D(edges[1].start(), edges[1].end()),
                new Point3D(edges[2].start(), edges[2].end()),
                new Point3D(edges[3].start(), edges[3].end())
        };
        Point3D ursprung = new Point3D(0, 0, 0);
        Direction = crossProduct(p[0], p[1]);
        for (int i = 1; i <= 3; i++) {
            Direction = crossProduct(p[0], p[i]);
            if (Direction.distance(ursprung) > 0.001)
                return Direction;
        }
        return Direction;
    }

    /**
     * Schaut, ob der gegebene Punkt innerhalb der
     * Seitenfläche liegt.
     *
     * @param p Punkt, an dem der View berührt wurde.
     * @return
     */
    public boolean inBounds(Point2D p) {
        for (int i = 0; i < this.edges.length; i++) {
            if (!behindEdge(p, i))
                return false;
        }
        return true;
    }

    private boolean behindEdge(Point2D p, int i) {
        Point2D a = edges[i].start().projectTo2D()[0];
        Point2D b = edges[i].end().projectTo2D()[0];
        Point2D diff = new Point2D(a, b);
        Point2D v = new Point2D(diff.y(), -diff.x());
        double threshold = v.scalar(a);
        boolean up = true;
        Edge e = edges[(i + 1) % 4];
        if (
                e.start().equals(edges[i].start())
                        ||
                        e.start().equals(edges[i].end())
        ) {
            up = e.end().projectTo2D()[0].scalar(v) > threshold;
        } else {
            up = e.start().projectTo2D()[0].scalar(v) > threshold;
        }
        if (up) {
            return v.scalar(p) >= threshold;
        }
        return v.scalar(p) <= threshold;
    }

    /**
     * https://de.wikipedia.org/wiki/Kreuzprodukt#Komponentenweise_Berechnung
     *
     * @param p0
     * @param p1
     * @return
     */
    private Point3D crossProduct(Point3D p0, Point3D p1) {
        double x = p0.y() * p1.z() - p0.z() * p1.y();
        double y = p0.z() * p1.x() - p0.x() * p1.z();
        double z = p0.x() * p1.y() - p0.y() * p1.x();
        return new Point3D(x, y, z);
    }

    private boolean unique(Point3D[] ps, Point3D p) {
        for (int i = 0; i < ps.length; i++) {
            if (p.equals(ps[i]))
                return false;
        }
        return true;
    }

    public Point3D[] getPoints() {
        Point3D[] result = new Point3D[4];
        int j = 0;
        for (int i = 0; i < edges.length; i++) {
            if (unique(result, edges[i].start())) {
                result[j] = edges[i].start();
                j++;
            }
            if (unique(result, edges[i].end())) {
                result[j] = edges[i].end();
                j++;
            }


        }
        return result;
    }

    /**
     * Verschiebt die Punkte in Richtung Direction.
     *
     * @param d
     */
    public void move(double d) {
        getDirection();
        Point3D v = new Point3D(
                Direction.x() * d,
                Direction.y() * d,
                Direction.z() * d
        );
        Point3D start_0 = edges[0].start();
        Point3D end_0 = edges[0].end();
        Point3D start_1 = null;
        Point3D end_1 = null;
        for (int i = 1; i <= 3; i++) {
            if (!edges[i].start().equals(start_0) && !edges[i].start().equals(end_0)) {
                if ((!edges[i].end().equals(start_0) && !edges[i].end().equals(end_0))) {
                    start_1 = edges[i].start();
                    end_1 = edges[i].end();
                }
            }
        }
        start_0.move(v.x(), v.y(), v.z());
        start_1.move(v.x(), v.y(), v.z());
        end_0.move(v.x(), v.y(), v.z());
        end_1.move(v.x(), v.y(), v.z());
    }


    public double sumDistance(Point2D p) {
        Point3D start_0 = edges[0].start();
        Point3D end_0 = edges[0].end();
        Point3D start_1 = null;
        Point3D end_1 = null;
        for (int i = 1; i <= 3; i++) {
            if (!edges[i].start().equals(start_0) && !edges[i].start().equals(end_0)) {
                if ((!edges[i].end().equals(start_0) && !edges[i].end().equals(end_0))) {
                    start_1 = edges[i].start();
                    end_1 = edges[i].end();
                }
            }
        }
        Point2D[] p2 = new Point2D[]{
                start_0.projectTo2D()[0],
                start_1.projectTo2D()[0],
                end_0.projectTo2D()[0],
                end_1.projectTo2D()[0]
        };
        double result = 0;
        for (int i = 0; i < p2.length; i++) {
            result += p2[i].getDistance(p);
        }
        return result;
    }
}
