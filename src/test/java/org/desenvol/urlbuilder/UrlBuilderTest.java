package org.desenvol.urlbuilder;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit tests.
 * 
 * @author Agustí Sánchez
 *
 */
@RunWith(JUnit4.class)
public class UrlBuilderTest {

	@Test
	public void testReturnString() {
		String url = new UrlBuilder("http://foo.net").path("test").param("simpleParam", "1/2")
				.param("multipleValuesParam", "first", "second").param("noValueParam").fragment("fragment").build();
		Assert.assertEquals(
				"http://foo.net/test?simpleParam=1%2F2&multipleValuesParam=first&multipleValuesParam=second&noValueParam#fragment",
				url);
	}

	@Test
	public void testReturnURI() throws URISyntaxException {
		URI uri = new UrlBuilder("http://foo.net").path("test").param("simpleParam", "1/2")
				.param("multipleValuesParam", "first", "second").param("noValueParam").fragment("fragment")
				.buildAsUri();
		Assert.assertEquals(
				"http://foo.net/test?simpleParam=1%2F2&multipleValuesParam=first&multipleValuesParam=second&noValueParam#fragment",
				uri.toString());

	}

	@Test
	public void anotherTest() {
		String url = new UrlBuilder().path("test").param("someParam", "1/2").param("novalue")
				.param("anotherParam", "other").fragment("fragment").build();
		Assert.assertEquals("/test?someParam=1%2F2&novalue&anotherParam=other#fragment", url);
	}

}
