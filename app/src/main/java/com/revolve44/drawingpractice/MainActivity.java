package com.revolve44.drawingpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import com.revolve44.drawingpractice.animateLib.WeatherAnim;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private SeekBar numSeek;
    private SeekBar speedSeek;

    StarWars starWars;
    WeatherAnim weatherAnim;
    LetItSnow letItSnow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numSeek = (SeekBar) findViewById(R.id.num);
        speedSeek = (SeekBar) findViewById(R.id.speed);

        weatherAnim = findViewById(R.id.wa);
        weatherAnim.init(1);

        letItSnow  = findViewById(R.id.ls);

//        PathView path_view = (PathView) findViewById(R.id.path);
//        path_view.init();
//        starWars = findViewById(R.id.starwars);
//        //starWars.setNUMofELEMENTS(2000);
//        //starWars.setSPEED(seekBar.getProgress()); //4
//        starWars.init();


        numSeek.setOnSeekBarChangeListener(this);;
        speedSeek.setOnSeekBarChangeListener(this);;

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        System.out.println("lox ");

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.equals(numSeek))
        {
            System.out.println("lox ");
            starWars.setNUMofELEMENTS(seekBar.getProgress());
            //starWars.setSPEED(8); //4
            starWars.refreshView();
        }
        else if (seekBar.equals(speedSeek))
        {
            // do something else
            System.out.println("lox ");
            //starWars.setNUMofELEMENTS(2000);
            starWars.setSPEED(seekBar.getProgress()); //4
            starWars.refreshView();
        }
        System.out.println("lox ");
        System.out.println("lox " + seekBar.getProgress());
    }
    int a = 0;
    public void switchType(View view) {
        if (a<=1){
            weatherAnim.init(a);
            a++;
        }else {
            a=0;
        }




    }
}