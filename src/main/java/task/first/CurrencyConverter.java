package main.java.task.first;

import main.java.task.first.Implementations.HandlerImpl;
import main.java.task.first.Interfaces.Handler;

import java.util.Scanner;

public class CurrencyConverter {
    private Handler handler = new HandlerImpl();

    public static void main(String[] args) {
        new CurrencyConverter().start();
    }

    private void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Для начала введите итоговую валюту, к которой будет приведен результат выражения, например: \n" +
                        "\\$(\\$ потому что данный символ зарезервирован) или P(англ буква)");
                String currency = scanner.nextLine();
                System.out.println("Напишите выражение, например (здесь \\ добавлять не надо): " +
                        "737p + $85.4 - 130p - $30");
                String expression = scanner.nextLine();
                if (expression.equals("quit")) break;
                double result = handler.doHandlerAndGetResult(currency, expression);
                System.out.println(String.format("Валюта: %s\nРезультат: %.2f",currency,result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
