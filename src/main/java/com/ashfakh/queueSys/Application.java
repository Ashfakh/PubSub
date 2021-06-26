package com.ashfakh.queueSys;

import com.ashfakh.queueSys.enums.SubscriberPriority;
import com.ashfakh.queueSys.publisher.PublisherModel;
import com.ashfakh.queueSys.queue.MessageQueue;
import com.ashfakh.queueSys.subscriber.Subscriber;
import com.ashfakh.queueSys.subscriber.SubscriberModel;

import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue("Queue1", 10);
        PublisherModel publisherModel = new PublisherModel(queue);
        queue.subscribe(new Subscriber("s1",0,2,3, SubscriberPriority.EARLIEST,"http://service1.com"));
        publisherModel.publish("abc");
        publisherModel.publish("def");
        publisherModel.publish("ghi");
        queue.subscribe(new Subscriber("s2",0,2,2, SubscriberPriority.EARLIEST,"http://service2.com"));
        queue.subscribe(new Subscriber("s3",0,3,1, SubscriberPriority.LATEST,"http://service3.com"));
        publisherModel.publish(Arrays.asList("jkl","nmo","pqr"));

    }
}
