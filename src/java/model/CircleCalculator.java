/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kyle Uhan
 */
public class CircleCalculator implements CalculateAreaStrategy{

    private double diameter;
    
    public CircleCalculator(){
    }
    
    public CircleCalculator(final String diameter){
       setDiameter(Double.parseDouble(diameter));
    }
    
    @Override
    public double getArea(){
        double area = getDiameter()/2;
        area = Math.pow(area, 2);
        area *= Math.PI;
        return Math.round(area*100.0)/100.0;
    };
    
    @Override
    public final double getArea(ArrayList<String> list){
        setDiameter(Double.parseDouble(list.get(ZERO)));
        return getArea();
    }

    public final double getDiameter() {
        return diameter;
    }
    public final void setDiameter(final double diameter) {
        this.diameter = diameter;
    }
    
    @Override
    public List<String> getValues() {
        List<String> values = new ArrayList<>();
        values.add(getDiameter()+"");
        values.add((getDiameter()/2) + "");
        return values;
    }

    @Override
    public String toString() {
        return "Diameter: " + getDiameter() + "<br>Radius: " + getDiameter()/2;
    }

   

}
