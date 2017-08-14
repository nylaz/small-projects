package se.iftac.lnn.chatlab.core;

public class ChatEvent {

    private int EventTypeId;
    private int SenderUserId;
    private int ReceiverUserId;
    private int MessageId;
    private int SessionId;

    public ChatEvent(int EventTypeId, int SenderUserId, int RecieverUserId, int MessageId, int SessionId ) {
        this.EventTypeId=EventTypeId;
        this.SenderUserId=SenderUserId;
        this.ReceiverUserId=RecieverUserId;
        this.MessageId=MessageId;
        this.SessionId=SessionId;
    }

    public int getEventTypeId() {
        return EventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
        EventTypeId = eventTypeId;
    }

    public int getSenderUserId() {
        return SenderUserId;
    }

    public void setSenderUserId(int senderUserId) {
        SenderUserId = senderUserId;
    }

    public int getReceiverUserId() {
        return ReceiverUserId;
    }

    public void setReceiverUserId(int receiverUserId) {
        ReceiverUserId = receiverUserId;
    }

    public int getMessageId() {
        return MessageId;
    }

    public void setMessageId(int messageId) {
        MessageId = messageId;
    }

    public int getSessionId() {
        return SessionId;
    }

    public void setSessionId(int sessionId) {
        SessionId = sessionId;
    }

}
