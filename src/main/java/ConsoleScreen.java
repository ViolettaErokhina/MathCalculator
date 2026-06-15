import java.util.Scanner;

public class ConsoleScreen {
    private Scanner scanner;

    public ConsoleScreen() {
        scanner = new Scanner(System.in);
    }

    public void printWelcome() {
        System.out.println("==================================");
        System.out.println(" Добро пожаловать в Супер Калькулятор! ");
        System.out.println("==================================");
    }

    public void printMenuOptions() {
        System.out.println("\nЧто будем делать?");
        System.out.println("1 - Решить математический пример");
        System.out.println("2 - Посмотреть старые решения (историю)");
        System.out.println("3 - Выгрузить историю в файл");
        System.out.println("0 - Выйти из программы");
        System.out.print("Ваш выбор: ");
    }

    public String askUserChoice() {
        return scanner.nextLine();
    }

    public String askString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public void displayMessage(String msg) {
        System.out.println(msg);
    }
}