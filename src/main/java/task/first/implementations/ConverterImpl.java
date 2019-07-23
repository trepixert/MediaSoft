package task.first.implementations;

import task.first.interfaces.Converter;
import task.first.singletons.ResourcesContainerSingleton;

public class ConverterImpl implements Converter {

    private final double DOLLAR_COURSE;

    public ConverterImpl() {
        DOLLAR_COURSE = ResourcesContainerSingleton.getInstance().getDollarCourse();
    }

    @Override
    public String toDollars(String number) {
        if (!number.contains("p")) {
            return null;
        }
        number = number.replace("p", "");
        double num = Double.parseDouble(number);
        double result = num / DOLLAR_COURSE;
        return String.format("$%.2f", result);
    }

    @Override
    public String toRubbles(String number) {
        if (!number.contains("$")) {
            return null;
        }
        number = number.replace("$", "");
        double num = Double.parseDouble(number);
        return (num * DOLLAR_COURSE) + "p";
    }
}
