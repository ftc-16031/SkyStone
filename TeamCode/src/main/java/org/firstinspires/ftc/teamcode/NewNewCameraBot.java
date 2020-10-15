package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.RobotLog;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;


public class NewNewCameraBot extends FourWheelDriveBot {

    static public class Area extends Object{
        public int x;
        public int y;
        public int width;
        public int height;
    }

    final static int startX = 1320;
    final static int startY = 500;
    final static int boxWidth = 90;
    final static int boxHeight = 90;
    final static int numberOfColumns = 5;
    final static int numberOfRows = 5;

    protected static  Area[][] boxes = new Area[numberOfColumns][numberOfRows];

    public NewNewCameraBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {



        super.init(ahwMap);
    }

    @Override
    public void print(String message) {

    }

    final static int NORINGS = 0;
    final static int ONERING = 1;
    final static int FOURRINGS = 4;

    static protected void printAndSave(Bitmap bmp, int average, String label){
        RobotLog.d("Image %s with %d x %d and average RGB #%02X #%02X #%02X", label, bmp.getWidth(), bmp.getHeight(), Color.red(average), Color.green(average), Color.blue(average));
        try (FileOutputStream out = new FileOutputStream(String.format("/sdcard/FIRST/ftc_%s.png", label))) {
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static public int detectRings(String fileName) {
        Bitmap bmp = BitmapFactory.decodeFile(fileName);

        for (int i = 0; i < numberOfColumns; i++) {
            for (int j = 0; j < numberOfRows; j++) {
                boxes[i][j] = new Area();
                boxes[i][j].x = startX + boxWidth * i;
                boxes[i][j].y = startY + boxHeight * j;
                boxes[i][j].width = boxWidth;
                boxes[i][j].height = boxHeight;
            }
        }

        Bitmap[][] b = new Bitmap[numberOfColumns][numberOfRows];
        int[][] c = new int[numberOfColumns][numberOfRows];
        for (int i = 0; i < numberOfColumns; i++) {
            for (int j = 0; j < numberOfRows; j++) {
                b[i][j] = Bitmap.createBitmap(bmp, boxes[i][j].x, boxes[i][j].y, boxes[i][j].width, boxes[i][j].height);
                c[i][j] = getAverageRGB(b[i][j]);
                printAndSave(b[i][j], c[i][j], String.format("box_%d_%d", i, j));
            }
        }
        RobotLog.d("Created 9 sub-bitmaps");
        RobotLog.d("Calculate AVG for 9 sub-bitmaps");
        RobotLog.d("Saved 9 sub-bitmaps");
        int numberOfRings = chooseRings(c);
        RobotLog.d("Determine # of rings through 9 sub-bitmaps");
        return numberOfRings;

    }

    static public int chooseRings (int[][] c){

        int correctBoxes = 0;
        for (int i = 0; i < numberOfColumns; i++) {
            for (int j = 0; j < numberOfRows; j++) {
                if (colourIsCorrect(c[i][j], i, j)) {
                    correctBoxes++;
                }
            }
        }
        if (correctBoxes <= 2) {
            return NORINGS;
        } else if (correctBoxes <= 5) {
            return ONERING;
        } else {
            return FOURRINGS;
        }
    }

    static public boolean colourIsCorrect (int c, int i, int j) {
        int red = Color.red(c);
        int green = Color.green(c);
        int blue = Color.blue(c);

        if ((120 < red && 200 > red && 50 < green && 110 > green) || (120 < red && 200 > red && 100 > blue)) {
            RobotLog.d(String.format("Box %d,%d is true", i, j));
            return true;
        } else {
            RobotLog.d(String.format("Box %d,%d is false", i, j));
            return false;
        }
    }

    static protected int getAverageRGB (Bitmap bmp){

        int totalRed = 0;
        int totalGreen = 0;
        int totalBlue = 0;
        int width = bmp.getWidth();
        int height = bmp.getHeight();

        for (int x=0; x < width; x++) {
            for (int y=0; y < height; y++) {
                int pixel = bmp.getPixel(x, y);

                int red = Color.red(pixel);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);

                totalRed += red;
                totalGreen += green;
                totalBlue += blue;
            }
        }

        int averageRed = totalRed / (width * height);
        int averageGreen = totalGreen / (width * height);
        int averageBlue = totalBlue / (width * height);

        return Color.rgb(averageRed, averageGreen, averageBlue);
    }

}
