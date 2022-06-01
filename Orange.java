package hr.java.production.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Orange extends Item implements Edible{
    public static BigDecimal calPerKilo = new BigDecimal("10");
    private BigDecimal weight;

    /**Orange class' constructor.
     *
     * @param name string, Orange's name,
     * @param category Category, orange's category
     * @param width BigDecimal, Orange's width,
     * @param height BigDecimal, Orange's height,
     * @param length BigDecimal, Orange's length,
     * @param productionCost BigDecimal, Orange's production cost,
     * @param sellingPrice BigDecimal, Orange's selling price,
     * @param discountAmount Discount, discount on Orange
     * @param weight, BigDecimal, Orange's weight
     */

    public Orange(Long id, String name, Category category, BigDecimal width, BigDecimal height,
                  BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, Discount discountAmount,
                  BigDecimal weight) {
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

    @Override
    public BigDecimal calculateKilocalories() {
        return(calPerKilo.multiply(weight));
    }

    @Override
    public BigDecimal calculatePrice() {
        return (getSellingPrice().multiply(weight));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Orange orange = (Orange) o;
        return Objects.equals(weight, orange.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), weight);
    }
}
