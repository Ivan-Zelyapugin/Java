/**
 * Класс, представляющий участок земли.
 */
public class Land {
    /**
     * Адрес участка.
     */
    protected String address;

    /**
     * Площадь участка.
     */
    protected int area;

    /**
     * Расстояние до ближайшего города.
     */
    protected int distanceToCity;

    /**
     * Цена участка.
     */
    protected int price;

    /**
     * Высшая точка на участке.
     */
    protected int height;

    /**
     * Конструктор для создания объекта участка земли.
     *
     * @param address       Адрес участка.
     * @param area          Площадь участка.
     * @param distanceToCity Расстояние до ближайшего города.
     * @param price         Цена участка.
     * @param height        Высшая точка на участке.
     */
    public Land(String address, int area, int distanceToCity, int price, int height) {
        this.address = address;
        this.area = area;
        this.distanceToCity = distanceToCity;
        this.price = price;
        this.height = height;
    }

    /**
     * Метод для отображения информации об участке.
     */
    public void displayInfo() {
        System.out.print("Участок находится по адресу: " + address + ", стоит: " + price + " денег"
                + ", занимает площадь: " + area + ", расстояние: " + distanceToCity + ", Высшая точка на участке:" + height);
    }

    /**
     * Метод для редактирования полной информации об участке.
     *
     * @param address       Новый адрес участка.
     * @param area          Новая площадь участка.
     * @param distanceToCity Новое расстояние до ближайшего города.
     * @param price         Новая цена участка.
     * @param height        Новая высшая точка на участке.
     */
    public void editFullInfo(String address, int area, int distanceToCity, int price, int height) {
        this.address = address;
        this.area = area;
        this.distanceToCity = distanceToCity;
        this.price = price;
        this.height = height;
    }
}
