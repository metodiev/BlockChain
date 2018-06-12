package com.blockchain.no;

import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

public class VerifyEthereumMessages {

    public static String compressPubKey(BigInteger pubKey) {
        String pubKeyYPrefix = pubKey.testBit(0) ? "03" : "02";
        String pubKeyHex = pubKey.toString(16);
        String pubKeyX = pubKeyHex.substring(0, 64);
        return pubKeyYPrefix + pubKeyX;
    }

    public static void main(String[] args) throws Exception {
        //BigInteger privKey = Keys.createEcKeyPair().getPrivateKey();
        BigInteger privKey = new BigInteger("97ddae0f3a25b92268175400149d65d6887b9cefaf28ea2c078e05cdc15a3c0a", 16);
        BigInteger pubKey = Sign.publicKeyFromPrivate(privKey);
        ECKeyPair keyPair = new ECKeyPair(privKey, pubKey);

        String msg = "Message for signing";
        byte[] msgHash = Hash.sha3(msg.getBytes());
        Sign.SignatureData signature = Sign.signMessage(msgHash, keyPair, false);


        //   System.out.println();

        BigInteger pubKeyRecovered = Sign.signedMessageToKey(msg.getBytes(), signature);
        boolean validSig = pubKey.equals(pubKeyRecovered);

        String jSonDocument = String.format("{Signature:0x%s%s0%s, v:0x%s r: 0x%s s: 0x%s}", Hex.toHexString(signature.getR()), Hex.toHexString(signature.getS()), signature.getV() - 27, signature.getV() - 27, Hex.toHexString(signature.getR()), Hex.toHexString(signature.getS()));

        String address = Keys.getAddress(pubKeyRecovered);


        System.out.println(
                "{" + "\n" +
                        "\"address\":" + "\"" +"0x" + address + "\"" + "," + "\n"  +
                        "\"msg\":" + "\"" + msg + "\"" +"," + "\n" +
                        "\"sig\":" +  "\""  + Hex.toHexString(signature.getR()) + "\"" + "," + "\n" +
                        Hex.toHexString(signature.getS()) + signature.getV() +"\"" + "," + "\n" +
                        "\"version\":" + "\"" +(signature.getV() - 27) + "\"" + "\n" +
                "}"
        );
        /*
        {
        "address": "0xa44f70834a711f0df388ab016465f2eeb255ded0",
         "msg": "Message for signing",
         "sig": "0x6f0156091cbe912f2d5d1215cc3cd81c0963c8839b93af60e0921b61a19c54300c71006dd93f3508c432daca21db0095f4b16542782b7986f48a5d0ae3c583d401",
         "version": "1"
        }

         */
    }
}
