/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.iftac.lnn.chatlab.core;

public class ChatUser {
   
    private String nickName;
    private String fullName;
    private ChatUserType userType;
    private String ipAddress;
    private String additionalInfo;

    public ChatUser(String nickName, String fullName, ChatUserType chatUserType, String ipAddress, String additionalInfo) {
        this.nickName=nickName;
        this.fullName=fullName;
        this.userType=chatUserType;
        this.ipAddress = ipAddress;
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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
    
}
