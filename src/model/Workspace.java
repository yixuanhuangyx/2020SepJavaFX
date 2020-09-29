package model;

import java.io.File;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.collections.ObservableList;

public class Workspace {

	private ObjectMapper mapper = new ObjectMapper();
	

	private ArrayList<CompetenceBean> groups = new ArrayList<CompetenceBean>();

	public ArrayList<CompetenceBean> fromFile(File file) throws Exception{

		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		ArrayList<CompetenceBean> groups = mapper.readValue(file,new TypeReference<ArrayList<CompetenceBean>>() {});
		return groups;
	}
	
	public void save(File f) throws Exception{
		mapper.writeValue(f, groups);
	}
	
	public void setData(ObservableList<Competence> grps) throws Exception {
		groups.clear();
		for (Competence g : grps) {
//			groups.add(new CompetenceBean(g));
		}	
	}
}
