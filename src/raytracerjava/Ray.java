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
public class Ray {
    public double[] org;
    public double[] dest;
    public double[] dir;
    public Ray(double[] origin, double[] destination){
        org = origin;
        dest = destination;
        dir = calc.sub(dest, org);
    }
    public void print(){
        System.out.println("------------------");
        System.out.println(dir[0] + " " + dir[1] + " " +dir[2]);
    }

}
