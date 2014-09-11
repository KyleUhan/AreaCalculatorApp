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
public interface CalculateAreaStrategy {
    public static final int ZERO = 0;
    public double getArea();
    public double getArea(ArrayList<String> list);
    public List<String> getValues();
}
