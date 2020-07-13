package com.square.mall.cache.constant;


public enum WorkModel {
    SINGLE("single"),
    CLUSTER("cluster"),
    SHARDING("sharding");

    private String name;


    WorkModel(String name) { this.name = name; }



    public String getName() { return this.name; }
}
