# UrlBuilder

A simple Java API to build URL strings with a fluid syntax and zero dependencies.

## Features

* fluid syntax
* query parameters automatically encoded with `URLEncode` and `UTF8`
* recognizes the following elements of URL spec: schema, authorization, host, port, path, query parameter, fragment
* supports parameter without value
* supports starting with a base URI
* supports returning an schema relative URL

## Examples

### Simple usage

```java
   String url = new UrlBuilder("http://foo.net")
                        .path("test")
                        .param("simpleParam", "1/2")
				        .param("multipleValuesParam", "first", "second")
				        .param("noValueParam")
				        .fragment("fragment")
				        .build();
```

returns:

```
   http://foo.net/test?simpleParam=1%2F2&multipleValuesParam=first&multipleValuesParam=second&noValueParam#fragment
```

 