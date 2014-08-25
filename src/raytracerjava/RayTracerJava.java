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
    public static Color sphere1 = new Color(255, 0, 0);
    public static Color sphere2 = new Color(0, 255, 0);
    public static Color black = new Color(0, 0, 0);
    public static Color plane = new Color(255, 255, 255);
    public static Camera cam;

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
        CameraPlane pl = new CameraPlane(planeDim);
        Point pt = new Point(eyeDim);
        //Create Camera
        cam = new Camera(pl, pt, w, h);
        //Set Camera Frame
        cam.setTopDir(topDim, .001);
        //CreateObjects
        //Sphere
        double[] sDim1 = {0, 0, 0, 400};
        Sphere s1 = new Sphere(sDim1);
        s1.setColor(sphere1);
        s1.setKA(0.1);
        s1.setKD(0.9);
        s1.setKS(1);
        s1.setN(2000);
        double[] sDim2 = {10, 10, -10, 100};
        Sphere s2 = new Sphere(sDim2);
        s2.setColor(sphere2);
        s2.setKA(0.1);
        s2.setKD(0.9);
        s2.setKS(1);
        s2.setN(2000);
        //Plane
        double[] pDim1 = {0, 1, 0, 40};
        Plane p1 = new Plane(pDim1);
        p1.setColor(plane);
        p1.setKA(0.1);
        p1.setKD(0.9);
        p1.setKS(1);
        p1.setN(1000);
        //Lighting
        double[] l1Dim = {200, 200, 200};
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
        Point pt = null;
        Point nPt;
        ObjectR oInter = null;
        double minDist = Double.MAX_VALUE;
        for (ObjectR o : ObjectR.objects) {
            nPt = o.intersect(r);
            double dist = calc.distance(nPt.coord, cam.point.coord);
            if(dist < minDist){
                pt = nPt;
                minDist = dist;
                oInter = o;
            }
        }
        if (pt != null) {
                //Find Light source
                for(Light s : Light.lights){
                     Ray shadow = new Ray(pt.coord, s.dim);
                     for(ObjectR oShadow : ObjectR.objects){
                         if(!Light.lights.contains(oShadow) && oShadow.intersect(shadow) != null){
                             if(oShadow == oInter){
                                 return shade(oInter, pt, s, cam);
                             } else {
                                 return black;
                             }
                         }
                     }
                    return shade(oInter, pt, s, cam);
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
    public static double specular(ObjectR o, Point p, Light l, Camera c){
        double[] norm = calc.normalize(o.normal(p));
        double[] eye = calc.normalize(calc.sub(c.point.coord, p.coord));
        double[] light = calc.normalize(calc.sub(l.dim, p.coord));
        double[] h = calc.normalize(calc.add(eye, light));
        double factor = calc.dot(norm, h);
        factor = Math.max(factor, 0);
        return o.ks() * Math.pow(factor, o.n());
    }
    public static Color shade(ObjectR o, Point p, Light l, Camera cam){
        double c1 = diffuse(o, p, l);
        c1 += specular(o, p, l, cam);
        Color c = o.color(p);
        int r = Math.min(255, (int)(c.getRed()*c1));
        int g = Math.min(255, (int)(c.getGreen()*c1));
        int b = Math.min(255, (int)(c.getBlue()*c1));
        return new Color(r,g,b);
    }
}
