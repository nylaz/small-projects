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
public class ChatMessage {
    
    private int messageDirectionId;
    private int messageTypeId;
    private String messageText;
    
    public ChatMessage(int messageDirectionId, int messageTypeId, String messageText){
        this.messageDirectionId=messageDirectionId;
        this.messageTypeId=messageTypeId;
        this.messageText=messageText;
    }

    public int getMessageDirectionId() {
        return messageDirectionId;
    }

    public void setMessageDirectionId(int messageDirectionId) {
        this.messageDirectionId = messageDirectionId;
    }

    public int getMessageTypeId() {
        return messageTypeId;
    }

    public void setMessageTypeId(int MessageTypeId) {
        this.messageTypeId = MessageTypeId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String MessageText) {
        this.messageText = MessageText;
    }
}