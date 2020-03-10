package edu.cetys.icc.broker.information.winutils;


import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;

public interface User32 extends StdCallLibrary {
	User32 INSTANCE = (User32)Native.loadLibrary("user32", User32.class);

	/**
	* Contains the time of the last input.
	* @see http://msdn.microsoft.com/library/default.asp?url=/library/en-us/winui/winui/windowsuserinterface/userinput/keyboardinput/keyboardinputreference/keyboardinputstructures/lastinputinfo.asp
	*/
	public static class LASTINPUTINFO extends Structure {
	public int cbSize = 8;

	/// Tick count of when the last input event was received.
	public int dwTime;
	}

	/**
	* Retrieves the time of the last input event.
	* @see http://msdn.microsoft.com/library/default.asp?url=/library/en-us/winui/winui/windowsuserinterface/userinput/keyboardinput/keyboardinputreference/keyboardinputfunctions/getlastinputinfo.asp
	* @return time of the last input event, in milliseconds
	*/
	public boolean GetLastInputInfo(LASTINPUTINFO result);
};
