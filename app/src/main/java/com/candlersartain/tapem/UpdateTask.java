/**
 * Created by Candler on 4/9/2016.
 */
package com.candlersartain.tapem;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class UpdateTask extends AsyncTask<Void, Integer, String>{

    private static Socket socket;
    private String incoming;
    ProgressBar bar = MainActivity.bar1;
    TextView scoreDisplay = MainActivity.scoreDisplay;

    @Override
    protected String doInBackground(Void...params) {

        String serverIpAddress = "104.131.40.244";
        PrintWriter out;
        BufferedReader in;
        try {
            //check to see if there is already an initialized socket
            if(socket == null){
                InetAddress serverAddr = InetAddress.getByName(serverIpAddress);
                socket = new Socket(serverAddr, 6969);
            }

            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(MainActivity.score); //post score to server
            incoming = in.readLine(); //get latest score from server

            //send the score received from server to onProgressUpdate
            if (incoming != null){
                publishProgress(Integer.parseInt(incoming));
            }
            //Log.d("UpdateTask", "Server response: " + incoming);
        } catch (Exception e) {
            Log.e("UpdateTask", "S: Error", e);
        }

        return incoming;
    }

    @Override
    protected void onProgressUpdate(Integer... scores){
        super.onProgressUpdate(scores);
        int s = scores[0];

        //if a winner has been reached, end server communication thread and close the socket
        if(s >= 25){
            //Log.d("UpdateTask", "Score limit reached.");
            MainActivity.active = false;
            scoreDisplay.setText("You win!");
            try{
                socket.close();
            } catch(Exception e){
                Log.e("UpdateTask", "Could not close socket.");
            }
        }
        else {
            bar.setProgress(s); //if no winner, update the progress bar
        }
    }
}
