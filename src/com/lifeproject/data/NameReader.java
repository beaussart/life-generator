package com.lifeproject.data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by beaussan on 02/09/15.
 */
public class NameReader extends Reader {

	private List<String> nom = new ArrayList<String>();

	public NameReader(String fileName){
		super(fileName);
		BufferedReader br=null;
		try{
			br = new BufferedReader(new FileReader(new File(getClass().getResource(fileName).getPath())));
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				nom.add(sCurrentLine);
			}
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		finally{
			try{
				if(br != null)br.close();
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}
		nom.remove(nom.size()-1);
	}

	public List<String> getNom(){
		return nom;
	}
}
