package main.java.task.first.Implementations;

import main.java.task.first.Interfaces.Converter;
import main.java.task.first.Interfaces.UploaderRateFromFile;

import java.io.File;
import java.util.Scanner;

public class ConverterImpl implements Converter {
    private final double DOLLAR_COURSE;
    private UploaderRateFromFile uploaderRateFromFile = path -> {
        File file = new File(path);
        try (Scanner scanner = new Scanner(file)){
            return Double.parseDouble(scanner.nextLine());
        }catch (Exception e){
            System.err.println(file.getAbsolutePath());
        }
        return 65.75;
    };

    public ConverterImpl() {
        DOLLAR_COURSE = uploaderRateFromFile.read("C:\\Users\\Ramis\\IdeaProjects\\MediaSoft\\src\\main\\java\\resources\\Rate.txt");
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
