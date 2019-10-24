import basic.zWin32.com.wmi.KernelWMIZZZTest;
import junit.framework.Test;
import junit.framework.TestSuite;


public class Win32AllTest {
	public static Test suite(){
		TestSuite objReturn = new TestSuite();
		//Merke: Die Tests bilden in ihrer Reihenfolge in etwa die Hierarchie im Framework ab. 
		//            Dies beim Einf�gen weiterer Tests bitte beachten.         
		
		//TODO GOON objReturn.addTestSuite(HashTableWithDupsTest.class);
		  
		objReturn.addTestSuite(KernelWMIZZZTest.class);
		//objReturn.addTestSuite(KernelReaderHTMLZZZTest.class);
	
		
		return objReturn;
	}
	/**
	 * Hiermit eine Swing-Gui starten.
	 * Das ist bei eclipse aber nicht notwendig, au�er man will alle hier eingebundenen Tests durchf�hren.
	 * @param args
	 */
	public static void main(String[] args) {
		junit.swingui.TestRunner.run(Win32AllTest.class);
	}
}
