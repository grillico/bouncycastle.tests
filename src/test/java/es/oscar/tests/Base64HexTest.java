package es.oscar.tests;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Base64HexTest {
	private static final Logger LOG = LoggerFactory.getLogger(Base64HexTest.class);

	@Test
	public void encodeTest(){
		String hex = "68656C6C6F20776F726C64";
		LOG.info("Hexadecimal string: ".concat(hex));
		LOG.info("Base64 string: ".concat(Base64.toBase64String(Hex.decode(hex.getBytes()))));
	}

	@Test
	public void decodeTest(){
		String base64 = "aGVsbG8gd29ybGQ=";
		LOG.info("Base64 string: ".concat(base64));
		LOG.info("Hexadecimal string: ".concat(Hex.toHexString(Base64.decode(base64.getBytes())).toUpperCase()));
	}
}
