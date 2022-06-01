package hr.java.production.model;

import java.math.BigDecimal;

public interface Edible {

    /**
     * @return calculated Kilocalories and prices depending on their weight.
     */

    BigDecimal calculateKilocalories();
    BigDecimal calculatePrice();
}
