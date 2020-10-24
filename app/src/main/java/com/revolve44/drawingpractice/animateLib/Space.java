package com.revolve44.drawingpractice.animateLib;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class Space implements WeatherPrinciple {

    Random rand = new Random();

    ArrayList<Double> radiansArray = new ArrayList<>();
    ArrayList<Double> speedArray = new ArrayList<>();
    ArrayList<Double> orbitArrayX = new ArrayList<>();

    int heightY = 0;
    int widthX = 0;


    @Override
    public void init(int NUMofELEMENTS, int SPEED, int INPUTheightY, int INPUTwidthX) {
        for (int o = 0; o< NUMofELEMENTS; o++){
            radiansArray.add(Math.toRadians(rand.nextDouble()*360)); //***
            speedArray.add(rand.nextDouble()*SPEED);
            orbitArrayX.add(rand.nextDouble()*100);

        }
        heightY = INPUTheightY;
        widthX =INPUTwidthX;
        Log.d("H/W"," W "+ widthX+" H "+heightY);

    }

    double x = 0;
    double y = 0;
    @Override
    public double xCoord(int i) {
        //[height of Orbit]    [speed ]        [angle to move]
        x = (orbitArrayX.get(i) * (speedArray.get(i)) * Math.cos(radiansArray.get(i)));

        return x;

    }

    @Override
    public double yCoord(int i) {
       // x = (orbitArrayX.get(i) * (speedArray.get(i)) * Math.cos(radiansArray.get(i)));
        y = orbitArrayX.get(i) * (speedArray.get(i)) * Math.sin(radiansArray.get(i));


        return y;

    }

//    public void JustInTimeUpdate(int i, int height, int width){
//         orbitArrayX.set(i,orbitArrayX.get(i)+1);
////        if (orbitArrayX.get(i)>REFRESHING){
////            if (i!=0){
////                orbitArrayX.set(i,rand.nextDouble()*REFRESHING);
////            }
////        }
//    }

    public void JustInTimeUpdate(int i, int REFRESHING){
        orbitArrayX.set(i,orbitArrayX.get(i)+1);
        if (orbitArrayX.get(i)>REFRESHING){
            if (i!=0){
                orbitArrayX.set(i,rand.nextDouble()*REFRESHING);
            }
        }
    }
}
