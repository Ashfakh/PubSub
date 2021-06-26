package com.ashfakh.queueSys.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RequestModel {

    String json;

    @Override
    public String toString() {
        return json;
    }
}
