package main.java.task.first.Implementations;

import main.java.task.first.Interfaces.Converter;
import main.java.task.first.Interfaces.ExpressionParserAndCalc;
import main.java.task.first.Interfaces.Handler;

import java.util.Arrays;
import java.util.List;

public class HandlerImpl implements Handler {
    private final Converter converter;
    private final ExpressionParserAndCalc parserAndCalc;
    private List<String> symbolsCurrency = Arrays.asList("\\$");
    private List<String> lettersCurrency = Arrays.asList("P");

    public HandlerImpl() {
        converter = new ConverterImpl();
        parserAndCalc = new ExpressionParserAndCalcImpl();
    }

    @Override
    public double doHandlerAndGetResult(String currency, String expression) throws Exception {
        if (!symbolsCurrency.contains(currency)) {
            if (!lettersCurrency.contains(currency)) {
                throw new Exception("Такой валюты нет");
            }
        }
        expression = expression.replaceAll(" ", "");
        String newExpression = "";
        switch (currency) {
            case "\\$":
                newExpression = handlerToDollars(expression);
                break;
            case "P":
                newExpression = handlerToRubles(expression);
                break;
        }
        newExpression = newExpression.replaceAll(symbolsCurrency.contains(currency) ?
                        currency
                        :
                        lettersCurrency.contains(currency) ?
                                currency.toLowerCase()
                                : ""
                , "");
        newExpression = newExpression.replaceAll(",", ".");
        List<String> expressions = parserAndCalc.parse(newExpression);
        boolean flag = parserAndCalc.isFlag();
        double result = 0;
        if (flag) {
            result = parserAndCalc.calc(expressions);
        }
        return result;
    }

    private String handlerToRubles(String expression) {
        StringBuilder editExpression = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char symbol = expression.charAt(i);
            if (symbol == '$') {
                StringBuilder temp = new StringBuilder();
                int index = i;
                while (symbol != '+' && symbol != '-'
                        && symbol != ')' && index != expression.length() - 1) {
                    temp.append(symbol);
                    index++;
                    symbol = expression.charAt(index);
                }
                if (index == expression.length() - 1) {
                    temp.append(symbol);
                }
                editExpression.append(converter.toRubbles(new String(temp)));
                if (i + 1 != expression.length() - 1) {
                    editExpression.append(symbol);
                }
                i = index;
                continue;
            }
            editExpression.append(symbol);
        }
        return new String(editExpression);
    }

    private String handlerToDollars(String expression) {
        StringBuilder editExpression = new StringBuilder(expression);
        for (int i = 0; i < editExpression.length(); i++) {
            char symbol = editExpression.charAt(i);
            if (symbol == 'p') {
                int indexr = i;
                editExpression.insert(i + 1, '/');
                while (symbol != '+' && symbol != '-'
                        && symbol != '(' && indexr != 0) {
                    indexr--;
                    symbol = editExpression.charAt(indexr);
                }
                if (indexr != 0) {
                    editExpression.insert(indexr + 1, '/');
                } else {
                    editExpression.insert(indexr, '/');
                }
                i++;
            }
        }
        String[] splitByRoubles = new String(editExpression).split("/");
        editExpression = new StringBuilder();
        for (int i = 0; i < splitByRoubles.length; i++) {
            String splitExpression = splitByRoubles[i];
            editExpression.append(
                    splitExpression.contains("p") ?
                            converter.toDollars(splitExpression)
                            :
                            splitExpression
            );
        }
        return new String(editExpression);
    }

}
