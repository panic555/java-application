package hr.java.production.thread;

import hr.java.production.model.Database;
import hr.java.production.model.Item;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchItemThread implements Runnable {
    private TextField itemTextField;
    private ComboBox<String> categoryDropDown;
    private TableView<Item> itemsTableView;
    private List<Item> itemList;


    public SearchItemThread(TextField itemTextField, ComboBox<String> categoryDropDown, TableView<Item> itemsTableView
    , List<Item> itemList) {
        this.itemTextField = itemTextField;
        this.categoryDropDown = categoryDropDown;
        this.itemsTableView = itemsTableView;
        this.itemList = itemList;
    }

    @Override
    public void run() {

        List<Item> filteredItemList = new ArrayList<>(itemList);
        String enteredItemName = itemTextField.getText();
        String selectedCategory = categoryDropDown.getValue();
        if(Optional.ofNullable(enteredItemName).isEmpty() == false){
            filteredItemList = filteredItemList.stream()
                    .filter(x -> x.getName().toLowerCase().contains(enteredItemName.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if(Optional.ofNullable(selectedCategory).isEmpty() == false){
            filteredItemList = filteredItemList.stream()
                    .filter(x -> x.getCategory().getName().toLowerCase().equals(selectedCategory.toLowerCase()))
                    .collect(Collectors.toList());
        }

        itemsTableView.setItems(FXCollections.observableList(filteredItemList));

    }
}
