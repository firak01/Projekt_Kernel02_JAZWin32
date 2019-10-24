package basic.zWin32.com.shell;

import java.io.IOException;

import basic.zBasic.ExceptionZZZ;
import basic.zBasic.IConstantZZZ;
import basic.zBasic.ReflectCodeZZZ;
import basic.zWin32.com.shell.KernelEventLogZZZ;
import basic.zKernel.KernelZZZ;
import custom.zKernel.LogZZZ;
import junit.framework.TestCase;

public class KernelEventLogZZZTest extends TestCase  implements IConstantZZZ{
		
	//Kernel und Log-Objekt
	private KernelZZZ objKernel;
	private LogZZZ objLog;
		
	/// +++ Die eigentlichen Test-Objekte
	private KernelEventLogZZZ  objEventLogTest=null;
	
	protected void setUp(){
		try {			
							
			//Kernel + Log - Object dem TestFixture hinzuf�gen. Siehe test.zzzKernel.KernelZZZTest
			objKernel = new KernelZZZ("EventLog", "01", "", "ZKernelConfigEventLog_test.ini",(String)null);
			objLog = objKernel.getLogObject();
			
			//### Die TestObjecte						
			//+++The main object used for testing:
			objEventLogTest = new KernelEventLogZZZ(objKernel, null);
		
		}catch(ExceptionZZZ ez){
			fail("Method throws an exception." + ez.getMessageLast());
		}
		
	
	}//END setup
	
	public void testContructor(){
		
		try{
		
				//+++An object just initialized
				String[] saFlag={"init"};
				KernelEventLogZZZ objEventLogInit = new KernelEventLogZZZ(objKernel, saFlag);
				boolean btemp = objEventLogInit.getFlag("init");
				assertTrue("Unexpected: The init flag was expected to be set", btemp);
		
				//+++ This is not correct when using the test object
				btemp = objEventLogTest.getFlag("init");
				assertFalse("Unexpected: The init flag was expected to NOT be set", btemp);
								
		}catch(ExceptionZZZ ez){
			fail("Method throws an exception." + ez.getMessageLast());
		}
	}//END testConstructor
	
	public void testLogWriteRead(){
		
	
		try{		
			boolean btemp = this.objEventLogTest.writeEvent("TEST");
			assertTrue(btemp);
/*
			try {

				objProcess = load.exec("cmd /c " + sPROCESS_CAPTION);
				Thread.sleep(1000); //Dem Notepad 1 Sekunde Zeit zum Starten geben
			} catch (IOException e) {
				ExceptionZZZ ez = new ExceptionZZZ(sERROR_RUNTIME +"IOException thrown on execution of Runtime.load(..)", iERROR_PARAMETER_VALUE, this, ReflectionZZZ.getMethodCurrentName(), "");
				throw ez;
			} catch (InterruptedException e) {
				ExceptionZZZ ez = new ExceptionZZZ(sERROR_RUNTIME+"InterruptedException thrown on execution of Thread.sleep(..)", iERROR_PARAMETER_VALUE, this, ReflectionZZZ.getMethodCurrentName(), "");
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

	
		*/		
			
		}catch(ExceptionZZZ ez){
			fail("Method throws an exception." + ez.getMessageLast());
		}	
	
	}
	
	
}//END class
