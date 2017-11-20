package org.desenvol.urlbuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Builds a URL.
 * 
 * @author Agustí Sánchez
 *
 */
public class UrlBuilder {

	private String schema;

	private String host;

	private String port;

	private String base;

	private List<NameValuePair> queryParams = new ArrayList<>();

	private List<String> paths = new ArrayList<>();

	public UrlBuilder() {
	}

	public UrlBuilder(String base) {
		super();
		this.base = base;
	}

	public UrlBuilder scheme(String schema) {
		this.schema = schema;
		return this;
	}

	public UrlBuilder base(String base) {
		this.base = base;
		return this;
	}

	public UrlBuilder host(String host) {
		this.host = host;
		return this;
	}

	public UrlBuilder port(String port) {
		this.port = port;
		return this;
	}

	public UrlBuilder path(String path) {
		paths.add(path);
		return this;
	}

	/**
	 * Adds a query parameter.
	 * 
	 * @param port
	 * @return
	 */
	public UrlBuilder param(String name, String value) {
		queryParams.add(new NameValuePair(name, value));
		return this;
	}

	public String getSchema() {
		return schema;
	}

	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
	}

	public String getBase() {
		return base;
	}

	public List<String> getPaths() {
		return paths;
	}

	public List<NameValuePair> getQueryParams() {
		return queryParams;
	}

	public String build() {

		StringBuilder builder = new StringBuilder();
		if (base != null) {
			builder.append(base);
		} else {
			if (schema != null) {
				builder.append(schema);
				builder.append("://");
			}

			if (host != null) {
				builder.append(host);
			}

			if (port != null) {
				builder.append(':');
				builder.append(port);
			}
		}

		if (!paths.isEmpty()) {
			for (String path : paths) {
				builder.append('/');
				builder.append(path);
			}
		}

		if (!queryParams.isEmpty()) {
			builder.append('?');
			Iterator<NameValuePair> iterator = queryParams.iterator();
			NameValuePair nameValuePair = iterator.next();
			builder.append(nameValuePair.getName());
			builder.append('=');
			try {
				builder.append(URLEncoder.encode(nameValuePair.getValue(), "UTF8"));
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
			while (iterator.hasNext()) {
				builder.append('&');
				nameValuePair = iterator.next();
				builder.append(nameValuePair.getName());
				builder.append('=');
				try {
					builder.append(URLEncoder.encode(nameValuePair.getValue(), "UTF8"));
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}
			}
		}

		return builder.toString();

	}

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

}
