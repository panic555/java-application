package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

public class FirstScreenController {
    public void showItemSearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("itemSearch.fxml"));
        Scene scene = null;

        try{
            scene = new Scene(fxmlLoader.load(), 600, 500);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        HelloApplication.getStage().setTitle("Production Application");
        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();


    }

    public void showCategorySearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("categorySearch.fxml"));
        Scene scene = null;

        try{
            scene = new Scene(fxmlLoader.load(), 600, 500);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        HelloApplication.getStage().setTitle("Production Application");
        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();
    }

    public void showFactorySearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("factorySearch.fxml"));
        Scene scene = null;

        try{
            scene = new Scene(fxmlLoader.load(), 600, 500);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        HelloApplication.getStage().setTitle("Production Application");
        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();
    }

    public void showStoreSearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("storeSearch.fxml"));
        Scene scene = null;

        try{
            scene = new Scene(fxmlLoader.load(), 600, 500);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        HelloApplication.getStage().setTitle("Production Application");
        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();
    }

    public void showDucanSearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ducanSearch.fxml"));
        Scene scene = null;

        try {
            scene = new Scene(fxmlLoader.load(), 600, 500);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HelloApplication.getStage().setTitle("Production Application");
        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();
    }
    public void showAddNewItemScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addNewItemScreen.fxml"));
        Scene scene = null;

        try {
            scene = new Scene(fxmlLoader.load(), 600, 500);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HelloApplication.getStage().setTitle("Production Application");
        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();
    }

    public void showAddNewCategoryScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addNewCategoryScreen.fxml"));
        Scene scene = null;

        try{
            scene = new Scene(fxmlLoader.load(), 600, 500);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        HelloApplication.getStage().setTitle("Production Application");
        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();


    }

    public void showAddNewFactoryScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addNewFactoryScreen.fxml"));
        Scene scene = null;

        try{
            scene = new Scene(fxmlLoader.load(), 600, 500);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        HelloApplication.getStage().setTitle("Production Application");
        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();

    }
    public void showAddNewStoreScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addNewStoreScreen.fxml"));
        Scene scene = null;

        try{
            scene = new Scene(fxmlLoader.load(), 600, 500);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        HelloApplication.getStage().setTitle("Production Application");
        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();


    }
}
