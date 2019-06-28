package main.test;

import main.java.task.first.Implementations.ConverterImpl;
import main.java.task.first.Interfaces.Converter;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConverterImplTest {

    @Test
    public void toDollars() {
        Converter converter = new ConverterImpl();
    }

    @Test
    public void toRubbles() {
        Converter converter = new ConverterImpl();
        String result = converter.toRubbles("$85.4");
        BigDecimal resultOf = new BigDecimal(result).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("5615.05", resultOf.toString());
    }
}