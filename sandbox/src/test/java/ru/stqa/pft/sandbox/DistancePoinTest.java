package ru.stqa.pft.sandbox;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DistancePoinTest {

    @Test
    public void zeroDistance() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 0);
        Assert.assertEquals(p1.distance(p2), 0.0);
    }

    @Test
    public void negativeTest() {
        Point p1 = new Point(-1, 0);
        Point p2 = new Point(-3, -4);

        Assert.assertEquals(round(p1.distance(p2)), 4.473);
    }

    @Test
    public void normalTest() {
        Point p1 = new Point(5, 5);
        Point p2 = new Point(5, 5);
        Assert.assertEquals(p1.distance(p2), 0.0);
    }

    @Test
    public void doubleTest() {
        Point p1 = new Point(1.25, -0.4);
        Point p2 = new Point(5.3, 2.9);
        Assert.assertEquals(round(p1.distance(p2)), 5.225);
    }

    public static double round(double value) {
        return new BigDecimal(value).setScale(3, RoundingMode.UP).doubleValue();
    }

}
