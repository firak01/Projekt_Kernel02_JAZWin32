package basic.zWin32.com.shell;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import basic.zKernel.KernelZZZ;
import basic.zKernel.flag.IFlagZUserZZZ;
import basic.zBasic.ExceptionZZZ;
import basic.zBasic.ReflectCodeZZZ;
import basic.zBasic.util.datatype.string.StringZZZ;
import basic.zKernel.AbstractKernelUseObjectZZZ;

public class KernelEventLogZZZ extends AbstractKernelUseObjectZZZ {
	private ActiveXComponent objShellService = null;
	
	public KernelEventLogZZZ(KernelZZZ objKernel, String[] saFlagControl) throws ExceptionZZZ{
		super(objKernel);
		KernelEventLogNew_(saFlagControl);
	}
	
	private void KernelEventLogNew_(String[] saFlagControl) throws ExceptionZZZ{
		main:{		
		if(saFlagControl != null){
			String stemp; boolean btemp;
			for(int iCount = 0;iCount<=saFlagControl.length-1;iCount++){
				stemp = saFlagControl[iCount];
				btemp = setFlag(stemp, true);
				if(btemp==false){ 								   
					   ExceptionZZZ ez = new ExceptionZZZ(stemp, IFlagZUserZZZ.iERROR_FLAG_UNAVAILABLE, this, ReflectCodeZZZ.getMethodCurrentName()); 					   
					   throw ez;		 
				}
			}
			if(this.getFlag("init")==true) break main;
		}

		objShellService = new ActiveXComponent("WScript.Shell");
		}//END main:
	}
	
	
	public boolean writeEvent(String sMessage) throws ExceptionZZZ{
		/* DOKU
		 * Adds an event entry to a log file.

object.LogEvent(intType, strMessage [,strTarget])
Arguments
object 
WshShell object. 
intType 
Integer value representing the event type. 
strMessage 
String value containing the log entry text. 
strTarget 
Optional. String value indicating the name of the computer system where the event log is stored (the default is the local computer system). Applies to Windows NT/2000 only. 
Remarks
The LogEvent method returns a Boolean value (true if the event is logged successfully, otherwise false). In Windows NT/2000, events are logged in the Windows NT Event Log. In Windows 9x/Me, events are logged in WSH.log (located in the Windows directory). There are six event types.

Type Value 
0 SUCCESS 
1 ERROR 
2 WARNING 
4 INFORMATION 
8 AUDIT_SUCCESS 
16 AUDIT_FAILURE 

Example
The following code logs SUCCESS or ERROR depending on the outcome of the function runLoginScript().

[VBScript] 
Set WshShell = WScript.CreateObject("WScript.Shell")
rc = runLoginScript()      'Returns true if logon succeeds.

if rc then
   WshShell.LogEvent 0, "Logon Script Completed Successfully"
else
   WshShell.LogEvent 1, "Logon Script failed"
end if
[JScript] 
var WshShell = WScript.CreateObject("WScript.Shell");
var rc = runLoginScript();

if (rc) 
   WshShell.LogEvent(0, "Logon Script Completed Successfully");
else
   WshShell.LogEvent(1, "Logon Script failed");
See Also

		 */
		boolean bReturn = false;
		main:{
			check:{
				if(StringZZZ.isEmpty(sMessage)){
					ExceptionZZZ ez = new ExceptionZZZ("Message", iERROR_PARAMETER_MISSING, this, ReflectCodeZZZ.getMethodCurrentName());
					throw ez;				
				}
			}//END check:
		
		
		//FAlsche Syntax: Das sind einfach zuviele methoden....  this.objShellService.invoke("LogEvent", 0, "Das ist ein FGL-Test");
		Variant objArg1 = new Variant(0);
		Variant objArg2 = new Variant("Das ist ein FGL-Test");
		Dispatch.call(objShellService, "LogEvent", objArg1 , objArg2);
		bReturn = true;
		}//END main:
		return bReturn;
	}
}
