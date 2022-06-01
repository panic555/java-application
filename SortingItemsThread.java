package hr.java.production.thread;

import com.example.demo.HelloApplication;
import hr.java.production.model.Item;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingItemsThread implements Runnable {
    private List<Item> items;
    private TableView<Item> itemsTableView;
    private TableColumn<Item, String> itemNameTableColumn;
    private TableColumn<Item, String> itemCategoryTableColumn;
    private TableColumn<Item, String> itemWidthTableColumn;
    private TableColumn<Item, String> itemHeightTableColumn;
    private TableColumn<Item, String> itemLengthTableColumn;
    private TableColumn<Item, String> itemProductionCostTableColumn;
    private TableColumn<Item, String> itemSellingPriceTableColumn;

    public SortingItemsThread(List<Item> items, TableView<Item> itemsTableView, TableColumn<Item, String> itemNameTableColumn, TableColumn<Item, String> itemCategoryTableColumn, TableColumn<Item, String> itemWidthTableColumn, TableColumn<Item, String> itemHeightTableColumn, TableColumn<Item, String> itemLengthTableColumn,
                              TableColumn<Item, String> itemProductionCostTableColumn, TableColumn<Item,
            String> itemSellingPriceTableColumn) {
        this.items = items;
        this.itemsTableView = itemsTableView;
        this.itemNameTableColumn = itemNameTableColumn;
        this.itemCategoryTableColumn = itemCategoryTableColumn;
        this.itemWidthTableColumn = itemWidthTableColumn;
        this.itemHeightTableColumn = itemHeightTableColumn;
        this.itemLengthTableColumn = itemLengthTableColumn;
        this.itemProductionCostTableColumn = itemProductionCostTableColumn;
        this.itemSellingPriceTableColumn = itemSellingPriceTableColumn;
    }

    @Override
    public void run() {

            Collections.sort(items, new Sorter());
            itemNameTableColumn.setCellValueFactory(cellData -> {
                        return new SimpleStringProperty(cellData.getValue().getName());
                    });

                    itemCategoryTableColumn.setCellValueFactory(cellData -> {
                        return new SimpleStringProperty(cellData.getValue().getCategory().getName());
                    });
                    itemWidthTableColumn.setCellValueFactory(cellData -> {
                        return new SimpleStringProperty(cellData.getValue().getWidth().toString());
                    });
                    itemHeightTableColumn.setCellValueFactory(cellData -> {
                        return new SimpleStringProperty(cellData.getValue().getHeight().toString());
                    });
                    itemLengthTableColumn.setCellValueFactory(cellData -> {
                        return new SimpleStringProperty(cellData.getValue().getLength().toString());
                    });
                    itemProductionCostTableColumn.setCellValueFactory(cellData -> {
                        return new SimpleStringProperty(cellData.getValue().getProductionCost().toString());
                    });
                    itemSellingPriceTableColumn.setCellValueFactory(cellData -> {
                        return new SimpleStringProperty(cellData.getValue().getSellingPrice().toString());
                    });

                    ObservableList<Item> itemsObservableList = FXCollections.observableList(items);

                    itemsTableView.setItems(itemsObservableList);
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
