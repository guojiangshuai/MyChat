
package com.huanghua.client;

import com.huanghua.pojo.User;
import com.huanghua.service.ChatService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class RegisterThread implements Runnable {
    private Socket mSocket;
    private DataInputStream mDis;
    private DataOutputStream mDos;
    private ChatService mService = null;
    private User mUser;

    public RegisterThread(ChatService service, User u) {
        mService = service;
        mUser = u;
    }

    public RegisterThread() {

    }

    @Override
    public void run() {
        try {
            mSocket = new Socket("192.168.1.94", 12345);
            mDis = new DataInputStream(mSocket.getInputStream());
            mDos = new DataOutputStream(mSocket.getOutputStream());
            startRegister(mUser);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private void startRegister(User u) {
        try {
            mDos.writeUTF("<#USERREGISTER#>" + u.getName() + "|" + u.getPassword());
            String msg = mDis.readUTF();
            if (msg != null && msg.startsWith("<#REGISTERSUCCES#>")) {
                String userId = msg.substring(18);
                u.setId(userId);
                mService.userRegisterSucces(mUser);
            } else {
                mService.userRegisterFail();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (mDis != null) {
                mDis.close();
            }
            if (mDos != null) {
                mDos.close();
            }
            if (mSocket != null) {
                mSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
