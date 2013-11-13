package has.client.connection;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Request {
	
	private String command;
	
	public String getCommand() {
		return command;
	}

	public Request(String command)
	{
		this.command = command;
	}
	
	
}
