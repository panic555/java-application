package com.example.demo;

import hr.java.production.model.*;
import hr.java.production.thread.AddFactoryThread;
import hr.java.production.thread.AddStoreThread;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

public class AddNewStoreController {
    @FXML
    private TextField storeNameTextField;

    @FXML
    private TextField storeAddressTextField;

    @FXML
    private ChoiceBox<String> storeItemsChoiceBox;

    private static List<String> itemNameList = new ArrayList<>();
    private static List<Item> itemList = new ArrayList<>();
    private static List<Store> storesList = new ArrayList<>();
    private static Set<Item> storeItems = new TreeSet<>();
    private static List<Category> categories = new ArrayList<>();

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

        for(Item item : itemList){
            storeItemsChoiceBox.getItems().add(item.getName());
            itemNameList.add(item.getName());
        }

    }

    @FXML
    protected void onButtonClick() {

        long id = storesList.size() + 1;
        String name = storeNameTextField.getText();
        String address = storeAddressTextField.getText();
        String item = storeItemsChoiceBox.getValue();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText("There was one or more errors!");

        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setTitle("Store added successfully");
        success.setHeaderText("Store added successfully");
        success.setContentText("Store " + name + " was saved successfully!");

        if (name.equals("") || address.equals("") || item == null || item.equals("")) {
            alert.setContentText("All fields must be filled!");
            alert.showAndWait();
        }

        else {

            AddStoreThread thread = new AddStoreThread(storeNameTextField, storeAddressTextField, storeItemsChoiceBox,
                    storesList);
            Thread newRunner = new Thread(thread);
            newRunner.start();
            success.showAndWait();
        }

    }
}
