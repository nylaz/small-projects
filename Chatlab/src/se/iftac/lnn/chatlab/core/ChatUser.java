/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.iftac.lnn.chatlab.core;

/**
 *
 * @author lnn
 */
public class ChatUser {
   
    private String nickName;
    private String fullName;
    private ChatUserType userType;
    private String ipAdress;
    private String additionalInfo;

    public ChatUser(String nickName, String fullName, ChatUserType chatUserType, String ipAdress, String additionalInfo) {
        this.nickName=nickName;
        this.fullName=fullName;
        this.userType=chatUserType;
        this.ipAdress=ipAdress;
        this.additionalInfo=additionalInfo;
    }

    public ChatUserType getUserType() {
        return userType;
    }

    public void setUserType(ChatUserType userType) {
        this.userType = userType;
    }
    

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
    
}
