package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NewCameraBotTest {
    @Test
    public void detectRings() {
        double expected = 4;
        assertEquals(expected, NewNewCameraBot.detectRings("/sdcard/FIRST/Four Rings.png"), 0.1);
    }
}