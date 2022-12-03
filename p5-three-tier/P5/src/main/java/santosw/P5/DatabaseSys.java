package santosw.P5;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/***************************************************************
 * Student Name: Wilver Santos
 * File Name: DatabaseSys.java
 * Assignment number: Project 5
 *
 * the DatabaseSys class refactors the given TestDB.java file into a reusable, clean class
 * for handling connecting to, inputting data, and retrieving data from an SQL database, and further refactored to store information
 * about instrument inventory and location.  
 ***************************************************************/

public class DatabaseSys 
{
	private Connection conn;
	private Statement stat;
	private ResultSet result;
	
	public DatabaseSys() throws IOException, ClassNotFoundException, SQLException
	{
		SimpleDataSource.init("database.properties");
		
		this.conn = SimpleDataSource.getConnection();
		this.stat = conn.createStatement();
		
		IssueCommand("DROP TABLE Instruments");
		IssueCommand("DROP TABLE Locations");
		IssueCommand("DROP TABLE Inventory");
		
		try 
		{
			createInstruments();
			System.out.println("-------- INSTRUMENT TABLE CREATED --------");
			
			createLocations();
			System.out.println("-------- LOCATIONS TABLE CREATED --------");
			
			createInventory();
			System.out.println("-------- INVENTORY TABLE CREATED --------");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public String ShowResults(ResultSet someResult)
	{
		String results = "";
		try
		{
			System.out.println("read showing results");
			ResultSetMetaData rsm = someResult.getMetaData();
			int cols = rsm.getColumnCount();
			while(result.next())
			{
				for(int i = 1; i <= cols; i++)
				{
					
						results += someResult.getString(i) + " ";
					
				}
				
				results += "\n";
			}
			System.out.println();
		}
		catch(SQLException sqe)
		{
			System.out.println("Message: " + sqe.getMessage() + "\n");
		}

		return results;
	}
	
	public ResultSet IssueQuery(String queryString)
	{
		try
		{
			this.result = this.stat.executeQuery(queryString);
			
		}
		catch (SQLException sqe)
		{
			System.out.println("Message: " + sqe.getMessage());
		}
		return this.result;
	}
	
	public void IssueCommand(String commandString)
	{
		try
		{
			this.stat.execute(commandString);
		}
		catch (SQLException sqe)
		{
			System.out.println("Message: " + sqe.getMessage());
		}
	}
	
	public void CloseConnection()
	{
		try 
		{	
			IssueCommand("DROP TABLE Instruments");
			IssueCommand("DROP TABLE Locations");
			IssueCommand("DROP TABLE Inventory");
			
			conn.close();
			System.out.println("------ DISCONNECTED ------");
		} 
		
		catch (SQLException sqe) 
		{	
			System.out.println("Message: " + sqe.getMessage());
		}
	}
	
	public ResultSet createInstruments() throws Exception
	  {
			IssueCommand("CREATE TABLE Instruments (instName CHAR(12),instNumber INTEGER,cost DOUBLE,descrip CHAR(20))");
			IssueCommand("INSERT INTO Instruments VALUES ('guitar',1,100.0,'yamaha')");
			IssueCommand("INSERT INTO Instruments VALUES ('guitar',2,500.0,'gibson')");
			IssueCommand("INSERT INTO Instruments VALUES ('bass',3,250.0,'fender')");
			IssueCommand("INSERT INTO Instruments VALUES ('keyboard',4,600.0,'roland')");
			IssueCommand("INSERT INTO Instruments VALUES ('keyboard',5,500.0,'alesis')");
	         ResultSet result = IssueQuery("SELECT * FROM Instruments");
	         return result;
	  }

	  public ResultSet createLocations() throws Exception
	  {
		  IssueCommand("CREATE TABLE Locations (locName CHAR(12),locNumber INTEGER,address CHAR(50))");
		  IssueCommand("INSERT INTO Locations VALUES ('PNS',1,'Pensacola Florida')");
		  IssueCommand("INSERT INTO Locations VALUES ('CLT',2,'Charlotte North Carolina')");
		  IssueCommand("INSERT INTO Locations VALUES ('DFW',3,'Dallas Fort Worth Texas')");
	         ResultSet result = IssueQuery("SELECT * FROM Locations");
	         return result;
	  }

	  public ResultSet createInventory() throws Exception
	  {
		  IssueCommand("CREATE TABLE Inventory (iNumber INTEGER,lNumber INTEGER,quantity INTEGER)");
		  IssueCommand("INSERT INTO Inventory VALUES (1,1,15)");
		  IssueCommand("INSERT INTO Inventory VALUES (1,2,27)");
		  IssueCommand("INSERT INTO Inventory VALUES (1,3,20)");
		  IssueCommand("INSERT INTO Inventory VALUES (2,1,10)");
		  IssueCommand("INSERT INTO Inventory VALUES (2,2,10)");
		  IssueCommand("INSERT INTO Inventory VALUES (2,3,35)");
		  IssueCommand("INSERT INTO Inventory VALUES (3,1,45)");
		  IssueCommand("INSERT INTO Inventory VALUES (3,2,10)");
		  IssueCommand("INSERT INTO Inventory VALUES (3,3,17)");
		  IssueCommand("INSERT INTO Inventory VALUES (4,1,28)");
		  IssueCommand("INSERT INTO Inventory VALUES (4,2,10)");
		  IssueCommand("INSERT INTO Inventory VALUES (4,3,16)");        
	         ResultSet result = IssueQuery("SELECT * FROM Inventory");
	         return result;
	  }
}
	
