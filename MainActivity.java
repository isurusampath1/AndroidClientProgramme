package com.sampath.client;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Test;
import junit.framework.TestCase;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText e1;
    Button button;
        private static Socket s;
        private static PrintWriter printWriter;
        String message = "";
        private static String ip= "192.168.1.110";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.editText);
        button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = e1.getText().toString();

                mytask mt = new mytask();
                mt.execute();
                Toast.makeText(getApplicationContext(), "Data sent", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void send_text(View v){

        System.out.print("Hello hello");

        message = e1.getText().toString();

        mytask mt = new mytask();
        mt.execute();
        Toast.makeText(getApplicationContext(), "Data sent", Toast.LENGTH_SHORT).show();


    }

    class mytask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                System.out.print("Here is Client");

               s = new Socket(ip,5000);
                printWriter = new PrintWriter(s.getOutputStream());
                printWriter.write(message);
                printWriter.flush();
                printWriter.close();
                s.close();







            }catch (IOException e){
                e.printStackTrace();
            }






            return null;
        }
    }
}
