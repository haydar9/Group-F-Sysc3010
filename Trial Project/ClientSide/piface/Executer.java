 //does not have to be a class...
//can integrate into the server side file
//parameter would be the "pythontest.py"
// to execute the "scripts" button pressed.. etc..

import java.io.*;
public class python
{
	
	public static void main(String[] args){
	
	try{
	
	Runtime.getRuntime().exec("python pythontest.py");

	}catch(Exception e){}
	
	}
	
}