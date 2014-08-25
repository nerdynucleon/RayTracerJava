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
public class calc {
    public static double dotVec(Vector v1, Vector v2){
        return dot(v1.direction, v2.direction);
    }
    public static double dot(double[] p1, double[] p2){
        double sum = 0.0;
        for(int x = 0; x < p1.length; x++){
            sum += p1[x] * p2[x];
        }
        return sum;
    }
    public static double[] sub(double[] p1, double[] p2){
        double[] rtn = new double[p1.length];
        for(int x = 0; x < p1.length; x++){
            rtn[x] = p1[x] - p2[x];
        }
        return rtn;
    }
    public static double[] add(double[] p1, double[] p2){
        double[] rtn = new double[p1.length];
        for(int x = 0; x < p1.length; x++){
            rtn[x] = p1[x] + p2[x];
        }
        return rtn;
    }
    public static int[] add(int[] p1, int[] p2){
        int[] rtn = new int[p1.length];
        for(int x = 0; x < p1.length; x++){
            rtn[x] = p1[x] + p2[x];
        }
        return rtn;
    }
    public static double[] scale(double c, double[] coord){
        //nondestructive
        double[] rtn = new double[coord.length];
        for(int x = 0; x < coord.length; x++){
            rtn[x] = c * coord[x];
        }
        return rtn;
    }
    public static double[] crossR3(double[] p1, double[] p2){
        double[] rtn = new double[3];
        rtn[0] = p1[1]*p2[2] - p1[2]*p2[1];
        rtn[1] = p1[2]*p2[0] - p1[0]*p2[2];
        rtn[2] = p1[0]*p2[1] - p1[1]*p2[0];
        return rtn;
    }
    public static double vecPlaneIntersect(Plane p, Vector v){
        //Formula @http://en.wikipedia.org/wiki/Line%E2%80%93plane_intersection
        return dot(sub(p.pointNearOrigin().coord,v.origin),p.normal(null))/dot(v.direction, p.normal(null));
    }
    public static Point ptPtNormPlane(Plane p, Point pt){
        /**Finds the point that, using the normal of the plane, intersects
         *the given point.
         */
        Vector vec = new Vector(pt.coord, p.normal(null));
        return new Point(add(scale(vecPlaneIntersect(p, vec),p.normal(null)),pt.coord));
    }
    public static double distance(double[] p1, double[] p2){
        return Math.pow(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2) + Math.pow(p1[2] - p2[2], 2),0.5);
    }
    public static double distance(double[] p1){
        return Math.pow(Math.pow(p1[0], 2) + Math.pow(p1[1], 2) + Math.pow(p1[2], 2),0.5);
    }
    public static double[] copyVec(double[] vec, int n){
        double[] rtn = new double[n];
        for(int x = 0; x < n; x++){
            rtn[x] = vec[x];
        }
        return rtn;
    }
    public static double[] normalize(double[] v){
        return scale(1/distance(v), v);
    }
}
