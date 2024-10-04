package org.example.websyslab1_proj.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class GeometryTest {

    @Test
    void computeSide3() {
        double error = 0.00001;
        assertTrue(Geometry.computeSide3(3, 4, Math.PI / 2) - 5 < error);
        assertTrue(Geometry.computeSide3(15, 8, Math.PI / 2) - 17 < error);
        assertTrue(Geometry.computeSide3(8, 8, Math.PI / 3) - 8 < error);
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Geometry.computeSide3(1, 1, -2);
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Geometry.computeSide3(1, 1, 22);
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Geometry.computeSide3(-1, 1, Math.PI / 4);
            }
        });
    }
}