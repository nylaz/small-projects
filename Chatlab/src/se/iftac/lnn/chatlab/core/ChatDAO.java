/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.iftac.lnn.chatlab.core;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatDAO {
    
    private static final String ADD_CHATUSER = "INSERT INTO ChatUser ( NickName, FullName, UserTypeId, IpAdress, AdditionalInfo) "
            + "VALUES (?,?,?,?,?)";
    private static final String GET_CHATUSER = "SELECT NickName, FullName, UserTypeId, IpAdress, AdditionalInfo from ChatUser WHERE NickName=?";
    private static final String CHECK_USER_EXIST = "SELECT NickName from ChatUser WHERE NickName=?";
    private static final String ADD_MESSAGE = "INSERT INTO Message ( MessageDirectionId, MessageTypeId, MessageText) " 
            + "VALUES (?,?,?)";
    private static final String ADD_EVENT = ("INSERT INTO EventLog ( EventTypeId, SenderUserId, ReceiverUserId, MessageId, SessionId) " 
            + "VALUES (?)");
    private static final String ADD_SESSION = "INSERT INTO Session ()";
    private static final String ADD_CHAT_MESSAGE = "INSERT INTO Message (MessageDirection, MessageTypeId, MessageText) "
			+ "VALUES (?;?;?)";
    private static final String ADD_ERROR  = "";
    private final Connection dbConnection;

    public ChatDAO() throws SQLException, IOException {
        dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/ChatDB?" + "user=root&password=123456");
    }


    public void addChatUser(ChatUser user) throws SQLException {
        if (chatUserExists(user.getNickName())) {
            PreparedStatement statement = dbConnection.prepareStatement(ADD_CHATUSER);
            statement.setString(1, user.getNickName());
            statement.setString(2, user.getFullName());
            statement.setInt(3, user.getUserType().getTypeId());
            statement.setString(4, user.getIpAddress());
            statement.setString(5, user.getAdditionalInfo());
            statement.execute();
        }
    }
    
    public boolean chatUserExists(String nickName) throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement(CHECK_USER_EXIST);
        statement.setString(1, nickName);
        ResultSet result = statement.executeQuery();
        return (result == null);
    }

    public void addEvent(ChatEvent event) throws SQLException{
        PreparedStatement statement = dbConnection.prepareStatement(ADD_EVENT);
        statement.setInt(1, event.getEventTypeId());
        statement.setInt(2, event.getSenderUserId());
        statement.setInt(3, event.getReceiverUserId());
        statement.setInt(4, event.getMessageId());
        statement.setInt(5, event.getSessionId());
        statement.execute();
    }

    public ChatUser getChatUser(String nickName) throws Exception {
        PreparedStatement statement = dbConnection.prepareStatement(GET_CHATUSER);
        statement.setString(1, nickName);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            nickName = result.getString("NickName");
            String fullName = result.getString("FullName");
            int userTypeId = result.getInt("UserTypeId");
            String ipAdress = result.getString("IpAdress");
            String additionalInfo = result.getString("AdditionalInfo");
            return new ChatUser(nickName, fullName, ChatUserType.values()[userTypeId], ipAdress, additionalInfo);
        }
        else {
            throw new Exception("Can't find user.");
        }
    }
    
    public void addChatMessage(ChatMessage message) throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement(ADD_MESSAGE);
        statement.setInt(1, message.getMessageDirectionId());
        statement.setInt(2, message.getMessageTypeId());
        statement.setString(3, message.getMessageText());
        statement.execute();
    }
    
    public ChatMessage getChatMessage(){
        return null;
    }
    
    public void logError (Exception e) throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement(ADD_ERROR);
        statement.setObject(1, e);
        statement.setString(2, "Error");
    }
}