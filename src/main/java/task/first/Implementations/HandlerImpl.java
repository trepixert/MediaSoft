package main.java.task.first.Implementations;

import main.java.task.first.Converter;
import main.java.task.first.Handler;

public class HandlerImpl implements Handler {
    private Converter converter = new ConverterImpl();

    @Override
    public String doHandlerAndGetResult(String currency, String expression) {
        expression = expression.replaceAll(" ", "");
        String newExpression = "";
        switch (currency) {
            case "$":
                newExpression = handlerToDollars(expression);
                break;
            case "P":
                newExpression = handlerToRubles(expression);
                return newExpression;
        }
        return null;
    }

    private String handlerToRubles(String expression) {
        StringBuilder sb = new StringBuilder();
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
                sb.append(converter.toRubbles(new String(temp)));
                if (i+1 != expression.length() - 1) {
                    sb.append(symbol);
                }
                i = index;
                continue;
            }
            sb.append(symbol);
        }
        return new String(sb);
    }

    private String handlerToDollars(String expression) {

        return null;
    }

}
