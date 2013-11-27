package has.server.controller;

public interface DeviceInterface {

	public boolean turnLed(int ledId, boolean on);
	public double readTemperature();
}
