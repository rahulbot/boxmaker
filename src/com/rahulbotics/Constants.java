package com.rahulbotics;

import java.awt.*;

/**
 * <p>A bunch of useful constants for lots of little projects.</p>
 *
 * @author rahulb
 */
public interface Constants{

	/** my website homepage */
	public static String HOMEPAGE = 
		new String("http://www.rahulbotics.com/");
	
	// What I'm currently calling myself
	public static String ORGANIZATON_NAME = "Rahulbotics";

/******************************************************************************
 **		OS BOOLEANS
 *****************************************************************************/

	/** the java vendor */
	public static String jv = System.getProperty("java.vendor");
	/** the os architecture */
	public static String arch = System.getProperty("os.arch");
	/** the os name */
	public static String os = System.getProperty("os.name");

	/** true if this is a Mac */
    public static boolean isMac = System.getProperty("mrj.version") != null;
	/** true if this is Mac OS X */
    public static boolean isMacOSX = isMac && os.startsWith("Mac OS X");
	/** true if this is Mac Classic */
    public static boolean isMacClassic = isMac && (!isMacOSX);
	/** true if this is Windows */
    public static boolean isWindows = os.startsWith("Windows");
	/** true if this is Solaris */
    public static boolean isSolaris = os.startsWith("Solaris") || os.startsWith("SunOS");
	/** true if this is Linux */
    public static boolean isLinux = os.startsWith("Linux");
	/** true if this is Unix */
	public static boolean isUnix = isLinux || isSolaris;
	/** true if this is a Micrsoft VM, more useful for Applets */
    public static boolean isMicrosoftVM = jv.startsWith("Microsoft");
	/** true if this is a Netscape VM, more useful for Applets */
    public static boolean isNetscapeVM = jv.startsWith("Netscape");


/******************************************************************************
 **		PLATFORM-DEPENDANT CONSTANTS
 ******************************************************************************/

	/** the system-dependant end-of-line symbol */
	public static String EOL = (isMacClassic) ? "\n" : System.getProperty("line.separator");
	/** the system-dependant command-key mask */
	public static int OS_KEY_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

}
