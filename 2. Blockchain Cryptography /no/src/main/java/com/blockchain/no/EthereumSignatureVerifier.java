package com.blockchain.no;

import java.math.BigInteger;

import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;
import org.web3j.utils.Numeric;

public class EthereumSignatureVerifier {

	public static void main(String[] args) throws Exception {

		String AddressConst = "0xa44f70834a711F0DF388ab016465f2eEb255dEd0";
		AddressConst = new String(AddressConst.toLowerCase());
		// String Signature =
		// "0xacd0acd4eabd1bec05393b33b4018fa38b69eba8f16ac3d60eec9f4d2abc127e3c92939e680b91b094242af80fce6f217a34197a69d35edaf616cb0c3da4265b01";
		String Signature = "0x5550acd4eabd1bec05393b33b4018fa38b69eba8f16ac3d60eec9f4d2abc127e3c92939e680b91b094242af80fce6f217a34197a69d35edaf616cb0c3da4265b01";
		String Message = "exercise-cryptography";

		String input = "{\"signature\":\"0xacd0acd4eabd1bec05393b33b4018fa38b69eba8f16ac3d60eec9f4d2abc127e3c92939e680b91b094242af80fce6f217a34197a69d35edaf616cb0c3da4265b01\",\"v\":\"0x1\",\"r\":\"0xacd0acd4eabd1bec05393b33b4018fa38b69eba8f16ac3d60eec9f4d2abc127e\",\"s\":\"0x3c92939e680b91b094242af80fce6f217a34197a69d35edaf616cb0c3da4265b\"}";

		String[] separateInput = input.split(",");

		String r = Signature.substring(0, 66);
		String s = "0x" + Signature.substring(66, 130);
		// String v = "0x"+Signature.substring(130, 132);

		String msg = "exercise-cryptography";
		byte[] msgHash = Hash.sha3(msg.getBytes());

		Sign.SignatureData signature = new SignatureData((byte) 28, Numeric.hexStringToByteArray(r),
				Numeric.hexStringToByteArray(s));

		BigInteger pubKeyRecovered = Sign.signedMessageToKey(msg.getBytes(), signature);
		String address = Keys.getAddress(pubKeyRecovered);
	

		address = new String("0x" + address);
		System.out.println("0x" + address);

		boolean areEqualsAdresses = address.equals(AddressConst.toLowerCase());
		if (areEqualsAdresses) {
			System.out.println("valid");
		} else {
			System.out.println("invalid");
		}

	}
}
