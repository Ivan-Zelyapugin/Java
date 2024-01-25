/**
 *Задание: реализовать программу выполняющую частотный анализ строки.
 * Определите символы, которые:
 *  входят и в первую и во вторую строку;
 *  входят в первую и не входят во вторую;
 *  содержатся хотя бы в одной строке.
 * @author Зеляпугин Сафронов 22ВП2
 * @version  0.3
 * @since 17.10.2023
 */
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Set;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
/**
 * Класс Main выполняет частотный анализ слов и символов
 * из двух введенных строк. Задачей является определение символов, которые входят и в первую и во вторую строку или
 * символов, которые входят только в первую строку или символов, которые встречаются хотя бы в одной из строк.
 */
public class Main {
    /**
     * Сканнер для ввода с клавиатуры.
     */
    public static final Scanner scanner = new Scanner(System.in);
    /**
     * Путь к файлу, в который будут сохранены результаты анализа.
     */
    public static final String puth = "result.txt";

    /**
     * Метод main - точка входа в программу.
     *
     * @param args Аргументы командной строки (не используются в данной программе).
     */
    public static void main(String[] args) {
        System.out.println("Лабораторная работа 4\nВыполнили студенты группы 22ВП2 Зеляпугин и Сафронов\nЗадание: " +
                "Проведите частотный анализ слов и символов из первой строки. Определите символы, которые:\n" +
                "входят и в первую и во вторую строку;\n" +
                "входят в первую и не входят во вторую;\n" +
                "содержатся хотя бы в одной строке.\n");
        while (true) {
            System.out.print("Введите первую строку: ");
            String firstLine = checkEmpty("Строка не может быть пустой. Попробуйте снова.");
            System.out.print("Введите вторую строку: ");
            String secondLine = checkEmpty("Строка не может быть пустой. Попробуйте снова.");
            boolean flag =true;
            while (flag){
                Set<Character> analis = new HashSet<>();
                Iterator<Character> iterAnalis;

                System.out.println("Выберите действие:");
                System.out.println("1. Определить символы входящие и в первую и во вторую строку");
                System.out.println("2. Определить символы входящие в первую и не входящие во вторую строку");
                System.out.println("3. Определить символы входящие хотябы в одну строку");
                System.out.println("4. Выход из приложения");
                System.out.println("5. Начать обрабатывать другие строки");
                switch (checkEmpty("Строка не может быть пустой. Попробуйте снова.")) {
                    case "1":
                        for (char symbol : firstLine.toCharArray()) {
                            if (secondLine.contains(String.valueOf(symbol))) {
                                analis.add(symbol);
                            }
                        }
                        System.out.println("Первая строка: "+firstLine);
                        System.out.println("Вторая строка: "+secondLine);
                        System.out.print("Общие символы: ");
                        iterAnalis = analis.iterator();
                        while (iterAnalis.hasNext()) {
                            char symbol = iterAnalis.next();
                            System.out.print(symbol + " ");
                        }
                        Choice(analis);
                        break;
                    case "2":
                        for (char symbol : firstLine.toCharArray()) {
                            if (!secondLine.contains(String.valueOf(symbol))) {
                                analis.add(symbol);
                            }
                        }
                        System.out.println("Первая строка: "+firstLine);
                        System.out.println("Вторая строка: "+secondLine);
                        System.out.print("Символы входящие только в первую строку: ");
                        iterAnalis = analis.iterator();
                        while (iterAnalis.hasNext()) {
                            char symbol = iterAnalis.next();
                            System.out.print(symbol + " ");
                        }
                        Choice(analis);
                        break;
                    case "3":
                        for (char symbol : firstLine.toCharArray()) {
                            analis.add(symbol);
                        }
                        for (char symbol : secondLine.toCharArray()) {
                            analis.add(symbol);
                        }
                        System.out.println("Первая строка: "+firstLine);
                        System.out.println("Вторая строка: "+secondLine);
                        System.out.print("Символы входящие хотя бы в одну строку: ");
                        iterAnalis = analis.iterator();
                        while (iterAnalis.hasNext()) {
                            char symbol = iterAnalis.next();
                            System.out.print(symbol + " ");
                        }
                        Choice(analis);
                        break;
                    case "4":
                        return;
                    default:
                        System.out.println("Вы ввели не корректную команду");
                        break;
                    case "5":
                        flag=false;
                        break;
                }
            }
        }
    }
    /**
     * Метод для сохранения символов в файл.
     *
     * @param filename Имя файла, в который будут сохранены результаты анализа.
     * @param symbols  Множество символов для сохранения.
     */
    private static void saveToFile(String filename, Set<Character> symbols) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write("Символы: " + symbols + "\n");
            System.out.println("Результаты сохранены в файл: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * Метод для обработки выбора пользователя относительно сохранения результатов в файл.
     *
     * @param line Множество символов для сохранения.
     */
    private static void Choice(Set line){
        System.out.println();
        System.out.println("Желаете ли сохранить результат в файле?");
        System.out.println("1. Да");
        System.out.println("2. нет");
        switch (scanner.nextInt()){
            case 1:
                saveToFile(puth, line);
                break;
            case 2:
                break;
            default:
                System.out.println("Вы ввели не корректную команду");
        }
    }
    /**
     * Проверяет, что введенная строка не пуста.
     *
     * @param errorMessage Сообщение об ошибке, если строка пуста.
     * @return Введенная непустая строка.
     */
    public static String checkEmpty(String errorMessage) {
        String input = "";
        while (input.isEmpty()) {

            input = scanner.next().trim();
            if (input.isEmpty()) {
                System.out.println(errorMessage);
            }
        }
        return input;
    }

}

