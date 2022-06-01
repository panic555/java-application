package hr.java.production.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Store extends NamedEntity implements Serializable {
    private String webAddress;
    private Set<Item> items;

    /**Store class' constructor.
     *
     * @param name, string, Store's name,
     * @param webAddress string, Store's web address,
     * @param items array of Items to be chosen for the store.
     */

    public Store(Long id, String name, String webAddress, Set<Item> items) {
        super(id, name);
        this.webAddress = webAddress;
        this.items = items;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Store store = (Store) o;
        return Objects.equals(webAddress, store.webAddress) && Objects.equals(items, store.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), webAddress, items);
    }
}
