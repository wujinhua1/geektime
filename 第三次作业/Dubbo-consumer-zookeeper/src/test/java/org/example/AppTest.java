package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * Unit test for simple DubboConsumerApplication.
 */
public class AppTest {

    @Test
    public void test(){
        String str = "0";
        BigDecimal bigDecimal1 = BigDecimal.valueOf(0.76);
        BigDecimal bigDecimal = new BigDecimal("0.75");
        if(bigDecimal1.compareTo(bigDecimal)>0){
            str = "1";
        }
        System.out.println(bigDecimal1);
        System.out.println(str);
    }
}
