package com.ashfakh.queueSys.http;

import com.ashfakh.queueSys.entity.RequestModel;
import com.ashfakh.queueSys.entity.ResponseModel;

public class ExampleServiceImpl implements ServiceInterface {

    @Override
    public ResponseModel makeHttpCall(String endpoint, RequestModel requestModel) {
        //Make http call via rest client here, Have a common rest client for the system
        System.out.println("Making http call to : " + endpoint + " with request " + requestModel.toString());
        return new ResponseModel();
    }
}
