package edu.cetys.icc.broker.information.grid;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.Queue;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;



public class GridInfBroker {

	/* The login capabilities */
    Logger logger = Logger.getLogger("MyLog");
    FileHandler fh;

	
	/**
	 * The number of thread to execute
	 */
	private final static int THREAD_COUNT = 2;
	
	
	/**
	 * The buffer limit
	 */
	private  final static int MAX_BUFFER_CAPACITY = 10;
	
	
	/**
	 * The thread pool
	 */
	private Vector<Thread> threadPool;
	
	
	/**
	 * Buffered data
	 */
	private Vector<String> metaDataBuffer;
	
	
	/**
	 * Client connection buffer
	 */
	private Queue<Socket> commEndPointQueue;
	
	
	
	/**
	 * Class constructor 
	 */
	public GridInfBroker(){
		this.threadPool = new Vector<Thread>();
		this.metaDataBuffer = new Vector<String>();
		this.commEndPointQueue = new LinkedList<Socket>();
	
		try {
			fh = new FileHandler("C:\\Users\\ahiralesc\\Documents\\mysrc\\java_codes\\InformationBroker\\files\\MyLogFile.log", true);
			logger.addHandler(fh);
			logger.setLevel(Level.ALL);
			SimpleFormatter formatter = new SimpleFormatter();
		    fh.setFormatter(formatter);
		} catch (SecurityException e) {
		      e.printStackTrace();
		   } catch (IOException e) {
		      e.printStackTrace();
		   }	
	}
	
	
	/**
	 * Initiates the thread pool and executes each thread.
	 * Slave threads will poll immediately the master thread 
	 * for work, in this case for a client connection.
	 * 
	 */
	private void prepare() {
		for(int i=0; i<THREAD_COUNT; i++) {
			Thread worker = new Thread(new CommEndPointHandler(this));
			this.threadPool.add(worker);
			worker.start();
		}
	}
	
	
	/**
	 * Gets an available client connection
	 * 
	 * @return null or a client socket descriptor
	 */
	public synchronized Socket getSocketEndPoint() {
		Socket sock = null;
		try {
			sock = this.commEndPointQueue.remove();
		}catch(NoSuchElementException e) {}
		
		return sock;
	}
	
	
	/**
	 * Put socket and state date to buffer 
	 * 
	 */
	public synchronized void put(String state) {
		this.metaDataBuffer.add(state);
	}
	
	
	
	/**
	 * Listens for client connections and buffers them in
	 * a socket buffer client queue.
	 */
	private void start() {
		try {
			
			/* All this lazy server does is:
			 * - receive client connections
			 * - when the 
			 *  
			 */
			ServerSocket serverSock = new ServerSocket(4242);
			
			while(true) {
				Socket sock = serverSock.accept();
				// Add it to the client connection queue
				this.commEndPointQueue.add(sock);
				// Check if we have reached the maximum buffer capacity
			
				if(this.metaDataBuffer.size() >= GridInfBroker.MAX_BUFFER_CAPACITY) {
					/* Other threads should not try to use the data structure while storing
					 * it's content to persistence. 
					 */
					synchronized(this.metaDataBuffer) {
						toPersistance();
						this.metaDataBuffer.clear();
					}
				}
				
			} //End while
		}catch(IOException e) {
			e.printStackTrace();
		}//End try
	}
	
	
	/**
	 * Stores the contents of metaDataBuffer to a file
	 */
	public void toPersistance(){
		for(String s : metaDataBuffer)
			logger.log(Level.INFO, s);
	}
	
	
	/**
	 * Main thread
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GridInfBroker master = new GridInfBroker();
		master.prepare();
		master.start();
	}
	
} // End MasterThread