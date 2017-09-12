/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.iftac.lnn.chatlab.core;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatListener implements Runnable {

    private ChatConnection chatCon;
    private ChatDAO chatDAO;
    private ChatClient client;

    public ChatListener(ChatConnection con, ChatClient client, ChatDAO chatDAO) throws IOException, SQLException {
        this.chatCon = con;
        this.client=client;
        this.chatDAO=chatDAO;
    }

    private String parseSegment(ChatConnection con) throws ChatListenerException {
        String segment = "";
        int returnValue;
        do {
            try {
                returnValue = con.getIn().read();
            } catch (IOException ex) {
                Logger.getLogger(ChatListener.class.getName()).log(Level.SEVERE, null, ex);
                return "";
            }
            if (returnValue != -1) {
                segment += (char) returnValue;
            } else {
                throw new ChatListenerException();
            }
        } while (((char) returnValue) != '>');
        return segment.substring(1, segment.length() - 1);
    }

    @Override
    public void run() {

        try {
            while (true) {
                String typeOfMsg = parseSegment(chatCon);
                switch (typeOfMsg) {
                    case "FRIEND": {
                        // <FRIEND><YOURNICKNAME><FULLNAME><IPADDRESS><INFO>
                        String nick = parseSegment(chatCon);
                        String name = parseSegment(chatCon);
                        String ip = parseSegment(chatCon);
                        String info = parseSegment(chatCon);
                        try {
                            ChatUser user = new ChatUser(nick, name, ChatUserType.FRIEND, ip, info);
                            chatDAO.addChatUser(user);
                            client.newLogin(chatDAO.getChatUser(nick));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case "PRIVATE": {
                        // <PRIVATE><NICKNAME><Message>
                        String nick = parseSegment(chatCon);
                        String msg = parseSegment(chatCon);
                        try {
                            chatDAO.addChatMessage(new ChatMessage(1, 2, msg));
                            client.newPrivateMessage(nick, msg);
                        } catch (SQLException ex) {
                            Logger.getLogger(ChatListener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                    case "PUBLIC": {
                        // <PUBLIC><NICKNAME><Message>
                        String nick = parseSegment(chatCon);
                        String msg = parseSegment(chatCon);
                        try {
                            chatDAO.addChatMessage(new ChatMessage(1, 1, msg));
                            client.newPublicMessage(nick, msg);
                        } catch (SQLException ex) {
                            Logger.getLogger(ChatListener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                    case "LOGOUT": {
                        // <LOGOUT><YOURNICKNAME>
                        String nick = parseSegment(chatCon);
                        System.out.println("* " + nick + " logged out");
                        try {
                            client.newLogout(chatDAO.getChatUser(nick));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        } catch (ChatListenerException e) {
            System.out.println("Chat connection was interrupted.");
        }

    }
}
