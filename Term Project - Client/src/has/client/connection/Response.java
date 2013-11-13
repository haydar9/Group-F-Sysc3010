package has.client.connection;

import has.client.model.ClientModel;


public class Response {
	
	
	private ClientModel currentModelFromServer;

	public Response(ClientModel currentModelFromServer)
	{
		this.currentModelFromServer = currentModelFromServer;
	}
	
	public ClientModel getCurrentModelFromServer() {
		return currentModelFromServer;
	}

	public void setCurrentModelFromServer(ClientModel currentModelFromServer) {
		this.currentModelFromServer = currentModelFromServer;
	}
	
	
}
