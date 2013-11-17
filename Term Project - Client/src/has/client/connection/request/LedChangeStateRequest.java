package has.client.connection.request;

import java.util.Map;
import java.util.TreeMap;

public class LedChangeStateRequest implements Request {

	private boolean state;
	
	
	public LedChangeStateRequest(boolean state)
	{
		this.state = state;
	}
	
	@Override
	public Map<String, Object> getAttributesMap() {
		Map<String, Object> attrmap = new TreeMap<String, Object>();
		attrmap.put("Led", state);
		return attrmap;
	}

}
