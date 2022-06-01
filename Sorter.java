package hr.java.production.thread;

import hr.java.production.model.Item;

import java.util.Comparator;

public class Sorter implements Comparator<Item> {
    @Override
    public int compare(Item o1, Item o2) {
        return o2.getSellingPrice().compareTo(o1.getSellingPrice());
    }
}
