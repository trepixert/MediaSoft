package main.test;

import org.junit.Assert;
import org.junit.Test;
import task.first.main.java.task.first.Converter;
import task.first.main.java.task.first.Implementations.ConverterImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConverterImplTest {

    @Test
    public void toDollars() {
        Converter converter = new ConverterImpl();
        String result = converter.toDollars("737p");
        BigDecimal resultOf = new BigDecimal(result).setScale(1, RoundingMode.HALF_UP);
        Assert.assertEquals("11.2", resultOf.toString());
    }

    @Test
    public void toRubbles() {
        Converter converter = new ConverterImpl();
        String result = converter.toRubbles("$85.4");
        BigDecimal resultOf = new BigDecimal(result).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("5615.05", resultOf.toString());
    }
}