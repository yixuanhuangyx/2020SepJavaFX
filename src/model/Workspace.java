package model;

import java.io.File;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.collections.ObservableList;

public class Workspace {

	private ObjectMapper mapper = new ObjectMapper();

	private ArrayList<CompetenceBean> cps = new ArrayList<CompetenceBean>();

	public ArrayList<CompetenceBean> fromFile(File file) throws Exception{

		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		ArrayList<CompetenceBean> groups = mapper.readValue(file,new TypeReference<ArrayList<CompetenceBean>>() {});
		return groups;
	}
	
	public void save(File f) throws Exception{
		mapper.writeValue(f, cps);
	}
	
	public void setData(ObservableList<Competence> valueList) throws Exception {
		cps.clear();
		for (Competence g : valueList) {
			cps.add(new CompetenceBean(g));
		}	
	}
}