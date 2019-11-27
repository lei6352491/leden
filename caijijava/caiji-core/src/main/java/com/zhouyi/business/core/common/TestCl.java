package com.zhouyi.business.core.common;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class TestCl {


    public static void main(String[] args) {
        System.out.println(getCurrentMonthDay());
    }


    public void test2() throws RuntimeException{
       throw new RuntimeException("ss") ;
    }


    /**
     * 获取当月有多少天
     * @return
     */
    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }




}

