package model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Etudiant {

	private StringProperty etuName;
	private ObservableList<Competence> listCps;
	

	public Etudiant( String name, List<Competence> cps){
		this.etuName = new SimpleStringProperty(name);
		this.listCps = FXCollections.observableArrayList(cps);
	}
	
	public Etudiant(List<Competence> cps) {
		this.etuName = new SimpleStringProperty("Unnamed");
		this.listCps = FXCollections.observableArrayList(cps);
	}
	
	public Etudiant() {
		this.etuName = new SimpleStringProperty("Unnamed");
		this.listCps = FXCollections.observableArrayList(new ArrayList<Competence>());
	}

	public Etudiant(EtudiantBean etuValue) throws ParseException {
		etuName = new SimpleStringProperty(etuValue.getName());
		listCps = FXCollections.observableArrayList();
		for(CompetenceBean cpValue : etuValue.getCps()) {
			listCps.add(new Competence(cpValue));
		}
	}
	
	
	

	public StringProperty etuNameProperty() {
		return etuName;
	}
	
	public ObservableList<Competence> listCpsProperty() {
		return listCps;
	}
	
	public String getNameOfEtu() {
		return etuName.get();
	}
	
}
