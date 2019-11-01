package com.jmu.client;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private EditText mEtIp,mEtData;
    private OutputStream mOutputStream = null;
    private Socket mSocket = null;
    private String ip;
    private String data;
    private boolean socketStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEtIp = findViewById(R.id.et_ip);
        mEtData = findViewById(R.id.et_data);
    }

    public void connect(View view){
        ip = mEtIp.getText().toString();
        Toast.makeText(MainActivity.this,""+ip,Toast.LENGTH_SHORT).show();

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                if (!socketStatus) {
                    try {
                        //连接服务端
                        mSocket = new Socket(ip,8000);
                        if(mSocket != null){
                            socketStatus = true;
                        }

                        mOutputStream = mSocket.getOutputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    public void send(View view){
        data = mEtData.getText().toString();
        Toast.makeText(MainActivity.this,""+data,Toast.LENGTH_SHORT).show();

        if (data != null) {
            //在后面加上 '\0' ,是为了在服务端方便我们去解析；
            data = data + '\0';
        }

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                if(socketStatus){
                    try {
                        mOutputStream.write(data.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    /*当客户端界面返回时，关闭相应的socket资源*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*关闭相应的资源*/
        try {
            mOutputStream.close();
            mSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

