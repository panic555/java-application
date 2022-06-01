package hr.java.production.thread;

import hr.java.production.model.Store;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchStoreThread implements Runnable{

    private TextField storeTextField;
    private TableView<Store> storesTableView;
    private List<Store> storesList;

    public SearchStoreThread(TextField storeTextField, TableView<Store> storesTableView, List<Store> storesList) {
        this.storeTextField = storeTextField;
        this.storesTableView = storesTableView;
        this.storesList = storesList;
    }





    @Override
    public void run() {
        String enteredStore = storeTextField.getText();

        List<Store> filteredStoreList = new ArrayList<>(storesList);
        if(Optional.ofNullable(enteredStore).isEmpty() == false) {
            filteredStoreList = filteredStoreList.stream()
                    .filter(x -> x.getName().toLowerCase().contains(enteredStore.toLowerCase()))
                    .collect(Collectors.toList());
        }
        storesTableView.setItems(FXCollections.observableList(filteredStoreList));

    }
}
