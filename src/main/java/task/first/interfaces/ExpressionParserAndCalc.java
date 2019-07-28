package task.first.interfaces;

import java.util.List;

public interface ExpressionParserAndCalc {
    List<String> parse(String infix);
    double calc(List<String> postfix);
}
