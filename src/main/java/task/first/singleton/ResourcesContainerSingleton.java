package task.first.singleton;

import task.first.interfaces.UploadProperty;

import java.util.Properties;

public class ResourcesContainerSingleton {
    private static ResourcesContainerSingleton INSTANCE;

    private UploadProperty property = InstancesContainerSingleton.getInstance().uploadProperty();

    private String operators;
    private String delimiters;
    private double dollarCourse;

    private ResourcesContainerSingleton() {
        Properties properties = new Properties();
        properties = property.upload(properties);
        dollarCourse = Double.parseDouble(properties.getProperty("app.course", "65.75"));
        operators = properties.getProperty("app.operators", "+-");
        delimiters = properties.getProperty("app.delimiters", "()+-");
    }

    public static ResourcesContainerSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ResourcesContainerSingleton();
        }
        return INSTANCE;
    }

    public String getOperators() {
        return operators;
    }

    public String getDelimiters() {
        return delimiters;
    }

    public double getDollarCourse() {
        return dollarCourse;
    }
}
