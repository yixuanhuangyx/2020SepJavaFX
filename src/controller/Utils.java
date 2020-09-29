package controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Utils {
	
	public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	public static String dateFormat = "dd-MM-yyyy";
	
	public static boolean checkInput(TextField text, TextArea output) {
		if(!isNumeric(text.getText())) {
			text.setStyle("-fx-text-inner-color: red;");
			output.setText("Veuillez entrer la valeur valid��e ");
			return false;
		}
		return true;
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        Double.parseDouble(strNum);
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
	
	public static final LocalDate NOW_LOCAL_DATE(){
		String date = new SimpleDateFormat(Utils.dateFormat).format(Calendar.getInstance().getTime());
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Utils.dateFormat);
	    LocalDate localDate = LocalDate.parse(date , formatter);
	    return localDate;
	}
}
