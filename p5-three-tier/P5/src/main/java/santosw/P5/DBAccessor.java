package santosw.P5;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.Scanner;

/***************************************************************
 * Student Name: Wilver Santos
 * File Name: DBAccessor.java
 * Assignment number: Project 5
 *
 * the DBAccessor class is a proxy class for the Instrument client to communicate with the Instrument database.  
 ***************************************************************/

public class DBAccessor implements Runnable 
{
	private Socket s;
	private Scanner in;
	private PrintWriter out;
	private DatabaseSys instrumentDB;
	private String[] parameters;
	private boolean jobDone;
	   
	DBAccessor(Socket s, DatabaseSys someSys)
	{
		this.s = s;
		this.instrumentDB = someSys;
		jobDone = false;
	}

	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		try
	      {
	         try
	         {
	            in = new Scanner(s.getInputStream());
	            out = new PrintWriter(s.getOutputStream());
	            doService();            
	         }
	         finally
	         {
	            s.close();
	         }
	      }
	      catch (IOException exception)
	      {
	         exception.printStackTrace();
	      }
	}
	
	public void doService() throws IOException
	{      
			while(!jobDone)
			{	
				parameters = in.nextLine().split(" ");
				
				executeCommand(parameters);
			}
	}
	
	public void executeCommand(String[] command)
	   {
			/*
			 * command[0] = type
			 * command[1] = brand
			 * command[2] = cost
			 * command[3] = location
			 */
			ResultSet queryResult;
			String thisQuery;
			System.out.println("Command recieved");
			
			thisQuery = buildQuery(command);
			queryResult = instrumentDB.IssueQuery(thisQuery);
			
			//FINALLY, once the result set has been received, it can be formatted into a string.
			String results = instrumentDB.ShowResults(queryResult);
			out.println(results);
			out.flush();
			jobDone = true;
	   }
	
	private String buildQuery(String[] command)
	{
		/*
		 * command[0] = type
		 * command[1] = brand
		 * command[2] = cost
		 * command[3] = location
		 */
			String someQuery = "SELECT Instruments.instName, Instruments.descrip, Instruments.cost, Inventory.quantity, Locations.address FROM Instruments"
					+ " JOIN Inventory ON Instruments.instNumber = Inventory.iNumber";
			
			if(!command[0].matches("all"))
			{
				someQuery += " AND Instruments.instName = '" + command[0] + "'";
			}
			if(!command[2].matches("0"))
			{
				someQuery += " AND Instruments.cost <= " + command[2];
			}
			if(!command[1].matches("all"))
			{
				someQuery += " AND Instruments.descrip = '" + command[1] + "'";
			}
			
			someQuery += " JOIN Locations ON Inventory.lNumber = Locations.locNumber";
			
			if(!command[3].matches("all"))
			{
				someQuery += " AND Locations.locName = '" + command[3] + "'";
			}
		
		return someQuery;
	}

}
