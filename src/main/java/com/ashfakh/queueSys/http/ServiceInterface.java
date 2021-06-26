package com.ashfakh.queueSys.http;

import com.ashfakh.queueSys.entity.RequestModel;
import com.ashfakh.queueSys.entity.ResponseModel;

public interface ServiceInterface {
    ResponseModel makeHttpCall(String endPoint, RequestModel requestModel);
}
