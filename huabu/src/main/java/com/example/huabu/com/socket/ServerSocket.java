package com.example.huabu.com.socket;

import android.content.Intent;
import android.os.Bundle;

import com.example.huabu.com.MainActivity;
import com.example.huabu.com.print.PaintView;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by 撩个小媳妇 on 2018/5/10.
 */

public class ServerSocket implements Runnable {

    java.net.ServerSocket mServerSocket;
    Socket mClient;
    ClientSocket mClientSocket;
    PaintView paintView;

    private boolean isRunning = true;

    public ServerSocket(PaintView paintView) {
        this.paintView = paintView;
    }

    public void exit() {
        isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning) {
            if (mClient == null) {
                try {
                    mClient = accept();//监听连接
                    SocketConfig.CLIENT_IP = mClient.getInetAddress().getHostAddress();
                    sendBroadcast();
                } catch (IOException e) {
                    mClient = null;
                    mServerSocket = null;
                }
            }
            if (mClientSocket == null && mClient != null) {
                try {
                    mClientSocket = new ClientSocket(mClient);
                    paintView.setClientSocket(mClientSocket);
                } catch (IOException e) {
                    try {
                        mClient.close();
                    } catch (IOException e1) {
                    }
                    mClient = null;
                    mClientSocket = null;
                }
            }

            Thread.yield();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
        disconnect();
    }

    private void sendBroadcast() {
        Intent intent = new Intent(SocketConfig.BDC_CONNECT_CLIENT);
        Bundle bundle = new Bundle();
        bundle.putString(SocketConfig.KEY_CLIENT_IP, SocketConfig.CLIENT_IP);
        intent.putExtras(bundle);
        MainActivity.localBroadcastManager.sendBroadcast(intent);
    }


    private void disconnect() {
        mClientSocket.exit();
        try {
            mClient.close();
        } catch (IOException e) {
        }
        try {
            mServerSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mClientSocket = null;
        mClient = null;
        mServerSocket = null;
    }

    private Socket accept() throws IOException {
        if (mServerSocket == null) {
            mServerSocket = new java.net.ServerSocket(SocketConfig.SVR_PORT);
        }
        return mServerSocket.accept();
    }
}
