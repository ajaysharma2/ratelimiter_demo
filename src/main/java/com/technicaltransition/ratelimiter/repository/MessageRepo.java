package com.technicaltransition.ratelimiter.repository;

import com.technicaltransition.ratelimiter.modal.MessageData;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<MessageData, Long> {

    MessageData getMessageById(long messageId);
}
