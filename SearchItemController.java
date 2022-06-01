package com.example.demo;

import hr.java.production.model.Category;
import hr.java.production.model.Database;
import hr.java.production.model.Discount;
import hr.java.production.model.Item;
import hr.java.production.thread.SearchCategoryThread;
import hr.java.production.thread.SearchItemThread;
import hr.java.production.thread.Sorter;
import hr.java.production.thread.SortingItemsThread;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class SearchItemController {
    @FXML
    private TextField itemTextField;

    @FXML
    private ComboBox<String> categoryDropDown;

    private static List<Item> itemList = new ArrayList<>();

    @FXML
    private TableView<Item> itemsTableView;

    @FXML
    private TableColumn<Item, String> itemNameTableColumn;

    @FXML
    private TableColumn<Item, String> itemCategoryTableColumn;

    @FXML
    private TableColumn<Item, String> itemWidthTableColumn;

    @FXML
    private TableColumn<Item, String> itemHeightTableColumn;

    @FXML
    private TableColumn<Item, String> itemLengthTableColumn;

    @FXML
    private TableColumn<Item, String> itemProductionCostTableColumn;

    @FXML
    private TableColumn<Item, String> itemSellingPriceTableColumn;

    public void initialize() {
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
        Collections.sort(itemList, new Sorter());
        Timeline clock = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                HelloApplication.getStage().setTitle("The most expesnsive item is: " + itemList.get(0).getName());
            }
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        SortingItemsThread thread = new SortingItemsThread(itemList, itemsTableView, itemNameTableColumn, itemCategoryTableColumn,
        itemWidthTableColumn, itemHeightTableColumn, itemLengthTableColumn, itemProductionCostTableColumn, itemSellingPriceTableColumn);

        Thread newRunner = new Thread(thread);
        newRunner.start();


        for(Category category : categories){
            categoryDropDown.getItems().add(category.getName());
        }

    }

    @FXML
    protected void onButtonClick(){

        SearchItemThread thread = new SearchItemThread(itemTextField, categoryDropDown, itemsTableView, itemList);
        Thread newRunner = new Thread(thread);
        newRunner.start();

    }
}
