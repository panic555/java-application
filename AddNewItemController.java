package com.example.demo;

import hr.java.production.model.Category;
import hr.java.production.model.Database;
import hr.java.production.model.Item;
import hr.java.production.thread.AddCategoryThread;
import hr.java.production.thread.AddItemThread;
import hr.java.production.thread.SearchStoreThread;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddNewItemController {

    @FXML
    private TextField itemNameTextField;

    @FXML
    private ComboBox<String> itemCategoryDropDown;

    @FXML
    private TextField itemHeightTextField;

    @FXML
    private TextField itemWidthTextField;

    @FXML
    private TextField itemLengthTextField;

    @FXML
    private TextField itemProductionCostTextField;

    @FXML
    private TextField itemSellingPriceTextField;

    @FXML
    protected void initialize(){
        List<Category> categories = new ArrayList<>();

        try {
            categories = Database.getAllCategoriesFromDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Category category : categories){
            itemCategoryDropDown.getItems().add(category.getName());
        }
    }

    @FXML
    protected void onButtonClick() {
        List<Category> categories = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        int i = 0;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText("There was one or more errors!");
        String name = itemNameTextField.getText();
        String category = itemCategoryDropDown.getValue();

        String s = "";

        if(category != null){
            for(Category cat : categories) {
                if (category.equals(cat.getName())) {
                    category = String.valueOf(cat.getId());
                }
            }

        }
        else{
            s = s + ("Category is not chosen!\n");
        }
        String height = (itemHeightTextField.getText());
        String width = (itemWidthTextField.getText());
        String length = (itemLengthTextField.getText());
        String productionPrice = (itemProductionCostTextField.getText());
        String sellingPrice = (itemSellingPriceTextField.getText());


        if(name.isEmpty()){
            s = s + ("The name field must not be empty!\n");
        }

        if(height.isEmpty()){
            s = s + ("The height field must not be empty!\n");
        }
        else if(height.matches("^\\d+\\.\\d+")){
            i++;
        }
        else{
            s = s + ("Item's height must be in decimal format!\n");
        }
        if(width.isEmpty()){
            s = s + ("The width field must not be empty!\n");
        }
        else if(width.matches("^\\d+\\.\\d+")){
            i++;
        }
        else{
            s = s + ("Item's width must be in decimal format!\n");
        }
        if(length.isEmpty()){
            s = s + ("The length field must not be empty!\n");
        }
        else if(length.matches("^\\d+\\.\\d+")){
            i++;
        }
        else{
            s = s + ("Item's length must be in decimal format!\n");
        }
        if(productionPrice.isEmpty()){
            s = s + ("The production cost field must not be empty!\n");
        }
        else if(productionPrice.matches("^\\d+\\.\\d+")){
            i++;
        }
        else{
            s = s + ("Item's production cost must be in decimal format!\n");
        }
        if(sellingPrice.isEmpty()){
            s = s + ("The selling price field must not be empty!\n");
        }
        else if(sellingPrice.matches("^\\d+\\.\\d+")){
            i++;
        }
        else{
            s = s + ("Item's selling price must be in decimal format!\n");
        }

        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setTitle("Item added successfully");
        success.setHeaderText("Item added successfully");
        success.setContentText("Item " + name + " was saved successfully!");

        if(i != 5){
            alert.setContentText(s);
            alert.showAndWait();
            s = "";
            i = 0;
        }
        else {
            AddItemThread thread = new AddItemThread(itemNameTextField, itemCategoryDropDown, itemHeightTextField, itemWidthTextField,
                    itemLengthTextField, itemProductionCostTextField, itemSellingPriceTextField);
            Thread newRunner = new Thread(thread);
            newRunner.start();
            success.showAndWait();
        }
    }
}
