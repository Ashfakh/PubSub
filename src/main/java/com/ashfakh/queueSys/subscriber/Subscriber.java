package com.ashfakh.queueSys.subscriber;

import com.ashfakh.queueSys.entity.RequestModel;
import com.ashfakh.queueSys.enums.SubscriberPriority;
import com.ashfakh.queueSys.http.ExampleServiceImpl;
import com.ashfakh.queueSys.http.ServiceInterface;

public class Subscriber extends SubscriberModel {

    String endPoint;

    public Subscriber(String name, int offset,
               int batchSize,
               int maxRetries,
               SubscriberPriority subscribePriority,
               String endPoint) {
        super(name, offset, batchSize, maxRetries, subscribePriority);
        this.endPoint = endPoint;
    }


    //WIll be using beans or verticles here for scalability
    ServiceInterface service = new ExampleServiceImpl();


    @Override
    public void task(String json) {
        //Todo:Have an object mapper to add string into Json
        service.makeHttpCall(endPoint, new RequestModel(json));
    }
}
