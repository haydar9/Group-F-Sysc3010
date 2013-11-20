package has.server.controller;

public interface DeviceInterface {

	public boolean turnLed(int ledId, boolean status);
	public double readTemperature();
}
