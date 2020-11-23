package com.archivsoft.nobelWill.model;

import com.archivsoft.nobelWill.util.PemFile;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.*;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

public class Wlt {
	public PrivateKey privateKey;
	public PublicKey publicKey;

	public void generateKeyPair() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA",	"BC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");

			// Initialize the key generator and generate a KeyPair
			keyGen.initialize(ecSpec, random); // 256 bytes provides an acceptable security level
			
			KeyPair keyPair = keyGen.generateKeyPair();
			
			// Set the public and private keys from the keyPair
			privateKey = keyPair.getPrivate();
			publicKey = keyPair.getPublic();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String createPemPrivate() throws IOException {
		StringWriter writer = new StringWriter();
		PemWriter pemWriter = new PemWriter(writer);
		pemWriter.writeObject(new PemObject("Private KEY", privateKey.getEncoded()));
		pemWriter.flush();
		pemWriter.close();
		return writer.toString();
	}

	public String createPemPublic() throws IOException {
		StringWriter writer = new StringWriter();
		PemWriter pemWriter = new PemWriter(writer);
		pemWriter.writeObject(new PemObject("PUBLIC KEY", publicKey.getEncoded()));
		pemWriter.flush();
		pemWriter.close();
		return writer.toString();
	}

//	private KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException {
//		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
//		generator.initialize(KEY_SIZE);
//
//		KeyPair keyPair = generator.generateKeyPair();
//		LOGGER.info("RSA key pair generated.");
//		return keyPair;
//	}

	public void writePemFile(Key key, String description, String filename)
			throws FileNotFoundException, IOException {
		PemFile pemFile = new PemFile(key, description);
		pemFile.write(filename);

		LOGGER.info(String.format("%s successfully writen in file %s.", description, filename));
	}
}
