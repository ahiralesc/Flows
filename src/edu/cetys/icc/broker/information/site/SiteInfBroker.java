package edu.cetys.icc.broker.information.site;

import java.io.*;

import edu.cetys.icc.broker.information.instrumentation.Instrumentor;


public class SiteInfBroker {
	
	private Instrumentor inst;

	public void go() {
		try {
				// Detect the OS type, and chose a proper monitoring strategy
				String os = System.getProperty("os.name").toLowerCase();
				
				if(os.indexOf("win") >=0 ) { // Windows machine 
					ProactiveInstr snoopy = new ProactiveInstr();
					snoopy.monitor();
				} else if(os.indexOf("mac") >=0 ) {
					// This is a Mac machine
				} else if(os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0) {
					// This is a Unix machine
				}
				
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		SiteInfBroker client = new SiteInfBroker();
		client.go();
	}
}
