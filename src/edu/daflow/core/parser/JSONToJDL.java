package edu.daflow.core.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.dataflow.core.job.SWFJob;
import edu.dataflow.core.util.FileSetting;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;


public class JSONToJDL {

	JSONParser parser;
	
	HashMap<String,SWFJob> tjobs;
	
	
	public JSONToJDL(){
		parser = new JSONParser();
		tjobs = new HashMap<String, SWFJob>();
	}
	
	
	public DirectedSparseGraph<SWFJob, Number> load(String fileName) {
		JSONArray jobs = null;
		
		DirectedSparseGraph<SWFJob, Number> g = new DirectedSparseGraph<SWFJob, Number>();
		
		try {
			JSONObject data = (JSONObject) parser.parse(new FileReader(fileName));
			jobs = (JSONArray) data.get("jobs");
						
			for(Object obj : jobs){
				
				SWFJob swfJob = new SWFJob();
				
				JSONObject job = (JSONObject) obj;
				// Get the job id
				swfJob.setJID((String) job.get("jid"));
				
					
				// Parsing of the activation set
				JSONArray as 		= (JSONArray) job.get("activationSet");
				swfJob.setActivationSetPath((String)((JSONObject) as.get(0)).get("path"));
				swfJob.setActivationSetOpt((String)((JSONObject) as.get(0)).get("options"));
								
				// Parsing the successor set
				JSONArray suc 		= (JSONArray) job.get("successors");
				for(Object sobj : suc) {
					JSONObject  su = (JSONObject) sobj;
					swfJob.addSuccessor((String) su.get("jid"));
				}
				
				// Parsing the predecessor set
				JSONArray puc 		= (JSONArray) job.get("predecessors");
				for(Object sobj : puc) {
					JSONObject  pe = (JSONObject) sobj;
					swfJob.addPredecessor((String) pe.get("jid"));
				}
				
				// Parsing of the input file arguments
				JSONArray inData 		= (JSONArray) job.get("in");
				for(Object iobj : inData) {
					JSONObject  in = (JSONObject) iobj;
					String inFile = (String) in.get("fileName");
					String inPath = (String) in.get("path");
					FileSetting fs = new FileSetting(inFile,inPath);
					swfJob.addInputFile(fs);
				}
				
				
				// Parsing of the out file arguments
				JSONArray outData 		= (JSONArray) job.get("out");
				for(Object oobj : outData) {
					JSONObject  out = (JSONObject) oobj;
					String outFile = (String) out.get("fileName");
					String outPath = (String) out.get("path");
					FileSetting fs = new FileSetting(outFile,outPath);
					swfJob.addInputFile(fs);
				}
				
				g.addVertex(swfJob);
				tjobs.put(swfJob.getJID(), swfJob);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Create the set of edges
		int i=0;
		for(SWFJob job : tjobs.values()) {
			// Create the successors
			for(String su : job.getSuccesors()) {
				if( !su.equals("null")) {
					SWFJob suc = tjobs.get(su);
					g.addEdge(new Integer(i),job,suc,EdgeType.DIRECTED);
					i = i + 1;
				}
			}
		}
		
		return g;
	}
	
	
	private void aumentaGrafo(DirectedSparseGraph<SWFJob, Number> g) {
		
		int i = 0;
		for(SWFJob job : g.getVertices()) {
			if(!(job.getOutputFiles().size() == 0)) {
				SWFJob borrado = new SWFJob();
				for(SWFJob suc : g.getSuccessors(job)){
					FileSetting fs = job.getInputFiles().get(0);
					borrado.addInputFile(fs);
					g.addEdge(new Integer(i),suc,borrado,EdgeType.DIRECTED);
					i++;
				}
			}
		}
	}
	
	public void toFile(DirectedSparseGraph<SWFJob, Number> g ){
		
	}
	
	
	public static void main(String args[]) {
		JSONToJDL translator = new JSONToJDL();
		DirectedSparseGraph<SWFJob, Number> g = translator.load("files/workflow.json");
		translator.aumentaGrafo(g);
		translator.toFile(g);
	}
}
