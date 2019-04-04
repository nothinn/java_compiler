/**
 * TestDouble.java
 */

import java.lang.Double;
import java.lang.Math;
import java.lang.System;

public class TestDouble {
    public static void main(String[] args) {
        int a = 42;

        double b = 4.2;

        //a = a-a;

        //b = b-b;

        a = a - b;


        double r = Double.parseDouble(args[0]);
        System.out.println(Math.PI * r * r);
    }
}
