package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import controller.Utils;
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
	
	StringProperty croyance; // TODO: toJsonObject / get separed field
	StringProperty etat;
	
	
	Map<String, String> prerequises = new HashMap<>();	// <name, distance> 
//	ObservableList<Prerequise> listPres;
	
	
	
	
	public List<Prerequise> listPresProperty() {
		List<Prerequise> res = new ArrayList<>();
		if(this.prerequises!=null) {
	        for (Entry<String, String> entry : this.prerequises.entrySet())  {
	            res.add(new Prerequise (entry.getKey(),entry.getValue()));
	        }
		}
		return res;
	}
	
	public Map<String, String> getPrerequises() {
		return prerequises;
	}

	public void addPrerequises(Prerequise pre) {
		this.prerequises.put(pre.getName(),pre.getDistance());
	}
	
	public void setPrerequises(Map<String, String> prerequises) {
//		this.prerequises = prerequises;
		
		if (prerequises == null) return;
        for (Entry<String, String> entry : prerequises.entrySet())  {
        	this.prerequises.put(entry.getKey(),entry.getValue());
        }
	}

	
	
	
	public Competence() {
		this.name = new SimpleStringProperty();
		this.createdDate = new SimpleObjectProperty();
		this.lastModifiedDate = new SimpleObjectProperty();
		this.noteEvaluation = new SimpleStringProperty();
		this.x = new SimpleStringProperty();
		this.r = new SimpleStringProperty();
		this.croyance = new SimpleStringProperty();
		this.etat = new SimpleStringProperty();
		
		this.prerequises = new HashMap<>();
//		this.listPres = FXCollections.observableArrayList(new ArrayList<Prerequise>());
	}
	
	public Competence(
			String name,
			LocalDate create,
			LocalDate modify,
			String note,
			String x,
			String r,
			String croyance,
			String etat,
			List<Prerequise> listPres
			) {
		this.name = new SimpleStringProperty(name);
		this.createdDate = new SimpleObjectProperty(create);
		this.lastModifiedDate = new SimpleObjectProperty(modify);
		this.noteEvaluation = new SimpleStringProperty(note);
		this.x = new SimpleStringProperty(x);
		this.r = new SimpleStringProperty(r);
		this.croyance = new SimpleStringProperty(croyance);
		this.etat = new SimpleStringProperty(etat);
		
		this.prerequises = new HashMap<>();
		for (Iterator<Prerequise> iter =  listPres.iterator(); iter.hasNext();){
			Prerequise element = (Prerequise) iter.next();
			this.prerequises.put(element.getName(),element.getDistance());	             
		}
//		this.listPres = FXCollections.observableArrayList(listPres);
	}
	
	public Competence(CompetenceBean cp) throws ParseException {
		this.name = new SimpleStringProperty(cp.getName());
		
		Date cDate = new SimpleDateFormat("dd-MM-yyyy").parse(cp.getCreatedDate());
		LocalDate cLocalDate = cDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	
		Date mDate = new SimpleDateFormat("dd-MM-yyyy").parse(cp.getLastModifiedDate());
		LocalDate mLocalDate = mDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		this.createdDate = new SimpleObjectProperty(cLocalDate);
		this.lastModifiedDate = new SimpleObjectProperty(mLocalDate);
		
		this.noteEvaluation = new SimpleStringProperty(cp.getNoteEvaluation());
		this.x = new SimpleStringProperty(cp.getX());
		this.r = new SimpleStringProperty(cp.getR());
		this.croyance = new SimpleStringProperty(cp.getCroyance());
		this.etat = new SimpleStringProperty(cp.getEtat());
		

		this.prerequises = new HashMap<>();
		if(cp.getPrerequises()!=null) {
	        for (Entry<String, String> entry : cp.getPrerequises().entrySet())  {
	            this.prerequises.put(entry.getKey(),entry.getValue());
	        }
		}
//		this.listPres = FXCollections.observableArrayList();
//		for(PrerequiseBean preValue: cp.getPres()) {
//			listPres.add(new Prerequise(preValue));
//		}
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

		this.prerequises = new HashMap<>();	
//		Map<Integer, Float> prerequises;	
	}
	
	public void editCp(Competence newCp) {

		this.name.setValue(newCp.nameProperty().getValue());
		this.createdDate.setValue(newCp.createdDateProperty().getValue());
		this.lastModifiedDate.setValue(newCp.lastModifiedDateProperty().getValue());
		this.noteEvaluation.setValue(newCp.noteEvaluationProperty().getValue());
		this.x.setValue(newCp.xProperty().getValue());
		this.r.setValue(newCp.rProperty().getValue());

		this.croyance.setValue(newCp.croyanceProperty().getValue());
		this.etat.setValue(newCp.etatProperty().getValue());
		
		
		this.prerequises = new HashMap<>();
		if(newCp.getPrerequises()!=null) {
	        for (Entry<String, String> entry : newCp.getPrerequises().entrySet())  {
	            this.prerequises.put(entry.getKey(),entry.getValue());
	        }
		}
	}
	
}
