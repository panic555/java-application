package com.example.demo;

import hr.java.production.model.Category;
import hr.java.production.model.Database;
import hr.java.production.thread.AddCategoryThread;
import hr.java.production.thread.SearchStoreThread;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddNewCategoryController {
    @FXML
    private TextField categoryNameTextField;

    @FXML
    private TextField categoryDescriptionTextField;

    private static List<Category> categoryList = new ArrayList<>();


    @FXML
    protected void onButtonClick(){

        long id = categoryList.size() + 1;
        String name = categoryNameTextField.getText();
        String description = categoryDescriptionTextField.getText();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText("There was one or more errors!");

        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setTitle("Item added successfully");
        success.setHeaderText("Item added successfully");
        success.setContentText("Category " + name + " was saved successfully!");

        if(name.equals("") || description.equals("")) {
            alert.setContentText("All fields must be filled!");
            alert.showAndWait();
        }
        else {

            AddCategoryThread thread = new AddCategoryThread(categoryList, categoryNameTextField, categoryDescriptionTextField);
            Thread newRunner = new Thread(thread);
            newRunner.start();
            success.showAndWait();
        }

    }
}
