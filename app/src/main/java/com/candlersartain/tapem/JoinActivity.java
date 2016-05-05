package com.candlersartain.tapem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button join = (Button) findViewById(R.id.joinBtn);
        join.setOnClickListener(connectListener); //starts the MainActivity
    }

    private View.OnClickListener connectListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(JoinActivity.this.getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    };
}