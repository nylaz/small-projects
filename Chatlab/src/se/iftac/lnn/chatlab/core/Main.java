package se.iftac.lnn.chatlab.core;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
    
    private static ChatConnection chatCon;
    private static ChatDAO chatDAO;
    private static Scanner input;
    
    public static void main (String[] args) throws IOException, SQLException, ClassNotFoundException{
        
        chatCon = new ChatConnection();
        chatDAO = new ChatDAO();
        Executor par = Executors.newCachedThreadPool();
        par.execute(new ChatParser(chatCon, chatDAO));
        
        chatCon.sendToServer("<REGISTER><FOO><BOO><1.1.1.1><NA>");
        
        input = new Scanner(System.in);
        String a = input.nextLine();
        chatCon.sendToServer("<PRIVATE><FOO><SUPER><"+ a +">");
        String b = input.nextLine();
        chatCon.sendToServer("<PRIVATE><FOO><SUPER><"+ b +">");
        chatCon.sendToServer("<LOGOUT><FOO>");
        
    }   
}
