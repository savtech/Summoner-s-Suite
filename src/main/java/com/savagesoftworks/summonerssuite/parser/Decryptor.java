package com.savagesoftworks.summonerssuite.parser;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.zip.Inflater;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class Decryptor {
	
	private static final String PROTOCOL_KEY = "E2wN5Eo0t4gle92Z";
	private static final Key KEY = new SecretKeySpec(PROTOCOL_KEY.getBytes(StandardCharsets.UTF_8), "AES");
	private static final IvParameterSpec IV_SPEC = new IvParameterSpec(new byte[16]);

	
	private Decryptor() {}

	private static byte[] decrypt(byte[] data) {
		
		byte[] decryptedData = null;
		
		try {
		
			final Cipher cipher;
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, KEY, IV_SPEC);
			
			final byte[] decodedData = Base64.getDecoder().decode(data);
			decryptedData = cipher.doFinal(decodedData);

		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return decryptedData;
		
	}
	
	public static byte[] decryptRequest(byte[] data) {

		return decrypt(data);
		
	}
	
	public static byte[] decryptResponse(byte[] data) {
		
		final Inflater inflater = new Inflater();
		final byte[] buffer = new byte[1024];
		
		byte[] decryptedData = null;
		
		try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length)) {

			inflater.setInput(decrypt(data));
			int count;
			
			while (!inflater.finished()) {  
				
			    count = inflater.inflate(buffer);
			    outputStream.write(buffer, 0, count);  
			    
			}  
			
			decryptedData = outputStream.toByteArray();  
			inflater.end();
		   
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return decryptedData; 

	}

}