/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.iftac.lnn.chatlab.core;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lnn
 */
public class ChatParser implements Runnable {

    private Character readChar;
    private String tempString = "";
    private String usefulInfo = "";
    private ArrayList<String> parserList = new ArrayList();
    private final ChatConnection chatCon;
    private final ChatDAO chatDAO;

    public ChatParser(ChatConnection chatCon, ChatDAO chatDAO) {
        this.chatCon = chatCon;
        this.chatDAO=chatDAO;
    }

    @Override
    public void run() {

        while (true) {
            try {
                readChar = (char) chatCon.getIn().read();
            } catch (IOException ex) {
                Logger.getLogger(ChatParser.class.getName()).log(Level.SEVERE, null, ex);
            }
            tempString += readChar.toString();

            switch (tempString) {
                case "<FRIEND>":
                    tempString += "<";
                    for (int i = 0; i < 4; i++) {
                        try {
                            readChar = (char) chatCon.getIn().read();
                        } catch (IOException ex) {
                            Logger.getLogger(ChatParser.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        while (!readChar.toString().equals(">")) {
                            try {
                                readChar = (char) chatCon.getIn().read();
                            } catch (IOException ex) {
                                Logger.getLogger(ChatParser.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            tempString += readChar.toString();
                            usefulInfo += readChar.toString();
                            if (readChar.toString().equals(">") && i != 4) {
                                parserList.add(usefulInfo.substring(0, (usefulInfo.length()-1)));
                                usefulInfo="";
                                tempString += "<";
                            }
                        }
                    }

                    System.out.println(tempString);
            {
                try {
                    chatDAO.addChatUser(new ChatUser(parserList.get(0),parserList.get(1),new ChatUserType(2),parserList.get(2),parserList.get(3)));
                    } catch (SQLException ex) {
                    Logger.getLogger(ChatParser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    tempString = "";
                    break;
                case "<PRIVATE>":
                    tempString += "<";
                    for (int i = 0; i < 2; i++) {
                        try {
                            readChar = (char) chatCon.getIn().read();
                        } catch (IOException ex) {
                            Logger.getLogger(ChatParser.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        while (!readChar.toString().equals(">")) {
                            try {
                                readChar = (char) chatCon.getIn().read();
                            } catch (IOException ex) {
                                Logger.getLogger(ChatParser.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            tempString += readChar.toString();
                            if (readChar.toString().equals(">") && i != 1) {
                                tempString += "<";
                            }
                        }
                    }

                    System.out.println(tempString);
            {
                try {
                    chatDAO.addChatMessage(new ChatMessage(1,2,tempString));
                } catch (SQLException ex) {
                    Logger.getLogger(ChatParser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    tempString = "";
                    break;
                case "<PUBLIC>":
                    tempString += "<";
                    for (int i = 0; i < 2; i++) {
                        try {
                            readChar = (char) chatCon.getIn().read();
                        } catch (IOException ex) {
                            Logger.getLogger(ChatParser.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        while (!readChar.toString().equals(">")) {
                            try {
                                readChar = (char) chatCon.getIn().read();
                            } catch (IOException ex) {
                                Logger.getLogger(ChatParser.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            tempString += readChar.toString();
                            if (readChar.toString().equals(">") && i != 1) {
                                tempString += "<";
                            }
                        }
                    }

                    System.out.println(tempString);
                    tempString = "";
                    break;
                case "<LOGOUT>":
                    tempString += "<";
                    for (int i = 0; i < 1; i++) {
                        try {
                            readChar = (char) chatCon.getIn().read();
                        } catch (IOException ex) {
                            Logger.getLogger(ChatParser.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        while (!readChar.toString().equals(">")) {
                            try {
                                readChar = (char) chatCon.getIn().read();
                            } catch (IOException ex) {
                                Logger.getLogger(ChatParser.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            tempString += readChar.toString();
                            if (readChar.toString().equals(">") && i != 1) {
                                tempString += "<";
                            }
                        }
                    }

                    System.out.println(tempString);
                    tempString = "";
            }

        }

    }
}
