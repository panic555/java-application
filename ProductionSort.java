package hr.java.production.sort;

import hr.java.production.model.Item;

import java.util.Comparator;

public class ProductionSort implements Comparator<Item> {

    @Override
    public int compare(Item o1, Item o2) {
        return Integer.compare(o1.getSellingPrice().intValue(), o2.getSellingPrice().intValue());
    }
}
