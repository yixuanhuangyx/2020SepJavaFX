package Controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

	@FXML
	private TextField note;
	@FXML
	private TextField x;
	@FXML
	private TextField r;
	@FXML
	private Button calculate;
	@FXML
	private TextArea output;

	// public Stage primaryStage;

	@FXML
	public void initialize() {
		System.out.println("initialize  controller ----");

		calculate.setOnAction(event -> {
			if(!note.getText().matches("^[0-9]*\\.?[0-9]*$")) {
				output.setText("Veuillez entrer la valeur note valid¨¦e ");
			}else if(!x.getText().matches("^[0-9]*\\.?[0-9]*$")) {
				output.setText("Veuillez entrer la valeur x valid¨¦e (x>=0)");
			}else if(!r.getText().matches("^[0-9]*\\.?[0-9]*$")) {
				output.setText("Veuillez entrer la valeur r valid¨¦e (x>r>0)");
			}else {
				fonctionsTransformation(Float.parseFloat(note.getText()), Float.parseFloat(x.getText()), Float.parseFloat(r.getText()));
			}
		});

	}

	private void fonctionsTransformation(float note, float x, float r) {
		String outputText = "Veuillez v¨¦rifier les donn¨¦es entr¨¦es";  
		if (note <= (x-r)) {
			 outputText = "m(a) = 0 ; m(n) = 1 ; m(i) = 0 ; m(c) = 0";
		}else if (note <= x && note > (x-r)) {
			 outputText = "m(a) = 0 ; m(n) = " + calculateZone1N(note,x,r) + " ; m(i) = " + calculateZone1I(note,x,r) + " ; m(c) = 0";
		}else if (note <= (x+r) && note > x) {
			outputText = "m(a) = " + calculateZone2A(note,x,r) + " ; m(n) = 1 ; m(i) = " + calculateZone2I(note,x,r) + " ; m(c) = 0";
		}else if(note > (x-r)) {
			outputText = "m(a) = 1 ; m(n) = 0 ; m(i) = 0 ; m(c) = 0";
		}
		output.setText(outputText);
	}

	private float calculateZone2A(float note, float x, float r) {
		float res = note - x;
		res /= r;
		return res;
	}

	private float calculateZone2I(float note, float x, float r) {
		float res = -1 * note + x;
		res /= r;
		res += 1;
		return res;
	}

	private float calculateZone1I(float note, float x, float r) {
		float res =  note - x ;
		res /= r;
		res += 1;
		return res;	}

	private float calculateZone1N(float note, float x, float r) {
		float res = -1 * note + x;
		res /= r;
		return res;
	}



}
