package donor.view.equipments;

import java.io.IOException;

import com.upt.lp.app.YourRealMainClass;

import javafx.fxml.FXML;

public class ViewEquipmentsController {
	
private YourRealMainClass yourRealMainClass;
	
	public void setYourRealMainClass(YourRealMainClass yourRealMainClass) {
        this.yourRealMainClass = yourRealMainClass;
    }
	
	@FXML
	private void goHome() throws IOException {
		yourRealMainClass.showMainItems();
	}

}
