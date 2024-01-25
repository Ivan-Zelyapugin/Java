/**
 * Класс, представляющий промышленные сооружения на не жилой территории.
 */
public class IndustrialFacilities extends NotResidentialArea {
    /**
     * Название промышленного объекта.
     */
    protected String name;

    /**
     * Количество работников на промышленном объекте.
     */
    protected int numberOfEmployees;

    /**
     * Количество этажей в промышленном объекте.
     */
    protected int numberOfFloors;

    /**
     * Конструктор для создания объекта промышленного объекта в не жилой территории.
     *
     * @param address           Адрес участка.
     * @param area              Площадь участка.
     * @param distanceToCity    Расстояние до ближайшего города.
     * @param price             Цена участка.
     * @param height            Высшая точка на участке.
     * @param propertyType      Тип недвижимости на участке.
     * @param owner             Владелец не жилой территории.
     * @param heightOfFlor      Высота потолка в не жилой территории.
     * @param name              Название промышленного объекта.
     * @param numberOfEmployees Количество работников на промышленном объекте.
     * @param numberOfFloors    Количество этажей в промышленном объекте.
     */
    public IndustrialFacilities(String address, int area, int distanceToCity, int price, int height, String propertyType, String owner, int heightOfFlor, String name, int numberOfEmployees, int numberOfFloors) {
        super(address, area, distanceToCity, price, height, propertyType, owner, heightOfFlor);
        this.name = name;
        this.numberOfEmployees = numberOfEmployees;
        this.numberOfFloors = numberOfFloors;
    }

    /**
     * Метод для отображения информации о промышленных объектах в не жилой территории.
     */
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Название: " + name + ", Количество работников: " + numberOfEmployees + ", Количество этажей: " + numberOfFloors);
    }
}
