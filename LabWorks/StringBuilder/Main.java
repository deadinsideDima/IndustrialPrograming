import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        try {
            // Чтение входных данных из файла
            BufferedReader fileReader = new BufferedReader(new FileReader("input.txt"));
            String inputString = fileReader.readLine();
            String delimiterString = fileReader.readLine();
            fileReader.close();

            // Создаем список для хранения вещественных чисел
            ArrayList<Double> numbers = new ArrayList<>();

            // Разбиваем первую строку на лексемы, используя разделители из второй строки
            StringTokenizer tokenizer = new StringTokenizer(inputString, delimiterString);

            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken().trim();
                try {
                    // Попробуем распарсить число
                    double number = Double.parseDouble(token);
                    numbers.add(number);
                } catch (NumberFormatException e) {
                    // Лексема не является числом
                }
            }

            // Поиск времени (ЧЧ-ММ) в лексемах
            String time = findTime(inputString);

            // Генерация случайного числа и добавление его в строку
            double randomValue = Math.random();
            String randomString = String.format("%.2f", randomValue);
            StringBuilder modifiedString = new StringBuilder(inputString);

            if (numbers.isEmpty()) {
                // Если нет чисел, добавляем случайное число в середину строки
                int insertIndex = modifiedString.length() / 2;
                modifiedString.insert(insertIndex, randomString);
            } else {
                // Если есть числа, добавляем случайное число после каждого числа
                for (Double number : numbers) {
                    int lastIndex = modifiedString.lastIndexOf(Double.toString(number));
                    modifiedString.insert(lastIndex + Double.toString(number).length(), " " + randomString);
                }
            }

            // Удаление подстроки с минимальной длиной
            String shortestSubstring = findShortestSubstring(modifiedString.toString());
            modifiedString.replace(modifiedString.indexOf(shortestSubstring), modifiedString.indexOf(shortestSubstring) + shortestSubstring.length(), "");

            // Вывод результатов
            if (time != null) {
                System.out.println("Время (ЧЧ-ММ): " + time);
            } else {
                System.out.println("Время не найдено.");
            }
            System.out.println("Модифицированная строка: " + modifiedString.toString());

            // Запись результатов в файл
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("output.txt"));
            fileWriter.write("Вещественные числа:\n");
            for (Double num : numbers) {
                fileWriter.write(num + "\n");
            }
            if (time != null) {
                fileWriter.write("Время (ЧЧ-ММ): " + time + "\n");
            } else {
                fileWriter.write("Время не найдено.\n");
            }
            fileWriter.write("Модифицированная строка: " + modifiedString.toString() + "\n");
            fileWriter.close();

            System.out.println("Результаты записаны в файл 'output.txt'");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для поиска времени в лексемах
    private static String findTime(String input) {
        String[] tokens = input.split("\\s+");
        for (String token : tokens) {
            if (token.matches("\\d{2}-\\d{2}")) {
                String[] timeParts = token.split("-");
                int hours = Integer.parseInt(timeParts[0]);
                int minutes = Integer.parseInt(timeParts[1]);

                // Проверка на правильность часов и минут
                if (hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59) {
                    return token;
                }
            }
        }
        return null;
    }

    // Метод для поиска подстроки с минимальной длиной
    private static String findShortestSubstring(String input) {
        String[] words = input.split(" ");
        String shortest = words[0];

        for (String word : words) {
            if (word.length() < shortest.length()) {
                shortest = word;
            }
        }

        return shortest;
    }
}
