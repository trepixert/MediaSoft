package task.first.implementations;

import task.first.interfaces.Converter;
import task.first.interfaces.ExpressionParserAndCalc;
import task.first.interfaces.Handler;
import task.first.singletons.InstancesContainerSingleton;

import java.util.Arrays;
import java.util.List;

public class HandlerImpl implements Handler {
    private InstancesContainerSingleton instance = InstancesContainerSingleton.getInstance();
    private final Converter converter;
    private final ExpressionParserAndCalc parserAndCalc;
    private List<String> currencies = Arrays.asList("P", "\\$");
    private StringBuilder editExpression;

    public HandlerImpl() {
        converter = instance.converter();
        parserAndCalc = instance.parserAndCalc();
    }

    /**
     * Метод обработки, введенного пользователем, выражения
     * Сначала идет проверка на наличие необходимой валюты, далее обработка и привод выражения
     * к той валюте, которую изначально ввел пользователь, например:
     * если валюта доллар, то методом обработки выражения будет:
     *
     * @param currency   валюта
     * @param expression выражение, которое необходимо посчитать
     * @return результатом является выполнение выражения
     * @throws Exception в случае, если введенная пользователем валюта не обнаружится
     * @see HandlerImpl#handlerToDollars(String)
     * если валюта рубль, то:
     * @see HandlerImpl#handlerToRubles(String)
     * Смысл обработки в том, чтобы конвертировать все выражение к одной валюте
     * Далее выражение обрабатывается обратной польской нотацией:
     * @see ExpressionParserAndCalcImpl#parse(String)
     * и вычисляется:
     * @see ExpressionParserAndCalcImpl#calc(List)
     */
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
        newExpression =
                newExpression
                        .replaceAll(
                                currencies.contains(currency) ?
                                        currency.toLowerCase()
                                        :
                                        ""
                                , "")
                        .replaceAll(",", ".");
        List<String> expressions = parserAndCalc.parse(newExpression);

        return parserAndCalc.calc(expressions);
    }

    /**
     * @param expression выражение, которое необходимо конвертировать
     * @return конвертированное выражение в рублях
     */
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

    /**
     * @param expression выражение, которое необходимо конвертировать
     * @return конвертированное выражение в долларах
     */
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

    /**
     * Чтобы было удобнее выделить, засплитить и конвертировать нужные числа, я их "завернул" в /,
     * в первом случае применяется реверсированное выделение числа, то есть сначала ищется число с интересующей
     * валютой, например рубль: 737р р находится справа, поэтому чтобы выделить данное число (поместить / перед 7
     * Итоговый вариант: /737p/) нужно уйти на несколько индексов влево, что и делается в теле if
     * во втором случае применяется линейной выделение числа, например: ($85.4 -> итоговый вариант /$85.4/)
     * перед валютой ставится / и дальше идёт обычный переход по индексам, пока не будет достигнут один из символов (+, -,(
     * или дойдёт до конца строки) и далее при возврате строки к функции, что её вызвала начинается сплит по /, таким образом
     * мы отделяем нужные нам числа от других
     *
     * @param isReversed поскольку валюта в рублях пишется справа, а в долларах слева, то
     *                   я принял решение разделить поиск чисел на два вида:
     *                   в первом случае, если валюта, аналогично рублю, будет находиться справа
     *                   от числа, то применяется реверсированное выделение числа, а иначе
     *                   линейное (объяснение выше)
     * @param currency   валюта, которую нужно конвертировать
     * @return строку с установленными слэшами, для последующего сплита и конвертации
     */
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
