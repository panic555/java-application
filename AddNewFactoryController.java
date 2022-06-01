package com.example.demo;

import hr.java.production.enums.Cities;
import hr.java.production.model.*;
import hr.java.production.thread.AddFactoryThread;
import hr.java.production.thread.SearchItemThread;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

public class AddNewFactoryController {
    @FXML
    private TextField factoryNameTextField;

    @FXML
    private TextField factoryAddressTextField;

    @FXML
    private ChoiceBox<String> factoryItemsChoiceBox;

    private static List<String> itemNameList = new ArrayList<>();

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


        for(Item item : itemList){
            factoryItemsChoiceBox.getItems().add(item.getName());
            itemNameList.add(item.getName());
        }

    }

    @FXML
    protected void onButtonClick() {
        long id = addressesList.size() + 1;
        long id1 = factoryList.size() + 1;
        String name = factoryNameTextField.getText();
        String address = factoryAddressTextField.getText();
        String item = factoryItemsChoiceBox.getValue();


        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText("There was one or more errors!");

        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setTitle("Factory added successfully");
        success.setHeaderText("Factory added successfully");
        success.setContentText("Factory " + name + " was saved successfully!");

        if(name.equals("") || address.equals("") || item == null){
            alert.showAndWait();
        }

        else {
            AddFactoryThread thread = new AddFactoryThread(factoryNameTextField, factoryAddressTextField, factoryItemsChoiceBox,
                    factoryList, addressesList);
            Thread newRunner = new Thread(thread);
            newRunner.start();
            success.showAndWait();
        }

    }
}
