package com.blockchain.no;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.generators.OpenBSDBCrypt;
import org.bouncycastle.crypto.generators.SCrypt;
import org.springframework.security.crypto.keygen.KeyGenerators;



public class HashFactory {
	public static String Password = "p@$$w0rd~3";
	public static String Salt = "7b07a2977a473e84fc30d463a2333bcfea6cb3400b16bec4e17fe981c925ba4f";

	public static void main(String[] args) {

	}
/*
	public static byte[] generate(byte[] P, byte[] S, int N, int r, int p, int dkLen) {
		if (P == null) {
			throw new IllegalArgumentException("Passphrase P must be provided.");
		}
		if (S == null) {
			throw new IllegalArgumentException("Salt S must be provided.");
		}
		if (N <= 1 || !isPowerOf2(N)) {
			throw new IllegalArgumentException("Cost parameter N must be > 1 and a power of 2");
		}
		// Only value of r that cost (as an int) could be exceeded for is 1
		if (r == 1 && N >= 65536) {
			throw new IllegalArgumentException("Cost parameter N must be > 1 and < 65536.");
		}
		if (r < 1) {
			throw new IllegalArgumentException("Block size r must be >= 1.");
		}
		int maxParallel = Integer.MAX_VALUE / (128 * r * 8);
		if (p < 1 || p > maxParallel) {
			throw new IllegalArgumentException("Parallelisation parameter p must be >= 1 and <= " + maxParallel
					+ " (based on block size r of " + r + ")");
		}
		if (dkLen < 1) {
			throw new IllegalArgumentException("Generated key length dkLen must be >= 1.");
		}
		//return MFcrypt(P, S, N, r, p, dkLen);

	}
	
	  // note: we know X is non-zero
    private static boolean isPowerOf2(int x)
    {
        return ((x & (x - 1)) == 0);
    }
}
*/
}