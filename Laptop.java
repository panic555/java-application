package hr.java.production.model;

import java.math.BigDecimal;
import java.util.Objects;

public non-sealed class Laptop extends Item implements Technical{
    private BigDecimal expirationDate;

    /**Laptop Class' constructor.
     *
     * @param name string, Laptop's name
     * @param category, Category, Laptop's category
     * @param width BigDecimal, Laptop's width
     * @param height BigDecimal, Laptop's height,
     * @param length BigDecimal, Laptop's length,
     * @param productionCost BigDecimal, Laptop's production cost,
     * @param sellingPrice BigDecimal, Laptop's selling price
     * @param discountAmount Discount, discount on laptop,
     * @param expirationDate BigDecimal, years when laptop will expire.
     */
    public Laptop(Long id, String name , Category category, BigDecimal width, BigDecimal height, BigDecimal length,
                  BigDecimal productionCost, BigDecimal sellingPrice, Discount discountAmount, BigDecimal expirationDate) {
        super(id, name, category, width, height, length, productionCost, sellingPrice);
        this.expirationDate = expirationDate;
    }

    public BigDecimal getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(BigDecimal expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public BigDecimal expirationDate() {
        return expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Laptop laptop = (Laptop) o;
        return Objects.equals(expirationDate, laptop.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), expirationDate);
    }
}
