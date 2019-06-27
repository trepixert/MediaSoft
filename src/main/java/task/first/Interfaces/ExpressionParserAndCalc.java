package main.java.task.first.Interfaces;

import java.util.List;

public interface ExpressionParserAndCalc {
    List<String> parse(String infix);
    double calc(List<String> postfix);

    boolean isFlag();
}
