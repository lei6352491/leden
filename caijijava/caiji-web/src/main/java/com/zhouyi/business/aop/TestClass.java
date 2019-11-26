package com.zhouyi.business.aop;

public class TestClass {

    public static void main(String[] args) {
        new TestClass().test();
    }


    public void test(){
        throw new RuntimeException("1");
    }
}
