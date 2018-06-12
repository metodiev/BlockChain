package com.blockchain.no;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.xml.bind.DatatypeConverter;


import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.jcajce.provider.digest.SHA512;
import org.bouncycastle.jcajce.provider.digest.Whirlpool;
import org.bouncycastle.jce.provider.BouncyCastleProvider;




public class CalculateHashes {
	

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		
		//SHA-384
		
		Security.addProvider(new BouncyCastleProvider());
		  String input = "blockchain";
	

		MessageDigest messageDigest = MessageDigest.getInstance("SHA-384");
		byte[] hashedString = messageDigest.digest(input.getBytes());

		 String hashValueSHA384 = DatatypeConverter.printHexBinary(hashedString).toLowerCase();
		 System.out.println(hashValueSHA384);
	
	  
		//SHA - 512
	   
	    SHA512.Digest digestSHA512 = new SHA512.Digest();
	    byte[] byteDigestSha512 = digestSHA512.digest(input.getBytes());
	    String hashValueSHA512 = DatatypeConverter.printHexBinary(byteDigestSha512).toLowerCase();
	    
	    System.out.println(hashValueSHA512);
		
		
		//SHA3 - 512
	
	    SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
	    byte[] digest = digestSHA3.digest(input.getBytes());
	    String hashValue = DatatypeConverter.printHexBinary(digest).toLowerCase();
	    
	    System.out.println(hashValue);
	    
	    
	    //Keccak - 512
	  
	    Keccak.Digest512 digestKeccak = new Keccak.Digest512();
	    byte[] digest1 = digestKeccak.digest(input.getBytes());
	    String hashValueKeccak = DatatypeConverter.printHexBinary(digest1).toLowerCase();
	    
	    System.out.println(hashValueKeccak);
	    
	    //Whirlpool-512

	    Whirlpool.Digest digestWhirpool = new Whirlpool.Digest();
	    byte[] digestByteWhirpool = digestWhirpool.digest(input.getBytes());
	    String hashValueWhirpool = DatatypeConverter.printHexBinary(digestByteWhirpool).toLowerCase();
	    
	    System.out.println(hashValueWhirpool.toUpperCase());
	    
	}
}
