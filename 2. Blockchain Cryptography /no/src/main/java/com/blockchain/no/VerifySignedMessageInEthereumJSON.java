package com.blockchain.no;

import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

public class VerifySignedMessageInEthereumJSON {

    public static void main(String[] args) throws Exception {
        {
            String input = "{\n" +
                    "  \"address\": \"0xa44f70834a711f0df388ab016465f2eeb255ded0\",\n" +
                    "  \"msg\": \"Message for signing\",\n" +
                    "  \"sig\": \"0x6f0156091cbe912f2d5d1215cc3cd81c0963c8839b93af60e0921b61a19c54300c71006dd93f3508c432daca21db0095f4b16542782b7986f48a5d0ae3c583d401\",\n" +
                    "  \"version\": \"1\"\n" +
                    "}";

            input = input.replaceAll("[\\.$|\"|}||{]||", "");
            String[] strInput = input.split("\",");


            System.out.println(strInput[0].split(",")[2].split(":")[1].replaceFirst(" ", ""));

            String address = strInput[0].split(",")[0].split(":")[1].replace(" ", "");//"0xa44f70834a711f0df388ab016465f2eeb255ded0";

            String msg = strInput[0].split(",")[1].split(":")[1].replaceFirst(" ", "");//"Message for signing";

            String sig = strInput[0].split(",")[2].split(":")[1].replaceFirst(" ", "");//"0x6f0156091cbe912f2d5d1215cc3cd81c0963c8839b93af60e0921b61a19c54300c71006dd93f3508c432daca21db0095f4b16542782b7986f48a5d0ae3c583d401";

            String v = "1";
            String r = sig.substring(0, 66);
            String s = "0x" + sig.substring(66, 130);
            // String v = "0x"+Signature.substring(130, 132);


            Sign.SignatureData signature = new Sign.SignatureData((byte) 28,
                    Numeric.hexStringToByteArray(r),
                    Numeric.hexStringToByteArray(s));


            BigInteger pubKeyRecovered = Sign.signedMessageToKey(msg.getBytes(), signature);

            //System.out.println(pubKeyRecovered.toString(16));
            String addressFromPubKeyRecovered = Keys.getAddress(pubKeyRecovered);
            addressFromPubKeyRecovered = new String("0x" + addressFromPubKeyRecovered);
            System.out.println(addressFromPubKeyRecovered);

            boolean areEqualsAdresses = address.equals(addressFromPubKeyRecovered.toLowerCase());
            if (areEqualsAdresses) {
                System.out.println("valid");
            } else {
                System.out.println("invalid");
            }
        }
    }
}
