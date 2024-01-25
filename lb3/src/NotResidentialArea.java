/**
 * Класс, представляющий не жилую территорию (например, коммерческую недвижимость) на участке земли.
 */
public class NotResidentialArea extends Land {
    /**
     * Тип недвижимости на участке (например, офисное здание, магазин и т. д.).
     */
    protected String propertyType;

    /**
     * Владелец не жилой территории.
     */
    protected String owner;

    /**
     * Высота потолка в не жилой территории.
     */
    protected int heightOfFlor;

    /**
     * Конструктор для создания объекта не жилой территории на участке земли.
     *
     * @param address       Адрес участка.
     * @param area          Площадь участка.
     * @param distanceToCity Расстояние до ближайшего города.
     * @param price         Цена участка.
     * @param height        Высшая точка на участке.
     * @param propertyType  Тип недвижимости.
     * @param owner         Владелец не жилой территории.
     * @param heightOfFlor  Высота потолка в не жилой территории.
     */
    public NotResidentialArea(String address, int area, int distanceToCity, int price, int height, String propertyType, String owner, int heightOfFlor) {
        super(address, area, distanceToCity, price, height);
        this.propertyType = propertyType;
        this.owner = owner;
        this.heightOfFlor = heightOfFlor;
    }

    /**
     * Метод для отображения информации о не жилой территории на участке.
     */
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.print(", Тип недвижимости: " + propertyType + ", Владелец: " + owner + ", Высота потолка: " + heightOfFlor);
    }
}
