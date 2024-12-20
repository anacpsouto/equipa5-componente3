package application;

import java.io.IOException;

import gui.UtilController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private static Scene mainScene;

	@Override
	public void start(Stage primaryStage) {
		UtilController.setStage(primaryStage);
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LoginView.fxml"));
			Parent root = loader.load();

			mainScene = new Scene(root);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Donation System");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Scene getMainScene() {
		return mainScene;
	}

	// --------------------CLASSE DO DIOGO
	// (YourRealMainClass)-------------------------------------------------------------------------------------
	/*
	 * package com.upt.lp.app;
	 * 
	 * import java.io.IOException;
	 * 
	 * import javafx.application.Application; import javafx.fxml.FXMLLoader; import
	 * javafx.scene.Scene; import javafx.scene.layout.BorderPane; import
	 * javafx.stage.Modality; import javafx.stage.Stage;
	 * 
	 * import donor.view.MainItemsController; import
	 * donor.view.ViewEquipmentsController; import
	 * donor.view.RegisterEquipmentController; import
	 * donor.view.RegisterEquipmentComputerController; import
	 * donor.view.RegisterEquipmentAcessoryController;
	 * 
	 * public class YourRealMainClass extends Application { private Stage
	 * primaryStage; private BorderPane mainLayout;
	 * 
	 * @Override public void start (Stage primaryStage) throws IOException {
	 * this.primaryStage = primaryStage; this.primaryStage.setTitle("Donor Menu");
	 * showMainView(); showMainItems(); }
	 * 
	 * private void showMainView() throws IOException { FXMLLoader loader = new
	 * FXMLLoader();
	 * loader.setLocation(App.class.getResource("/donor/view/MainView.fxml"));
	 * mainLayout = loader.load(); Scene scene = new Scene(mainLayout);
	 * primaryStage.setScene(scene); primaryStage.show(); }
	 * 
	 * public void showMainItems() throws IOException { FXMLLoader loader = new
	 * FXMLLoader();
	 * loader.setLocation(App.class.getResource("/donor/view/MainItems.fxml"));
	 * BorderPane mainItems = loader.load();
	 * 
	 * MainItemsController controller = loader.getController();
	 * controller.setYourRealMainClass(this);
	 * 
	 * mainLayout.setCenter(mainItems); }
	 * 
	 * public void showViewEquipmentsScene() throws IOException { FXMLLoader loader
	 * = new FXMLLoader();
	 * loader.setLocation(App.class.getResource("/donor/view/ViewEquipments.fxml"));
	 * BorderPane viewEquipment = loader.load();
	 * 
	 * ViewEquipmentsController controller = loader.getController();
	 * controller.setYourRealMainClass(this);
	 * 
	 * mainLayout.setCenter(viewEquipment); }
	 * 
	 * public void showRegisterEquimentStage() throws IOException { FXMLLoader
	 * loader = new FXMLLoader();
	 * loader.setLocation(App.class.getResource("/donor/view/RegisterEquipment.fxml"
	 * )); BorderPane registerEquipment = loader.load();
	 * 
	 * RegisterEquipmentController controller = loader.getController();
	 * controller.setYourRealMainClass(this);
	 * 
	 * Stage addDialogStage = new Stage();
	 * addDialogStage.setTitle("Register New Equipment");
	 * addDialogStage.initModality(Modality.WINDOW_MODAL);
	 * addDialogStage.initOwner(primaryStage); Scene scene = new Scene
	 * (registerEquipment); addDialogStage.setScene(scene);
	 * addDialogStage.showAndWait(); }
	 * 
	 * public void showEditEquimentStage() throws IOException { FXMLLoader loader =
	 * new FXMLLoader();
	 * loader.setLocation(App.class.getResource("/donor/view/EditEquipment.fxml"));
	 * BorderPane editEquipment = loader.load();
	 * 
	 * Stage addDialogStage = new Stage();
	 * addDialogStage.setTitle("Edit Equipment");
	 * addDialogStage.initModality(Modality.WINDOW_MODAL);
	 * addDialogStage.initOwner(primaryStage); Scene scene = new Scene
	 * (editEquipment); addDialogStage.setScene(scene);
	 * addDialogStage.showAndWait(); }
	 * 
	 * public void showDeleteEquimentStage() throws IOException { FXMLLoader loader
	 * = new FXMLLoader();
	 * loader.setLocation(App.class.getResource("/donor/view/DeleteEquipment.fxml"))
	 * ; BorderPane deleteEquipment = loader.load();
	 * 
	 * Stage addDialogStage = new Stage();
	 * addDialogStage.setTitle("Delete Equipment");
	 * addDialogStage.initModality(Modality.NONE);
	 * addDialogStage.initOwner(primaryStage); Scene scene = new Scene
	 * (deleteEquipment); addDialogStage.setScene(scene);
	 * addDialogStage.showAndWait(); }
	 *///---------------------------------------------------------------------------------------------------------fim
	
	//------------------------PARTE COMENTADA DENTRO DO CODIGO DO DIOGO-------------------------------------------------
	 /* public void showRegisterComputer() throws IOException { FXMLLoader loader
	 * = new FXMLLoader(); loader.setLocation(App.class.getResource(
	 * "/donor/view/RegisterEquipmentComputer.fxml")); BorderPane registerComputer =
	 * loader.load();
	 * 
	 * RegisterEquipmentComputerController controller = loader.getController();
	 * controller.setYourRealMainClass(this);
	 * 
	 * 
	 * mainLayout.setCenter(registerComputer); }
	 */

	//---------------------------------------------------------------------------------------------------------INICIO
	/*public void showRegisterComputer() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("/donor/view/RegisterEquipmentComputer.fxml"));
		BorderPane registerComputer = loader.load();

		// Controlador de RegisterComputer
		RegisterEquipmentComputerController controller = loader.getController();
		controller.setYourRealMainClass(this);

		// Atualiza o conteúdo central do mainLayout com a tela de Register Computer
		// mainLayout.setCenter(registerComputer);

		Stage addDialogStage = new Stage();
		addDialogStage.setTitle("Register Computer");
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.initOwner(primaryStage);
		Scene scene = new Scene(registerComputer);
		addDialogStage.setScene(scene);
		addDialogStage.showAndWait();
	}
	*/ //-----------------------------------------------------------------------------------------------------FIM

	//------------------------PARTE COMENTADA DENTRO DO CODIGO DO DIOGO-------------------------------------------------
	/*
	 * public void showRegisterAcessory() throws IOException { FXMLLoader loader =
	 * new FXMLLoader(); loader.setLocation(App.class.getResource(
	 * "/donor/view/RegisterEquipmentAcessory.fxml")); BorderPane registerAcessory =
	 * loader.load();
	 * 
	 * RegisterEquipmentAcessoryController controller = loader.getController();
	 * controller.setYourRealMainClass(this);
	 * 
	 * 
	 * mainLayout.setCenter(registerAcessory); }
	 */
	//---------------------------------------------------------------------------------------------------------INICIO
	/*public void showRegisterAcessory() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("/donor/view/RegisterEquipmentAcessory.fxml"));
		BorderPane registerAcessory = loader.load();

		// Controlador de RegisterAcessory
		RegisterEquipmentAcessoryController controller = loader.getController();
		controller.setYourRealMainClass(this);

		// Atualiza o conteúdo central do mainLayout com a tela de Register Accessory
		// mainLayout.setCenter(registerAcessory);

		Stage addDialogStage = new Stage();
		addDialogStage.setTitle("Register Acessory");
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.initOwner(primaryStage);
		Scene scene = new Scene(registerAcessory);
		addDialogStage.setScene(scene);
		addDialogStage.showAndWait();
	}
	*/ //-----------------------------------------------------------------------------------------------------FIM
}
