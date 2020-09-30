package model;

import java.util.ArrayList;



public class EtudiantBean {
	private String name;
	private ArrayList<CompetenceBean> cps = new ArrayList<CompetenceBean>();
	
	public EtudiantBean() {
		
	}
	
	public EtudiantBean(String name, ArrayList<CompetenceBean> cps) {
		this.name = name;
		this.cps = cps;
	}
	
	public EtudiantBean(Etudiant etu) {
		this.name = etu.etuNameProperty().getValue();
		for(Competence cp : etu.listCpsProperty()) {
			this.cps.add(new CompetenceBean(cp));
		}
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<CompetenceBean> getCps() {
		return cps;
	}
	public void setCps(ArrayList<CompetenceBean> cps) {
		this.cps = cps;
	}

}
