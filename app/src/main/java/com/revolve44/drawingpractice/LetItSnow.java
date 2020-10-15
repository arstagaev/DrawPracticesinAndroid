package com.revolve44.drawingpractice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LetItSnow extends View {
    private static final int DEFAULT_CIRCLE_COLOR = Color.RED;

    private int circleColor = DEFAULT_CIRCLE_COLOR;
    private Paint paint;

    private int sizeoval = 2;

    private int numOfFlakes = 400;
    private int FALLINGSPEED = 6;

    public int width;
    public int height;
    ArrayList<Float> differentSizes = new ArrayList<>();


    HashMap<Float, Float> hashMap = new HashMap<>();
    Random rand = new Random();


    public LetItSnow(Context context) {
        super(context);
    }

    public LetItSnow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
//
//    public LetItSnow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }


    private void init(Context context, AttributeSet attrs)
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displaymetrics);

        height = displaymetrics.heightPixels;
        width = displaymetrics.widthPixels;

        for (int o = 0; o<numOfFlakes; o++){
            //Also init all the drops
//            hashMap.put(rand.nextInt((int) (400f- 1f)) + 1f,-rand.nextInt((int) (400f- 1f)) + 1f );
            hashMap.put((float) rand.nextFloat()*width,-rand.nextFloat()*height);
            differentSizes.add(rand.nextFloat()*2);

        }
        System.out.println("hashmap "+hashMap.toString()+" Whithg "+width);
        paint = new Paint();
        paint.setAntiAlias(true);
        moveIt();

    }



    public void setCircleColor(int circleColor)
    {
        this.circleColor = circleColor;
        invalidate();
    }

    public int getCircleColor()
    {
        return circleColor;
    }

    int controlForArray = 0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        controlForArray = 0;


        paint.setColor(Color.BLACK);
        canvas.drawRect(0,0,width,height,paint);
        Log.d("Width", width+"");
       // canvas.drawCircle(40,hashMap.get(20f),20,paint);
       // canvas.drawOval(40,40,46,46,paint);
        for (HashMap.Entry<Float, Float> e : hashMap.entrySet()) {
            //sizeoval = rand.nextInt(4);

            Float key = e.getKey();
            Float value = e.getValue();
            paint.setColor(Color.WHITE);

            canvas.drawCircle(key,value,differentSizes.get(controlForArray),paint);

            e.setValue(value+rand.nextInt((int) (2f- 1f)) + 1f );
            controlForArray++;

            if (value>height){ e.setValue(1f); }
        }

        try
        { Thread.sleep(FALLINGSPEED); }
        catch (Exception e) { e.printStackTrace(); }
        invalidate();
    }

    private void moveIt() {
//        for (int turn = 0; turn<360;turn++ ){
//            try
//            { Thread.sleep(FALLINGSPEED); }
//            catch (Exception e) { e.printStackTrace(); }
//            invalidate();
//            Log.d("moveIt","yep");
//            if (turn>330){
//                turn = 0;
//            }
//        }

    }


}
