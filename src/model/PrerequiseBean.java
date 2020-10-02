package model;


public class PrerequiseBean {

	String name;
	String distance;
	
	Croyance cpCroyance;
	
	public PrerequiseBean() {
		name = "";
		distance = "";
		cpCroyance = new Croyance(0,1,0,0);
	}
	
	public PrerequiseBean(Prerequise pre) {
		name = pre.nameProperty().getValue();
		distance = pre.distanceProperty().getValue();
	}

	public PrerequiseBean(String key, String value) {
		this.name = key;
		this.distance = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public Croyance getCpCroyance() {
		return cpCroyance;
	}

	public void setCpCroyance(Croyance cpCroyance) {
		this.cpCroyance = cpCroyance;
	}
		
}
