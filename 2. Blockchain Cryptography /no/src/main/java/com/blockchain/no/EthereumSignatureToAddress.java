package com.blockchain.no;

import java.math.BigInteger;
import java.security.SignatureException;

import javax.xml.bind.DatatypeConverter;

import org.bouncycastle.crypto.tls.ECCurveType;
import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.bouncycastle.jce.interfaces.ECKey;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;
import org.web3j.utils.Numeric;

import com.chrylis.codec.base58.Base58Codec;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;

public class EthereumSignatureToAddress {
	
    public static void main(String[] args) throws Exception {

    	
    	String input = "{\"signature\":\"0xacd0acd4eabd1bec05393b33b4018fa38b69eba8f16ac3d60eec9f4d2abc127e3c92939e680b91b094242af80fce6f217a34197a69d35edaf616cb0c3da4265b01\",\"v\":\"0x1\",\"r\":\"0xacd0acd4eabd1bec05393b33b4018fa38b69eba8f16ac3d60eec9f4d2abc127e\",\"s\":\"0x3c92939e680b91b094242af80fce6f217a34197a69d35edaf616cb0c3da4265b\"}";
    	
    	String [] separateInput = input.split(",");
    	String signature1 = separateInput[0].split(":")[1].replaceAll("\"", "");
    	String v = separateInput[1].split(":")[1].replaceAll("\"", "");
    	String r = separateInput[2].split(":")[1].replaceAll("\"", "");
    	String s = separateInput[3].split(":")[1].replaceAll("\"}", "").replaceAll("\"", "").
    			replaceAll("0x", "");
    	 String msg = "exercise-cryptography";
         byte[] msgHash = Hash.sha3(msg.getBytes());
 	  
         Sign.SignatureData signature = new SignatureData((byte) 28,
                    Numeric.hexStringToByteArray(r),
                    Numeric.hexStringToByteArray(s));
         
  	   
         BigInteger pubKeyRecovered = Sign.signedMessageToKey(msg.getBytes(), signature);
  	   
  	    
         //System.out.println(pubKeyRecovered.toString(16));
         String address = Keys.getAddress(pubKeyRecovered);
        // System.out.println(address);
         

	      // System.out.println("Address derived from pubKey: " + addr.toLowerCase());
	       System.out.println("0x" + address);
         
    //System.out.println( Hex.toHexString(signature.getS()) );
    //System.out.println( Hex.toHexString(signature.getS()) );
    
    	
    	
    }
}
