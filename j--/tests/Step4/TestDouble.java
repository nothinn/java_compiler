/**
 * TestDouble.java
 */

import java.lang.Double;
import java.lang.Math;
import java.lang.System;

public class TestDouble {
    public static void main(String[] args) {
        double r = Double.parseDouble(args[0]);
        System.out.println(Math.PI * r * r);
    }
}
