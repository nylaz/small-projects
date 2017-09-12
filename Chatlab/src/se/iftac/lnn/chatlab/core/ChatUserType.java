/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.iftac.lnn.chatlab.core;

public enum ChatUserType {
    SELF(1), FRIEND(2);

    private int typeId;

    ChatUserType(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId){
        this.typeId=typeId;
    }
}
