interface ClientInterface {
	public void receive(String message);
	public void send(String message);
	public void connect(String server, int port);
	public void close();
}
