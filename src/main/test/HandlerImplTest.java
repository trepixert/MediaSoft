package main.test;

import org.junit.Assert;
import org.junit.Test;
import task.first.main.java.task.first.Handler;
import task.first.main.java.task.first.Implementations.HandlerImpl;

public class HandlerImplTest {

    @Test
    public void test(){
        Handler handler = new HandlerImpl();
        String result = handler.doHandlerAndGetResult("P", "737р + ($85.4 - $1) + $1");
        Assert.assertEquals("everythingIsFine",result);
    }

}