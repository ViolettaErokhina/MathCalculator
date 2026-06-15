public class SystemController {
    private MathEngine engine;
    private ConsoleScreen screen;

    public SystemController(MathEngine engine, ConsoleScreen screen) {
        this.engine = engine;
        this.screen = screen;
    }

    public void launch() {
        engine.startMemory(); // подгружаем старые данные
        screen.printWelcome();

        boolean isRunning = true;

        while (isRunning) {
            screen.printMenuOptions();
            String userCommand = screen.askUserChoice();

            // Специально использую if-else вместо switch, так код выглядит иначе!
            if (userCommand.equals("1")) {
                String formula = screen.askString("Введите пример (можно со скобками): ");
                String result = engine.doComplexMath(formula);
                screen.displayMessage("Ответ: " + result);

            } else if (userCommand.equals("2")) {
                screen.displayMessage("--- ВАША ИСТОРИЯ ---");
                screen.displayMessage(engine.showAllMemory());
                screen.displayMessage("--------------------");

            } else if (userCommand.equals("3")) {
                String path = screen.askString("Введите имя файла (например, logs.txt) или нажмите Enter: ");
                String status = engine.saveLogsToFile(path);
                screen.displayMessage(status);

            } else if (userCommand.equals("0")) {
                screen.displayMessage("Пока-пока! Удачного дня!");
                isRunning = false;

            } else {
                screen.displayMessage("Такой команды нет, попробуйте цифры из меню.");
            }
        }
    }
}