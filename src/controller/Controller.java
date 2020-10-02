package controller;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import model.Competence;
import model.Croyance;
import model.DataTreeViewRoot;
import model.Etudiant;
import model.EtudiantBean;
import model.Prerequise;
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
	private GridPane mainPane;
	@FXML
	private GridPane cpPane;
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
	private GridPane prePane;
	@FXML
	private TreeView<Object> cp_prerequises;
	//private ListView<Object> cp_prerequises;
	@FXML
	private Button addPreBtn;
	@FXML
	private Button deletePreBtn;
	@FXML
	private Label preNumberLabel;
	 
	private TreeItem<Object> prerequises;
	private TreeItem<Object> preSelected;
	

	
	@FXML
	private TreeView<Object> dataTreeView;
	@FXML
	private Button createNewBtn;
	@FXML
	private Button deleteBtn;
	@FXML
	private Button saveBtn;


	private DataTreeViewRoot root = new DataTreeViewRoot();
	private Etudiant etuSelected = null;
	private Competence cp;
	
	private ObservableList<Etudiant> etus = FXCollections.observableArrayList();

	private TreeItem<Object> treeItemSelected = new TreeItem<Object>();
	private TreeItem<Object> rootNode;

	private Workspace workspace = new Workspace();
	private String path = "data\\data.json";
	private File file= new File(path);
	
	
	@FXML
	public void initialize() {
		System.out.println("initialize  controller ----");
		
		cpPane.visibleProperty().setValue(false);
		
		initializeCp();


		// force the field to be numeric only
//		cp_note.textProperty().addListener(new ChangeListener<String>() {
//		    @Override
//		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
//		        String newValue) {
//		        if (!newValue.matches("^[-+]?\\d*\\.?\\d*$")) {
//		            cp_note.setText("");
//		        }
//		    }
//		});
		
		addEventToTransformationBtn();
		addEventToRevisionBtn();
		addEventToFusionBtn();
		addEventToPropagationBtn();
		
		addEventToCreateNewBtn();
		addEventToSaveBtn();
		addEventToDeleteBtn();
		
		addEventToCreateNewPreBtn();
		addEventToDeletePreBtn();
		
		//-- addEventToMenuBar()
//		@FXML 
//		private MenuBar myMenuBar;
//		@FXML
//		private Menu fileMenu;
//		@FXML
//		private MenuItem saveMenuItem;
//		@FXML
//		private MenuItem loadMenuItem;
	
		loadData();
	}
	
	private void initializeCp() {
		cp = new Competence();

		cp_name.textProperty().bindBidirectional(cp.nameProperty());
		cp_createdDate.valueProperty().bindBidirectional(cp.createdDateProperty());
		cp_modifiedDate.valueProperty().bindBidirectional(cp.lastModifiedDateProperty());
		
		cp_croyance.textProperty().bindBidirectional(cp.croyanceProperty());
		cp_etat.textProperty().bindBidirectional(cp.etatProperty());
		
		cp_note.textProperty().bindBidirectional(cp.noteEvaluationProperty());
		cp_x.textProperty().bindBidirectional(cp.xProperty());
		cp_r.textProperty().bindBidirectional(cp.rProperty());
	}
	
	private void loadData() {
		try {
			etus.clear();
			System.out.println("load etus size-- "+etus.size());

			ArrayList<EtudiantBean> etusValue = workspace.fromFile(file);
			System.out.println("load etusValue size-- "+etusValue.size());
			
			for(EtudiantBean etu : etusValue) {
				etus.add(new Etudiant(etu));
			}
			System.out.println("load etus size-- "+etus.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		initializeTree();
		addListnerToEtus();
	}

	private void addListnerToEtus() {
		ListChangeListener<Object> h;
		h = change ->{
			change.next();
			if (change.wasRemoved()) {
				TreeItem<Object> parentEtudiant = treeItemSelected.getParent();
				parentEtudiant.getChildren().remove(treeItemSelected);
			}
			if (change.wasAdded()) {
				change.getAddedSubList().forEach(item -> {
					TreeItem<Object> cpLeaf = new TreeItem<Object>(item);
					treeItemSelected.getChildren().add(cpLeaf);
				});
			}
		};
		
		ListChangeListener<Object> f;
		f = change -> {
			change.next();
			if (change.wasRemoved()) {
				rootNode.getChildren().remove(treeItemSelected);
			}
			if (change.wasAdded()) {
				change.getAddedSubList().forEach(item -> {
					TreeItem<Object> etuLeaf = new TreeItem<Object>(item);
					rootNode.getChildren().add(etuLeaf);
					((Etudiant)item).listCpsProperty().addListener(h);
				});
			}
		};
		
		ListChangeListener<Object> k;
		k = change -> {
			change.next();
			if (change.wasRemoved()) {
				prerequises.getChildren().remove(preSelected);
			}
			if (change.wasAdded()) {
				change.getAddedSubList().forEach(item -> {
					TreeItem<Object> preLeaf = new TreeItem<Object>(item);
					prerequises.getChildren().add(preLeaf);
				});
			}
		};
		
		for(Etudiant etu: etus) {
//			for(Competence cp: etu.listCpsProperty()) {
//				cp.listPresProperty().addListener(k);
//			}
			etu.listCpsProperty().addListener(h);
		}
		etus.addListener(f);
	}

	private void initializePreTree() {
		TreeItem<Object> preTreeItem = new TreeItem<Object>("Prerequises format:\"Name:Distance\"");
		preTreeItem.setExpanded(true);
		
		if(cp.getPrerequises()!=null) {
	        for (Entry<String, String> entry : cp.getPrerequises().entrySet())  {
	            Prerequise preObj = new Prerequise(entry.getKey(),entry.getValue());
				TreeItem<Object> preLeaf = new TreeItem<Object>(preObj);
				preTreeItem.getChildren().add(preLeaf);
	        }
		}

		TreeView<Object> preTreeView = new TreeView<Object>(preTreeItem);
		preTreeView.setEditable(true);
		preTreeView.setCellFactory(param -> new preTreeCellImpl());
		prePane.getChildren().add(preTreeView);
		

		preTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldVal, newVal) -> {
				preSelected = newVal;
//				Prerequise preSelected = (Etudiant) newVal.getValue();
//				newVal.setExpanded(true);
		});
		
		
		
		if(cp.getPrerequises()==null) {
			preNumberLabel.setText("Total(n) : 0");
		}else {
			Map<String,String> test = cp.getPrerequises();
			preNumberLabel.setText("Total(n) : "+cp.getPrerequises().size());
		}	
	}
	
	private void initializeTree() {
		rootNode = new TreeItem<Object>(root);
		rootNode.setExpanded(true);
		
		for(Etudiant etu: etus) {
			TreeItem<Object> etuNode = new TreeItem<Object>(etu);
			for (Competence cp : etu.listCpsProperty()) {
				TreeItem<Object> cpLeaf = new TreeItem<Object>(cp);
				etuNode.getChildren().add(cpLeaf);
			}
			rootNode.getChildren().add(etuNode);
		}
		
//		TreeView<Object> 
		dataTreeView = new TreeView<Object>(rootNode);
		dataTreeView.setEditable(true);
		dataTreeView.setCellFactory(param -> new TextFieldTreeCellImpl());

		mainPane.getChildren().add(dataTreeView);

		dataTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldVal, newVal) -> {
			if(newVal.getValue() instanceof DataTreeViewRoot) {
				etuSelected = null;
				
				root.racineSelectedProperty().set(true);
				cpPane.visibleProperty().set(false);
				
				newVal.setExpanded(true);
			}else if(newVal.getValue() instanceof Etudiant) {
				etuSelected = (Etudiant) newVal.getValue();
				
				root.racineSelectedProperty().set(false);
				cpPane.visibleProperty().set(false);
				
				
				treeItemSelected = newVal;
			}else if(newVal.getValue() instanceof Competence) {
				etuSelected = null;
				
				root.racineSelectedProperty().set(false);
				cpPane.visibleProperty().set(true);
				
				cp.nameProperty().setValue(((Competence)newVal.getValue()).nameProperty().get());
				LocalDate date = ((Competence)newVal.getValue()).createdDateProperty().get();
				cp.createdDateProperty().setValue(date);
				cp.lastModifiedDateProperty().setValue(((Competence)newVal.getValue()).lastModifiedDateProperty().get());
				
				cp.noteEvaluationProperty().setValue(((Competence)newVal.getValue()).noteEvaluationProperty().get());
				cp.xProperty().setValue(((Competence)newVal.getValue()).xProperty().get());
				cp.rProperty().setValue(((Competence)newVal.getValue()).rProperty().get());
				
				cp.croyanceProperty().setValue(((Competence)newVal.getValue()).croyanceProperty().get());
				cp.etatProperty().setValue(((Competence)newVal.getValue()).etatProperty().get());
				
				Map<String,String> value = ((Competence)newVal.getValue()).getPrerequises();
				cp.setPrerequises(value);
				
				
				treeItemSelected = newVal;

				initializePreTree();
			}
		});

	}
	
	private void addEventToDeleteBtn(){
		deleteBtn.setOnAction(event -> {
			if (treeItemSelected.getValue() instanceof Competence) {
				//remove the competence
				((Etudiant)treeItemSelected.getParent().getValue()).listCpsProperty().remove(treeItemSelected.getValue());
				cpPane.visibleProperty().set(false);
			} else if (etuSelected != null) {
				// remove a student
				etus.remove(treeItemSelected.getValue());
				cpPane.visibleProperty().set(false);
			}
		});	
	}
	
	
	private void addEventToCreateNewPreBtn() {
		addPreBtn.setOnAction(event -> {
			Prerequise pre = new Prerequise("Unknown","Unknown");
			
			Map<String,String> preMap = cp.getPrerequises();
			preMap.put(
					pre.getName(),
					pre.getDistance()
			);
			cp.setPrerequises(preMap);
			
			initializePreTree();
		});
	}
	
	private void addEventToDeletePreBtn(){
		deletePreBtn.setOnAction(event -> {
			Prerequise test = (Prerequise) preSelected.getValue();
			cp.removePre(test);

			initializePreTree();
		});
	}	
	
	private void addEventToCreateNewBtn() {
		createNewBtn.setOnAction(event -> {
			if(root.racineSelectedProperty().getValue()) {
				cpPane.visibleProperty().set(false);
				Etudiant newEtu = new Etudiant();
				etus.add(newEtu);
			}else if(etuSelected != null) {
				cpPane.visibleProperty().set(true);
				cp.videCompetence();
			}
		});
	}
	
	
	private void addEventToSaveBtn() {
		saveBtn.setOnAction(event -> {
			boolean dataValidated = true;
			
			if (cp_name.textProperty().getValue().length() == 0 && dataValidated == true) {
				cp_name.setStyle("-fx-border-color: red ;");
				System.out.println("cp_name erreur!");
				dataValidated = false;
			} else {
				cp_name.setStyle("-fx-border-color: black ;"); 
			}

			if (cp_createdDate == null && dataValidated == true) {
				cp_createdDate.setStyle("-fx-border-color: red ;");
				System.out.println("cp_createdDate erreur!");
				dataValidated = false;
			} else {
				cp_createdDate.setStyle("-fx-border-color: black ;"); 
			}
			
			if (cp_modifiedDate == null && dataValidated == true) {
				cp_modifiedDate.setStyle("-fx-border-color: red ;");
				System.out.println("cp_modifiedDate erreur!");
				dataValidated = false;
			} else {
				cp_modifiedDate.setStyle("-fx-border-color: black ;"); 
			}

			if (cp_croyance == null && dataValidated == true) {
				cp_croyance.setStyle("-fx-border-color: red ;");
				System.out.println("cp_croyance erreur!");
				dataValidated = false;
			} else {
				cp_croyance.setStyle("-fx-border-color: black ;"); 
			}

			if (cp_note == null && dataValidated == true) {
				cp_note.setStyle("-fx-border-color: red ;");
				System.out.println("cp_note erreur!");
				dataValidated = false;
			} else {
				cp_note.setStyle("-fx-border-color: black ;"); 
			}
			
			if (cp_x == null && dataValidated == true) {
				cp_x.setStyle("-fx-border-color: red ;");
				System.out.println("cp_x erreur!");
				dataValidated = false;
			} else {
				cp_x.setStyle("-fx-border-color: black ;"); 
			}
			
			if (cp_r == null && dataValidated == true) {
				cp_r.setStyle("-fx-border-color: red ;");
				System.out.println("cp_r erreur!");
				dataValidated = false;
			} else {
				cp_r.setStyle("-fx-border-color: black ;"); 
			}


			if (cp_etat == null && dataValidated == true) {
				cp_etat.setStyle("-fx-border-color: red ;");
				System.out.println("cp_etat erreur!");
				dataValidated = false;
			} else {
				cp_etat.setStyle("-fx-border-color: black ;"); 
			}
			
			if(etuSelected != null && dataValidated == true) {
				Competence newCp = addCompetence();
				etuSelected.listCpsProperty().add(newCp);
				try {
					workspace.setData(etus);
					workspace.save(file);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}else if(etuSelected == null && dataValidated == true) {
				Competence newCp = addCompetence();
				((Competence)treeItemSelected.getValue()).editCp(newCp);
				try {
					workspace.setData(etus);
					workspace.save(file);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
			
		});
		
		
	}
	
	
	private Competence addCompetence() {
		
		String name = cp_name.getText();
		LocalDate createdDate = cp_createdDate.getValue();
		LocalDate modifiedDate = cp_modifiedDate.getValue();
		String note = cp_note.getText();
		String x = cp_x.getText();
		String r = cp_r.getText();
		String croyance = cp_croyance.getText();
		String etat = cp_etat.getText();
		
		
		List<Prerequise> listPreRes = new ArrayList<>();
		if(cp.getPrerequises()!=null) {
	        for (Entry<String, String> entry : cp.getPrerequises().entrySet())  { 
	            Prerequise preObj = new Prerequise(entry.getKey(),entry.getValue());
	            listPreRes.add(preObj);
	        }
		}
		
		Competence cp;
		cp = new Competence(name,createdDate,modifiedDate,note,x,r,croyance,etat,listPreRes);
		
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
				outputTransformation.setText(res.toString() +"\n"+"Etat Competence:"+ Utils.getEtat(res) );
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
	
	

	private final class TextFieldTreeCellImpl extends TreeCell<Object> {

		private TextField textField;

		public TextFieldTreeCellImpl() {
		}

		@Override
		public void startEdit() {
			// we could only edit etudiant in treeview
			if (!(getTreeItem().getValue() instanceof Etudiant)) {
				return;
			}

			super.startEdit();
			if (textField == null) {
				createTextField();
			}

			System.out.println("startEdit");
			setText(null);
			setGraphic(textField);
			textField.selectAll();
		}

		@Override
		public void cancelEdit() {
			super.cancelEdit();
			System.out.println("cancelEdit");
			setText(((Etudiant) getItem()).getNameOfEtu());
			setGraphic(getTreeItem().getGraphic());
		}

		@Override
		public void updateItem(Object item, boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				if (isEditing()) {
					if (textField != null) {
						textField.setText(getString());
					}
					setText(null);
					setGraphic(textField);
				} else {
					setText(getString());
					setGraphic(getTreeItem().getGraphic());
				}
			}
		}

		private void createTextField() {

			System.out.println("createTextField");
			textField = new TextField(getString());
			textField.setOnKeyReleased(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent t) {
					if (t.getCode() == KeyCode.ENTER) {
						((Etudiant) getTreeItem().getValue()).etuNameProperty().set(textField.getText());
						commitEdit(((Etudiant) getTreeItem().getValue())); 
					} else if (t.getCode() == KeyCode.ESCAPE) {
						cancelEdit();
					}
				}
			});
		}

		private String getString() {
			if (getItem() instanceof Etudiant) {
				return getItem() == null ? "" : ((Etudiant) getItem()).getNameOfEtu();
			} else if (getItem() instanceof DataTreeViewRoot) {
				return getItem() == null ? "" : ((DataTreeViewRoot) getItem()).getTitre();
			} else {
				return getItem() == null ? "" : ((Competence) getItem()).nameProperty().getValue();
			}
		}
	}
	

	private final class preTreeCellImpl extends TreeCell<Object> {

		private TextField textField;

		public preTreeCellImpl() {
		}

		@Override
		public void startEdit() {
			if (!(getTreeItem().getValue() instanceof Prerequise)) {
				return;
			}

			super.startEdit();
			if (textField == null) {
				createTextField();
			}

			System.out.println("startEdit");
			setText(null);
			setGraphic(textField);
			textField.selectAll();
		}

		@Override
		public void cancelEdit() {
			super.cancelEdit();
			System.out.println("cancelEdit");
			setText(getString());
			setGraphic(getTreeItem().getGraphic());
		}

		@Override
		public void updateItem(Object item, boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				if (isEditing()) {
					if (textField != null) {
						textField.setText(getString());
					}
					setText(null);
					setGraphic(textField);
				} else {
					setText(getString());
					setGraphic(getTreeItem().getGraphic());
				}
			}
		}

		private void createTextField() {

			System.out.println("createTextField");
			textField = new TextField(((Prerequise) getItem()).toListOutputString());
			textField.setOnKeyReleased(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent t) {
					if (t.getCode() == KeyCode.ENTER) {
						System.out.println("commitEdit");
						((Prerequise) getTreeItem().getValue()).setInput(textField.getText());
						commitEdit(((Prerequise) getTreeItem().getValue())); 
						cp.addPrerequises(((Prerequise) getTreeItem().getValue()));
					} else if (t.getCode() == KeyCode.ESCAPE) {
						cancelEdit();
					}
				}
			});
		}
		
		private String getString() {
			if (getItem() instanceof Prerequise) {
				return getItem() == null ? "" : ((Prerequise) getItem()).toListOutputString();
			} else {
				return getItem() == null ? "" : (String) getItem();
			} 
		}

	}
	
}
