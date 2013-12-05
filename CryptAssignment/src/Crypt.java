import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.NoSuchPaddingException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
/** 
 * This Crypt class constructs javax.crypto.Cipher objects with password-based data encryption, PBEWithSHA1AndDESEDE.  
 * The salt is currently constant in this class, but it could be modified to take a salt as part of a constructor.  
 * This class performs no exception handling, all potential exceptions are thrown.
 * 
 * <h6>This code is released for educational purposes only and does not come with any 
 * warranty for the merchantability or fitness for any particular purpose.</h6>
 * @author Geoffery Miller
 * @version 0.1
 */
public class Crypt {
	private Cipher encrypt, decrypt;
	/** 
	 * Crypt objects require a password secret.  
	 * To create a Crypt object, you will need to provide the password that it will use to 
	 * encrypt and decrypt data with. The salt used by Crypt is constant.
	 * 
	 * @param password  The password to construct the encryption and decryption ciphers
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws UnsupportedEncodingException This will only occur if UTF-8 is not supported on your system
	 * @throws InvalidKeyException
	 * @throws InvalidParameterSpecException
	 * @throws InvalidAlgorithmParameterException
	 */
	public Crypt(String password) throws	InvalidKeySpecException, 
										NoSuchAlgorithmException, 
										NoSuchPaddingException, 
										UnsupportedEncodingException, 
										InvalidKeyException,
										InvalidParameterSpecException,
										InvalidAlgorithmParameterException	{
		String algorithm = "PBEWithSHA1AndDESede";
		int iterations = 1000;
		char[] pass_c = password.toCharArray();
		byte[] salt_b = getSalt();
		SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
		KeySpec spec = new PBEKeySpec(pass_c, salt_b, iterations, 128);
		SecretKey secret = factory.generateSecret(spec);
		
		AlgorithmParameterSpec params = new PBEParameterSpec(salt_b,iterations);

		encrypt = Cipher.getInstance(secret.getAlgorithm());
		encrypt.init(Cipher.ENCRYPT_MODE, secret, params);

		decrypt = Cipher.getInstance(secret.getAlgorithm());
		decrypt.init(Cipher.DECRYPT_MODE, secret, params);
		
		
	}

	/**
	 * This method will take an array of unencrypted bytes and encrypt them
	 * using the password provided to create this Crypt object.  The returned 
	 * byte array will be encrypted.
	 * @param data The data to encrypt using this Crypt object
	 * @return The encrypted data 
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public byte[] encrypt(byte[] data) throws	IllegalBlockSizeException, 
												BadPaddingException {
		byte[] enc = encrypt.doFinal(data);
		return enc;
	}

	/** 
	 * This method takes a byte array of encrypted bytes and returns a 
	 * byte array of decrypted bytes, using the password provided to create
	 * this Crypt object.
	 * @param data The data to decrypt using this Cypher object
	 * @return The decrypted data 
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public byte[] decrypt(byte[] data) throws	IllegalBlockSizeException,
												BadPaddingException {
		byte[] dec = decrypt.doFinal(data);
		return dec;
	}

	private byte[] getSalt() throws UnsupportedEncodingException {
		byte[] salt = {	(byte)0x0,(byte)0x0,(byte)0x0,(byte)0x0,
						(byte)0x0,(byte)0x0,(byte)0x0,(byte)0x0	};
		return salt;
	}
	
}
