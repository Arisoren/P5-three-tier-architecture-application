package santosw.P5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

/***************************************************************
 * Student Name: Wilver Santos
 * File Name: InstrumentServer.java
 * Assignment number: Project 5
 *
 * the InstrumentServer class is a client server responsible for hosting a SQL database of Instruments and the quantities and locations of them in a logistics system.
 * it can be connected to by local clients, and will create a proxy thread for the user to interact with the database whenever a user connects.  
 ***************************************************************/

public class InstrumentServer 
{
	final static int SBAP_PORT = 8889;
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException
	{
		DatabaseSys instrumentDB = new DatabaseSys();
		
		ServerSocket server = new ServerSocket(SBAP_PORT);
		System.out.println("Waiting for clients to connect...");
		
		while (true)
	      {
	         Socket s = server.accept();
	         System.out.println("Client connected.");
	         DBAccessor access = new DBAccessor(s, instrumentDB);
	         Thread t = new Thread(access);
	         t.start();
	      }		
	}
}
