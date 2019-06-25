package main.java.task.first;

import main.java.task.first.Implementations.HandlerImpl;

import java.util.Scanner;

public class CurrencyConverter {
    private Handler handler = new HandlerImpl();

    public static void main(String[] args) {
        new CurrencyConverter().start();
    }

    private void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("At first type finally currency which you want to see, for example: \n" +
                        "$ or P(eng letter)");
                String currency = scanner.next();
                System.out.println("Type expression, for example: " +
                        "737p + $85.4 - 130p - $30");
                String expression = scanner.nextLine();
                if (expression.equals("quit")) break;
                expression = handler.doHandlerAndGetResult(currency, expression);
                System.out.println(expression);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
