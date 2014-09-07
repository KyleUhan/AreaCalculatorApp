/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Kyle Uhan
 */
public class TriangleCalculator implements CalculateAreaStrategy {

    private double trianleOne;
    private double trianleTwo;
    private double triangleThree = 0.0;

    public TriangleCalculator() {
    }

    public TriangleCalculator(final String trianleOne, final String trianleTwo) {
        setTrianleOne(trianleOne);
        setTrianleTwo(trianleTwo);
    }

    @Override
    public double getArea() {
        double s1 = Math.pow(getTrianleOne(), 2);
        double s2 = Math.pow(getTrianleTwo(), 2);
        double s3 = Math.sqrt(s1 + s2);
        s1 = Math.sqrt(s1);
        s2 = Math.sqrt(s2);
        setTriangleThree(Math.round(s3*100.0)/100.0);
        double p = ( s1 + s2 + s3)*.5;
        double area = Math.sqrt(p*((p-s1)*(p-s2)*(p-s3)));
       
        
        return  Math.round(area*100.0)/100.0;
    }

    @Override
    public final double getArea(ArrayList<String> list) {
        setTrianleOne(list.get(ZERO));
        setTrianleTwo(list.get(1));
        return getArea();

    }

    public double getTrianleOne() {
        return trianleOne;
    }

    public final void setTrianleOne(String trianleOne) {
        this.trianleOne = Double.parseDouble(trianleOne);
    }

    public double getTrianleTwo() {
        return trianleTwo;
    }

    public final void setTrianleTwo(String trianleTwo) {
        this.trianleTwo = Double.parseDouble(trianleTwo);
    }

    public double getTriangleThree() {
        return triangleThree;
    }

    public void setTriangleThree(double triangleThree) {
        this.triangleThree = triangleThree;
    }

    @Override
    public String toString() {
        return "Side 1: " + getTrianleOne() + "<br> Side 2: " + getTrianleTwo() + "<br> Side 3: " + getTriangleThree();
    }

}
