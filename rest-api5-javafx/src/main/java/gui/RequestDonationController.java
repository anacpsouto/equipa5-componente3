package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RequestDonationController {

    @FXML
    private TextField equipmentIdField;
    @FXML
    private TextField donorIdField;

    private static final String DONATION_REQUEST_URL = "http://localhost:8080/api/requests"; // API para registrar pedidos

    private RestTemplate restTemplate = new RestTemplate();

    @FXML
    private void registerDonationRequest() {
        String equipmentId = equipmentIdField.getText();
        String donorId = donorIdField.getText();

        String url = DONATION_REQUEST_URL + "?equipmentId=" + equipmentId + "&donorId=" + donorId;

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
