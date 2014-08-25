/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import junit.framework.TestCase;
import raytracerjava.Point;

/**
 *
 * @author Alexandre
 */
public class RayTracerTest extends TestCase {
    
    public RayTracerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDistance(){
        Point p1 = new Point(1, 1, 1);
        Point p2 = new Point(1, 2, 1);
        assertEquals(1.0, Point.distance(p1, p2));
    }
}
