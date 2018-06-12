package com.blockchain.no;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.engines.Salsa20Engine;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.generators.SCrypt;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

public class DeriveKeyByPassScrypt {

	/**
	 * @param args
	 * @throws GeneralSecurityException
	 */
	public static void main(String[] args) throws GeneralSecurityException {
		String password = "p@$$w0rd~3";
		String sol = "7b07a2977a473e84fc30d463a2333bcfea6cb3400b16bec4e17fe981c925ba4f";
		int N = 16384;
		int r = 16;
		int p = 1;
		int dkLen = 32;

		byte[] a = SCrypt.generate(password.getBytes(), sol.getBytes(), N, r, p, dkLen);
		String hex = String.format("%064x", new BigInteger(1, a));
		System.out.println(hex);

	}

}
