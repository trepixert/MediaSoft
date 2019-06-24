package task.first.Implementations;

import task.first.Converter;

public class ConverterImpl implements Converter {
    private final double DOLLARCOURSE = 65.75;
    @Override
    public String toDollars(String number) {
        if(!number.contains("p")){
            return null;
        }
        number = number.replace("p","");
        double num = Double.parseDouble(number);
        return (num/DOLLARCOURSE)+"";
    }

    @Override
    public String toRubbles(String number) {
        if(!number.contains("$")){
            return null;
        }
        number = number.replace("$","");
        double num = Double.parseDouble(number);
        return (num*DOLLARCOURSE)+"";
    }
}
