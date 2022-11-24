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
 * Assignment number: Project 1
 *
 *the DatabaseSys class refactors the given TestDB.java file into a reusable, clean class
 *for handling connecting to, inputting data, and retrieving data from an SQL database.  
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
	}
	
	public void ShowResults(ResultSet someResult)
	{
		try
		{
			System.out.println("read showing results");
			ResultSetMetaData rsm = someResult.getMetaData();
			int cols = rsm.getColumnCount();
			while(result.next())
			{
				for(int i = 1; i <= cols; i++)
				{
					System.out.print(someResult.getString(i)+" ");
					System.out.println("");
				}
			}
			System.out.println();
		}
		catch(SQLException sqe)
		{
			System.out.println("Message: " + sqe.getMessage() + "\n");
		}
		
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
	
	public ResultSet createInstruments() throws Exception
	{
	         this.stat.execute("CREATE TABLE Instruments (instName CHAR(12),instNumber INTEGER,cost DOUBLE,descrip CHAR(20))");
	         this.stat.execute("INSERT INTO Instruments VALUES ('guitar',1,100.0,'yamaha')");
	         this.stat.execute("INSERT INTO Instruments VALUES ('guitar',2,500.0,'gibson')");
	         this.stat.execute("INSERT INTO Instruments VALUES ('bass',3,250.0,'fender')");
	         this.stat.execute("INSERT INTO Instruments VALUES ('keyboard',4,600.0,'roland')");
	         this.stat.execute("INSERT INTO Instruments VALUES ('keyboard',5,500.0,'alesis')");
	         ResultSet result = this.stat.executeQuery("SELECT * FROM Instruments");
	         return result;
	}
	
	public ResultSet createLocations() throws Exception
	{
		this.stat.execute("CREATE TABLE Locations (locName CHAR(12),locNumber INTEGER,address CHAR(50))");
		this.stat.execute("INSERT INTO Locations VALUES ('PNS',1,'Pensacola Florida')");
		this.stat.execute("INSERT INTO Locations VALUES ('CLT',2,'Charlotte North Carolina')");
		this.stat.execute("INSERT INTO Locations VALUES ('DFW',3,'Dallas Fort Worth Texas')");
	    ResultSet result = this.stat.executeQuery("SELECT * FROM Locations");
	    return result;
	}
	
	public ResultSet createInventory() throws Exception
	{
		this.stat.execute("CREATE TABLE Inventory (iNumber INTEGER,lNumber INTEGER,quantity INTEGER)");
		this.stat.execute("INSERT INTO Inventory VALUES (1,1,15)");
		this.stat.execute("INSERT INTO Inventory VALUES (1,2,27)");
		this.stat.execute("INSERT INTO Inventory VALUES (1,3,20)");
		this.stat.execute("INSERT INTO Inventory VALUES (2,1,10)");
		this.stat.execute("INSERT INTO Inventory VALUES (2,2,10)");
		this.stat.execute("INSERT INTO Inventory VALUES (2,3,35)");
		this.stat.execute("INSERT INTO Inventory VALUES (3,1,45)");
		this.stat.execute("INSERT INTO Inventory VALUES (3,2,10)");
		this.stat.execute("INSERT INTO Inventory VALUES (3,3,17)");
		this.stat.execute("INSERT INTO Inventory VALUES (4,1,28)");
		this.stat.execute("INSERT INTO Inventory VALUES (4,2,10)");
		this.stat.execute("INSERT INTO Inventory VALUES (4,3,16)");        
	    ResultSet result = this.stat.executeQuery("SELECT * FROM Inventory");
	    return result;
	}
	
	public void CloseConnection()
	{
		try 
		{
			conn.close();
		} 
		
		catch (SQLException sqe) 
		{	
			System.out.println("Message: " + sqe.getMessage());
		}
		
		System.out.println("dropped Table Test2, closed connection and ending program");
	}
}
