package com.jmu.servert;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity {

    private TextView mTvInfo;
    private TextView mTvGetData;
    private ServerSocket mServerSocket = null;
    private InputStream mInputSteam;
    private String ip;
    private int port;

    StringBuffer mStringBuffer = new StringBuffer();

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //本地ip和port信息
                    mTvInfo.setText(msg.obj.toString());
                    break;
                case 2:
                    //从客户端获取到的消息
                    mTvGetData.setText("CustomInfo:"+msg.obj.toString());
                    mStringBuffer.setLength(0);
                    break;

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvInfo = findViewById(R.id.info);
        mTvGetData = findViewById(R.id.data);

        receiveData();
    }

    /**
     * 服务器端接收数据
     * 需要注意以下一点：
     * 服务器端应该是多线程的，因为一个服务器可能会有多个客户端连接在服务器上；
     */
    public void receiveData() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                /*指明服务器端的端口号*/
                try {
                    mServerSocket = new ServerSocket(8000);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                getLocalIpAddress(mServerSocket);

                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = "IP:" + ip + " , PORT: " + port;
                handler.sendMessage(msg);

                //持续获取消息
                while (true) {
                    Socket socket = null;
                    try {
                        if (mServerSocket != null) {
                            socket = mServerSocket.accept();
                            mInputSteam = socket.getInputStream();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    new ServerThread(socket, mInputSteam).start();
                }
            }
        };
        thread.start();
    }

    private void getLocalIpAddress(ServerSocket serverSocket) {
        try {
            //for(;;)
            for (Enumeration<NetworkInterface> mEnumNetwork = NetworkInterface.getNetworkInterfaces(); mEnumNetwork.hasMoreElements();) {
                NetworkInterface mNetwork = mEnumNetwork.nextElement();
                for (Enumeration<InetAddress> mEnumIpAddress = mNetwork.getInetAddresses(); mEnumIpAddress.hasMoreElements();) {
                    InetAddress mIpAddress = mEnumIpAddress.nextElement();
                    String mIp = mIpAddress.getHostAddress().substring(0, 3);
                    if(mIp.equals("192")){
                        ip = mIpAddress.getHostAddress();    //获取本地IP
                        port = serverSocket.getLocalPort();    //获取本地的PORT
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    class ServerThread extends Thread {
        private Socket socket;
        private InputStream inputStream;
        private StringBuffer stringBuffer = mStringBuffer;

        public ServerThread(Socket socket, InputStream inputStream) {
            this.socket = socket;
            this.inputStream = inputStream;
        }

        @Override
        public void run() {
            int len;
            byte[] bytes = new byte[24];
            boolean isString = false;

            try {
                if (inputStream != null) {
                    //输入流关闭时循环才会停止
                    //数据读完了，再读是等于0
                    while ((len = inputStream.read(bytes)) != -1) {
                        for (int i = 0; i < len; i++) {
                            if (bytes[i] != '\0') {
                                stringBuffer.append((char) bytes[i]);
                            } else {
                                isString = true;
                                break;
                            }
                        }
                        if (isString) {
                            Message msg = handler.obtainMessage();
                            msg.what = 2;
                            msg.obj = stringBuffer;
                            handler.sendMessage(msg);
                            isString = false;
                        }
                    }
                }
            } catch (IOException e) {
                //当这个异常发生时，说明客户端那边的连接已经断开
                e.printStackTrace();
                try {
                    inputStream.close();
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        }
    }

    /*当按返回键时，关闭相应的socket资源*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            mServerSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

