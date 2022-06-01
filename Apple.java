package hr.java.production.model;

import java.math.BigDecimal;

public class Apple extends Item implements Edible{
    public static BigDecimal calPerKilo = new BigDecimal("20");
    private BigDecimal weight;

    /**Apple Class' constructor.
     *
     * @param name string, name of Apple class
     * @param category Category, category of Apple class
     * @param width, BigDecimal, width of Apple class
     * @param height BigDecimal, height of Apple class
     * @param length BigDecimal, length of Apple class
     * @param productionCost BigDecimal, production cost of Apple class
     * @param sellingPrice BigDecimal, selling price of Apple class
     * @param discountAmount BigDecimal, discount ammount of Apple class
     * @param weight BigDecimal, weight of Apple class
     */

    public Apple(Long id, String name,  Category category, BigDecimal width, BigDecimal height,
                 BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice,
                 Discount discountAmount, BigDecimal weight) {
        super(id, name, category, width, height, length, productionCost, sellingPrice);
        this.weight = weight;
    }

    public static BigDecimal getCalPerKilo() {
        return calPerKilo;
    }

    public static void setCalPerKilo(BigDecimal calPerKilo) {
        Apple.calPerKilo = calPerKilo;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    /**
     *
     * @return calculated amount of calories per kilogram
     */

    @Override
    public BigDecimal calculateKilocalories() {
        return (calPerKilo.multiply(weight));
    }

    /**
     *
     * @return calculated price based on weight
     */


    /**
     *
     * @return
     */
    @Override
    public BigDecimal calculatePrice() {
        return (getSellingPrice().multiply(weight));
    }
}
