package edu.cetys.icc.broker.information.instrumentation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import edu.cetys.icc.broker.information.data.State;

public class UnixInstrumentor extends AbstractInstrumentor{

	@Override
	public State getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumRunningJobs() {
		//TODO: debug
		try {
	        String line;
	        Process p = Runtime.getRuntime().exec("ps -e");
	        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        while ((line = input.readLine()) != null) {
	            System.out.println(line); //<-- Parse data here.
	        }
	        input.close();
	    } catch (Exception err) {
	        err.printStackTrace();
	    }
		
		return 0;
	}

	@Override
	public int getNumWaitingJobs() {
		// TODO Auto-generated method stub
		return 0;
	}
}
