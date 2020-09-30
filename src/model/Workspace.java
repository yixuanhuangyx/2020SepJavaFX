package model;

import java.io.File;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.collections.ObservableList;

public class Workspace {

	private ObjectMapper mapper = new ObjectMapper();

	private ArrayList<EtudiantBean> etus = new ArrayList<EtudiantBean>();

	public ArrayList<EtudiantBean> fromFile(File file) throws Exception{

		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		ArrayList<EtudiantBean> etus = mapper.readValue(file,new TypeReference<ArrayList<EtudiantBean>>() {});
		return etus;
	}
	
	public void save(File f) throws Exception{
		mapper.writeValue(f, etus);
	}
	
	public void setData(ObservableList<Etudiant> valueList) throws Exception {
		etus.clear();
		for (Etudiant g : valueList) {
			etus.add(new EtudiantBean(g));
		}	
	}
}
