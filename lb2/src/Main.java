/**
 *Лабораторная работа 2 бригада 7 Сафронов Зеляпугин
 *Задание Написать регулярное выражение, определяющее является ли данная
 *строчка валидным E-mail адресом
 * @author Зеляпугин Сафронов 22ВП2
 * @version  1.3
 * @since 26.09.2023
 */

import java.util.Scanner;
public class Main{

    /**
     * основная программа выводит информацию и выоплняет проверку корректности
     * @param args получает массив символов из командной строки
     */
    public static void main(String[] args) {
        System.out.println("Лабораторная работа 2 \nВыполнили студенты группы 22ВП2 Зеляпугин и Сафронов \nЗадание: " +
                "написать регулярку проверяющяя корректность e-mail.");
        Scanner st = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        while (true) {
            if (!start(st).equals("q")) {
                check(in);
            } else
                break;
        }
    }

    /**
     * функция которая позволяет начать или закончить программу
     * @param start принимает данные с консоли для старта
     * @return возвращает выбор пользователя
     */
    public static String start(Scanner start){
        System.out.println("Если необходимо закончить напиши q, иначе любой символ");
        return start.next();
    }

    /**
     * функция для проверки корректности e-mail
     * @param scan принимает данные с консоли для хранения почты
     */
    public static void check(Scanner scan){
        System.out.println("Введи e-mail для проверки корректности!");
        String email = scan.nextLine();
        if(email.matches("^[.-_]*\\w+([-_.]\\w+)*@\\w+\\.\\w+[^.-_]$")||email.equals("root@localhost"))
        {
            System.out.println("e-mail корректен!");
        } else {
            System.out.println("e-mail не корректен!");
        }
    }
}
