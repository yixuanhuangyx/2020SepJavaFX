package Controller;

import Model.Croyance;
import Model.etatEnum;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
	private Button calculatePropagation;
	

	
	
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
				fonctionsTransformation(Utils.getNumber(note),Utils.getNumber(x),Utils.getNumber(r));
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
				Croyance cryNew = fonctionsTransformation(Utils.getNumber(noteNew),Utils.getNumber(xNew),Utils.getNumber(rNew));
				Croyance cryOld = fonctionsTransformation(Utils.getNumber(noteOld),Utils.getNumber(xOld),Utils.getNumber(rOld));
				Croyance res = sameEtat(cryNew, cryOld)? regleRevisionInterne(cryNew, cryOld) : moyenne(cryNew,cryOld);
				outputRevision.setText(res.toString());
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
				System.out.println("calculate fusion");
				Croyance res = fusion(
						new Croyance(Utils.getNumber(croyance_locale_a),Utils.getNumber(croyance_locale_n),Utils.getNumber(croyance_locale_i),Utils.getNumber(croyance_locale_c)),
						new Croyance(Utils.getNumber(croyance_propa_a),Utils.getNumber(croyance_propa_n),Utils.getNumber(croyance_propa_i),Utils.getNumber(croyance_propa_c))
				);
				outputFusion.setText(res.toString());
			}
		});
		

	}
	
	

	private Croyance regleRevisionInterne(Croyance cryI, Croyance cryA) {
		final float a = cryI.getA()/(cryI.getA()*cryI.getN()*cryI.getI())*cryA.getI()+cryA.getA();
		final float n = cryI.getN()/(cryI.getA()*cryI.getN()*cryI.getI())*cryA.getI()+cryA.getN();
		final float i = cryI.getI() * cryA.getA();
		final float c = 1-a-n-i;
		return new Croyance(a,n,i,c);
	}
	
	private Croyance moyenne(Croyance cry1, Croyance cry2) {
		final float a = (cry1.getA()+cry2.getA())/2;
		final float n = (cry1.getN()+cry2.getN())/2;
		final float i = (cry1.getI()+cry2.getI())/2;
		final float c = 1-a-n-i;
		return new Croyance(a,n,i,c);
	}

	
	/*
	 * Si le m¨ºme ¨¦tat alors proc¨¦dure 1: r¨¨gle de la r¨¦vision interne d¨¦taill¨¦ ds l'article
	 * Sinon proc¨¦dure 2: la moyenne de les deux croyances
	 * */
	private boolean sameEtat(Croyance cry1, Croyance cry2) {
		return getEtat(cry1)==getEtat(cry2);
	}
	
	
	/*
	 * m(a) > 0 et m(n) =0    ¨¦tat acquise 
	 * m(a)= 0 et  m(n) > 0   ¨¦tat non acquise
	 * m(a)> 0 et m(i)> 0 ¨¦tat probablement acquise 
	 * m(n)> 0 et m(i)> 0 ¨¦tat probablement non acquise 
	 * */
	private etatEnum getEtat(Croyance cry) {
		if(cry.getA() > 0 && cry.getN() == 0) {
			return etatEnum.acquise;
		} else if(cry.getA() == 0 && cry.getN() > 0) {
			return etatEnum.nonAcquise;
		} else if(cry.getA() > 0 && cry.getI() > 0) {
			return etatEnum.probablementAcquise;
		} else if(cry.getN() > 0 && cry.getI() > 0) {
			return etatEnum.probablementNonAcquise;
		}else 
			return etatEnum.undefined;
	}
	

	/*
	 * la fusion de la croyance locale et la croyance propag¨¦e
	 * */
	private Croyance fusion(Croyance cry1, Croyance cry2) {
		final float a = cry1.getA()*cry2.getA() + cry1.getI()*cry2.getA() + cry2.getI()*cry1.getA();
		final float n = cry1.getN()*cry2.getN() + cry1.getI()*cry2.getN() + cry2.getI()*cry1.getN();
		final float i = cry1.getI()*cry2.getI();
		final float c = 1-a-n-i;
		return new Croyance(a,n,i,c);
	}
	

	private Croyance fonctionsTransformation(float note, float x, float r) {
		String outputText = "Veuillez v¨¦rifier les donn¨¦es entr¨¦es";
		Croyance croyance = new Croyance(0, 1, 0, 0);
		if (note <= (x - r)) {
			outputText = croyance.toString();
		} else if (note <= x && note > (x - r)) {
			croyance = new Croyance(0, calculateZone1N(note, x, r), calculateZone1I(note, x, r), 0);
			outputText = croyance.toString();
		} else if (note <= (x + r) && note > x) {
			croyance = new Croyance(calculateZone2A(note, x, r), 1, calculateZone2I(note, x, r), 0);
			outputText = croyance.toString();
		} else if (note > (x - r)) {
			croyance = new Croyance(1, 0, 0, 0);
			outputText = croyance.toString();
		}
		outputTransformation.setText(outputText);
		return croyance;
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
		float res = note - x;
		res /= r;
		res += 1;
		return res;
	}

	private float calculateZone1N(float note, float x, float r) {
		float res = -1 * note + x;
		res /= r;
		return res;
	}
	
	
}
