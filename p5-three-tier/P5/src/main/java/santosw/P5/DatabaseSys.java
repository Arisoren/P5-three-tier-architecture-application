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
	
