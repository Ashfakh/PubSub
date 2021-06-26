package com.ashfakh.queueSys.subscriber;


import com.ashfakh.queueSys.enums.SubscriberPriority;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.ashfakh.queueSys.enums.SubscriberPriority.LATEST;

@AllArgsConstructor
public abstract class SubscriberModel {

    //Subscriber name
    String name;
    //Offset is set as a value from which it continues (not to be used now, can be used in case persisting into DB and reinitializing subscriber)
    int offset = 0;
    //Batch size at which subscriber gets messages
    int batchSize = 1;
    //Retry count
    int maxRetries = 1;
    SubscriberPriority subscribePriority= LATEST;


    public void run(List<String> jsons){
        System.out.println("Running in sub "+ name +" batch size : "+jsons.size());
        jsons.forEach(msg -> run(msg,0));
    }

    public void run(String json,int retryCount){
        if(retryCount<=maxRetries) {
            try {
                task(json);
            } catch (Exception e) {
                System.out.println("Exception in iteration " + retryCount + ": retriying");
                run(json, retryCount + 1);

            }
        }else{
            System.out.println("Retry counts exceeded limit" +maxRetries);
        }
    }

    public abstract void task(String json);

    public int getBatchSize(){
        return this.batchSize;
    }

    public void setOffset(int offset){
        this.offset = offset;
    }

    public int getOffset(){
        return this.offset;
    }

    public SubscriberPriority getSubscribePriority(){
        return this.subscribePriority;
    }


}
