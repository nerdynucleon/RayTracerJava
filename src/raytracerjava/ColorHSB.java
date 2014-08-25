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
public class ColorHSB {
    public float hue; // The color 0 - 360;
    public float bright; //The amount of white 0 - 100;
    public float sat; // The amount of color 0 - 100;
    public ColorHSB(float h, float s, float b){
        hue = h;
        bright = b;
        sat = s;
    }
}
