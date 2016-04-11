/**
 * Created by Candler on 4/9/2016.
 */
package com.candlersartain.tapem;

import android.os.AsyncTask;
import android.util.Log;
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

    private PrintWriter out;
    private BufferedReader in;
    private boolean connected = false;
    public String winner = "you won";

    Socket socket = MainActivity.socket;

    @Override
    protected void onPreExecute(){
            connected = true;
        try{
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e){
            Log.e("UpdateTask", "S: Error", e);
        }

    }

    @Override
    protected String doInBackground(Void...params) {

        while (connected) {
            try {
                out.println(MainActivity.score);
                Thread.sleep(1000);
                String incoming = in.readLine();
                if (incoming != null){
                    publishProgress(Integer.parseInt(incoming));
                }
                Log.d("UpdateTask", "Server response: " + incoming);
            } catch (Exception e) {
                Log.e("UpdateTask", "S: Error", e);
            }
        }

        return winner;
    }

    @Override
    protected void onProgressUpdate(Integer... scores){
        super.onProgressUpdate(scores);
        if(scores[0] >= 25){
            Log.d("UpdateTask", "Score limit reached.");
            connected = false;
            try{
                socket.close();
            } catch(Exception e){
                Log.e("UpdateTask", "Could not close socket.");
            }
        }
    }

    @Override
    protected void onPostExecute(String result){
        Log.d("UpdateTask", result);
    }

}
