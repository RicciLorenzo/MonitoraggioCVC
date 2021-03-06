package model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encode {

	public static String cryptingString(String messageToEncrypt){
		
		MessageDigest sha256 = null;
		
		try {
			sha256 = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			}
		
		sha256.update(StandardCharsets.UTF_8.encode(messageToEncrypt));
		
		return (String.format("%032x", new BigInteger(1, sha256.digest())));
		
	}

}
