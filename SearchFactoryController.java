package com.example.demo;

import hr.java.production.enums.Cities;
import hr.java.production.model.*;
import hr.java.production.thread.SearchFactoryThread;
import hr.java.production.thread.SearchItemThread;
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

public class SearchFactoryController {
    @FXML
    private TextField factoryTextField;

    @FXML
    private TableView<Factory> factoriesTableView;

    @FXML
    private TableColumn<Factory, String> factoryNameTableColumn;

    @FXML
    private TableColumn<Factory, String> factoryAddressTableColumn;

    @FXML
    private TableColumn<Factory, String> factoryItemsTableColumn;

    private static List<Item> itemList = new ArrayList<>();
    private static List<Factory> factoryList = new ArrayList<>();
    private static List<Address> addressesList = new ArrayList<>();
    private static Set<Item> factoryItems = new TreeSet<>();
    private static  List<Category> categories = new ArrayList<>();

    public void initialize() {
        itemList.clear();


        try {
            categories = Database.getAllCategoriesFromDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            itemList = Database.getAllItemsFromDatabase(categories);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            addressesList = Database.getAllAddressesFromDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            factoryList = Database.getAllFactoriesFromDatabase(factoryItems, addressesList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Factory factory : factoryList){
            try {
                factoryItems = (Database.getItemsForTheFactory(itemList, factory));
                factory.setItems(factoryItems);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }}
            factoryNameTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getName());
        });

        factoryAddressTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getAddress().getStreet() + cellData.getValue().getAddress().getHouseNumber());
        });
        
        factoryItemsTableColumn.setCellValueFactory(cellData -> {
                    return new SimpleStringProperty(cellData.getValue().getItems().stream().map(Item::getName).collect(Collectors.toList())
                            .toString());
                });


        ObservableList<Factory> factoryObservableList = FXCollections.observableList(factoryList);

        factoriesTableView.setItems(factoryObservableList);

    }

    @FXML
    protected void onButtonClick() {

        SearchFactoryThread thread = new SearchFactoryThread(factoryTextField, factoryList, factoriesTableView);
        Thread newRunner = new Thread(thread);
        newRunner.start();
    }
}
