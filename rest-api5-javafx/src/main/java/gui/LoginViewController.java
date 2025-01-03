package gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.upt.lp.client.RestApi5ClientApplication;
import com.upt.lp.rest_api5.model.UserType;
import com.upt.lp.rest_api5.model.Utilizador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginViewController implements Initializable {

	@FXML
	private Button loginButton;

	@FXML
	private Button loginCancel;

	@FXML
	private TextField loginEmail;

    @FXML
    private Button loginClickHere;
	@FXML
	private PasswordField loginPassword;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		RestApi5ClientApplication restApi = new RestApi5ClientApplication();

		
		restApi.searchManager("carlos@gmail.com","Eucarlos@2001");

		loginButton.setOnAction(ae -> {

			String email = loginEmail.getText();
			String password = loginPassword.getText();

			//RestApi5ClientApplication restApi = new RestApi5ClientApplication();

			try {
				restApi.login(email, password, null);
				UtilController.changeScene("/gui/RegisterView.fxml");

			} catch (Exception e) {
				e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("login failed. Try again.");
				alert.show();

			}	

		});
		
		loginClickHere.setOnAction(ae -> {


			try {
				UtilController.changeScene("/gui/RegisterView.fxml");

			} catch (Exception e) {
				e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("login failed. Try again.");
				alert.show();
			}	

		});
		
	}
}
