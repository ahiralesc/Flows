package edu.cetys.icc.broker.information.site;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import edu.cetys.icc.broker.information.data.State;
import edu.cetys.icc.broker.information.winutils.User32;
import edu.cetys.icc.broker.information.winutils.Kernel32;

/**
* Utility method to retrieve the idle time on Windows and sample code to test it.
* JNA shall be present in your classpath for this to work (and compile).
* @author Adan Hirales Carbajal
*/
public class ProactiveInstr {

	/**
	 * Get the amount of milliseconds that have elapsed since the last input event
	 * (mouse or keyboard)
	 * @return idle time in milliseconds
	 */
	public int getIdleTimeMillisWin32() {
		User32.LASTINPUTINFO lastInputInfo = new User32.LASTINPUTINFO();
		User32.INSTANCE.GetLastInputInfo(lastInputInfo);
		return Kernel32.INSTANCE.GetTickCount() - lastInputInfo.dwTime;
	}



	public void monitor() throws IOException {
		if (!System.getProperty("os.name").contains("Windows")) {
			System.err.println("ERROR: Only implemented on Windows");
			System.exit(1);
		}

		State state = State.UNKNOWN;
		DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");

		for (;;) {
			int idleSec = getIdleTimeMillisWin32() / 1000;

			State newState = idleSec < 30 ? State.ONLINE : idleSec > 5 * 60 ? State.AWAY : State.IDLE;
			// String userName = System.getProperty("user.name");

			if (newState != state) {
				state = newState;
								
				// Get host state information
				System.out.println("Trying to send " + dateFormat.format(new Date()).toString() + " # " + state.toString());
				
				Socket clientSock = new Socket("127.0.0.1", 49153);
				OutputStreamWriter streamWriter = new OutputStreamWriter(clientSock.getOutputStream());
				BufferedWriter writer = new BufferedWriter(streamWriter);
				
				writer.write(dateFormat.format(new Date()).toString() + " # " + state.toString() + "\n");
				writer.flush();
				writer.close();
			}
			try { Thread.sleep(100); } catch (Exception ex) {}
		}//End for
	}
	
}//End class
