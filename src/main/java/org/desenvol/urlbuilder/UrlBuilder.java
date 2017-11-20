package org.desenvol.urlbuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Builds a URL.
 * 
 * @author Agustí Sánchez
 *
 */
public class UrlBuilder {

	private boolean isProtocolRelative = false;

	private String schema;

	private String host;

	private Integer port;

	private String base;

	private String user;

	private String password;

	private String fragment;

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

	/**
	 * Will prepend <code>//</code> without schema
	 * 
	 * @return
	 */
	public UrlBuilder protocolRelative() {
		this.isProtocolRelative = true;
		this.schema = null;
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

	public UrlBuilder port(Integer port) {
		this.port = port;
		return this;
	}

	public UrlBuilder user(String user) {
		this.user = user;
		return this;
	}

	public UrlBuilder pasword(String password) {
		this.password = password;
		return this;
	}

	public UrlBuilder path(String path) {
		paths.add(path);
		return this;
	}

	public UrlBuilder fragment(String fragment) {
		this.fragment = fragment;
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

	/**
	 * Adds a query parameter without a value.
	 * 
	 * @param port
	 * @return
	 */
	public UrlBuilder param(String name) {
		return param(name, null);
	}

	public String getSchema() {
		return schema;
	}

	public boolean isProtocolRelative() {
		return isProtocolRelative;
	}

	public String getHost() {
		return host;
	}

	/**
	 * Returns the specified port
	 * 
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * Returns the specified base
	 * 
	 * @return the base
	 */
	public String getBase() {
		return base;
	}

	/**
	 * Returns the immutable list of paths
	 * 
	 * @return paths as an immutable list
	 */
	public List<String> getPaths() {
		return Collections.unmodifiableList(paths);
	}

	/**
	 * Returns the immutable list of query parameters
	 * 
	 * @return query parameters as an immutable list
	 */
	public List<NameValuePair> getQueryParams() {
		return Collections.unmodifiableList(queryParams);
	}

	public String getFragment() {
		return fragment;
	}

	public String build() {

		StringBuilder builder = new StringBuilder();
		if (base != null) {
			builder.append(base);
		} else {
			if (schema != null) {
				builder.append(schema);
				builder.append(':');
			}

			if (schema != null || isProtocolRelative) {
				builder.append("//");
			}

			if (user != null || password != null) {
				if (user != null) {
					builder.append(user);
				}
				if (password != null) {
					builder.append(':');
					builder.append(password);
				}
				builder.append('@');
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
			appendParam(builder, nameValuePair);
			while (iterator.hasNext()) {
				builder.append('&');
				nameValuePair = iterator.next();
				appendParam(builder, nameValuePair);
			}
		}

		if (fragment != null) {
			builder.append('#');
			builder.append(fragment);
		}

		return builder.toString();

	}

	private void appendParam(StringBuilder builder, NameValuePair nameValuePair) {
		builder.append(nameValuePair.getName());
		if (nameValuePair.getValue() != null) {
			builder.append('=');
			try {
				builder.append(URLEncoder.encode(nameValuePair.getValue(), "UTF8"));
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
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
