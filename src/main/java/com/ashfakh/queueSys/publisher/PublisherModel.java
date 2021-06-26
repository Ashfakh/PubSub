package com.ashfakh.queueSys.publisher;


import com.ashfakh.queueSys.queue.MessageQueue;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class PublisherModel {

    private Optional<MessageQueue> msgQueue = Optional.empty();

    public PublisherModel(MessageQueue messageQueue) {
        msgQueue = Optional.of(messageQueue);
    }

    public void publish(String msg) {
        if (msgQueue.isPresent()) {
            System.out.println("Publishing to msgQueue : " + msgQueue.get().getQueueName());
            msgQueue.get().publish(Collections.singletonList(msg));
        } else {
            System.out.println("Publishing to msgQueue : " + msgQueue.get().getQueueName());
        }
    }

    public void publish(List<String> msgs) {
        if (msgQueue.isPresent()) {
            System.out.println("Publishing to msgQueue : " + msgQueue.get().getQueueName());
            msgQueue.get().publish(msgs);
        } else {
            System.out.println("Publishing to msgQueue : " + msgQueue.get().getQueueName());
        }
    }

}
