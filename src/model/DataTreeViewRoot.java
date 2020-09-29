package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;

public class DataTreeViewRoot {
	private String titre = "competences data";
	private final BooleanProperty racineSelected = new SimpleBooleanProperty();
	private ObservableList<Competence> listCps;
	
	public String getTitre() {
		return titre;
	}
	
	public BooleanProperty racineSelectedProperty() {
		return racineSelected;
	}
	
	public ObservableList<Competence> listCPsProperty() {
		return listCps;
	}
	
}
