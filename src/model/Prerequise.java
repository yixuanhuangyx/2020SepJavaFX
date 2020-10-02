package model;

import java.text.ParseException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Prerequise {
	StringProperty name;
	StringProperty distance;
	
	Croyance cpCroyance;

	public Prerequise() {
		this.name = new SimpleStringProperty();
		this.distance = new SimpleStringProperty();
		
		this.cpCroyance = new Croyance(0,1,0,0);
	}
	
	public Prerequise(String name, String distance) {
		this.name = new SimpleStringProperty(name);
		this.distance = new SimpleStringProperty(distance);
		
		this.cpCroyance = new Croyance(0,1,0,0);
	}

	public Prerequise(PrerequiseBean preValue) {
		this.name = new SimpleStringProperty(preValue.getName());
		this.distance = new SimpleStringProperty(preValue.getDistance());
		
		this.cpCroyance = new Croyance(0,1,0,0);
	}
	
	public Prerequise(String text) {

		this.name = new SimpleStringProperty(text.split("[\\.]")[0]);
		this.distance = new SimpleStringProperty(text.split("[\\.]")[1]);
		this.cpCroyance = new Croyance(0,1,0,0);
	}
	
	public StringProperty nameProperty() {
		return name;
	}

	public StringProperty distanceProperty() {
		return distance;
	}

	public Croyance getCpCroyance() {
		return cpCroyance;
	}

	public void setCpCroyance(Croyance cpCroyance) {
		this.cpCroyance = cpCroyance;
	}

	public String getName() {
		return name.getValue();
	}

	public String getDistance() {
		return distance.getValue();
	}


	public String toListOutputString() {
		String res = this.name.getValue() + ":" + this.distance.getValue();
		return res;
	}

	public void setInput(String text) {
		this.name = new SimpleStringProperty(text.split("[\\:]")[0]);
		this.distance = new SimpleStringProperty(text.split("[\\:]")[1]);
	}
	
	
	
}
