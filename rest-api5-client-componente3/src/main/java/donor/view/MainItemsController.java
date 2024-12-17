package donor.view;

import java.io.IOException;

import com.upt.lp.app.YourRealMainClass;

import javafx.fxml.FXML;

public class MainItemsController {
	
	private YourRealMainClass yourRealMainClass;
	
	public void setYourRealMainClass(YourRealMainClass yourRealMainClass) {
        this.yourRealMainClass = yourRealMainClass;
    }
	
	@FXML
	private void goViewEquipments() throws IOException {
		yourRealMainClass.showViewEquipmentsScene();
	}

}
