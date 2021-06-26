package com.ashfakh.queueSys.queue;

import com.ashfakh.queueSys.enums.SubscriberPriority;
import com.ashfakh.queueSys.subscriber.SubscriberModel;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageQueue {

    private int MAX_SIZE = 10;
    private String queueName;
    private List<String> queue = new Vector<>();

    public MessageQueue(String queueName, Integer size) {
        this.queueName = queueName;
        this.MAX_SIZE = size;
    }

    private ConcurrentLinkedQueue<SubscriberModel> subscribers = new ConcurrentLinkedQueue<>();

    public void subscribe(SubscriberModel subscriber) throws RuntimeException {
        subscribers.add(subscriber);
        if (subscriber.getSubscribePriority() == SubscriberPriority.EARLIEST) {
            runInBatches(subscriber, queue);
        }

    }


    public void publish(List<String> jsons) throws RuntimeException {
        if (jsons.size() > MAX_SIZE)
            throw new RuntimeException("Can't add more than " + MAX_SIZE + " elements to the queue");
        if (jsons.size() > MAX_SIZE - queue.size()) {
            queue = queue.subList(0, MAX_SIZE - jsons.size());
            queue.addAll(jsons);
        } else {
            queue.addAll(jsons);
        }
        if (subscribers.isEmpty()) {
            System.out.println("No subscribers attached");
            return;
        }
        //TODO : publish is synchronous now, can have a separate threadpool and run this in parallel
        subscribers.forEach(s -> runInBatches(s, jsons));


    }

    void runInBatches(SubscriberModel subscriberModel, List<String> jsons) {
        List<List<String>> batches = Lists.partition(jsons, subscriberModel.getBatchSize());
        batches.forEach(b -> {
                    subscriberModel.run(b);
                    subscriberModel.setOffset((subscriberModel.getOffset() + b.size())%MAX_SIZE);
                }
        );
    }

    public String getQueueName() {
        return this.queueName;
    }


}
