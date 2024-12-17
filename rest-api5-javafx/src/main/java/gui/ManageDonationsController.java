package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.upt.lp.rest_api5.model.Doacao;

public class ManageDonationsController {

    @FXML
    private TableView<Doacao> donationTable;
    @FXML
    private TableColumn<Doacao, Long> idColumn;
    @FXML
    private TableColumn<Doacao, String> statusColumn;
    @FXML
    private TableColumn<Doacao, Long> requesterIdColumn;
    @FXML
    private TableColumn<Doacao, Long> donorIdColumn;
    @FXML
    private TableColumn<Doacao, Long> equipmentIdColumn;

    private static final String DONATION_URL = "http://localhost:8080/api/doacoes";

    private RestTemplate restTemplate = new RestTemplate();

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("estadoDoacao"));
        requesterIdColumn.setCellValueFactory(new PropertyValueFactory<>("idRequerente"));
        donorIdColumn.setCellValueFactory(new PropertyValueFactory<>("idDoador"));
        equipmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("idEquipamento"));
        loadDonations();
    }

    @FXML
    private void acceptDonation() {
        Doacao selectedDonation = donationTable.getSelectionModel().getSelectedItem();
        if (selectedDonation != null) {
            String url = DONATION_URL + "/accept-donation/" + selectedDonation.getId();
            try {
                restTemplate.put(url, null);
                showAlert("Donation accepted!");
                loadDonations();
            } catch (Exception e) {
                showAlert("Error: " + e.getMessage());
            }
        } else {
            showAlert("No donation selected.");
        }
    }

    @FXML
    private void rejectDonation() {
        Doacao selectedDonation = donationTable.getSelectionModel().getSelectedItem();
        if (selectedDonation != null) {
            String url = DONATION_URL + "/reject-donation/" + selectedDonation.getId();
            try {
                restTemplate.put(url, null);
                showAlert("Donation rejected!");
                loadDonations();
            } catch (Exception e) {
                showAlert("Error: " + e.getMessage());
            }
        } else {
            showAlert("No donation selected.");
        }
    }

    @FXML
    private void cancelDonation() {
        Doacao selectedDonation = donationTable.getSelectionModel().getSelectedItem();
        if (selectedDonation != null) {
            String url = DONATION_URL + "/cancelar/" + selectedDonation.getId();
            try {
                restTemplate.put(url, null);
                showAlert("Donation cancelled!");
                loadDonations();
            } catch (Exception e) {
                showAlert("Error: " + e.getMessage());
            }
        } else {
            showAlert("No donation selected.");
        }
    }

    @FXML
    private void loadDonations() {
        try {
            ResponseEntity<Doacao[]> response = restTemplate.getForEntity(DONATION_URL + "/all-by-requester-id?idRequerente=1", Doacao[].class); // Supondo que o idRequerente seja 1
            donationTable.getItems().setAll(response.getBody());
        } catch (Exception e) {
            showAlert("Error: " + e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}