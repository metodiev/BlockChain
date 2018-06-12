package com.blockchain.no;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPoint;

import org.bitcoinj.core.Base58;


import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.Sign;


public class PrivateKeyToBitcoinAddress {

/*	public static String compressPubKey(BigInteger pubKey) {
		String pubKeyYPrefix = pubKey.testBit(0) ? "03" : "02";
		String pubKeyHex = pubKey.toString(16);
		String pubKeyX = pubKeyHex.substring(0, 64);
		return pubKeyYPrefix + pubKeyX;
	}


    static private String adjustTo64(String s) {
        switch(s.length()) {
            case 62: return "00" + s;
            case 63: return "0" + s;
            case 64: return s;
            default:
                throw new IllegalArgumentException("not a valid key: " + s);
        }
    }
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
		String privateKey = "5HueCGU8rMjxEXxiPuD5BDku4MkFqeZyd4dZ1jvhTVqvbTLvyTJ";



        // decode to base58 privateKey
		Base58 base58 = new Base58();
		byte[] decodePrivateKey = base58.decode(privateKey);
		String strDecodePrivateKey = Hex.toHexString(decodePrivateKey);
		System.out.println(strDecodePrivateKey);

		// Drop the last 4 checksum bytes from the byte string

		int strSize = strDecodePrivateKey.length();
		String privateKyeDropBytes = strDecodePrivateKey.substring(0, strSize - 8);
		System.out.println(privateKyeDropBytes);

		// 4 - Drop the first byte (it should be 0x80).
		// If the private key corresponded to a compressed public key, also drop the
		// last byte
		// it should be 0x01). If it corresponded to a compressed public key, the WIF
		// string will
		// have started with K or L instead of 5 (or c instead of 9 on testnet). This is
		// the private key.
		int strSizeDrobFirst = privateKyeDropBytes.length();
		String privateKyeDropBytesFirst = privateKyeDropBytes.substring(2, strSizeDrobFirst);

		System.out.println(privateKyeDropBytesFirst);
		BigInteger privKeyBigInteger = new BigInteger(privateKyeDropBytesFirst.getBytes());
		BigInteger pubKey = Sign.publicKeyFromPrivate(privKeyBigInteger);

		*//*
		 * BitcoinPubKeyAddress addressTestNet =
		 * publicKeyTestNet.GetAddress(Network.TestNet);
		 * Console.WriteLine(addressTestNet);
		 *//*

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

		System.out.println(pubKey.toString(16));
		String compressPubKey = compressPubKey(pubKey);
		System.out.println(compressPubKey);

		System.out.println(String.format("%032x", pubKey));
	//
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");

        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
        keyGen.initialize(ecSpec);
        PublicKey pub = (PublicKey) pubKey;

        ECPublicKey epub = (ECPublicKey)pub;
        ECPoint pt = epub.getW();
        String sx = adjustTo64(pt.getAffineX().toString(16)).toUpperCase();
        String sy = adjustTo64(pt.getAffineY().toString(16)).toUpperCase();
        String bcPub = "04" + sx + sy;
        System.out.println("bcPub: " + bcPub);
        //

        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] s1 = sha.digest(bcPub.getBytes("UTF-8"));
        System.out.println("  sha: " + Hex.toHexString(s1).toUpperCase());

        MessageDigest rmd = MessageDigest.getInstance("RipeMD160", "BC");
        byte[] r1 = rmd.digest(s1);
        byte[] r2 = new byte[r1.length + 1];
        r2[0] = 0;
        for (int i = 0 ; i < r1.length ; i++) r2[i+1] = r1[i];
        System.out.println("  rmd: " + Hex.toHexString(r2));

       // byte[] s2 = sha.digest(r2);
        //System.out.println("  sha: " + bytesToHex(s2).toUpperCase());
        byte[] s3 = sha.digest(r2);
        System.out.println("  sha: " + Hex.toHexString(s3));

        byte[] a1 = new byte[25];
        for (int i = 0 ; i < r2.length ; i++) a1[i] = r2[i];
        for (int i = 0 ; i < 5 ; i++) a1[20 + i] = s3[i];

        System.out.println("  adr: " + Base58.encode(a1));
		 
		//Base58CheckEncode(RIPEMD160.Digest.getInstance(hashValueSHA256));
		// byte[] t1 = Base58.decodeChecked(compressPubKey);
		// System.out.println(Hex.toHexString(t1));*/

	//}
}