package main.test;

import main.java.task.first.Handler;
import main.java.task.first.Implementations.HandlerImpl;
import org.junit.Assert;
import org.junit.Test;


public class HandlerImplTest {

    @Test
    public void test(){
        Handler handler = new HandlerImpl();
        String result = handler.doHandlerAndGetResult("P", "737р + ($85.4 - $1) + $1");
        Assert.assertEquals("737р+(5615.05p-65.75p)+65.75p",result);
    }

}