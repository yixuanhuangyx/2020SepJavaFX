package Controller;

import Model.Croyance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

	//Function Transformation
	@FXML
	private TextField note;
	@FXML
	private TextField x;
	@FXML
	private TextField r;
	@FXML
	private Button calculateTransformation;
	@FXML
	private TextArea outputTransformation;
	
	
	
	@FXML
	private TextField noteNew;
	@FXML
	private TextField xNew;
	@FXML
	private TextField rNew;
	@FXML
	private TextField noteOld;
	@FXML
	private TextField xOld;
	@FXML
	private TextField rOld;
	@FXML
	private Button calculateRevision;
	@FXML
	private TextArea outputRevision;
	
	

	@FXML
	private TextField croyance_a;
	@FXML
	private TextField croyance_n;
	@FXML
	private TextField croyance_i;
	@FXML
	private TextField croyance_c;
	@FXML
	private TextField n;
	@FXML
	private TextField d;
	@FXML
	private Button calculatePropagation;
	@FXML
	private TextArea outputPropagation;
	

	
	
	//Function Fusion
	@FXML
	private TextField croyance_locale_a;
	@FXML
	private TextField croyance_locale_n;
	@FXML
	private TextField croyance_locale_i;
	@FXML
	private TextField croyance_locale_c;
	
	@FXML
	private TextField croyance_propa_a;
	@FXML
	private TextField croyance_propa_n;
	@FXML
	private TextField croyance_propa_i;
	@FXML
	private TextField croyance_propa_c;

	@FXML
	private Button calculateFusion;
	@FXML 
	private TextArea outputFusion;
	
	// public Stage primaryStage;

	@FXML
	public void initialize() {
		System.out.println("initialize  controller ----");

		calculateTransformation.setOnAction(event -> {
			if(
				Utils.checkInput(note, outputTransformation) &&
				Utils.checkInput(x, outputTransformation) &&
				Utils.checkInput(r, outputTransformation) 			
			) {
				Croyance res = Transformation.fonctionsTransformation(Utils.getNumber(note),Utils.getNumber(x),Utils.getNumber(r));
				outputTransformation.setText(res.toString());
			}
		});
		
		
		calculateRevision.setOnAction(event -> {
			if(	
				Utils.checkInput(noteNew, outputRevision) &&
				Utils.checkInput(xNew, outputRevision) &&
				Utils.checkInput(rNew, outputRevision) &&
				Utils.checkInput(noteOld, outputRevision) &&
				Utils.checkInput(xOld, outputRevision) &&
				Utils.checkInput(rOld, outputRevision) 
			){
				Croyance cryNew = Transformation.fonctionsTransformation(Utils.getNumber(noteNew),Utils.getNumber(xNew),Utils.getNumber(rNew));
				Croyance cryOld = Transformation.fonctionsTransformation(Utils.getNumber(noteOld),Utils.getNumber(xOld),Utils.getNumber(rOld));
				Croyance res = Revision.sameEtat(cryNew, cryOld)? Revision.regleRevisionInterne(cryNew, cryOld) : Revision.moyenne(cryNew,cryOld);
				String procedure = Revision.sameEtat(cryNew, cryOld)? " procedure: regle revision interne ":" procedure: moyenne ";
				outputRevision.setText(
						"Nouvelle Croyance : " + cryNew.toString() + "\n" +
						"Ancienne Croyance : " + cryOld.toString() + "\n" +
						procedure + "\n" +
						res.toString());
			}
		});
		
		
		calculatePropagation.setOnAction(event -> {
			if(	
					Utils.checkInput(croyance_a, outputPropagation) &&
					Utils.checkInput(croyance_n, outputPropagation) &&
					Utils.checkInput(croyance_i, outputPropagation) &&
					Utils.checkInput(croyance_c, outputPropagation) &&
					Utils.checkInput(d, outputPropagation) &&
					Utils.checkInput(n, outputPropagation) 
				){
					Croyance cry = new Croyance(Utils.getNumber(croyance_a),Utils.getNumber(croyance_n),Utils.getNumber(croyance_i),Utils.getNumber(croyance_c));
					Croyance res ;
					outputRevision.setText(
							"Croyance : " + cry.toString() + "\n" +
							 res.toString());
				}
		});
		
		calculateFusion.setOnAction(event -> {
			if(	
				Utils.checkInput(croyance_locale_a, outputFusion) &&
				Utils.checkInput(croyance_locale_n, outputFusion) &&
				Utils.checkInput(croyance_locale_i, outputFusion) &&
				Utils.checkInput(croyance_locale_c, outputFusion) &&
				Utils.checkInput(croyance_propa_a, outputFusion) &&
				Utils.checkInput(croyance_propa_n, outputFusion) &&
				Utils.checkInput(croyance_propa_i, outputFusion) &&
				Utils.checkInput(croyance_propa_c, outputFusion) 
			){
				Croyance cryLocale = new Croyance(Utils.getNumber(croyance_locale_a),Utils.getNumber(croyance_locale_n),Utils.getNumber(croyance_locale_i),Utils.getNumber(croyance_locale_c));
				Croyance cryPropa = new Croyance(Utils.getNumber(croyance_propa_a),Utils.getNumber(croyance_propa_n),Utils.getNumber(croyance_propa_i),Utils.getNumber(croyance_propa_c));
				Croyance res = Fusion.fusion(cryLocale,cryPropa);
				outputFusion.setText(
						"Croyance Locale : " + cryLocale.toString() + "\n" +
						"Croyance Propag¨¦e : " + cryPropa.toString() + "\n" +
						"apr¨¨s fonction fusion: " +	res.toString()
						);
			}
		});
		

	}
	


	
}
