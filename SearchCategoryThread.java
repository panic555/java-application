package hr.java.production.thread;


import hr.java.production.model.Category;
import hr.java.production.model.Database;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchCategoryThread implements Runnable{

    private TextField categoryTextField;
    private TableView<Category> categoriesTableView;
    private List<Category> categoryList;


    public SearchCategoryThread(TextField categoryTextField, TableView<Category> categoriesTableView, List<Category> categoryList) {
        this.categoryTextField = categoryTextField;
        this.categoriesTableView = categoriesTableView;
        this.categoryList = categoryList;
    }



    @Override
    public void run() {


        String enteredCategory = categoryTextField.getText();
        List<Category> filteredCategoryList = new ArrayList<>(categoryList);

        if(Optional.ofNullable(enteredCategory).isEmpty() == false) {
            filteredCategoryList = filteredCategoryList.stream()
                    .filter(x -> x.getName().toLowerCase().contains(enteredCategory.toLowerCase()))
                    .collect(Collectors.toList());
        }
        categoriesTableView.setItems(FXCollections.observableList(filteredCategoryList));

    }
}
