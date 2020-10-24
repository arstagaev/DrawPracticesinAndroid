package com.revolve44.drawingpractice.animateLib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class WeatherAnim extends View {

    private static final int DEFAULT_CIRCLE_COLOR = Color.RED;

    private int circleColor = DEFAULT_CIRCLE_COLOR;
    Snow snow = new Snow();

    private Paint paint;

    private int sizeoval = 4;

    public int NUMofELEMENTS = 400;
    private int DELAY_FPS = 2;
    private int SPEED = 4;
    private int REFRESHING = 300;
    private int halfwidth, halfheight = 0;


    ArrayList<Double> radiansArray = new ArrayList<>();
    ArrayList<Double> speedArray = new ArrayList<>();
    ArrayList<Double> orbitArrayX = new ArrayList<>();

    //set sizes
    DisplayMetrics displaymetrics = new DisplayMetrics();
    public int width =  displaymetrics.widthPixels;
    public int height = displaymetrics.heightPixels;
    ArrayList<Float> differentSizes = new ArrayList<>();

    HashMap<Float, Float> hashMap = new HashMap<>();
    Random rand = new Random();
    boolean ClearAll = false;


    public WeatherAnim(Context context) {
        super(context);
    }

    public WeatherAnim(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //init(context, attrs);
    }


    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
        super.onSizeChanged(xNew, yNew, xOld, yOld);

        width = xNew;
        height = yNew;
        Log.d("width: "," is "+width+" ][ "+height);
    }

    public void setNUMofELEMENTS(int NUMofELEMENTS) {
        this.NUMofELEMENTS = NUMofELEMENTS;
        //init();
        //invalidate();
    }

    public void setDELAY_FPS(int DELAY_FPS) {
        this.DELAY_FPS = DELAY_FPS;
        invalidate();
    }

    public void setSPEED(int SPEED) {
        this.SPEED = SPEED;
    }

    public void refreshView(){
        //Clear arrays
        radiansArray.clear();
        speedArray.clear();
        orbitArrayX.clear();

        ClearAll = true;
        paint.reset();
        init();
    }

    public void init() {
        Log.d("Width", width+"");
        Log.d("Start View", width+" " + height+" |[speed= "+SPEED+ " Num of Elements: "+NUMofELEMENTS);
        snow.init(NUMofELEMENTS,SPEED);

//        for (int o = 0; o< NUMofELEMENTS; o++){
//            radiansArray.add(Math.toRadians(rand.nextDouble()*360)); //***
//            speedArray.add(rand.nextDouble()*SPEED);
//            orbitArrayX.add(rand.nextDouble()*100);
//
//        }
        System.out.println("hashmap "+hashMap.toString()+" Whithg "+width);
        paint = new Paint();
        paint.setAntiAlias(true);

    }

    int timeInterval = 0;
    double x = 0;
    double y = 0;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);


        paint.setColor(Color.BLACK);
        canvas.drawRect(0,0,width,height,paint);
        Log.d("Width", "OnDraw "+width+" and H= "+height);
        halfheight = height/2;
        halfwidth = width/2;
        //Log.i("input data", )


        for (int i = 0; i<NUMofELEMENTS; i++){

            paint.setColor(Color.WHITE);
            x = snow.xCoord(i);
            y = snow.yCoord(i);

            canvas.drawOval((float) (halfwidth+x), (float) (halfheight+y),
                    (float) (halfwidth+sizeoval+x), (float) (halfheight+sizeoval+y),paint);

            snow.JustInTimeUpdate(i,REFRESHING);

//            orbitArrayX.set(i,orbitArrayX.get(i)+1);
//            if (orbitArrayX.get(i)>REFRESHING){
//                if (i!=0){
//                    orbitArrayX.set(i,rand.nextDouble()*REFRESHING);
//                }
//            }
        }
        timeInterval++;



        try { Thread.sleep(DELAY_FPS); }
        catch (Exception e) { e.printStackTrace(); }
        invalidate();
    }
}
