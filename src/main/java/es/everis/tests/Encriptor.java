package es.everis.tests;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.encodings.OAEPEncoding;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.util.encoders.Hex;

public class Encriptor {
	public static String encriptWithPublicKey(String inputData, String key) throws CertificateException, IOException, InvalidCipherTextException
	{
        String encryptedData = null;

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        
        X509Certificate cert = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(Hex.decode(key)));
        

        RSAKeyParameters param = (RSAKeyParameters) PublicKeyFactory.createKey(cert.getPublicKey().getEncoded());
        
        AsymmetricBlockCipher cipher = new OAEPEncoding(new RSAEngine(), new SHA256Digest());
        cipher.init(true, param);
        
        byte[] messageBytes = inputData.getBytes();
        byte[] cipheredBytes = cipher.processBlock(messageBytes, 0, messageBytes.length);

        encryptedData = Hex.toHexString(cipheredBytes);

        return encryptedData;
    }
}
