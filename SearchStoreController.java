package com.example.demo;

import hr.java.production.model.*;
import hr.java.production.thread.SearchFactoryThread;
import hr.java.production.thread.SearchStoreThread;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class SearchStoreController {
    @FXML
    private TextField storeTextField;

    @FXML
    private TableView<Store> storesTableView;

    @FXML
    private TableColumn<Store, String> storeNameTableColumn;

    @FXML
    private TableColumn<Store, String> storeWebAddressTableColumn;

    @FXML
    private TableColumn<Store, String> storeItemsTableColumn;

    private static List<Item> itemList = new ArrayList<>();
    private static List<Store> storesList = new ArrayList<>();
    private static Set<Item> storeItems = new TreeSet<>();

    public void initialize() {
        itemList.clear();
        List<Category> categories = new ArrayList<>();

        try {
            categories = Database.getAllCategoriesFromDatabase();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        try {
            itemList = Database.getAllItemsFromDatabase(categories);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        try {
            storesList = Database.getAllStoresFromDatabase(storeItems);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        for(Store store : storesList){
            try {
                storeItems = (Database.getItemsForTheStore(itemList, store));
                store.setItems(storeItems);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }

        storeNameTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getName());
        });

        storeWebAddressTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getWebAddress());
        });

        storeItemsTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getItems().stream().map(Item::getName).collect(Collectors.toList())
                    .toString());
        });


        ObservableList<Store> storeObservableList = FXCollections.observableList(storesList);

        storesTableView.setItems(storeObservableList);

    }

    @FXML
    protected void onButtonClick() {

        SearchStoreThread thread = new SearchStoreThread(storeTextField, storesTableView, storesList);
        Thread newRunner = new Thread(thread);
        newRunner.start();

    }
}
