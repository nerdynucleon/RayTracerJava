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
public class Vector {
    public double[] origin;
    public double[] direction;
    public Vector(double[] vOrigin, double[] vDirection){
        origin = vOrigin;
        direction = vDirection;
    }
    public double length(){
        return calc.distance(origin, direction);
    }
}
