package es.oscar.tests;

import java.util.Arrays;

import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HexTest {
	private static final Logger LOG = LoggerFactory.getLogger(HexTest.class);

	@Test
	public void encodeTest(){
		String plain = "hello world";
		LOG.info("Plain text: ".concat(new String(plain.getBytes())));
		LOG.info("Plain bytes: ".concat(Arrays.toString(plain.getBytes())));
		LOG.info("Hexadecimal string: ".concat(Hex.toHexString(plain.getBytes()).toUpperCase()));
	}

	@Test
	public void decodeTest(){
		String coded = "68656C6C6F20776F726C64";
		LOG.info("Hexadecimal string: ".concat(coded));
		LOG.info("Plain bytes: ".concat(Arrays.toString(new String(Hex.decode(coded)).getBytes())));
		LOG.info("Plain text: ".concat(new String(Hex.decode(coded))));
	}
}
