package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.http.ResponseEntity;

import com.upt.lp.client.RestApi5ClientApplication;
import com.upt.lp.rest_api5.model.UserType;
import com.upt.lp.rest_api5.model.Utilizador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class RegisterViewController implements Initializable {
	
	ObservableList<UserType> userTypeList =  FXCollections.observableArrayList(UserType.DONOR, UserType.RECIPIENT, UserType.MANAGER);
	
	@FXML
    private Button loginButtonRegisterPage;

    @FXML
    private Button registerButton;

    @FXML
    private TextField registerEmail;

    @FXML
    private TextField registerName;

    @FXML
    private PasswordField registerPassword;
    
    @FXML
    private ChoiceBox<UserType> registerUserType;
    
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		registerUserType.setValue(UserType.DONOR);
		registerUserType.setItems(FXCollections.observableArrayList(UserType.values()));
		
		registerButton.setOnAction(ae -> {

			String name = registerName.getText();
			String email = registerEmail.getText();
			String password = registerPassword.getText();
			UserType type = registerUserType.getValue();

			RestApi5ClientApplication restApi = new RestApi5ClientApplication();

			Utilizador utilizador = new Utilizador(null, name, email, password, type);
			
			
			try {
				restApi.registerUser(utilizador);
				UtilController.changeScene("/gui/LoginView.fxml");

			} catch (Exception e) {
				e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("login failed. Try again.");
				alert.show();

			}	

		});
		
		loginButtonRegisterPage.setOnAction(ae -> {

			//RestApi5ClientApplication restApi = new RestApi5ClientApplication();

			try {
				UtilController.changeScene("/gui/LoginView.fxml");

			} catch (Exception e) {
				e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("login failed. Try again.");
				alert.show();
			}	

		});
		
		
	}

}
