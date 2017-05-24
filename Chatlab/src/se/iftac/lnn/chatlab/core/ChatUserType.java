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
public class ChatUserType {
    
    private int typeId;
    private String description;
    
    public ChatUserType(int typeId){
        this.typeId=typeId;
        if(this.typeId == 1){
            setDescription("Self");
        }
        else if (typeId == 2){
            setDescription("Friend");
        }else {
            throw new IllegalArgumentException();
        }
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int TypeId) {
        this.typeId = TypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
