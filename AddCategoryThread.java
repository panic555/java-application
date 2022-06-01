package hr.java.production.thread;

import hr.java.production.model.Category;
import hr.java.production.model.Database;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AddCategoryThread implements Runnable{

    private List<Category> categoryList;
    private TextField categoryNameTextField;
    private TextField categoryDescriptionTextField;

    public AddCategoryThread(List<Category> categoryList, TextField categoryNameTextField, TextField categoryDescriptionTextField) {
        this.categoryList = categoryList;
        this.categoryNameTextField = categoryNameTextField;
        this.categoryDescriptionTextField = categoryDescriptionTextField;
    }

    @Override
    public void run() {
        try {
            categoryList = Database.getAllCategoriesFromDatabase();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        long id = categoryList.size() + 1;
        String name = categoryNameTextField.getText();
        String description = categoryDescriptionTextField.getText();



        try{
            Category category = new Category(id, categoryNameTextField.getText(), categoryDescriptionTextField.getText());
            Database.insertNewCategoryToDatabase(category);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
