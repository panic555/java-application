package hr.java.production.thread;

import hr.java.production.model.Category;
import hr.java.production.model.Database;
import hr.java.production.model.Item;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddItemThread implements Runnable{

    private TextField itemNameTextField;
    private ComboBox<String> itemCategoryDropDown;
    private TextField itemHeightTextField;
    private TextField itemWidthTextField;
    private TextField itemLengthTextField;
    private TextField itemProductionCostTextField;
    private TextField itemSellingPriceTextField;

    public AddItemThread(TextField itemNameTextField, ComboBox<String> itemCategoryDropDown, TextField itemHeightTextField, TextField itemWidthTextField,
                         TextField itemLengthTextField, TextField itemProductionCostTextField, TextField itemSellingPriceTextField) {
        this.itemNameTextField = itemNameTextField;
        this.itemCategoryDropDown = itemCategoryDropDown;
        this.itemHeightTextField = itemHeightTextField;
        this.itemWidthTextField = itemWidthTextField;
        this.itemLengthTextField = itemLengthTextField;
        this.itemProductionCostTextField = itemProductionCostTextField;
        this.itemSellingPriceTextField = itemSellingPriceTextField;
    }

    @Override
    public void run() {
        List<Category> categories = new ArrayList<>();
        List<Item> items = new ArrayList<>();

        String name = itemNameTextField.getText();
        String category = itemCategoryDropDown.getValue();
        long id = items.size() + 1;
        String height = (itemHeightTextField.getText());
        String width = (itemWidthTextField.getText());
        String length = (itemLengthTextField.getText());
        String productionPrice = (itemProductionCostTextField.getText());
        String sellingPrice = (itemSellingPriceTextField.getText());

        try {
            categories = Database.getAllCategoriesFromDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            items = Database.getAllItemsFromDatabase(categories);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(category != null){
            for(Category cat : categories) {
                if (category.equals(cat.getName())) {
                    category = String.valueOf(cat.getId());
                }
            }
        }

        if(category != null){
            for(Category cat : categories) {
                if (category.equals(cat.getName())) {
                    category = String.valueOf(cat.getId());
                }
            }}

        try{
            Item item = new Item(id, name, categories.get(Integer.parseInt(category)-1), new BigDecimal(width),
                    new BigDecimal(height), new BigDecimal(length), new BigDecimal(productionPrice), new BigDecimal(
                    sellingPrice));
            Database.insertNewItemToDatabase(item);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
