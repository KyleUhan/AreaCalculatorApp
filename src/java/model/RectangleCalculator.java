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
public class RectangleCalculator implements CalculateAreaStrategy {

    private double length;
    private double width;

    public RectangleCalculator() {
    }

    public RectangleCalculator(String length, String width) {
        setLength(width);
        setWidth(length);
    }

    @Override
    public final double getArea() {
        double area = getLength() * getWidth();
        return Math.round(area * 100.0) / 100.0;
    }

    @Override
    public final double getArea(ArrayList<String> list) {
        setLength(list.get(ZERO));
        setWidth(list.get(1));
        return getArea();
    }

    public final double getLength() {
        return length;
    }

    public final void setLength(String length) {
        this.length = Double.parseDouble(length);
    }

    public final double getWidth() {
        return width;
    }

    public final void setWidth(String width) {
        this.width = Double.parseDouble(width);
    }

    @Override
    public String toString() {
        return "";
    }
}
