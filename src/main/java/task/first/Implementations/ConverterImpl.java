package main.java.task.first.Implementations;

import main.java.task.first.Converter;

public class ConverterImpl implements Converter {
    private final double DOLLAR_COURSE = 65.75;
    @Override
    public String toDollars(String number) {
        if(!number.contains("p")){
            return null;
        }
        number = number.replace("p","");
        double num = Double.parseDouble(number);
        return "$"+(num/ DOLLAR_COURSE);
    }

    @Override
    public String toRubbles(String number) {
        if(!number.contains("$")){
            return null;
        }
        number = number.replace("$","");
        double num = Double.parseDouble(number);
        return (num* DOLLAR_COURSE)+"p";
    }
}
