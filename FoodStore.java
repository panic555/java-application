package hr.java.production.genericsi;

import hr.java.production.model.Edible;
import hr.java.production.model.Item;
import hr.java.production.model.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FoodStore <T extends Edible> extends Store {

    public FoodStore(Long id, String name, String webAddress, Set<Item> items) {
        super(id, name, webAddress, items);
    }

    public Set<T> addToList(Set<T> list, T type){
        list.add(type);
        return list;
    }

}
