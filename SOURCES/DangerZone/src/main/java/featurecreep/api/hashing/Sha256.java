package featurecreep.api.hashing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256 implements Hash {

	public static String getHashFromFileAsString(File file) throws NoSuchAlgorithmException, IOException {
		return bytesToHex(calculateFileHash(file));
	}

	public static byte[] calculateFileHash(File file) throws IOException, NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		try (FileInputStream fis = new FileInputStream(file)) {
			byte[] buffer = new byte[1024];
			int read;
			while ((read = fis.read(buffer)) != -1) {
				md.update(buffer, 0, read);
			}
		}
		return md.digest();
	}

	public static String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder();
		for (byte b : hash) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

}
