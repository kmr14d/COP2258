import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;



public class ChatClient extends JFrame implements ActionListener, KeyListener, ClientInterface {

	JTextArea incoming = new JTextArea(30, 65);
	JScrollPane messages = new JScrollPane(incoming);
	JTextField text = new JTextField(20);
	Button send;
	
	Socket socket;
	DataOutputStream dataout;
	ClientRunner client;
	
	
	public ChatClient(String title){
		//localhost = 'ec2-54-235-157-206.compute-1.amazonaws.com';
		
		super(title);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		send = new Button("send");
		send.addActionListener(this);
		text.addKeyListener(this);
		add(messages);
		add(text);
		add(send);
		connect("ec2-54-235-157-206.compute-1.amazonaws.com", 1000);
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			send(text.getText());
			//incoming.append(text.getText() + "\n");
			text.setText("");
		}
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("send")){
			send(text.getText());
			//incoming.append(text.getText() + "\n");
			text.setText("");
		}
	}

	public static void main(String[] args){
		ChatClient c = new ChatClient("Chat");
		c.setSize(800, 600);
		c.setVisible(true);
	}

	public void connect(String server, int port){
		System.out.println("Connecting to server");
		try {
			socket = new Socket(server, port);
			dataout = new DataOutputStream(socket.getOutputStream());
			client = new ClientRunner(this,socket);
		}
		catch(Exception e){
			System.out.println("Could not connect to server");
			System.exit(-1);
		}
	}
	
	public void receive(String message) {
		incoming.append(message + "\n");
	}

	public void send(String message) {
		System.out.println("Sending message: " + message);
		try{
			dataout.writeUTF(message);
			dataout.flush();
		}
		catch(Exception e){
			System.out.println("Sending error");
		}
	}

	public void close() {
		try {
			dataout.close();
			socket.close();
			client.close();
		}
		catch(Exception e){
			System.out.println("error closing");
		}
		System.exit(-1);
	}
	
}

