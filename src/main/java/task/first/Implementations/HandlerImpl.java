package main.java.task.first.Implementations;

import main.java.task.first.Interfaces.Converter;
import main.java.task.first.Interfaces.ExpressionParserAndCalc;
import main.java.task.first.Interfaces.Handler;

import java.util.Arrays;
import java.util.List;

public class HandlerImpl implements Handler {
    private final Converter converter;
    private final ExpressionParserAndCalc parserAndCalc;
    private List<String> currencies = Arrays.asList("P", "\\$");
    private StringBuilder editExpression;

    public HandlerImpl() {
        converter = new ConverterImpl();
        parserAndCalc = new ExpressionParserAndCalcImpl();
    }

    @Override
    public double doHandlerAndGetResult(String currency, String expression) throws Exception {
        if (!currencies.contains(currency)) {
            throw new Exception("Такой валюты нет");
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
        newExpression = newExpression.replaceAll(
                currencies.contains(currency) ?
                        currency.toLowerCase()
                        :
                        ""
                , "");
        newExpression = newExpression.replaceAll(",", ".");
        List<String> expressions = parserAndCalc.parse(newExpression);
        double result = 0;
        if (parserAndCalc.isFlag()) {
            result = parserAndCalc.calc(expressions);
        }
        return result;
    }

    private String handlerToRubles(String expression) {
        editExpression = new StringBuilder(expression);
        String[] splitByDollars = handleExpressionToConvert(true, '$').split("/");
        editExpression = new StringBuilder();
        for (String splitExpression : splitByDollars) {
            editExpression.append(
                    splitExpression.contains("$") ?
                            converter.toRubbles(splitExpression)
                            :
                            splitExpression
            );
        }
        return new String(editExpression);
    }

    private String handlerToDollars(String expression) {
        editExpression = new StringBuilder(expression);
        String[] splitByRoubles = handleExpressionToConvert(false, 'p').split("/");
        editExpression = new StringBuilder();
        for (String splitExpression : splitByRoubles) {
            editExpression.append(
                    splitExpression.contains("p") ?
                            converter.toDollars(splitExpression)
                            :
                            splitExpression
            );
        }
        return new String(editExpression);
    }

    private String handleExpressionToConvert(boolean isReversed, char currency) {
        char currencyAtExpression;
        if (isReversed) {
            for (int i = 0; i < editExpression.length(); i++) {
                currencyAtExpression = editExpression.charAt(i);
                if (currency == currencyAtExpression) {
                    editExpression.insert(i, '/');
                    while (currencyAtExpression != '+' && currencyAtExpression != '-'
                            && currencyAtExpression != ')' && i != editExpression.length() - 1) {
                        i++;
                        currencyAtExpression = editExpression.charAt(i);
                    }
                    if (i != editExpression.length() - 1 || currencyAtExpression == ')') {
                        editExpression.insert(i, '/');
                    } else {
                        editExpression.insert(i + 1, '/');
                    }
                    i++;
                }
            }
        } else {
            for (int i = 0; i < editExpression.length(); i++) {
                currencyAtExpression = editExpression.charAt(i);
                if (currency == currencyAtExpression) {
                    int indexr = i;
                    editExpression.insert(i + 1, '/');
                    while (currencyAtExpression != '+' && currencyAtExpression != '-'
                            && currencyAtExpression != '(' && indexr != 0) {
                        indexr--;
                        currencyAtExpression = editExpression.charAt(indexr);
                    }
                    if (indexr != 0) {
                        editExpression.insert(indexr + 1, '/');
                    } else {
                        editExpression.insert(indexr, '/');
                    }
                    i++;
                }
            }
        }
        return new String(editExpression);
    }

}
