package com.example;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.jetty.JettyWebSocketClient;


public class SocketHandler implements WebSocketHandler {
	
	
	private UpdateHandler updateHandler;
	
	private String updateURI;

	
	public SocketHandler(String updateURI) {
	 super();
	 updateHandler = new UpdateHandler(updateURI);
	 this.updateURI = updateURI;
	
	}

	@Override
	public void afterConnectionClosed(WebSocketSession arg0, CloseStatus arg1) throws Exception {
		
		System.out.println("Connection Closed.");
		System.out.println(arg1.getCode()+ arg1.getReason());
		System.out.println(arg0.getUri());
		JettyWebSocketClient client = new JettyWebSocketClient();
		WebSocketConnectionManager cManager = new WebSocketConnectionManager(client, this, arg0.getUri().toString());
		
		try{
			cManager.start();
			
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("controller closed.");
				
			}
	
		
		
		
	} 

	@Override
	@Autowired
	public void afterConnectionEstablished(WebSocketSession arg0) throws Exception {
	
		System.out.println("Connection Established.");
		
		try {
			Thread uThread = new Thread(updateHandler);
			uThread.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

	@Override
	public void handleMessage(WebSocketSession arg0, WebSocketMessage<?> arg1) throws Exception {

		System.out.println("Received Message:" + arg1.getPayload().toString());
		
		try {
			Thread uThread = new Thread(updateHandler);
			uThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	
	}

	@Override
	public void handleTransportError(WebSocketSession arg0, Throwable arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

}
