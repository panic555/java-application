package hr.java.production.model;

import java.math.BigDecimal;

public sealed interface Technical permits Laptop {
    /**
     *
     * @return How many years until laptop expires.
     */
    BigDecimal expirationDate();
}
