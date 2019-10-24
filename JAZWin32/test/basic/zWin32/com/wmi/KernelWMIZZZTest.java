package basic.zWin32.com.wmi;

import java.io.IOException;

import junit.framework.TestCase;
import basic.zBasic.ExceptionZZZ;
import basic.zBasic.IConstantZZZ;
import basic.zBasic.ReflectCodeZZZ;
import basic.zWin32.com.wmi.KernelWMIZZZ;
import basic.zKernel.KernelZZZ;
import custom.zKernel.LogZZZ;

public class KernelWMIZZZTest extends TestCase implements IConstantZZZ{
		
	//+++ TestProcess festlegen	 
	private Process objProcess = null;
	private final String sPROCESS_CAPTION = "notepad.exe";
	
	
	//Kernel und Log-Objekt
	private KernelZZZ objKernel;
	private LogZZZ objLog;
		
	/// +++ Die eigentlichen Test-Objekte
	private KernelWMIZZZ  objWMITest;
	
	protected void setUp(){
		try {			
							
			//Kernel + Log - Object dem TestFixture hinzuf�gen. Siehe test.zzzKernel.KernelZZZTest
			objKernel = new KernelZZZ("WMI", "01", "", "ZKernelConfigWMI_test.ini",(String)null);
			objLog = objKernel.getLogObject();
			
			//### Die TestObjecte						
			//+++The main object used for testing:
			objWMITest = new KernelWMIZZZ(objKernel, null);
		
		}catch(ExceptionZZZ ez){
			fail("Method throws an exception." + ez.getMessageLast());
		}
		
	
	}//END setup
	
	public void testContructor(){
		
		try{
		
				//+++An object just initialized
				String[] saFlag={"init"};
				KernelWMIZZZ objWMIInit = new KernelWMIZZZ(objKernel, saFlag);
				boolean btemp = objWMIInit.getFlag("init");
				assertTrue("Unexpected: The init flag was expected to be set", btemp);
		
				//+++ This is not correct when using the test object
				btemp = objWMITest.getFlag("init");
				assertFalse("Unexpected: The init flag was expected to NOT be set", btemp);
				
				
		}catch(ExceptionZZZ ez){
			fail("Method throws an exception." + ez.getMessageLast());
		}
	}//END testConstructor
	
	public void testProcessHandle(){
		try{
			//F�r den Test eine Process-Starten
			Runtime load = Runtime.getRuntime();
			try {
				objProcess = load.exec("cmd /c " + sPROCESS_CAPTION);
				Thread.sleep(1000); //Dem Notepad 1 Sekunde Zeit zum Starten geben
			} catch (IOException e) {
				ExceptionZZZ ez = new ExceptionZZZ("IOException thrown on execution of Runtime.load(..)", iERROR_RUNTIME, this, ReflectCodeZZZ.getMethodCurrentName());
				throw ez;
			} catch (InterruptedException e) {
				ExceptionZZZ ez = new ExceptionZZZ("InterruptedException thrown on execution of Thread.sleep(..)", iERROR_RUNTIME, this, ReflectCodeZZZ.getMethodCurrentName());
				throw ez;
			}
		
			//1. Z�hle die Anzahl der "notepad.exe Processe. Es darf nur einer sein, sonst wird ggf. ein Proces beendet, der nicht durch dieses TestSetup erzeugt worden ist.
			int icount = objWMITest.countProcessRunning(sPROCESS_CAPTION);
			assertTrue("There was at least one process with the name: '" + sPROCESS_CAPTION + "' expected to be started by this test.", icount > 0);
			assertEquals("There are more than one processes running with the name: '" + sPROCESS_CAPTION + "', please save all data and stop all processes before executing this test.",1, icount);
		
			//2. Kille den gerade gestarteten Process
			icount = objWMITest.killProcessAll(sPROCESS_CAPTION);
			assertTrue("There was at least one process with the name: '" + sPROCESS_CAPTION + "' expected to be killed by this test.", icount > 0);
			assertEquals("There were more than one processes killed with the name: '" + sPROCESS_CAPTION + "', but there should have run only one.",1, icount);
			
			
		}catch(ExceptionZZZ ez){
			fail("Method throws an exception." + ez.getMessageLast());
		}	
		/*
		try{
			String sHost = "192.168.3.101";
			String sPort = "80";
			
		//boolean btemp = objPingTest.ping(sHost, sPort);
		//assertTrue("Unable to connect to host: '" + sHost + " - " + sPort + "'", btemp);
	
	
		*/			
	}//END testPing()
	
	

}
