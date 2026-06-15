public class RunApp {
    public static void main(String[] args) {
        // Создаем кусочки нашего MVC и запускаем
        MathEngine myEngine = new MathEngine();
        ConsoleScreen myScreen = new ConsoleScreen();

        SystemController app = new SystemController(myEngine, myScreen);
        app.launch();
    }
}