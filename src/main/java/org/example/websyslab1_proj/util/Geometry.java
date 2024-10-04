package org.example.websyslab1_proj.util;

public class Geometry {
    public static double computeSide3(double side1, double side2, double angleRad) throws IllegalArgumentException {
        if (angleRad >= Math.PI || angleRad <= 0)
            throw new IllegalArgumentException("Use angles between 0 and PI exclusive");
        if (side1 <= 0 || side2 <= 0)
            throw new IllegalArgumentException("Use positive sides");
        return Math.sqrt(Math.pow(side1, 2) + Math.pow(side2, 2) - 2 * side1 * side2 * Math.cos(angleRad));
    }
}
