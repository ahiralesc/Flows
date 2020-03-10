package edu.cetys.icc.broker.information.winutils;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

public interface Kernel32 extends StdCallLibrary {
	Kernel32 INSTANCE = (Kernel32)Native.loadLibrary("kernel32", Kernel32.class);

	/**
	 * Retrieves the number of milliseconds that have elapsed since the system was started.
	 * @see http://msdn2.microsoft.com/en-us/library/ms724408.aspx
	 * @return number of milliseconds that have elapsed since the system was started.
	 */
	public int GetTickCount();
};