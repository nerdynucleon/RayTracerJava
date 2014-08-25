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
public class ObjectR {
    private int n;
    private double ks;
    private Color c;
    private double kd;
    private double ka;
    public static ArrayList<ObjectR> objects = new ArrayList<ObjectR>();
    public ObjectR(){
        objects.add(this);
    }
    public Point intersect(Ray r){
        return null;
    }
    public void setColor(Color c){
        this.c = c;
    }
    public Color color(Point p){
        return c;
    }
    public double[] normal(Point p){
        return null;
    }
    public void setKD(double kd){
        this.kd = kd;
    }
    public void setKA(double ka){
        this.ka = ka;
    }
    public double kd(){
        return kd;
    }
    public double ka(){
        return ka;
    }
    public void setN(int n){
        this.n = n;
    }
    public int n(){
        return n;
    }
    public void setKS(double ks){
        this.ks = ks;
    }
    public double ks(){
        return ks;
    }

    
}
