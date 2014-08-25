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
public class CameraPlane extends Plane {
    public CameraPlane(double[] dim){
        super(dim);
        objects.remove(this);
    }
}
