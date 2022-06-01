package com.example.demo;

import hr.java.production.model.Category;
import hr.java.production.model.Database;
import hr.java.production.model.Item;
import hr.java.production.thread.SearchCategoryThread;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchCategoryController {

    @FXML
    private TextField categoryTextField;

    @FXML
    private TableView<Category> categoriesTableView;

    @FXML
    private TableColumn<Category, String> categoryNameTableColumn;

    @FXML
    private TableColumn<Category, String> categoryDescriptionTableColumn;

    private static List<Category> categoryList = new ArrayList<>();

    public void initialize() {

        try {
            categoryList = Database.getAllCategoriesFromDatabase();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }


        categoryNameTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getName());
        });

        categoryDescriptionTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getDescription());
        });

        ObservableList<Category> categoryObservableList = FXCollections.observableList(categoryList);

        categoriesTableView.setItems(categoryObservableList);
    }

    @FXML
    protected void onButtonClick() {

        Database.activeConnectionWithDatabase = true;
        List<Category> filteredCategoryList = new ArrayList<>(categoryList);
        SearchCategoryThread thread = new SearchCategoryThread(categoryTextField, categoriesTableView, categoryList);
        Thread newRunner = new Thread(thread);
        newRunner.start();


    }
}
