package com.technicaltransition.ratelimiter.service;

import com.technicaltransition.ratelimiter.modal.MessageData;
import com.technicaltransition.ratelimiter.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageService {

    @Autowired
    private MessageRepo messageRepo;

    public MessageRepo getMessageRepo() {
        return messageRepo;
    }

    public void setMessageRepo(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public MessageData getMessageById(long id) {
        return messageRepo.getMessageById(id);
    }

    public void setMessageData(MessageData messageData) {
        messageRepo.save(messageData);
    }

    public Iterable<MessageData> findAll() {
        return messageRepo.findAll();
    }
}
