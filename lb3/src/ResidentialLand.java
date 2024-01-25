/**
 * Класс, представляющий жилую территорию на участке земли.
 */
public class ResidentialLand extends Land {
    /**
     * Тип жилого здания на участке (например, дом, квартира и т. д.).
     */
    protected String typeOfHouse;

    /**
     * Количество комнат в жилом здании.
     */
    protected int numbersOfRooms;

    /**
     * Конструктор для создания объекта жилой территории на участке земли.
     *
     * @param address       Адрес участка.
     * @param area          Площадь участка.
     * @param distanceToCity Расстояние до ближайшего города.
     * @param price         Цена участка.
     * @param height        Высшая точка на участке.
     * @param typeOfHouse   Тип жилого здания.
     * @param numbersOfRooms Количество комнат в жилом здании.
     */
    public ResidentialLand(String address, int area, int distanceToCity, int price, int height, String typeOfHouse, int numbersOfRooms) {
        super(address, area, distanceToCity, price, height);
        this.typeOfHouse = typeOfHouse;
        this.numbersOfRooms = numbersOfRooms;
    }

    /**
     * Метод для отображения информации о жилой территории на участке.
     */
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.print(", тип здания: " + typeOfHouse + ", кол-во комнат: " + numbersOfRooms);
    }
}
