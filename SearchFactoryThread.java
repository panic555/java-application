package hr.java.production.thread;

import hr.java.production.model.Factory;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchFactoryThread implements Runnable{

    private TextField factoryTextField;
    private List<Factory> factoryList;
    private TableView<Factory> factoriesTableView;

    public SearchFactoryThread(TextField factoryTextField, List<Factory> factoryList, TableView<Factory> factoriesTableView) {
        this.factoryTextField = factoryTextField;
        this.factoryList = factoryList;
        this.factoriesTableView = factoriesTableView;
    }

    @Override
    public void run() {
        String enteredFactory = factoryTextField.getText();

        List<Factory> filteredFactoryList = new ArrayList<>(factoryList);
        if(Optional.ofNullable(enteredFactory).isEmpty() == false) {
            filteredFactoryList = filteredFactoryList.stream()
                    .filter(x -> x.getName().toLowerCase().contains(enteredFactory.toLowerCase()))
                    .collect(Collectors.toList());
        }
        factoriesTableView.setItems(FXCollections.observableList(filteredFactoryList));
    }
}
