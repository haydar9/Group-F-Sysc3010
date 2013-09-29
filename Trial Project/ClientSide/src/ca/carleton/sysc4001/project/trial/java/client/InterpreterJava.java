package ca.carleton.sysc4001.project.trial.java.client;

import org.python.core.PyInstance;  
import org.python.util.PythonInterpreter;  

/**
 * Class to use to be able to run python code/commands
 * @Pierre
 *
 */

public class InterpreterJava  
{  
   PythonInterpreter interpreter = null;  

   public InterpreterJava()  
   {  
      PythonInterpreter.initialize(System.getProperties(),System.getProperties(), new String[0]);  
      this.interpreter = new PythonInterpreter();  
   }  

   void execfile( final String fileName )  
   {  
      this.interpreter.execfile(fileName);  
   }  

   PyInstance createClass( final String className, final String opts )  
   {  
      return (PyInstance) this.interpreter.eval(className + "(" + opts + ")");  
   }  

   public static void main( String gargs[] )  
   {  
      //InterpreterJava ie = new InterpreterJava();  
      //ie.execfile("filename.py");  
      //PyInstance hello = ie.createClass("filename", "None");  
      //hello.invoke("command");  
   }  
} 