import java.util.*;
import java.io.*;
import java.math.*;
import sun.misc.Signal;
import sun.misc.SignalHandler;
import java.lang.reflect.*;

class Main {
	public static int uneBonneGrosseVariableGlobale = 3;
	public static int k = 0;
    public static void main(String args[]) {
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
    public void run() {
		System.out.println(k);
    }
 });
		
    	while(true){
			k++;
		}
    }
    
    
}
