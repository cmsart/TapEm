package com.candlersartain.tapem;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Drawable right = new ColorDrawable(0xFF00FF00);
    Drawable def = new ColorDrawable(0xFFFFFFFF);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button btnStart = (Button) findViewById(R.id.start);
        final Button btnLeft = (Button) findViewById(R.id.b1);
        final Button btnRight = (Button) findViewById(R.id.b2);

        final ArrayList<Button> buttons = new ArrayList<Button>();
        buttons.add(btnLeft);
        buttons.add(btnRight);

        assert btnStart != null;
        btnStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setActiveButton(v, buttons);
            }
        });
    }

    public void setActiveButton (View v, ArrayList<Button>buttons){
        double rand = Math.random() * 1;
        rand = Math.round(rand);
        buttons.get((int)rand).setBackground(right);
    }

    public void checkTap(View v){
        TextView scoreDisplay = (TextView) findViewById(R.id.scoreNum);
        int scoreNum = Integer.parseInt(scoreDisplay.getText().toString());

        if(v.getBackground() == right){
            scoreNum++;
            String score = Integer.toString(scoreNum);
            scoreDisplay.setText(score);

            v.setBackground(def);
        }
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
