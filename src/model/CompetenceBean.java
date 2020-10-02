package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import controller.Utils;
import javafx.scene.control.TreeItem;

public class CompetenceBean {
	Integer id;
	String name;
	String createdDate;
	String lastModifiedDate;
	
	String noteEvaluation;
	String x;
	String r;
	//	Croyance croyance; // TODO: toJsonObject
	String croyance; // TODO: toJsonObject
	//	etatEnum etat;
	String etat;
	
	Map<String, String> prerequises;	// <id, distance> 
//	ArrayList<PrerequiseBean> pres = new ArrayList<PrerequiseBean>();



	public CompetenceBean() {
		name = "";
		createdDate = "";
		lastModifiedDate = "";
		noteEvaluation = "";
		x = "";
		r = "";
		croyance = "";
		etat = "";
		this.prerequises = new HashMap<>();
	}
	
	public CompetenceBean(Competence g) {
		name = g.nameProperty().getValue();
		createdDate = g.createdDateProperty().getValue().format(Utils.dateFormatter);
		lastModifiedDate = g.lastModifiedDateProperty().getValue().format(Utils.dateFormatter);
		noteEvaluation = g.noteEvaluationProperty().getValue();
		x = g.xProperty().getValue();
		r = g.rProperty().getValue();
		croyance = g.croyanceProperty().getValue();
		etat = g.etatProperty().getValue();
		

		this.prerequises = new HashMap<>();
		if(g.listPresProperty()!=null) {
			for(Prerequise pre: g.listPresProperty()) {
				this.prerequises.put(pre.getName(), pre.getDistance());
//				this.pres.add(new PrerequiseBean(pre));
			}
			
		}
	}


//	public ArrayList<PrerequiseBean> getPre() {
//
//		this.prerequises = new HashMap<>();
//		ArrayList<PrerequiseBean> res = new ArrayList<PrerequiseBean>();
//		if(this.prerequises!=null) {
//	        for (Entry<String, String> entry : this.prerequises.entrySet())  {
//	            PrerequiseBean preBean = new PrerequiseBean(entry.getKey(),entry.getValue());
//				res.add(preBean);
//	        }
//		}
//		return res;
//	}

//	public void setPres(ArrayList<PrerequiseBean> pres) {
//		this.pres = pres;
//	}
	
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getNoteEvaluation() {
		return noteEvaluation;
	}

	public void setNoteEvaluation(String noteEvaluation) {
		this.noteEvaluation = noteEvaluation;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getCroyance() {
		return croyance;
	}

	public void setCroyance(String croyance) {
		this.croyance = croyance;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Map<String, String> getPrerequises() {
		return prerequises;
	}

	
}
