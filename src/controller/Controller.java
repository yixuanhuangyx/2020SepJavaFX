package controller;

import java.io.File;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import model.Competence;
import model.Croyance;
import model.DataTreeViewRoot;
import model.Workspace;

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
	
	
	
	@FXML
	private TreeView<Object> dataTreeView;

	@FXML
	private Button createNewBtn;
	@FXML
	private Button saveBtn;
	
	
	@FXML 
	private TextField cp_name;
	@FXML 
	private DatePicker cp_createdDate;
	@FXML 
	private DatePicker cp_modifiedDate;
	@FXML 
	private TextField cp_croyance;
	@FXML 
	private TextField cp_etat;
	@FXML 
	private TextField cp_note;
	@FXML 
	private TextField cp_x;
	@FXML 
	private TextField cp_r;
	@FXML
	private ListView<Object> cp_prerequises;
	
	
	
	private Competence cp;
	private DataTreeViewRoot root;
	
	private ObservableList<Competence> cps = FXCollections.observableArrayList();
	private TreeItem<Object> treeItemSelected = new TreeItem<Object>();


	private Workspace workspace = new Workspace();
	private String path = "db\\db.json";
	private File file= new File(path);
	
	
	@FXML
	public void initialize() {
		System.out.println("initialize  controller ----");

		cp = new Competence();

		cp_name.textProperty().bindBidirectional(cp.nameProperty());
		cp_createdDate.valueProperty().bindBidirectional(cp.createdDateProperty());
		cp_modifiedDate.valueProperty().bindBidirectional(cp.lastModifiedDateProperty());
		
		cp_croyance.textProperty().bindBidirectional(cp.croyanceProperty());
		cp_etat.textProperty().bindBidirectional(cp.etatProperty());
		
		cp_note.textProperty().bindBidirectional(cp.noteEvaluationProperty());
		cp_x.textProperty().bindBidirectional(cp.xProperty());
		cp_r.textProperty().bindBidirectional(cp.rProperty());
	
		
		

//		sexGroup.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
//			if (sexGroup.getSelectedToggle() != null) {
//				contact.sexProperty().setValue(((RadioButton) sexGroup.getSelectedToggle()).getText());
//			}
//		});
//		contact.birthdayProperty().setValue(now);
//		contactPane.visibleProperty().set(false);
		


		// force the field to be numeric only
//		textField.textProperty().addListener(new ChangeListener<String>() {
//		    @Override
//		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
//		        String newValue) {
//		        if (!newValue.matches("\\d*")) {
//		            textField.setText(newValue.replaceAll("[^\\d]", ""));
//		        }
//		    }
//		});
		
		addEventToTransformationBtn();
		addEventToRevisionBtn();
		addEventToFusionBtn();
		addEventToPropagationBtn();
		

		addEventToCreateNewBtn();
		addEventToSaveBtn();
		
		
		//-- addEventToMenuBar()
		//save menu btn 
		//load menu btn
//		@FXML 
//		private MenuBar myMenuBar;
//		@FXML
//		private Menu fileMenu;
//		@FXML
//		private MenuItem saveMenuItem;
//		@FXML
//		private MenuItem loadMenuItem;
	
	
	}
	
	private void addEventToCreateNewBtn() {
		createNewBtn.setOnAction(event -> {
			cp.videCompetence();
		});
	}
	
	private void addEventToSaveBtn() {
		saveBtn.setOnAction(event -> {
			boolean dataValidated = true;
			
			if (cp_name ==null && dataValidated == true) {
				cp_name.setStyle("-fx-border-color: red ;");
				System.out.println("cp_name erreur!");
				dataValidated = false;
			} else {
				cp_name.setStyle("-fx-border-color: black ;"); 
			}

			// TODO: more check on cretaed date 
			if (cp_createdDate ==null && dataValidated == true) {
				cp_createdDate.setStyle("-fx-border-color: red ;");
				System.out.println("cp_createdDate erreur!");
				dataValidated = false;
			} else {
				cp_createdDate.setStyle("-fx-border-color: black ;"); 
			}
			
			if (cp_modifiedDate ==null && dataValidated == true) {
				cp_modifiedDate.setStyle("-fx-border-color: red ;");
				System.out.println("cp_modifiedDate erreur!");
				dataValidated = false;
			} else {
				cp_modifiedDate.setStyle("-fx-border-color: black ;"); 
			}

			// TODO: more check input value on croyance
			if (cp_croyance ==null && dataValidated == true) {
				cp_croyance.setStyle("-fx-border-color: red ;");
				System.out.println("cp_croyance erreur!");
				dataValidated = false;
			} else {
				cp_croyance.setStyle("-fx-border-color: black ;"); 
			}

			
			//check if input is number
			if (cp_note ==null && dataValidated == true) {
				cp_note.setStyle("-fx-border-color: red ;");
				System.out.println("cp_note erreur!");
				dataValidated = false;
			} else {
				cp_note.setStyle("-fx-border-color: black ;"); 
			}
			
			//check if input is number
			if (cp_x ==null && dataValidated == true) {
				cp_x.setStyle("-fx-border-color: red ;");
				System.out.println("cp_x erreur!");
				dataValidated = false;
			} else {
				cp_x.setStyle("-fx-border-color: black ;"); 
			}
			
			//check if input is number
			if (cp_r ==null && dataValidated == true) {
				cp_r.setStyle("-fx-border-color: red ;");
				System.out.println("cp_r erreur!");
				dataValidated = false;
			} else {
				cp_r.setStyle("-fx-border-color: black ;"); 
			}


			if (cp_etat ==null && dataValidated == true) {
				cp_etat.setStyle("-fx-border-color: red ;");
				System.out.println("cp_etat erreur!");
				dataValidated = false;
			} else {
				cp_etat.setStyle("-fx-border-color: black ;"); 
			}
			
			
			if(root!=null && dataValidated) {
				Competence newCp = addCompetence();
				root.listCPsProperty().add(newCp);


				try {
					workspace.setData(cps);
					workspace.save(file);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}	
		});
		
		
	}
	
	
	private Competence addCompetence() {
		Competence cp;
		
		String name = cp_name.getText();
		LocalDate createdDate = cp_createdDate.getValue();
		LocalDate modifiedDate = cp_modifiedDate.getValue();
		String note = cp_note.getText();
		String x = cp_x.getText();
		String r = cp_r.getText();
		String croyance = cp_croyance.getText();
		String etat = cp_etat.getText();
		
		cp = new Competence(name,createdDate,modifiedDate,note,x,r,croyance,etat);
		
		return cp;
	}

	
	private void addEventToTransformationBtn() {
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
	}
	
	private void addEventToRevisionBtn() {
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
	}
	
	private void addEventToPropagationBtn() {
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
					Croyance res = Propagation.propagation(Utils.getNumberInteger(n), Utils.getNumber(d), cry);
					outputPropagation.setText( res.toString());
				}
		});
	}
	
	private void addEventToFusionBtn() {
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
						"Croyance avec Propagation : " + cryPropa.toString() + "\n" +
						"Croyance avec Fusion: " +	res.toString()
						);
			}
		});
	}
	
	
}
