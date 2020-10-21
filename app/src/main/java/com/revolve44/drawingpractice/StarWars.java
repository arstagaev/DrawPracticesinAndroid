package com.revolve44.drawingpractice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class StarWars extends View {

    private static final int DEFAULT_CIRCLE_COLOR = Color.RED;

    private int circleColor = DEFAULT_CIRCLE_COLOR;
    private Paint paint;

    private int sizeoval = 4;

    private int numOfFlakes = 400;
    private int FALLINGSPEED = 2;
    private int REFRESHING = 300;
    private int halfwidth, halfheight = 0;


    ArrayList<Double> radiansArray = new ArrayList<>();
    ArrayList<Double> speedArray = new ArrayList<>();
    ArrayList<Double> orbitArrayX = new ArrayList<>();

    DisplayMetrics displaymetrics = new DisplayMetrics();
    public int width =  displaymetrics.widthPixels;
    public int height = displaymetrics.heightPixels;
    ArrayList<Float> differentSizes = new ArrayList<>();

    HashMap<Float, Float> hashMap = new HashMap<>();
    Random rand = new Random();


    public StarWars(Context context) {
        super(context);
    }

    public StarWars(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

//
//    public LetItSnow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
        super.onSizeChanged(xNew, yNew, xOld, yOld);

        width = xNew;
        height = yNew;
        Log.d("width: "," is "+width+" ][ "+height);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        // determine the ideal size of your content, unconstrained
//        int contentWidth = 200;
//        int contentHeight = 200;
//        width = getMeasuredWidth();
//        height = getMeasuredHeight();
//        // must call this method with the measured values!
//        setMeasuredDimension(width, height);
//        Log.d("width: onMeas"," is "+widthMeasureSpec+" ]][[ "+heightMeasureSpec);
//
//    }

    private void init(Context context, AttributeSet attrs)
    {


//        height = 200;
//        width = 600;
//        //onSizeChanged(100,100,0,0);
//
//        halfheight = height/2;
//        halfwidth = width/2;
        //measure();
        Log.d("Width", width+"");

        for (int o = 0; o<numOfFlakes; o++){
            radiansArray.add(Math.toRadians(rand.nextDouble()*360)); //***
            speedArray.add(rand.nextDouble()*4);
            orbitArrayX.add(rand.nextDouble()*100);

        }
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


        for (int i = 0; i<orbitArrayX.size(); i++){

//                g.setColor(Color.GREEN);
//                g.drawLine(50,50,150,50);

            paint.setColor(Color.WHITE);


//                canvas.fillOval((int)(350+1+orbitArrayX.get(i)*(speedArray.get(i)) * Math.cos(radiansArray.get(i))),
//                        (int)(350+1+orbitArrayX.get(i)*(speedArray.get(i)) * Math.sin(radiansArray.get(i))),
//                        sizeoval,sizeoval);
            x = (orbitArrayX.get(i)*(speedArray.get(i)) * Math.cos(radiansArray.get(i)));
            y = orbitArrayX.get(i)*(speedArray.get(i)) * Math.sin(radiansArray.get(i));

            canvas.drawOval((float) (halfwidth+x), (float) (halfheight+y),
                    (float) (halfwidth+sizeoval+x), (float) (halfheight+sizeoval+y),paint);

            orbitArrayX.set(i,orbitArrayX.get(i)+1);
            if (orbitArrayX.get(i)>REFRESHING){
                if (i!=0){
                    orbitArrayX.set(i,rand.nextDouble()*REFRESHING);
                }
            }

        }

        timeInterval++;

        try
        { Thread.sleep(FALLINGSPEED); }
        catch (Exception e) { e.printStackTrace(); }
        invalidate();
    }


}
