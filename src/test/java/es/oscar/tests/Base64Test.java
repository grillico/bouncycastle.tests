package es.oscar.tests;

import java.util.Arrays;

import org.bouncycastle.util.encoders.Base64;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Base64Test {
	private static final Logger LOG = LoggerFactory.getLogger(Base64Test.class);
	private static final String PLAIN = "hello world";
	private static final String CODED = "aGVsbG8gd29ybGQ=";

	@Test
	public void encodeTest(){
		LOG.info("Plain text: ".concat(PLAIN));
		LOG.info("Plain bytes: ".concat(Arrays.toString(PLAIN.getBytes())));
		String base64 = Base64.toBase64String(PLAIN.getBytes());
		LOG.info("Base64 string: ".concat(base64));
		Assert.assertEquals("Base 64 encoded not as expected", base64, CODED);
	}

	@Test
	public void decodeTest(){
		LOG.info("Base64 string: ".concat(CODED));
		String plain = new String(Base64.decode(CODED));
		LOG.info("Plain bytes: ".concat(Arrays.toString(plain.getBytes())));
		LOG.info("Plain text: ".concat(plain));
		Assert.assertEquals("Plain text not as expected", plain, PLAIN);
	}
}
