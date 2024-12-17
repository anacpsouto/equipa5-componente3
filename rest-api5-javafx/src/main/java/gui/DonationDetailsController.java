package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.web.client.RestTemplate;
import com.upt.lp.rest_api5.model.Doacao;

public class DonationDetailsController {

    @FXML
    private Label idLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Label requesterIdLabel;
    @FXML
    private Label donorIdLabel;
    @FXML
    private Label equipmentIdLabel;

    private static final String DONATION_URL = "http://localhost:8080/api/doacoes";

    private RestTemplate restTemplate = new RestTemplate();

    public void setDonationId(Long donationId) {
        String url = DONATION_URL + "/" + donationId;
        Doacao donation = restTemplate.getForObject(url, Doacao.class);
        if (donation != null) {
            idLabel.setText(donation.getId().toString());
            statusLabel.setText(donation.getEstadoDoacao());
            requesterIdLabel.setText(donation.getIdRequerente().toString());
            donorIdLabel.setText(donation.getIdDoador().toString());
            equipmentIdLabel.setText(donation.getIdEquipamento().toString());
        }
    }
}