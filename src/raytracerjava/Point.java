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
public class Point {
    public double[] coord = new double[3];
    public Point(double x, double y, double z){
        coord[0] = x;
        coord[1] = y;
        coord[2] = z;
    }
    public Point(double[] coord){
        this.coord = coord;
    }
    public double x(){
        return coord[0];
    }
    public double y(){
        return coord[1];
    }
    public double z(){
        return coord[2];
    }
    public static double distance(Point p1, Point p2){
        return calc.distance(p1.coord, p2.coord);
    }
}
