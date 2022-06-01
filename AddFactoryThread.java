package hr.java.production.thread;

import hr.java.production.model.Address;
import hr.java.production.model.Database;
import hr.java.production.model.Factory;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class AddFactoryThread implements Runnable{

    private TextField factoryNameTextField;
    private TextField factoryAddressTextField;
    private ChoiceBox<String> factoryItemsChoiceBox;
    private List<Factory> factoryList;
    private List<Address> addressesList;

    public AddFactoryThread(TextField factoryNameTextField, TextField factoryAddressTextField,
                            ChoiceBox<String> factoryItemsChoiceBox, List<Factory> factoryList, List<Address> addressesList) {
        this.factoryNameTextField = factoryNameTextField;
        this.factoryAddressTextField = factoryAddressTextField;
        this.factoryItemsChoiceBox = factoryItemsChoiceBox;
        this.factoryList = factoryList;
        this.addressesList = addressesList;
    }

    @Override
    public void run() {
        long id = addressesList.size() + 1;
        long id1 = factoryList.size() + 1;
        String name = factoryNameTextField.getText();
        String address = factoryAddressTextField.getText();
        String item = factoryItemsChoiceBox.getValue();



        try{
            Factory factory = new Factory(id, name, new Address(String.valueOf(id), address, "43", "Split", "21000"), null);
            Address address1 = new Address(String.valueOf(id), address, "43", "Split", "21000");
            Database.insertNewAddressToDatabase(address1);
            Database.insertNewFactoryToDatabase(factory);
        }
        catch(Exception e){
            e.printStackTrace();
        }



    }
}
