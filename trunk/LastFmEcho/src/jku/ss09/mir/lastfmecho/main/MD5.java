package jku.ss09.mir.lastfmecho.main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {


	public MD5()
	{

	}




	public static String calc(String value)
	{
		// MD5 Java

		/* Berechnung */

		StringBuffer hexString = new StringBuffer();
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");

			md5.reset();
			md5.update(value.getBytes());
			byte[] result = md5.digest();

			for (int i=0; i<result.length; i++) {
				hexString.append(Integer.toHexString(0xFF & result[i]));
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hexString.toString();
		//System.out.println("MD5: " + hexString.toString());


	}


}
