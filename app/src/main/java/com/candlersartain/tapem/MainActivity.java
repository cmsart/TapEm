package com.candlersartain.tapem;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button btnStart = (Button) findViewById(R.id.start); //start button

        //buttons for game GUI
        ImageView btn1 = (ImageView) findViewById(R.id.btn1);
        ImageView btn2 = (ImageView) findViewById(R.id.btn2);
        ImageView btn3 = (ImageView) findViewById(R.id.btn3);
        ImageView btn4 = (ImageView) findViewById(R.id.btn4);
        ImageView btn5 = (ImageView) findViewById(R.id.btn5);
        ImageView btn6 = (ImageView) findViewById(R.id.btn6);
        ImageView btn7 = (ImageView) findViewById(R.id.btn7);
        ImageView btn8 = (ImageView) findViewById(R.id.btn8);
        ImageView btn9 = (ImageView) findViewById(R.id.btn9);

        assert btnStart != null;
        btnStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int active = getRandom();
                ImageView clickedButton = (ImageView) findViewById(active);
                clickedButton.setTag("active");
                clickedButton.setImageResource(R.drawable.active_square);
            }
        });
    }

    public void checkTap(View v) {
        //TextView scoreDisplay = (TextView) findViewById(R.id.scoreNum);
        //int scoreNum = Integer.parseInt(scoreDisplay.getText().toString());

        ImageView currentButton = (ImageView) findViewById(v.getId());

        if(currentButton.getTag() == "active"){

            currentButton.setImageResource(R.drawable.default_square);
            currentButton.setTag(null);

            int active = getRandom();
            ImageView nextActiveButton = (ImageView) findViewById(active);
            nextActiveButton.setTag("active");
            nextActiveButton.setImageResource(R.drawable.active_square);
        }
    }

    public int getRandom (){
        Random r = new Random();
        int rand = r.nextInt((2131492978 - 2131492970) + 1) + 2131492970;

        return rand;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
