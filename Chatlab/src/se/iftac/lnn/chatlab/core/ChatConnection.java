/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.iftac.lnn.chatlab.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author lnn
 */
public class ChatConnection {
    
    private final Socket socket;
    private final OutputStreamWriter out;
    private final BufferedReader in;
    private final String adress = "chatlab.testbed.se";
    private final int port = 8000;


    
    public ChatConnection() throws IOException{
        this.socket = new Socket(adress,port);
        this.in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
        this.out = new OutputStreamWriter(socket.getOutputStream());
            
    }
    
    public Socket getSocket() {
        return socket;
    }

    public OutputStreamWriter getOut() {
        return out;
    }

    public BufferedReader getIn() {
        return in;
    }

    public String getAdress() {
        return adress;
    }

    public int getPort() {
        return port;
    }

    public void sendToServer(String msg) throws IOException {
        out.write(msg);
        out.flush();
    }
}
