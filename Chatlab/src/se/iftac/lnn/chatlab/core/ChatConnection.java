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
import java.util.ArrayList;

public class ChatConnection {
    
    private final Socket socket;
    private final OutputStreamWriter out;
    private final BufferedReader in;
    private final String address;
    private final int port;
    
    public ChatConnection(String address, int port) throws IOException {
        this.address = address;
        this.port = port;
        this.socket = new Socket(address,port);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new OutputStreamWriter(socket.getOutputStream());
            
    }
    
    Socket getSocket() {
        return socket;
    }

    OutputStreamWriter getOut() {
        return out;
    }

    BufferedReader getIn() {
        return in;
    }

    String getAddress() {
        return this.address;
    }

    int getPort() {
        return this.port;
    }

    void privateMessage(ChatUser from, ChatUser to, String message) throws IOException {
        out.write("<PRIVATE><" + from.getNickName() + "><" + to.getNickName() + "><" + message + ">");
        out.flush();
    }

    void publicMessage(ChatUser user, String message) throws IOException {
        out.write("<PUBLIC><" + user.getNickName() + "><" + message + ">");
        System.out.println("PUBLIC: <" + user.getNickName() + "> " + message);
        out.flush();
    }

    void logOut(ChatUser user) throws IOException {
        out.write("<LOGOUT><" + user.getNickName() + ">");
        out.flush();
    }

    void register(ChatUser user) throws IOException {
        out.write("<REGISTER><" + user.getNickName() + "><" + user.getFullName() + "><" + user.getIpAddress() + "><" + user.getAdditionalInfo() + ">");
        out.flush();
    }

}
