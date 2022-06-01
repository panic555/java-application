package hr.java.production.thread;

import hr.java.production.model.Database;
import hr.java.production.model.Store;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class AddStoreThread implements Runnable{

    private TextField storeNameTextField;
    private TextField storeAddressTextField;
    private ChoiceBox<String> storeItemsChoiceBox;
    private List<Store> storesList;

    public AddStoreThread(TextField storeNameTextField, TextField storeAddressTextField,
                          ChoiceBox<String> storeItemsChoiceBox, List<Store> storesList) {
        this.storeNameTextField = storeNameTextField;
        this.storeAddressTextField = storeAddressTextField;
        this.storeItemsChoiceBox = storeItemsChoiceBox;
        this.storesList = storesList;
    }

    @Override
    public void run() {

        long id = storesList.size() + 1;
        String name = storeNameTextField.getText();
        String address = storeAddressTextField.getText();
        String item = storeItemsChoiceBox.getValue();

        try {
            Store store = new Store(id, name, address, null);
            Database.insertNewStoreToDatabase(store);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
