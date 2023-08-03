package com.technicaltransition.ratelimiter.repository;

import com.technicaltransition.ratelimiter.modal.MessageData;

import java.util.ArrayList;
import java.util.List;

public class InMemoryMessageData {
    private List<MessageData> messageDataList = new ArrayList<>();

    public List<MessageData> getMessageDataList() {
        return messageDataList;
    }

    public void setMessageDataList(List<MessageData> messageDataList) {
        this.messageDataList = messageDataList;
    }

    public void addMessageData(MessageData messageData) {
        messageDataList.add(messageData);
    }
}
