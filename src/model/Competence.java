package model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

import controller.Utils;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Competence {
	Integer id;
	
	StringProperty name;
	ObjectProperty<LocalDate>  createdDate;
	ObjectProperty<LocalDate>  lastModifiedDate;
	
	StringProperty noteEvaluation;
	StringProperty x;
	StringProperty r;
	
	StringProperty croyance; 
	// TODO: toJsonObject
	// TODO: get separed field
	StringProperty etat;
	
	Map<Integer, Float> prerequises;	// <id, distance> 
	
	
	public Competence() {
		this.name = new SimpleStringProperty();
		this.createdDate = new SimpleObjectProperty();
		this.lastModifiedDate = new SimpleObjectProperty();
		this.noteEvaluation = new SimpleStringProperty();
		this.x = new SimpleStringProperty();
		this.r = new SimpleStringProperty();
		this.croyance = new SimpleStringProperty();
		this.etat = new SimpleStringProperty();
	}
	
	public Competence(
			String name,
			LocalDate create,
			LocalDate modify,
			String note,
			String x,
			String r,
			String croyance,
			String etat
			) {
		this.name = new SimpleStringProperty(name);
		this.createdDate = new SimpleObjectProperty(create);
		this.lastModifiedDate = new SimpleObjectProperty(modify);
		this.noteEvaluation = new SimpleStringProperty(note);
		this.x = new SimpleStringProperty(x);
		this.r = new SimpleStringProperty(r);
		this.croyance = new SimpleStringProperty(croyance);
		this.etat = new SimpleStringProperty(etat);
	}
	
	public StringProperty nameProperty() {
		return name;
	}
	public ObjectProperty<LocalDate> createdDateProperty() {
		return createdDate;
	}
	public ObjectProperty<LocalDate> lastModifiedDateProperty() {
		return lastModifiedDate;
	}
	

	public StringProperty noteEvaluationProperty() {
		return noteEvaluation;
	}
	public StringProperty xProperty() {
		return x;
	}
	public StringProperty rProperty() {
		return r;
	}
	

	public StringProperty croyanceProperty() {
		return croyance;
	}
	public StringProperty etatProperty() {
		return etat;
	}
	
	
	
	
	public void videCompetence() {
		
		this.name.setValue("");
		this.createdDate.setValue(Utils.NOW_LOCAL_DATE());
		this.lastModifiedDate.setValue(Utils.NOW_LOCAL_DATE());
		this.noteEvaluation.setValue("");
		this.x.setValue("");
		this.r.setValue("");

		this.croyance.setValue("");
		this.etat.setValue("");
		
//		Map<Integer, Float> prerequises;	
	}
	
	
	
}
