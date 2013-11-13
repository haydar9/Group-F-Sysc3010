package has.client.connection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import has.client.model.Model;

@XmlRootElement
public class Response {
	
	
	private Model currentModelFromServer;

	public Response(Model currentModelFromServer)
	{
		this.currentModelFromServer = currentModelFromServer;
	}
	
	public Model getCurrentModelFromServer() {
		return currentModelFromServer;
	}

	@XmlElement
	public void setCurrentModelFromServer(Model currentModelFromServer) {
		this.currentModelFromServer = currentModelFromServer;
	}
	
	
}
