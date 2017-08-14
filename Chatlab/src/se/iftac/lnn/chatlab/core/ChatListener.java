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

    public ChatListener(ChatConnection con) throws IOException, SQLException {
        this.chatCon = con;
        this.chatDAO = new ChatDAO();
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
            if ( returnValue != -1 ) {
                segment += (char)returnValue;
            } else {
                throw new ChatListenerException();
            }
        } while ( ((char)returnValue) != '>' );
        return segment.substring(1, segment.length() - 1);
    }

    @Override
    public void run() {

        try {
            while (true) {
                String tempString = parseSegment(chatCon);
                System.out.println("charlistener:" + tempString);

                switch (tempString) {
                    case "FRIEND": {
                        // <FRIEND><YOURNICKNAME><FULLNAME><IPADDRESS><INFO>
                        String nick = parseSegment(chatCon);
                        String name = parseSegment(chatCon);
                        String ip = parseSegment(chatCon);
                        String info = parseSegment(chatCon);

                        ChatUser user = new ChatUser(nick, name, ChatUserType.FRIEND, ip, info);
                        try {
                            chatDAO.addChatUser(user);
                        } catch (SQLException ex) {
                            Logger.getLogger(ChatListener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("* " + user.getNickName() + " logged on");
                        //chatWindow.addUser(user);
                        break;
                    }
                    case "PRIVATE": {
                        // <PRIVATE><NICKNAME><Message>
                        String nick = parseSegment(chatCon); // ?
                        String msg = parseSegment(chatCon);
                        try {
                            chatDAO.addChatMessage(new ChatMessage(1, 2, msg));
                        } catch (SQLException ex) {
                            Logger.getLogger(ChatListener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("PRIVATE: <" + nick + "> " + msg);
                        //chatWindow.incomingPrivateMessage(nick, msg);
                        break;
                    }
                    case "PUBLIC": {
                        // <PUBLIC><NICKNAME><Message>
                        String nick = parseSegment(chatCon);
                        String msg = parseSegment(chatCon);
                        try {
                            chatDAO.addChatMessage(new ChatMessage(1, 1, msg));
                        } catch (SQLException ex) {
                            Logger.getLogger(ChatListener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("<" + nick + "> " + msg);
                        //chatWindow.publicMessage(nick, msg);
                        break;
                    }
                    case "LOGOUT": {
                        // <LOGOUT><YOURNICKNAME>
                        String nick = parseSegment(chatCon);
                        System.out.println("* " + nick + " logged out");
                        //chatWindow.logOut(nick);
                    }
                }

            }
        } catch (ChatListenerException e) {
            System.out.println("Chat connection was interrupted.");
        }

    }
}
