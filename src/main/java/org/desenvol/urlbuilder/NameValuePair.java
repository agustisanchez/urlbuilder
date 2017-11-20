package org.desenvol.urlbuilder;

/**
 * 
 * Holds a name / value pair to be used to model query parameters and HTTP
 * headers.
 * 
 * @author Agustí Sánchez
 *
 */
class NameValuePair {

	private String name;

	private String value;

	public NameValuePair(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

}
