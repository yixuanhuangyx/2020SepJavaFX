package model;

import java.util.Map;

import controller.Utils;

public class CompetenceBean {
	Integer id;
	String name;
	String createdDate;
	String lastModifiedDate;
	
	Float noteEvaluation;
	Float x;
	Float r;
	Croyance croyance; // TODO: toJsonObject
	etatEnum etat;
	
	Map<Integer, Float> prerequises;	// <id, distance> 
	

	
	//getters and setters
	
	public CompetenceBean(Competence g) {
		name = g.nameProperty().getValue();
		createdDate = g.createdDateProperty().getValue().format(Utils.dateFormatter);
		lastModifiedDate = g.lastModifiedDateProperty().getValue().format(Utils.dateFormatter);
		noteEvaluation
	}
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getNoteEvaluation() {
		return noteEvaluation;
	}
	public void setNoteEvaluation(Float noteEvaluation) {
		this.noteEvaluation = noteEvaluation;
	}
	public Float getX() {
		return x;
	}
	public void setX(Float x) {
		this.x = x;
	}
	public Float getR() {
		return r;
	}
	public void setR(Float r) {
		this.r = r;
	}
	public Croyance getCroyance() {
		return croyance;
	}
	public void setCroyance(Croyance croyance) {
		this.croyance = croyance;
	}
	public etatEnum getEtat() {
		return etat;
	}
	public void setEtat(etatEnum etat) {
		this.etat = etat;
	}
	public Map<Integer, Float> getPrerequises() {
		return prerequises;
	}
	public void setPrerequises(Map<Integer, Float> prerequises) {
		this.prerequises = prerequises;
	}


	

}
