package main.test;

import main.java.task.first.Interfaces.Handler;
import main.java.task.first.Implementations.HandlerImpl;
import org.junit.Assert;
import org.junit.Test;


public class HandlerImplTest {

    @Test
    public void test(){
        Handler handler = new HandlerImpl();
        double result = 0;
        try {
            result = handler.doHandlerAndGetResult("\\$", "737p + ($85.4 - 1p) + $1");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(97.59,result);
    }

}