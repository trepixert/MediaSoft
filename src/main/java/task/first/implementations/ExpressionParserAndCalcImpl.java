package task.first.implementations;

import task.first.interfaces.ExpressionParserAndCalc;
import task.first.singletons.ResourcesContainerSingleton;

import java.util.*;

public class ExpressionParserAndCalcImpl implements ExpressionParserAndCalc {
    private ResourcesContainerSingleton resource = ResourcesContainerSingleton.getInstance();

    private final String operators = resource.getOperators();
    private final String delimiters = resource.getDelimiters();


    /**
     * Проверка на наличия разделителя
     *
     * @param token текущий токен
     * @return результат проверки на наличие разделителя
     */
    private boolean isDelimiter(String token) {
        return delimiters.contains(token);
    }

    private boolean isOperator(String token) {
        return operators.contains(token);
    }

    /**
     * @param token лексемма
     * @return приоритет лексемы
     */
    private int priority(String token) {
        if (token.equals("(")) return 1;
        if (token.equals("+") || token.equals("-")) return 2;
        return 3;
    }

    /**
     * В данном методе происходит разделение между числами и их операторами(разделителями)
     * проверяется согласованность скобок и их операторов
     * Сначала все числа добавляются в список постфикс, а операторы и скобки в стэк
     * когда все числа считанны, все элементы из стэка выталкиваются в инфиксный список
     *
     * @param infix инфиксное выражение (обычное выражение)
     * @return возвращает выражение в постфиксном виде
     */
    public List<String> parse(String infix) {
        List<String> postfix = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(infix, delimiters, true);
        String curr;
        try {
            while (tokenizer.hasMoreTokens()) {
                curr = tokenizer.nextToken();
                if (checkExpression(tokenizer, curr)) {
                    return postfix;
                }
                if (isDelimiter(curr)) {
                    if (curr.equals("(")) {
                        stack.push(curr);
                        continue;
                    }

                    if (curr.equals(")")) {
                        getNumsIntoBrackets(postfix, stack);
                        continue;
                    }

                    getOperatorsFromStack(postfix, stack, curr);

                } else {
                    postfix.add(curr);
                }
            }
            while (!stack.isEmpty()) {
                if (isOperator(stack.peek())) {
                    postfix.add(stack.pop());
                } else {
                    checkForConsistencyOfBrackets(stack);
                    return postfix;
                }
            }
            return postfix;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "\n" +
                    "Завершение работы программы.");
            System.exit(1);
            return null;
        }
    }

    /**
     * Исходя из приоритетов операторов
     * @see ExpressionParserAndCalcImpl#priority(String)
     * добавляет операторы в результативное выражение
     *
     * @param postfix результативное выражение
     * @param stack стэк операторов и скобок
     * @param curr текущий токен
     */
    private void getOperatorsFromStack(List<String> postfix, Stack<String> stack, String curr) {
        while (!stack.isEmpty() && (priority(curr) <= priority(stack.peek()))) {
            postfix.add(stack.pop());
        }
        stack.push(curr);
    }

    /**
     * Метод для получения всех чисел внутри скобки
     *
     * @param postfix результирующий список
     * @param stack   стэк с операторами и скобками
     */
    private void getNumsIntoBrackets(List<String> postfix, Stack<String> stack) {
        while (!stack.peek().equals("(")) {
            postfix.add(stack.pop());
            checkForConsistencyOfBrackets(stack);
        }
        stack.pop();
        if (!stack.isEmpty()) {
            postfix.add(stack.pop());
        }
    }

    /**
     * вызывается в определенный момент для проверки согласованности скобок
     * @see #parse(String)
     * @see #getNumsIntoBrackets(List, Stack)
     *
     * @param stack стэк операторов и скобок
     */
    private void checkForConsistencyOfBrackets(Stack<String> stack) {
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Скобки не согласованы.");
        }
    }

    /**
     * корректность оператора проверяется в методе
     *
     * @param tokenizer токенайзер
     * @param curr      текущий токен
     * @return результат проверки на корректность оператора и выражения
     * @see ExpressionParserAndCalcImpl#isOperator(String)
     */
    private boolean checkExpression(StringTokenizer tokenizer, String curr) {
        if (!tokenizer.hasMoreTokens() && isOperator(curr)) {
            throw new IllegalArgumentException("Некорректное выражение. Возможно указан один из неподдерживаемых операторов.");
        }
        return false;
    }

    /**
     * На вход поступает результативное выражение в форме списка
     * После чего проходим по каждому элементу выражения и с помощью
     * switch-case определяем оператор, в случае если элемент не оператор, то
     * он просто добавляется в стэк, если оператор, то
     * в случае сложения мы достаём из стэка последние 2 добавленных элемента(числа) - складываем
     * и помещаем результат в стэк
     * в случае разности делаем тоже самое, однако поскольку работаем со стэком, то нужно ввести два переменных
     * чтобы вычислить числа в необходимом порядке
     *
     * @param postfix результативное выражение
     * @return результат вычисления
     */
    public double calc(List<String> postfix) {
        Stack<Double> stack = new Stack<>();
        for (String x : postfix) {
            switch (x) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    Double b = stack.pop(), a = stack.pop();
                    stack.push(a - b);
                    break;
                default:
                    stack.push(Double.valueOf(x));
                    break;
            }
        }
        return stack.pop();
    }
}
