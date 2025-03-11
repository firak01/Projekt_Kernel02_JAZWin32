package basic.zWin32.com.wmi;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.EnumVariant;
import com.jacob.com.Variant;

import basic.zBasic.ExceptionZZZ;
import basic.zBasic.ReflectCodeZZZ;
import basic.zBasic.util.datatype.string.StringZZZ;
import basic.zKernel.IKernelZZZ;
import basic.zKernel.AbstractKernelUseObjectZZZ;
import basic.zKernel.KernelZZZ;
import basic.zKernel.flag.IFlagZEnabledZZZ;

public class KernelWMIZZZ extends AbstractKernelUseObjectZZZ{
	private ActiveXComponent objWMIService = null;
	
	public KernelWMIZZZ(IKernelZZZ objKernel, String[] saFlagControl) throws ExceptionZZZ{
		super(objKernel);
		KernelWMINew_(saFlagControl);
	}
	
	private void KernelWMINew_(String[] saFlagControl) throws ExceptionZZZ{
		
		main:{
	
		if(saFlagControl != null){
			String stemp; boolean btemp;
			for(int iCount = 0;iCount<=saFlagControl.length-1;iCount++){
				stemp = saFlagControl[iCount];
				btemp = setFlag(stemp, true);
				if(btemp==false){ 								   
					   ExceptionZZZ ez = new ExceptionZZZ( stemp, IFlagZEnabledZZZ.iERROR_FLAG_UNAVAILABLE, this, ReflectCodeZZZ.getMethodCurrentName()); 					 
					   throw ez;		 
				}
			}
			if(this.getFlag("init")==true) break main;
		}
		
		objWMIService = new ActiveXComponent("winmgmts:{impersonationLevel=impersonate}");
		}//END main:
	
	}
	
	public boolean isProcessRunning(String sProcessExe) throws ExceptionZZZ{
		boolean bReturn = false;
		main:{
			check:{
				if(StringZZZ.isEmpty(sProcessExe)) {
					ExceptionZZZ ez = new ExceptionZZZ("Process Name String", iERROR_PARAMETER_MISSING, this, ReflectCodeZZZ.getMethodCurrentName());
					throw ez;
				}				
			}//END check:
		
		Variant arg[] = new Variant[] {new Variant("Win32_Process")};
		Dispatch objProcs = objWMIService.invoke("InstancesOf",arg).toDispatch();
		EnumVariant enumProcs = Dispatch.get(objProcs, "_newenum" ).toEnumVariant();

		//ACHTUNG: Man muss jederzeit beenden duerfen, ohne dass ein Fehler passiert !!!
		while ( enumProcs.hasMoreElements()){
			Dispatch objProcess = enumProcs.Next().toDispatch();
			if(objProcess!=null){
					String strCaption = Dispatch.get(objProcess, "Caption").toString();
		
					//System.out.println("Process Name: " + strCaption);
					if(strCaption.equalsIgnoreCase(sProcessExe)){
						bReturn = true;
						break main;
					}
			}//END if(objProcess != null
		}
		
		}//END main:
		return bReturn;		
	}
	
	
	public int countProcessRunning(String sProcessExe) throws ExceptionZZZ{
		int iReturn = 0;
		main:{
			check:{
				if(StringZZZ.isEmpty(sProcessExe)) {
					ExceptionZZZ ez = new ExceptionZZZ("Process Name String", iERROR_PARAMETER_MISSING, this, ReflectCodeZZZ.getMethodCurrentName());
					throw ez;
				}				
			}//END check:
		
		Variant arg[] = new Variant[] {new Variant("Win32_Process")};
		Dispatch objProcs = objWMIService.invoke("InstancesOf",arg).toDispatch();
		EnumVariant enumProcs = Dispatch.get(objProcs, "_newenum" ).toEnumVariant();

		//ACHTUNG: Man muss jederzeit beenden d�rfen, ohne dass ein Fehler passiert !!!
		while ( enumProcs.hasMoreElements()){
			Dispatch objProcess = enumProcs.Next().toDispatch();
			if(objProcess!=null){
					String strCaption = Dispatch.get(objProcess, "Caption").toString();
		
					//System.out.println("Process Name: " + strCaption);
					if(strCaption.equalsIgnoreCase(sProcessExe)){
						iReturn++;
					}
			}//END if(objProcess != null
		}
		
		}//END main:
		return iReturn;	
	}
	
	public int killProcessAll(String sProcessExe) throws ExceptionZZZ{
		int iReturn = 0;
		main:{
			check:{
				if(StringZZZ.isEmpty(sProcessExe)) {
					ExceptionZZZ ez = new ExceptionZZZ("Process Name String", iERROR_PARAMETER_MISSING, this, ReflectCodeZZZ.getMethodCurrentName());
					throw ez;
				}			
			}//END check:
		
		Variant arg[] = new Variant[] {new Variant("Win32_Process")};
		Dispatch objProcs = objWMIService.invoke("InstancesOf",arg).toDispatch();
		EnumVariant enumProcs = Dispatch.get(objProcs, "_newenum" ).toEnumVariant();

		//ACHTUNG: Man muss jederzeit beenden duerfen, ohne dass ein Fehler passiert !!!
		while ( enumProcs.hasMoreElements()){
			Dispatch objProcess = enumProcs.Next().toDispatch();
			if(objProcess!=null){
					String strCaption = Dispatch.get(objProcess, "Caption").toString();
		
					//System.out.println("Process Name: " + strCaption);
					if(strCaption.equalsIgnoreCase(sProcessExe)){
						Dispatch.call(objProcess, "Terminate");
						iReturn++;
					}
			}//END if(objProcess != null
		}
		
		}//END main:
		return iReturn;	
	}
}//END classe
