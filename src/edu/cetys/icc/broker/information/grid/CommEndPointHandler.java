package edu.cetys.icc.broker.information.grid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketAddress;


/**
 * 
 * @author 	Adan Hirales Carbajal, 
 * 			ahirales@uabc.edu.mx
 * @version 1
 *
 */
public class CommEndPointHandler extends Thread{
		
	/**
	 * The metaData received from a client, used for callbacks 
	 */
	private String metaData;
	
	
	/**
	 * Reference to master thread
	 */
	private GridInfBroker rootThread;
	
	
	/**
	 * Sleep interval, in milliseconds
	 */
	private int timeOut = 2000;
	
	
	
	/**
	 * Class constructor
	 * 
	 * @param rootThread, a reference to the master thread.
	 */
	public CommEndPointHandler(GridInfBroker rootThread) {
		this.rootThread = rootThread;
	}
	
	
	
	/**
	 * Attends the client connection using a polling policy	
	 */
	public void run() {
		 
		while(true) {
			// Try to get some work. If there is no work, go to sleep.
			Socket sock = rootThread.getSocketEndPoint();
						
			if(sock == null) {
				
				// If no work is available... sleep
				try {
					sleep(this.timeOut);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				InputStreamReader streamReader;
								
				try {
					//Get the client IP address
					SocketAddress address = sock.getRemoteSocketAddress();
					String ip = address.toString();
					
					//Read in state from the client socket
					streamReader = new InputStreamReader(sock.getInputStream());
					BufferedReader reader = new BufferedReader(streamReader);
					String state = reader.readLine();
					
					//Store state and metaData to the local buffer
					this.metaData = ip + "|" + state;
					System.out.println("I received " + this.metaData);
					
					this.rootThread.put(this.metaData);
					
					sock.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // End catch
				
			} //End if
		} // End while
	} //End run
	
}
