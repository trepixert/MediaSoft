package task.first;

import task.first.interfaces.Handler;
import task.first.singleton.InstancesContainerSingleton;

import java.util.Scanner;

public class CurrencyConverter {
    private final Handler handler;

    public CurrencyConverter(){
        handler = InstancesContainerSingleton.getInstance().handler();
    }

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
                double result = handler.doHandlerAndGetResult(currency, expression);
                System.out.println(String.format("Валюта: %s\nРезультат: %.2f",currency,result));
                System.out.println("Если хотите выйти, напишите: да");
                if(scanner.nextLine().equalsIgnoreCase("да")) return;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
