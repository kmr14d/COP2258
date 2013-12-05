import java.net.*;
import java.io.*;

public class ClientRunner implements Runnable
{
	private Socket           socket   = null;
	private ChatClient       client   = null;
	private DataInputStream  streamIn = null;
	private Thread thread = null;
	private boolean run = true;
	public ClientRunner(ChatClient _client, Socket _socket)	{
		client   = _client;
		socket   = _socket;
		open();
		thread = new Thread(this);
		thread.start();
	}
	public void terminate()	{
		run = false;
	}
	public void open()	{
		try	{	
			streamIn  = new DataInputStream(socket.getInputStream());
		}
		catch(IOException ioe)	{
			System.out.println("Error getting input stream: " + ioe);
			client.close();
		}
	}
	public void close()	{
		try	{
			if (streamIn != null) streamIn.close();
			this.terminate();
		}
		catch(IOException ioe)	{
			System.out.println("Error closing input stream: " + ioe);
		}
	}
	public void run()	{
		while (run)	{	
			try	{  
				client.receive(streamIn.readUTF());
         		}
			catch(IOException ioe)	{  
				System.out.println("Listening error: " + ioe.getMessage());
				client.close();
			}
		}
		client.close();
	}
}