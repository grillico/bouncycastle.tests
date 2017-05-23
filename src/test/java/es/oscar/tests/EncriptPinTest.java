package es.oscar.tests;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.encodings.OAEPEncoding;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncriptPinTest {
	private static final String TPIN = "412456000000";
	private static final String PUBLIC_KEY = "308203AE30820196A0030201020210008805C24307181F781105160E0B3056300D06092A864886F70D01010B05003011310F300D06035504031306416479746F6E301E170D3137303532313132313134385A170D3237303532303132313134385A3015311330110603550403130A436F6D6D6F6E2048534D30820122300D06092A864886F70D01010105000382010F003082010A02820101008EEFAE164AFB9A8B4E0F0F290DF3AC5869C31BA890635AFBC34F10C2A72153C6C0E8EFD6FDC67C4274484C32C685448D3FFAA2DC304594769EB7CEED73489E6154886E94F30D20B6B7D6D70CF8C4DC5B33645B9B674DFE148602DCC289352FD6ECDE2B330E83BACAA58C21E0FC421E4A292A56CC99B23F8AA9E42276960B1F9CB115D4581B5A8DB6BED1F920598D8D9AF24709029E68EB9B8DCA3569162E4DD41A87DCBA1527BF74D22768D34602A2504229B1034EAB6A416C51BF09CE6E797470725B1D951BC5D124E98607377EF7E74422FE87E2F0E9209444C01DC45B69668C6149D2C9964E521A8FF6E08BDC96C4B86EEB6669647A8A5F768DD18D6F8FC70203010001300D06092A864886F70D01010B050003820201000B7B53C50DBDA9304F6C35DEE78C316D255FF6EA1F7C2C26C7B35FFE03372B27F664EC37D6C414EF7388A2D1AD194A91B0593A5E5D74BEAFFEF4E0AE897990E34C1BF3E88B5A5A5DCB78751EC2CE65046FDA59C0A16CACBB0CAB7BBCC0800524B446ED39ED74CF1246941BC1C4964E3273F0ED28A2BF1C0F81C6703FC5CFEEE5601D51CF0CE72036157FD923A598B866645A58757F246CAE7D128B441F8F7B9B8E3A518A05B320D5F64C686E6B34C74D80FE36B72F126FFD68337F7F355BFEC4ADFAE7C16DEE5662FB687DC29E0C20F6B2C8261E07EF606245A25D188AD7C3579E1F32DFD34BEA5AD493E1E871CC0E35B4C4BDAFB6C096CAD3DAA75CBD1C040D16564736B14C4256E0AFE3F2E2F21062147B83DF770EC3439F2BDD5F9AF115B90BB014B401CB4D7C372B83917E31817712E0DC2367A3CEF746E15CFE2D5E4950F7F8B4F519F4643E6411BAE1F08968BAB20AD61CED3E44D289FB4683DEF858F6C3ECDA547035D6C9292056389DBA85BFEB1EF7B0FE252D7F6326E1102D1B74BE7C7D8B246DEC3F73BD203588C723EA0E9025F49FF0CAD44E67D41DAD7F9CC03BF19E787F70CC0025C37D031E2EC57CF2408C2B25DF270D83C83569EF1A3712ACC735EA6794DEE76FCD3DAD644627C9B4E65257B48F24C1C4D0C7A58DA7A9F6F7B28F3D9052D1E42270A1295820D6B323E5B458F2DF24B444A47D7BFF79BD5322";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EncriptPinTest.class);

	@Test
	public void encriptTest() throws InvalidCipherTextException, IOException, CertificateException, NoSuchProviderException{
		String encriptedTPin = encrypt(PUBLIC_KEY, TPIN);
		LOGGER.info(encriptedTPin);
	}
	
	private String encrypt (String key, String inputData) throws IOException, InvalidCipherTextException, CertificateException, NoSuchProviderException{

        String encryptedData = null;

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        
        X509Certificate cert = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(Hex.decode(key)));
        

        AsymmetricKeyParameter param = PublicKeyFactory.createKey(cert.getPublicKey().getEncoded());
        
        AsymmetricBlockCipher cipher = new OAEPEncoding(new RSAEngine(), new SHA256Digest());
        cipher.init(true, param);
        
        byte[] messageBytes = inputData.getBytes();
        byte[] cipheredBytes = cipher.processBlock(messageBytes, 0, messageBytes.length);

        encryptedData = Hex.toHexString(cipheredBytes);

        return encryptedData;
    }
}