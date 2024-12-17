package gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.upt.lp.rest_api5.model.Doacao;

public class RequestDonationController {

    @FXML
    private TextField equipmentIdField;
    @FXML
    private TextField donorIdField;

    private static final String DONATION_REQUEST_URL = "http://localhost:8080/api/doacoes/registrar";

    private RestTemplate restTemplate = new RestTemplate();

    @FXML
    private void registerDonationRequest() {
        String equipmentId = equipmentIdField.getText();
        String donorId = donorIdField.getText();

        String url = DONATION_REQUEST_URL + "?idEquipamento=" + equipmentId + "&idDoador=" + donorId + "&idRequerente=1"; // Supondo que o idRequerente seja 1

        try {
            ResponseEntity<Doacao> response = restTemplate.postForEntity(url, null, Doacao.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                showAlert("Donation request successfully registered!");
            } else {
                showAlert("Failed to register donation request.");
            }
        } catch (Exception e) {
            showAlert("Error: " + e.getMessage());
        }
    }

    @FXML
    private void logout() {
        // Lógica para logout
    }

    @FXML
    private void exit() {
        System.exit(0);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}