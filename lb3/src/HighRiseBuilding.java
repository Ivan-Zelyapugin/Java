/**
 * Класс, представляющий высотное жилое здание на жилой территории.
 */
public class HighRiseBuilding extends ResidentialLand {
    /**
     * Количество этажей в высотном здании.
     */
    protected int floor;

    /**
     * Общая жилая площадь в высотном здании.
     */
    protected int liveSpace;

    /**
     * Конструктор для создания объекта высотного жилого здания на жилой территории.
     *
     * @param address       Адрес участка.
     * @param area          Площадь участка.
     * @param distanceToCity Расстояние до ближайшего города.
     * @param price         Цена участка.
     * @param height        Высшая точка на участке.
     * @param typeOfHouse   Тип жилого здания.
     * @param numbersOfRooms Количество комнат в жилом здании.
     * @param floor         Количество этажей в высотном здании.
     * @param liveSpace     Общая жилая площадь в высотном здании.
     */
    public HighRiseBuilding(String address, int area, int distanceToCity, int price, int height, String typeOfHouse, int numbersOfRooms, int floor, int liveSpace) {
        super(address, area, distanceToCity, price, height, typeOfHouse, numbersOfRooms);
        this.floor = floor;
        this.liveSpace = liveSpace;
    }

    /**
     * Метод для отображения информации о высотном жилом здании на жилой территории.
     */
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println(", кол-во этажей: " + floor + ", жилая площадь: " + liveSpace);
    }
}
