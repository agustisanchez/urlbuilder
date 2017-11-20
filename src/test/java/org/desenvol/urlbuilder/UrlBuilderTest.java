package org.desenvol.urlbuilder;

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
	public void someBasicTest() {
		String url = new UrlBuilder("http://expressjava.net").path("test").param("someParam", "1/2").param("novalue")
				.param("anotherParam", "other").fragment("fragment").build();
		Assert.assertEquals("http://expressjava.net/test?someParam=1%2F2&novalue&anotherParam=other#fragment", url);
	}

	@Test
	public void anotherTest() {
		String url = new UrlBuilder().path("test").param("someParam", "1/2").param("novalue")
				.param("anotherParam", "other").fragment("fragment").build();
		Assert.assertEquals("/test?someParam=1%2F2&novalue&anotherParam=other#fragment", url);
	}

}
