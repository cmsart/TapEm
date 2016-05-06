/**
 * Created by Candler on 4/9/2016.
 */
package com.candlersartain.tapem;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class UpdateTask extends AsyncTask<Void, String, String>{

    private static Socket socket;
    private String incoming;
    ProgressBar bar = MainActivity.bar1;
    ProgressBar bar2 = MainActivity.bar2;

    @Override
    protected String doInBackground(Void...params) {

        String serverIpAddress = "104.131.40.244";
        PrintWriter out;
        BufferedReader in;

        try {
            //check to see if there is already an initialized socket
            if(socket == null){
                InetAddress serverAddr = InetAddress.getByName(serverIpAddress);
                socket = new Socket(serverAddr, 1234);
            }

            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(MainActivity.score); //post score to server
            incoming = in.readLine(); //get latest scores from server

            if(incoming != null){
                publishProgress(incoming); //send the score received from server to onProgressUpdate
            }
        } catch (Exception e) {
            Log.e("UpdateTask", "Error initializing socket and/or I/O", e);
        }

        return incoming;
    }

    @Override
    protected void onProgressUpdate(String... scores){
        super.onProgressUpdate(scores);
        String[] s = scores[0].split(" ");

        int s1 = Integer.parseInt(s[0]);
        int s2 = Integer.parseInt(s[1]);

        bar.setProgress(s1); //if no winner, update the progress bar
        bar2.setProgress(s2);
    }

    protected void onPostExecute(String result) {
        int bar1progress = bar.getProgress();
        int bar2progress = bar2.getProgress();

        if(bar1progress >= 25){
            WinnerActivity.win = 1;
            MainActivity.active = false;
            try{
                socket.close();
            } catch(Exception e){
                Log.e("UpdateTask", "Could not close socket.", e);
            }
        } else if(bar2progress >= 25){
            WinnerActivity.win = 2;
            MainActivity.active = false;
            try{
                socket.close();
            } catch(Exception e){
                Log.e("UpdateTask", "Could not close socket.", e);
            }
        }
    }
}
