/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package raytracerjava;

import java.awt.image.BufferedImage;

/**
 *
 * @author Alexandre
 */
public class Camera {
    //Point Coordinates
    public Point point;
    //Resolution
    private int w;
    private int h;
    boolean wEven;
    boolean hEven;
    //RenderPlane
    private CameraPlane plane;
    private double space;
    //Dimensions of RenderPlane
    private Point cent;
    private double[] topRelativeCent;
    private double[] rightRelativeCent;
    private double topLength;
    private double rightLength;
    public Camera(CameraPlane pl, Point pt, int w, int h){
        plane = pl;
        point = pt;
        this.w = w;
        this.h = h;
        img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        wEven = w % 2 == 0;
        hEven = h % 2 == 0;
        cent = calc.ptPtNormPlane(pl, pt);
    }
    public void setTopDir(double[] pt, double space){
        /**
         * Takes in top of frame relative to the origin.
         */
        this.space = space;
        topRelativeCent = calc.sub(pt, cent.coord);
        rightRelativeCent = calc.crossR3(topRelativeCent, plane.normal(null));
        topLength = calc.distance(topRelativeCent);
        rightLength = calc.distance(rightRelativeCent);
    }
    
    public Ray createRay(int x, int y){
        double width = -w/2 * space + x * space;
        if(wEven){
            width += 0.5*space;
        }
        double height = h/2 * space - y * space;
        if(hEven){
            height -= 0.5*space;
        }
        double[] surfpt;
        surfpt = calc.add(calc.add(calc.scale(height/topLength, topRelativeCent),calc.scale(width/rightLength, rightRelativeCent)),cent.coord); 
        Ray rtn = new Ray(point.coord, surfpt);
        return rtn;
    }
    public BufferedImage img;
}
