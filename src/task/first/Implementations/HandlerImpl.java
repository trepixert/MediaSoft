package task.first.Implementations;

import task.first.Converter;
import task.first.Handler;

public class HandlerImpl implements Handler {
    private Converter converter = new ConverterImpl();

    @Override
    public String doHandler(String expression) {
        return null;
    }
}
