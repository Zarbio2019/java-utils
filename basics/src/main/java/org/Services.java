package org;

public class Services {

}
/**
* Matching fields JSON with different name

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonClass {
	@JsonProperty(value = "nombre")
	private String name;
	
	@JsonProperty(value = "valor")
	private String value;
	
	public JsonClass() {
		super();
	}
	
    ...
}
*/