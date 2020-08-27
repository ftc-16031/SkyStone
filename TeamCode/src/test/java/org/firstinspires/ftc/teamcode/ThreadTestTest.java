package org.firstinspires.ftc.teamcode;

import org.junit.Test;
import static org.junit.Assert.*;

public class ThreadTestTest {
    @Test
    public void foobarTest() {
        ThreadTest test = new ThreadTest();
        assertTrue(test.foobar(12));
        assertFalse(test.foobar(-1));
    }

    @Test
    public void anotherFoobar() {
        ThreadTest test = new ThreadTest();
        ThreadTest.CaseThree case3 = test.new CaseThree();
        assertTrue(case3.anotherFoobar(12));
        assertFalse(case3.anotherFoobar(-1));

    }

    @Test
    public void calculateCaseThreeTest() {
        ThreadTest test = new ThreadTest();
        ThreadTest.CaseThree case3 = test.new CaseThree();
        double[] expected1 = {1.37, 0.37};
        double[] expected2 = {1.87, 1.23};
        double[] expected3 = {-2.83, 5.10};
        double[] expected4 = {-6.60, -5.43};
        double[] expected5 = {0.95, -0.32};
        double[] expected6 = {5.00, 5.00};
        test.previousVL = 0;
        test.previousVR = 0;
        assertArrayEquals(expected1, case3.calculateCaseThree(1, 1, 1, 60), 0.01);
        test.previousVL = 0;
        test.previousVR = 0;
        assertArrayEquals(expected2, case3.calculateCaseThree(2, 2, 1, 60), 0.01);
        test.previousVL = 0;
        test.previousVR = 0;
        assertArrayEquals(expected3, case3.calculateCaseThree(3, 3, -5, 60), 0.01);
        test.previousVL = 0;
        test.previousVR = 0;
        assertArrayEquals(expected4, case3.calculateCaseThree(-8, -8, -3, 60), 0.01);
        test.previousVL = 0;
        test.previousVR = 0;
        assertArrayEquals(expected5, case3.calculateCaseThree(1, -1, 1, 60), 0.01);
        test.previousVL = 0;
        test.previousVR = 0;
        assertArrayEquals(expected6, case3.calculateCaseThree(5, 5, 5, 90), 0.01);
    }
}