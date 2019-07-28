package task.first.singletons;

import task.first.implementations.ConverterImpl;
import task.first.implementations.ExpressionParserAndCalcImpl;
import task.first.implementations.HandlerImpl;
import task.first.implementations.UploadPropertyImpl;
import task.first.interfaces.Converter;
import task.first.interfaces.ExpressionParserAndCalc;
import task.first.interfaces.Handler;
import task.first.interfaces.UploadProperty;

public class InstancesContainerSingleton {
    private static final InstancesContainerSingleton INSTANCE;

    private InstancesContainerSingleton(){}

    static{
        INSTANCE = new InstancesContainerSingleton();
    }

    public static InstancesContainerSingleton getInstance(){
        return INSTANCE;
    }

    public Converter converter(){
        return new ConverterImpl();
    }

    public Handler handler(){
        return new HandlerImpl();
    }

    public UploadProperty uploadProperty(){
        return new UploadPropertyImpl();
    }

    public ExpressionParserAndCalc parserAndCalc(){
        return new ExpressionParserAndCalcImpl();
    }
}
