package com.candlersartain.tapem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class WinnerActivity extends AppCompatActivity {

    public static int win = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView winner = (TextView) findViewById(R.id.winnerName);
        if(win == 1){
            winner.setText(R.string.p1_win);
        } else{
            winner.setText(R.string.p2_win);
        }
    }

}
