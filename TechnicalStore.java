package hr.java.production.genericsi;

import hr.java.production.model.Item;
import hr.java.production.model.Store;
import hr.java.production.model.Technical;

import java.util.Set;

public class TechnicalStore <T extends Technical> extends Store {
    public TechnicalStore(Long id, String name, String webAddress, Set<Item> items) {
        super(id, name, webAddress, items);
    }

    public Set<T> addToList(Set<T> list, T type) {
        list.add(type);
        return list;
    }
}
