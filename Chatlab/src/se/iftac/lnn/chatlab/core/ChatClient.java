package se.iftac.lnn.chatlab.core;

import java.io.IOException;
import java.sql.SQLException;

public class ChatClient {

    public static void main (String[] args) throws SQLException, ClassNotFoundException, IOException {
        ChatConnection con = new ChatConnection("chatlab.testbed.se", 8000);
        ChatListener listener = new ChatListener(con);
        Thread listenthread = new Thread(listener);
        listenthread.start();

        ChatUser tester = new ChatUser("nylazz", "Lars Nyman", ChatUserType.SELF, "192.168.1.1", "N/A");
        ChatUser superbot = new ChatUser("SUPER", "SUPER", ChatUserType.FRIEND, "1.2.3.4", "N/A");

        con.register(tester);
        con.privateMessage(tester, superbot,"Hej Super");
        try { Thread.sleep(10000); } catch ( InterruptedException ie ) { }
        con.publicMessage(tester, "test");
        try { Thread.sleep(10000); } catch ( InterruptedException ie ) { }
        con.privateMessage(tester, superbot,"Hej igen Super");
        try { Thread.sleep(10000); } catch ( InterruptedException ie ) { }
        con.privateMessage(tester, superbot,"Hej igen Super");
        try { Thread.sleep(10000); } catch ( InterruptedException ie ) { }
        con.logOut(tester);
    }
}
