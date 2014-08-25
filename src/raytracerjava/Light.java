/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package raytracerjava;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Alexandre
 */
public class Light extends ObjectR {
    public static ArrayList<Light> lights = new ArrayList<Light>();
    public double[] dim;
    public Light(double[] dim){
        super();
        this.dim = dim;
        lights.add(this);
    }
    private static final Color c = new Color(255, 255, 255);
    
}
