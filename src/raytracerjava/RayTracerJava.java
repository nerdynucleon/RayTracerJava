/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raytracerjava;

import com.sun.prism.PhongMaterial;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Alexandre
 */

public class RayTracerJava {

    public static Color background = new Color(135, 135, 135);
    public static Color sphere = new Color(255, 0, 0);
    public static Color black = new Color(0, 0, 0);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //RayTracing Variables
        int h = 600;
        int w = 800;
        double[] planeDim = {1., 0, 0, -100};
        double[] eyeDim = {100.5, 0, 0};
        double[] topDim = {100.0, 0.1, 0};
        //Normal Variables
        Plane pl = new Plane(planeDim);
        Point pt = new Point(eyeDim);
        //Create Camera
        Camera cam = new Camera(pl, pt, w, h);
        //Set Camera Frame
        cam.setTopDir(topDim, .001);
        //CreateObjects
        double[] sDim1 = {0, 0, 0, 100};
        Sphere s1 = new Sphere(sDim1);
        s1.setColor(sphere);
        s1.setKA(0.1);
        s1.setKD(0.9);
        //Lighting
        double[] l1Dim = {0, 200, 0};
        Light l1 = new Light(l1Dim);

        //Main Render Loop
        for (int yR = 0; yR < h; yR++) {
            for (int xR = 0; xR < w; xR++) {
                Ray primary = cam.createRay(xR, yR);
                cam.img.setRGB(xR, yR, trace(primary).getRGB());
            }
        }

        //Output Image
        try {
            File outputfile = new File("saved.png");
            ImageIO.write(cam.img, "png", outputfile);
        } catch (IOException e) {
        }
    }

    public static Color trace(Ray r) {
        for (ObjectR o : ObjectR.objects) {
            Point pt = o.intersect(r);
            if (pt != null) {
                //Find Light source
                for(Light s : Light.lights){
                     Ray shadow = new Ray(pt.coord, s.dim);
                     for(ObjectR oShadow : ObjectR.objects){
                         if(!Light.lights.contains(oShadow) && oShadow.intersect(shadow) != null){
                             return diffuse(o, pt, s);
                         }
                     }
                    return diffuse(o, pt, s);
                }
            }
        }
        return background;
    }
    public static double diffuse(ObjectR o, Point p, Light l){
        double[] norm = calc.normalize(o.normal(p));
        double[] light = calc.normalize(calc.sub(l.dim, p.coord));
        double factor = calc.dot(norm, light);
        factor = Math.max(factor, 0);
        return o.kd() * factor + o.ka();

    }
    public static int[] specular(ObjectR o, Point p, Light l, Camera c){
        int[] rgb = new int[3];
        double[] norm = calc.normalize(o.normal(p));
        double[] eye = calc.normalize(calc.sub(c.point.coord, p.coord));
        double[] light = calc.normalize(calc.sub(l.dim, p.coord));
        double[] h = calc.normalize(calc.add(eye, light));
        double factor = calc.dot(norm, h);
        factor = Math.max(factor, 0);
        rgb[0] = (int) (o.kd() * factor * o.color().getRed() + o.ka() * o.color().getRed());
        return rgb;
    }
    public static Color shade(ObjectR o, Point p, Light l){
        
    }
}
