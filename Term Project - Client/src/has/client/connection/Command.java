package has.client.connection;

public class Command {
	public final static Command getSystemUpdate = new Command("GSU");
	public final static Command updateLed1Status = new Command("ULED1");
	public final static Command updateLed2Status = new Command("ULED2");
	public final static Command updateLed3Status = new Command("ULED3");
	public final static Command updateFanStatus = new Command("UFAN");
	
	private String command;
	private boolean status;
	
	private Command(String command)
	{
		this.command = command;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Override
	public String toString()
	{
		return command;
	}
	
	
}
