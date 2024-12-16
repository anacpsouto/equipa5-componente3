package com.upt.lp.app;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import pt.upt.ei.lp.rest.RestClient;
import com.upt.lp.rest_api5.model.Computador;
import com.upt.lp.rest_api5.model.Acessorio;

import java.util.Optional;

public class EquipamentoClient extends Application {
    RestClient restClient = new RestClient();
    
    private void saveComputador(Computador computador) {		
		try {			
			restClient.createComputador(computador);
			showMessage("Computer registed.", AlertType.INFORMATION);
		} catch (Exception e) {
			showMessage("Error while registing computer.", AlertType.ERROR);
			//throw new RuntimeException("Failed to save the book.");
		}
	}
    
    private void saveAcessorio(Acessorio acessorio) {		
		try {			
			restClient.createAcessorio(acessorio);
			showMessage("Acessorie registed.", AlertType.INFORMATION);
		} catch (Exception e) {
			showMessage("Error while registing acessorie.", AlertType.ERROR);
			//throw new RuntimeException("Failed to save the book.");
		}
	}

    public static void main(String[] args) {
        launch(args);
    }

    @Override
	public void start(Stage primaryStage) throws Exception {

		
 
}
