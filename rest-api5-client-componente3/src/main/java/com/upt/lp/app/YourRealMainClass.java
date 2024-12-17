package com.upt.lp.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import donor.view.MainItemsController;
import donor.view.equipments.ViewEquipmentsController;

public class YourRealMainClass extends Application {
	private Stage primaryStage;
	private BorderPane mainLayout;
	
	@Override
	public void start (Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Donor Menu");
		showMainView();
		showMainItems();
	}
	
	private void showMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("/donor/view/MainView.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void showMainItems() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("/donor/view/MainItems.fxml"));
		BorderPane mainItems = loader.load();
		
		MainItemsController controller = loader.getController();
		controller.setYourRealMainClass(this);
		
		mainLayout.setCenter(mainItems);
	}
	
	public void showViewEquipmentsScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("/donor/view/equipments/ViewEquipments.fxml"));
		BorderPane viewEquipment = loader.load();
		
		ViewEquipmentsController controller = loader.getController();
	    controller.setYourRealMainClass(this);
	    
		mainLayout.setCenter(viewEquipment);
	}

    public static void main(String[] args) {
        launch(args);
    }
}
