package Controller;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Utils {
	public static boolean checkInput(TextField text, TextArea output) {
		if(!isNumeric(text.getText())) {
			text.setStyle("-fx-text-inner-color: red;");
			output.setText("Veuillez entrer la valeur valid¨¦e ");
			return false;
		}
		return true;
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}

	public static float getNumber(TextField text) {
		return Float.parseFloat(text.getText());
	}
	
	public static int getNumberInteger(TextField text) {
		return Integer.parseInt(text.getText());
	}
}
