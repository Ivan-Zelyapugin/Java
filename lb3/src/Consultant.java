import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Класс Consultant предоставляет функциональность для управления данными об участках земли.
 */
public class Consultant {
    private List<Land> lands = new ArrayList<>();

    /**
     * Добавляет участок земли в список.
     *
     * @param land Участок земли для добавления.
     */
    public void addLand(Land land) {
        lands.add(land);
    }

    /**
     * Находит самое высокое здание на участке.
     *
     * @return Самое высокое здание или null, если список пуст.
     */
    public Land findTallestBuilding() {
        if (lands.isEmpty()) {
            return null;
        }

        Land tallestBuilding = lands.get(0);
        for (Land land : lands) {
            if (land.height > tallestBuilding.height) {
                tallestBuilding = land;
            }
        }
        return tallestBuilding;
    }

    /**
     * Находит здания, выше 50 метров.
     *
     * @return Список зданий, выше 50 метров.
     */
    public List<Land> findBuildingOver50Meters() {
        List<Land> buildingsOver50Meters = new ArrayList<>();
        if (lands.isEmpty()) return buildingsOver50Meters;

        for (Land land : lands) {
            if (land.height > 50) {
                buildingsOver50Meters.add(land);
            }
        }
        return buildingsOver50Meters;
    }

    /**
     * Упорядочивает участки земли по высоте.
     */
    public void sortByHeight() {
        if (lands.isEmpty())
            System.out.println("Список пуст.");
        lands.sort(Comparator.comparing(land -> land.height));
    }

    /**
     * Отображает информацию о всех участках земли.
     */
    public void displayAllInfo() {
        if (lands.isEmpty())
            System.out.println("Список пуст.");

        for (Land land : lands) {
            land.displayInfo();
            System.out.println();
        }
    }

    /**
     * Находит участок земли по адресу.
     *
     * @param address Адрес для поиска.
     * @return Участок земли с указанным адресом или null, если не найден.
     */
    public Land findLandByAddress(String address) {
        for (Land land : lands) {
            if (land.address.equalsIgnoreCase(address)) {
                return land;
            }
        }
        return null;
    }

    /**
     * Редактирует атрибуты участка земли по его адресу.
     *
     * @param address Адрес участка земли для редактирования.
     */
    public void editBuilding(String address) {
        Land landToEdit = findLandByAddress(address);
        if (landToEdit == null) {
            System.out.println("Участка по такому адресу не найдено.");
            return;
        }

        System.out.println("Выберите атрибут для редактирования:");
        System.out.println("1. Адрес");
        System.out.println("2. Площадь");
        System.out.println("3. Расстояние до центра города");
        System.out.println("4. Стоимость");
        System.out.println("5. Высшая точка на участке");

        switch (landToEdit.getClass().getSimpleName()) {
            case "ResidentialLand":
                System.out.println("6. Кол-во комнат");
                System.out.println("7. Тип здания");
                break;
            case "NotResidentialArea":
                System.out.println("6. Высота потолка");
                System.out.println("7. Тип недвижимости ");
                System.out.println("8. Владелец ");
                break;
            case "HighRiseBuilding":
                System.out.println("6. Кол-во комнат");
                System.out.println("7. Тип здания");
                System.out.println("8. Кол-во этажей");
                System.out.println("9. Жилая площадь");
                break;
            case "IndustrialFacilities":
                System.out.println("6. Высота потолка");
                System.out.println("7. Тип недвижимости ");
                System.out.println("8. Владелец ");
                System.out.println("9. Количество работников ");
                System.out.println("10. Количество этажей ");
                break;
        }


        String attributeChoice = Main.scanner.nextLine().trim();

        switch (attributeChoice) {
            case "1":
                System.out.println("Введите новый адрес здания:");
                String newAddress = Main.checkEmpty("Новый адресс здания не может быть пустым. Попробуйте снова.");
                landToEdit.editFullInfo(newAddress, landToEdit.area, landToEdit.distanceToCity, landToEdit.price, landToEdit.height);
                break;
            case "2":
                int newArea = Main.checkPos("Введите новую площадь участка:", "Площадь не может быть отрицательной. Попробуйте снова.");
                landToEdit.editFullInfo(landToEdit.address, newArea, landToEdit.distanceToCity, landToEdit.price, landToEdit.height);
                break;
            case "3":
                int newDist = Main.checkPos("Введите новое расстояние до центра города:", "Расстояние не может быть отрицательным. Попробуйте снова.");
                landToEdit.editFullInfo(landToEdit.address, landToEdit.area, newDist, landToEdit.price, landToEdit.height);
                break;
            case "4":
                int newPrice = Main.checkPos("Введите цену участка:", "Цена не может быть отрицательным. Попробуйте снова.");
                landToEdit.editFullInfo(landToEdit.address, landToEdit.area, landToEdit.distanceToCity, newPrice, landToEdit.height);
                break;
            case "5":
                int newHeight = Main.checkPos("Введите самую высокую точку участка:", "Высота не может быть отрицательной. Попробуйте снова.");
                landToEdit.editFullInfo(landToEdit.address, landToEdit.area, landToEdit.distanceToCity, landToEdit.price, newHeight);
                break;
            case "6":
                int newRooms;
                int newHeightFlor;
                switch (landToEdit.getClass().getSimpleName()) {
                    case "ResidentialLand":
                        newRooms = Main.checkPos("Введите новое кол-во комнат:", "Кол-во не может быть отрицательной. Попробуйте снова.");
                        ((ResidentialLand) landToEdit).numbersOfRooms=newRooms;
                        break;
                    case "NotResidentialArea":
                        newHeightFlor = Main.checkPos("Введите новую высоту потолков :", "Высота не может быть отрицательной. Попробуйте снова.");
                        ((NotResidentialArea) landToEdit).heightOfFlor=newHeightFlor;
                        break;
                    case "HighRiseBuilding":
                        newRooms = Main.checkPos("Введите новое кол-во комнат:", "Кол-во не может быть отрицательной. Попробуйте снова.");
                        ((HighRiseBuilding) landToEdit).numbersOfRooms=newRooms;
                        break;
                    case "IndustrialFacilities":
                        newHeightFlor = Main.checkPos("Введите новую высоту потолков :", "Высота не может быть отрицательной. Попробуйте снова.");
                        ((IndustrialFacilities) landToEdit).heightOfFlor=newHeightFlor;
                        break;
                }
                break;
            case "7":
                String newTypeHouse;
                switch (landToEdit.getClass().getSimpleName()) {
                    case "ResidentialLand":
                        System.out.println("Введите новый тип здания:");
                        newTypeHouse = Main.checkEmpty("Новый тип здания не может быть пустым. Попробуйте снова.");
                        ((ResidentialLand) landToEdit).typeOfHouse=newTypeHouse;
                        break;
                    case "NotResidentialArea":
                        System.out.println("Введите новый тип здания:");
                        newTypeHouse = Main.checkEmpty("Новый тип здания не может быть пустым. Попробуйте снова.");
                        ((NotResidentialArea) landToEdit).propertyType=newTypeHouse;
                        break;
                    case "HighRiseBuilding":
                        System.out.println("Введите новый тип здания:");
                        newTypeHouse = Main.checkEmpty("Новый тип здания не может быть пустым. Попробуйте снова.");
                        ((HighRiseBuilding) landToEdit).typeOfHouse=newTypeHouse;
                        break;
                    case "IndustrialFacilities":
                        System.out.println("Введите новый тип здания:");
                        newTypeHouse = Main.checkEmpty("Новый тип здания не может быть пустым. Попробуйте снова.");
                        ((IndustrialFacilities) landToEdit).propertyType=newTypeHouse;
                        break;
                }
                break;
            case "8":
                String newStr;
                switch (landToEdit.getClass().getSimpleName()) {
                    case "NotResidentialArea":
                        System.out.println("Введите новый владельца:");
                        newStr = Main.checkEmpty("Новый владелец не может быть пустым. Попробуйте снова.");
                        ((NotResidentialArea) landToEdit).owner=newStr;
                        break;
                    case "HighRiseBuilding":
                        int newFloor = Main.checkPos("Введите новое кол-во этажей:", "Кол-во не может быть отрицательной. Попробуйте снова.");
                        ((HighRiseBuilding) landToEdit).floor=newFloor;
                        break;
                    case "IndustrialFacilities":
                        System.out.println("Введите новый владельца:");
                        newStr = Main.checkEmpty("Новый владелец не может быть пустым. Попробуйте снова.");
                        ((IndustrialFacilities) landToEdit).owner=newStr;
                        break;
                }
                break;
            case "9":
                switch (landToEdit.getClass().getSimpleName()) {
                    case "HighRiseBuilding":
                        int newLiveSpace = Main.checkPos("Введите новую жилую площадь:", "Площадь не может быть отрицательной. Попробуйте снова.");
                        ((HighRiseBuilding) landToEdit).liveSpace=newLiveSpace;
                        break;
                    case "IndustrialFacilities":
                        int newEmploes = Main.checkPos("Введите новое кол-во работников:", "Кол-во не может быть отрицательной. Попробуйте снова.");
                        ((IndustrialFacilities) landToEdit).numberOfEmployees=newEmploes;
                        break;
                }
                break;
            case "10":
                int newFloors = Main.checkPos("Введите новое кол-во этажей:", "Кол-во не может быть отрицательной. Попробуйте снова.");
                ((IndustrialFacilities) landToEdit).numberOfFloors=newFloors;
                break;
            default:
                System.out.println("Неверный выбор атрибута. Пожалуйста, попробуйте снова.");
        }


        System.out.println("Информация об участке после редактирования:");
        landToEdit.displayInfo();
    }


    /**
     * Выводит меню действий для пользователя.
     */
    public void menu() {
        System.out.println("\nВыберите действие:");
        System.out.println("add - Добавить участок");
        System.out.println("show - Показать все участки");
        System.out.println("find - Найти участок по адресу");
        System.out.println("edit - Редактировать данные участка");
        System.out.println("tallest - Найти самое высокое здание на участке");
        System.out.println("buildover50m - Показать здания выше 50м");
        System.out.println("sort - Отсортировать здания по высоте");
        System.out.println("exit - Выход");
    }
}

