package task.first;

import task.first.Implementations.HandlerImpl;

import java.util.Scanner;

public class CurrencyConverter {
    private Handler handler = new HandlerImpl();

    public static void main(String[] args) {
	// write your code here
        new CurrencyConverter().start();
    }

    private void start() {
        try(Scanner scanner = new Scanner(System.in)){
            while(true) {
                System.out.println("Type something here like this: \n" +
                        "toDollars(737р + toRubles($85,4))");
                String expression = scanner.nextLine();
                if(expression.equals("quit")) break;
                expression = handler.doHandler(expression);
                System.out.println(expression);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
