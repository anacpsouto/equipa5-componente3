package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.upt.lp.rest_api5.model.Doacao;

public class DonationRequestController {

    @FXML
    private TableView<Doacao> donationRequestsTable;
    @FXML
    private TableColumn<Doacao, Long> idColumn;
    @FXML
    private TableColumn<Doacao, Long> requesterIdColumn;
    @FXML
    private TableColumn<Doacao, Long> donorIdColumn;
    @FXML
    private TableColumn<Doacao, Long> equipmentIdColumn;
    @FXML
    private TableColumn<Doacao, String> statusColumn;

    private static final String DONATION_URL = "http://localhost:8080/api/doacoes";

    private RestTemplate restTemplate = new RestTemplate();

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        requesterIdColumn.setCellValueFactory(new PropertyValueFactory<>("idRequerente"));
        donorIdColumn.setCellValueFactory(new PropertyValueFactory<>("idDoador"));
        equipmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("idEquipamento"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("estadoDoacao"));
        loadDonationRequests();
    }

    @FXML
    private void loadDonationRequests() {
        try {
            ResponseEntity<Doacao[]> response = restTemplate.getForEntity(DONATION_URL + "/donation-requests-by-donor?doadorId=1", Doacao[].class); // Supondo que o idDoador seja 1
            donationRequestsTable.getItems().setAll(response.getBody());
        } catch (Exception e) {
            showAlert("Error: " + e.getMessage());
        }
    }

    @FXML
    private void acceptDonation() {
        Doacao selectedDonation = donationRequestsTable.getSelectionModel().getSelectedItem();
        if (selectedDonation != null) {
            String url = DONATION_URL + "/accept-donation/" + selectedDonation.getId();
            try {
                restTemplate.put(url, null);
                showAlert("Donation accepted!");
                loadDonationRequests();
            } catch (Exception e) {
                showAlert("Error: " + e.getMessage());
            }
        } else {
            showAlert("No donation selected.");
        }
    }

    @FXML
    private void rejectDonation() {
        Doacao selectedDonation = donationRequestsTable.getSelectionModel().getSelectedItem();
        if (selectedDonation != null) {
            String url = DONATION_URL + "/reject-donation/" + selectedDonation.getId();
            try {
                restTemplate.put(url, null);
                showAlert("Donation rejected!");
                loadDonationRequests();
            } catch (Exception e) {
                showAlert("Error: " + e.getMessage());
            }
        } else {
            showAlert("No donation selected.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}