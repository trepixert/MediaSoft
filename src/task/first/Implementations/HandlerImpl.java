package task.first.Implementations;

import task.first.Converter;
import task.first.Handler;

import java.util.Stack;

public class HandlerImpl implements Handler {
    private Converter converter = new ConverterImpl();
    private final String toDollars = "toDollars";
    private final String toRubles = "toRubles";
    private Stack<String> expressionInStack;

    @Override
    public String doHandler(String expression) {
        expressionInStack = makeAndGetStack(expression);
        return null;
    }

    private Stack<String> makeAndGetStack(String expression) {
        
        return null;
    }
}
