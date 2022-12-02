package santosw.P5;

import java.io.IOException;
import java.net.ServerSocket;

public class InstrumentServer 
{
	final static int SBAP_PORT = 8888;
	public static void main(String[] args) throws IOException
	{
		ServerSocket server = new ServerSocket(SBAP_PORT);
		System.out.println("Waiting for clients to connect...");
	}
}
