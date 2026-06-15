import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MathEngine {
    private final String memoryFile = "my_calc_memory.txt";
    private List<String> memoryList = new ArrayList<>();

    // Загружаем старые вычисления при старте
    public void startMemory() {
        File file = new File(memoryFile);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String str;
                while ((str = reader.readLine()) != null) {
                    memoryList.add(str);
                }
            } catch (IOException e) {
                System.out.println("Не удалось прочитать файл памяти.");
            }
        }
    }

    // Сохранение строчки в память
    private void writeToMemory(String mathString) {
        memoryList.add(mathString);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(memoryFile, true))) {
            writer.write(mathString + "\n");
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл!");
        }
    }

    // Эта штука выводит всю историю на экран
    public String showAllMemory() {
        if (memoryList.size() == 0) {
            return "Тут пока пусто, мы еще ничего не считали.";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < memoryList.size(); i++) {
            builder.append((i + 1)).append(") ").append(memoryList.get(i)).append("\n");
        }
        return builder.toString();
    }

    // Главный метод: считает через библиотеку exp4j
    public String doComplexMath(String userInput) {
        try {
            // чистим строку от случайных пробелов и запятых
            String cleanMath = userInput.replace(",", ".").replace(" ", "");

            // используем exp4j
            Expression expr = new ExpressionBuilder(cleanMath).build();
            double mathResult = expr.evaluate();

            String answer;
            if (mathResult % 1 == 0) {
                answer = String.valueOf((long) mathResult); // если целое
            } else {
                answer = String.valueOf(mathResult); // если дробное
            }

            String fullString = userInput + " = " + answer;
            writeToMemory(fullString); // сохраняем
            return answer;

        } catch (Exception e) {
            return "Ой, кажется тут ошибка в формуле! Проверьте скобки и знаки.";
        }
    }

    // Экспорт в отдельный файл логов
    public String saveLogsToFile(String folderPath) {
        if (folderPath == null || folderPath.isEmpty()) {
            folderPath = "saved_logs.txt";
        }
        try {
            File dest = new File(folderPath);
            Files.write(Paths.get(dest.getAbsolutePath()), memoryList);
            return "Успех! Файл сохранен по пути: " + dest.getAbsolutePath();
        } catch (Exception e) {
            return "Не получилось сохранить: " + e.getMessage();
        }
    }
}1