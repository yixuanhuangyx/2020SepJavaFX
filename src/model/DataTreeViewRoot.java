package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class DataTreeViewRoot {
	private String titre = "Dataset list";
	private final BooleanProperty racineSelected = new SimpleBooleanProperty();
	
	public String getTitre() {
		return titre;
	}
	
	public BooleanProperty racineSelectedProperty() {
		return racineSelected;
	}
	
}
