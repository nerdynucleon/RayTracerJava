/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raytracerjava;

/**
 *
 * @author Alexandre
 */
public class Sphere extends ObjectR {

    public double[] dim;

    public Sphere(double[] dim) {
        super();
        this.dim = dim;
    }
    public double[] rayOriginObjectSpace(Ray r){
        return calc.sub(r.org, dim);
    }

    /**
     * Returns closest point of intersection of sphere. Null pointer if there
     * doesn't exist an intersection.
     *
     * @param r is a ray to calculate intersection
     */
    public Point intersect(Ray r) {
        double[] o = rayOriginObjectSpace(r);
        double a = calc.dot(r.dir, r.dir);
        double b = 2.0 * calc.dot(r.dir, o);
        double c = calc.dot(o, o) - dim[3];
        double disc = b * b - 4.0 * a * c;
        if (disc < 0) {
            return null;
        }
        double discSquare = Math.sqrt(disc);
        double q;
        if (b < 0) {
            q = (-b - discSquare) / (2.0);
        } else {
            q = (-b + discSquare) / (2.0);
        }
        double t0 = q / a;
        double t1 = c / q;
        if (t0 > t1) {
            double temp = t0;
            t0 = t1;
            t1 = temp;
        }
        if (t1 < 0) {
            return null;
        }
        if (t0 < 0) {
            return new Point(calc.add(calc.scale(t1*0.999999, r.dir), r.org));
        } else {
            return new Point(calc.add(calc.scale(t0*0.999999, r.dir), r.org));
        }
    }
    /**
     * 
     * @param p the point on the sphere to calculate the normal
     * @return array double representing direction vector of normal from point p
     */
    public double[] normal(Point p){
        return calc.sub(p.coord, dim);
    }
}
