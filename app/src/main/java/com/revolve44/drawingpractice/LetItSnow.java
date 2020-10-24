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

import com.revolve44.drawingpractice.animateLib.Snow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class LetItSnow extends View {
    private static final int DEFAULT_CIRCLE_COLOR = Color.RED;

    private int circleColor = DEFAULT_CIRCLE_COLOR;
    private Paint paint;

    private int sizeoval = 2;

    private int NUMofELEMENTS = 400;
    private int FALLINGSPEED = 2;

    public int width;
    public int height;
    ArrayList<Float> differentSizes = new ArrayList<>();


    HashMap<Float, Float> hashMap = new HashMap<>();
    Random rand = new Random();

    ArrayList<Float> xSnowFlake = new ArrayList<>();
    ArrayList<Float> ySnowFlake = new ArrayList<>();
    ArrayList<Float> SPEED = new ArrayList<>();
    Snow snow = new Snow();

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


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        snow.init(NUMofELEMENTS,0,h,w);
    }

    private void init(Context context, AttributeSet attrs)
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displaymetrics);

        height = displaymetrics.heightPixels;
        width = displaymetrics.widthPixels;

        System.out.println("hashmap "+xSnowFlake.toString()+" Whithg "+width);
        paint = new Paint();
        paint.setAntiAlias(true);

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



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        paint.setColor(Color.BLUE);
        canvas.drawRect(0,0,width,height,paint);
        Log.d("Width", width+"");

        for (int i = 0; i<NUMofELEMENTS; i++){
            paint.setColor(Color.WHITE);

            canvas.drawCircle((float) snow.xCoord(i), (float) snow.yCoord(i),5,paint);
            snow.moveAndCheck(i);
        }


        try
        { Thread.sleep(FALLINGSPEED); }
        catch (Exception e) { e.printStackTrace(); }
        invalidate();
    }
}
