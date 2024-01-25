/**
 *Задание: реализовать программу имеющую иерархию минимум из 3 классов
 * Тема: Недвижимость
 * Функции:
 * – определить самое высокое здание;
 * – определить здания с высотой более 50 м;
 * – упорядочить массив по возрастанию высоты;
 * – организовать поиск по названию здания, исправление одного из полей и вывод полной информации о здании после редактирования
 * @author Зеляпугин Сафронов 22ВП2
 * @version  1.4
 * @since 9.10.2023
 */

import java.util.List;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Consultant consultant=new Consultant();

        while (true){
            consultant.menu();
            String choose = scanner.nextLine().trim().toLowerCase();
            switch (choose){
                case "add":
                    System.out.println("Выберете тип недвижимости:\n1. Жилая недвижимость\n2. Нежилая недвижимость\n3.Многоэтажное здание\n4.Промышленное предприятие");
                    String type = checkEmpty("Некорректно");

                    if(!(type.equals("1")||type.equals("2")||type.equals("3")||type.equals("4"))) {
                        System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                        break;
                    }

                    System.out.println("Адрес");
                    String adres = checkEmpty("Адрес не может быть пустым. Попробуйте снова.");
                    int area = checkPos("Введите площадь участка:", "Площадь не может быть отрицательной. Попробуйте снова.");
                    int distance = checkPos("Введите расстояние до центра города:", "Расстояние не может быть отрицательным. Попробуйте снова.");
                    int price = checkPos("Введите цену участка:", "Цена не может быть отрицательной. Попробуйте снова.");
                    int height = checkPos("Введите самую высокую точку участка:", "Высота не может быть отрицательной. Попробуйте снова.");

                    int rooms;
                    String typeHouse;
                    int heightFloor;
                    String owner;
                    switch (type){
                        case "1":
                            rooms = checkPos("Введите кол-во комнат:", "Кол-во не может быть отрицательной. Попробуйте снова.");
                            System.out.println("Введите тип здания:");
                            typeHouse = checkEmpty("Тип не может быть пустым. Попробуйте снова.");
                            consultant.addLand(new ResidentialLand(adres, area, distance, price, height, typeHouse, rooms));
                            System.out.println("Жилая недвижимость была успешно добавлена");
                            break;
                        case "2":
                            heightFloor = checkPos("Введите высоту потолка:", "Высота не может быть отрицательной. Попробуйте снова.");
                            System.out.println("Введите тип недвижимости:");
                            typeHouse = checkEmpty("Тип не может быть пустым. Попробуйте снова.");
                            System.out.println("Введите имя владельца:");
                            owner = checkEmpty("Имя не может быть пустым. Попробуйте снова.");
                            consultant.addLand(new NotResidentialArea(adres, area, distance, price, height, typeHouse, owner, heightFloor));
                            System.out.println("Нежилая территория была успешно добавлена");
                            break;
                        case "3":
                            rooms = checkPos("Введите кол-во комнат:", "Кол-во не может быть отрицательной. Попробуйте снова.");
                            System.out.println("Введите тип здания:");
                            typeHouse = checkEmpty("Тип не может быть пустым. Попробуйте снова.");
                            int floors = checkPos("Введите кол-во этажей:", "Кол-во не может быть отрицательной. Попробуйте снова.");
                            int liveSpace = checkPos("Введите жилую площадь:", "Площадь не может быть отрицательной. Попробуйте снова.");
                            consultant.addLand(new HighRiseBuilding(adres, area, distance, price, height, typeHouse, rooms, floors, liveSpace));
                            System.out.println("Высотное жилое здание было успешно добавлено");
                            break;
                        case "4":
                            heightFloor = checkPos("Введите высоту потолка:", "Высота не может быть отрицательной. Попробуйте снова.");
                            System.out.println("Введите тип недвижимости:");
                            typeHouse = checkEmpty("Тип не может быть пустым. Попробуйте снова.");
                            System.out.println("Введите имя владельца:");
                            owner = checkEmpty("Имя не может быть пустым. Попробуйте снова.");
                            System.out.println("Введите название предприятия:");
                            String name = checkEmpty("Название не может быть пустым. Попробуйте снова.");
                            int empoyes = checkPos("Введите кол-во рабочих:", "Кол-во не может быть отрицательной. Попробуйте снова.");
                            int floor = checkPos("Введите кол-во этажей:", "Кол-во не может быть отрицательной. Попробуйте снова.");
                            consultant.addLand(new IndustrialFacilities(adres, area, distance, price, height, typeHouse, owner, heightFloor, name, empoyes, floor));
                            System.out.println("Промышленное сооружение было успешно добавлено");
                            break;
                        default:
                            System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                    }
                    break;
                case "show":
                    consultant.displayAllInfo();
                    break;
                case "find":
                    System.out.println("Введите адрес участка для поиска:");
                    adres = checkEmpty("Адрес не может быть пустым. Попробуйте снова.");

                    Land found = consultant.findLandByAddress(adres);
                    if (found != null) {
                        found.displayInfo();
                    } else {
                        System.out.println("Участок по такому адресу не найден.");
                    }
                    break;
                case "edit":
                    System.out.println("Введите адрес участка для редактирования:");
                    adres = checkEmpty("Адрес не может быть пустым. Попробуйте снова.");
                    consultant.editBuilding(adres);
                    break;
                case "tallest":
                    Land tall = consultant.findTallestBuilding();
                    if (tall != null) {
                        System.out.println("Самое высокое здание: ");
                        tall.displayInfo();
                    } else {
                        System.out.println("Список пуст.");
                    }
                    break;
                case "buildover50m":
                    List<Land> lands = consultant.findBuildingOver50Meters();
                    if (!lands.isEmpty()) {
                        System.out.println("Участки где высота больше 50м:");
                        for (Land land : lands) {
                            land.displayInfo();
                        }
                    } else {
                        System.out.println("Нет участков с высотой больше 50м");
                    }
                    break;
                case "sort":
                    consultant.sortByHeight();
                    break;
                case "exit":
                    // Выход
                    return;
                default:
                    System.out.println("Неверная команда. Пожалуйста, попробуйте снова.");



            }
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

            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println(errorMessage);
            }
        }
        return input;
    }
    /**
     * Проверяет, что введенное целое число неотрицательно.
     *
     * @param promptMessage Сообщение для ввода числа.
     * @param errorMessage  Сообщение об ошибке, если введено отрицательное число.
     * @return Неотрицательное целое число.
     */
    public static int checkPos(String promptMessage, String errorMessage) {
        int value = -1;
        while (value < 0) {
            System.out.println(promptMessage);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value < 0) {
                    System.out.println(errorMessage);
                }else
                    break;
            }
            catch (NumberFormatException e) {System.out.println("Пожалуйста, введите корректное число.");}
        }
        return value;
    }
}
