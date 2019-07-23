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
    private static final InstancesContainerSingleton instance;

    private InstancesContainerSingleton(){}

    static{
        instance = new InstancesContainerSingleton();
    }

    public static InstancesContainerSingleton getInstance(){
        return instance;
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
