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
public class Plane extends ObjectR {
    private double[] dim;
    private double[] normal;
    public Plane(double[] def){
        super();
        dim = def;
        normal = new double[3];
        normal[0] = def[0];
        normal[1] = def[1];
        normal[2] = def[2];
    }
    public double[] normal(Point p){
        return normal;
    }
    public Point pointNearOrigin(){
        double denom = Math.pow(dim[0], 2) + Math.pow(dim[1], 2) + Math.pow(dim[2], 2);
        double[] coord = new double[3];
        coord[0] = dim[0] * -dim[3]/denom;
        coord[1] = dim[1] * -dim[3]/denom;
        coord[2] = dim[2] * -dim[3]/denom;
        return new Point(coord);
    }
    public Point intersect(Ray r){
        double numerator = calc.dot(calc.sub(pointNearOrigin().coord, r.org),normal);
        double denom = calc.dot(r.dir, normal);
        if(numerator == 0){
            return new Point(r.org);
        } else if (denom == 0){
            return null;
        } else {
            double d = numerator/denom;
            if(d < 0){
                return null;
            }
            return new Point(calc.add(calc.scale(d, r.dir),r.org));
        }
    } 
}
