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
        assertTrue(case3.calculateCaseThree(1, 1, 1));
        assertFalse(case3.anotherFoobar(-1));

    }
}