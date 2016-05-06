package com.candlersartain.tapem;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static int score;
    public static boolean active;
    public static ProgressBar bar1;
    public static ProgressBar bar2;
    public static TextView scoreDisplay;

    private Random r = new Random();
    private ArrayList<ImageView> buttons;
    private int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bar1 = (ProgressBar) findViewById(R.id.player1);
        bar2 = (ProgressBar) findViewById(R.id.player2);
        scoreDisplay = (TextView) findViewById(R.id.scoreView);

        score = 0;
        active = true;

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

        //arraylist to keep track of button state
        buttons = new ArrayList<>();
        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        buttons.add(btn4);
        buttons.add(btn5);
        buttons.add(btn6);
        buttons.add(btn7);
        buttons.add(btn8);
        buttons.add(btn9);

        //set first active square
        current = r.nextInt(9);
        buttons.get(current).setTag("active");
        buttons.get(current).setImageResource(R.drawable.active_square);

        //start server communication loop
        new Thread(new Runnable() {
            public void run() {
                while (active) {
                    try{
                        new UpdateTask().execute();
                        Thread.sleep(200);
                    } catch(InterruptedException e){
                        Log.e("MainActivity", "Error starting background thread.", e);
                    }
                }
                Intent intent = new Intent(MainActivity.this.getApplicationContext(), WinnerActivity.class);
                startActivity(intent);
            }
        }).start();

    }

    //check to see if the tapped square is active, if so, increment score
    public void checkTap(View v) {

        if(v.getTag() == "active"){

            //set the clicked button back to default state and remove tag
            buttons.get(current).setImageResource(R.drawable.default_square);
            buttons.get(current).setTag(null);

            int prev = current;
            current = r.nextInt(9);

            //make sure the same square is not repeated
            while(current == prev){
                current = r.nextInt(9);
            }

            //set a new active button
            buttons.get(current).setTag("active");
            buttons.get(current).setImageResource(R.drawable.active_square);

            //increment score and update score display
            score++;
            scoreDisplay.setText("Score: " + score);
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
