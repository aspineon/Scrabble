package prk.network;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * Klasa połączenia sieciowego po stronie serwera
 * 
 * Klasa dziedzicząca po klasie NetworkConneciton
 * 
 * @author Maciej Gawlowski 
 */

public class Server extends NetworkConnection {
	private int port;
	
	public Server(int port, Consumer<Serializable> onReceiveCallBack) {
		super(onReceiveCallBack);
		this.port = port;
	}

	@Override
	protected boolean isServer() {
		return true;
	}

	@Override
	protected String getIP() {
		return null;
	}

	@Override
	protected int getPort() {
		return port;
	}

}
