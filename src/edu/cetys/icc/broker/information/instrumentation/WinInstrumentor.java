package edu.cetys.icc.broker.information.instrumentation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import edu.cetys.icc.broker.information.data.State;

public class WinInstrumentor extends AbstractInstrumentor{

	@Override
	public State getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumRunningJobs() {
	    int numRunningJobs = -1;
		try {
	        @SuppressWarnings("unused")
			String line = null;
	            
	        Process p = Runtime.getRuntime().exec
		        (System.getenv("windir") +"\\system32\\"+"tasklist.exe /v /fi \"STATUS eq running\"");
	        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        
	        // Skip the first two lines
	        input.readLine();
	        input.readLine();
	        while ((line = input.readLine()) != null)
	        	numRunningJobs++;
	        input.close();
	    } catch (Exception err) {
	        err.printStackTrace();
	    }
		
		return numRunningJobs;
	}


	@Override
	public int getNumWaitingJobs() {
		// TODO Auto-generated method stub
		return 0;
	}
}
