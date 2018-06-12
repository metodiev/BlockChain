package com.blockchain.COnsesnsusAkgorithms;

/**
 * Hello world!
 *
 */
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.Signer;

import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.signers.DSAKCalculator;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Sign;

import com.google.gson.Gson;

import static com.sun.xml.internal.messaging.saaj.packaging.mime.util.ASCIIUtility.getBytes;

class Transaction {
	private String addresForm;
	private String addressTo;
	private String senderPublicKey;
	private int value;
	private int fee;
	private String dateCreated;
	private String[] senderSignature;
	static final X9ECParameters curve = SECNamedCurves.getByName("secp256k1");
	private static final ECDomainParameters Domain = new ECDomainParameters(curve.getCurve(), curve.getG(),
			curve.getN(), curve.getH());

	public Transaction(String addresFrom, String addressTo, String senderPublicKey, int value, int fee,
			String dateCreated, String[] senderSignature) {
		this.addresForm = addresFrom;
		this.addressTo = addressTo;
		this.senderPublicKey = senderPublicKey;
		this.value = value;
		this.fee = fee;
		this.dateCreated = dateCreated;
		this.senderSignature = senderSignature;
	}

	public static String BytesToHex(byte[] bytes) {
		return Hex.toHexString(bytes);
	}

	private static String calcRipeMD160(String text) throws UnsupportedEncodingException {
		byte[] bytes = getBytes(text);
		RIPEMD160Digest digest = new RIPEMD160Digest();
		digest.update(bytes, 0, bytes.length);
		byte[] result = new byte[digest.getDigestSize()];
		digest.doFinal(result, 0);
		return BytesToHex(result);
	}

	private static byte[] calcSHA256(String text) throws UnsupportedEncodingException {
		byte[] bytes = getBytes(text);
		SHA256Digest digest = new SHA256Digest();
		digest.update(bytes, 0, bytes.length);
		byte[] result = new byte[digest.getDigestSize()];
		digest.doFinal(result, 0);
		return result;

	}

	public static AsymmetricCipherKeyPair generateRandomKeys() {
		ECKeyPairGenerator gen = new ECKeyPairGenerator();
		SecureRandom secureRandom = new SecureRandom();
		KeyGenerationParameters keyGenParam = new ECKeyGenerationParameters(Domain, secureRandom);
		gen.init(keyGenParam);
		return gen.generateKeyPair();

	}

	public static String encodeECPointHexCompressed(ECPoint point) {
		BigInteger x = point.getXCoord().toBigInteger();
		BigInteger y = point.getYCoord().toBigInteger();
		return x.toString(16) + (y.testBit(0) ? 1 : 0);
	}

	public static String compressPubKey(BigInteger pubKey) {
		String pubKeyYPrefix = pubKey.testBit(0) ? "03" : "02";
		String pubKeyHex = pubKey.toString(16);
		String pubKeyX = pubKeyHex.substring(0, 64);
		return pubKeyYPrefix + pubKeyX;
	}

	//14
	public static ECPoint getPublicKeyFromPrivateKey(BigInteger privKey) {
		ECPoint pubKey = curve.getG().multiply(privKey).normalize();
		return pubKey;
	}

	private static void randomPrivateKeyToAddress() throws UnsupportedEncodingException {

		System.out.println("Random private key --> public key --> address");
		System.out.println("---------------------------------------------");
		AsymmetricCipherKeyPair keyPair = generateRandomKeys();
		BigInteger privateKey = (((ECPrivateKeyParameters) keyPair.getPrivate()).getD());
		System.out.println("Private key (hex):" + privateKey.toString(16));
		System.out.println("Private key:" + privateKey.toString(10));
		
		ECPoint pubKey = ((ECPrivateKeyParameters)keyPair.getPublic()).getD());
		System.out.println("Public Key:(" + pubKey.getXCoord().toBigInteger().toString(10) + ","
				+ pubKey.getYCoord().toBigInteger().toString(10) + ")");
		String pubKeyCompressed = encodeECPointHexCompressed(pubKey);
		System.out.println("Public key (compressed): " + pubKeyCompressed);
		
		String addr = calcRipeMD160(pubKeyCompressed);
		System.out.println("Blockchain address:" + addr);

	}

	private static void signAndVerifyTransaction(String recipientAddress, int value, int fee, String iso8601,
			String senderPrivKeyHex) throws UnsupportedEncodingException, NoSuchFieldException, NoSuchAlgorithmException {
		System.out.println("Generate sign a transaction");
		System.out.println("---------------------------");
		System.out.println("Sender private key:" + senderPrivKeyHex);
		BigInteger privateKey = new BigInteger(senderPrivKeyHex, 16);
		ECPoint pubKey = getPublicKeyFromPrivateKey(privateKey);
		String senderPubKeyCompressed = encodeECPointHexCompressed(pubKey);
		System.out.println("Public key (compressed):" + senderPubKeyCompressed);
		String senderAddress = calcRipeMD160(senderPubKeyCompressed);
		System.out.println("BlockChain address:" + senderAddress);

		//16
		Transaction tran = new Transaction(
				senderAddress,
				recipientAddress,
				senderPubKeyCompressed,
				value,
				fee,
				iso8601datetime,
				null
		);
		Gson gson = new Gson();
		String tranJson = gson.toJson(tran);

		byte[] tranHash = calcSHA256(tranJson);
		System.out.println("Transaction hash(sha256):" + BytesToHex(tranHash));

		BigInteger [] tranSignatureBig = signData(privateKey, tranHash);
		System.out.println("Transaction signature: [" + tranSignatureBig[0].toString(16) + ","
				+ tranSignatureBig[1].toString(16) + "]");

	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		


		
		existingPrivateKeyToAddress("7e4670ae70c98d24f3662c172dc510a085578b9ccc717e6c2f4e547edd960a34");

	    signAndVerifyTransaction1("f51362b7351ef62253a227a77751ad9b2302f911", 25000,
	    		10, "2018-02-10T17:53:48.972Z", "7e4670ae70c98d24f3662c172dc510a085578b9ccc717e6c2f4e547edd960a34");
	}
	
	private static BigInteger[] signData(BigInteger privateKey, byte[] data) throws NoSuchAlgorithmException{
		ECDomainParameters ecSpec = new ECDomainParameters(curve.getCurve(), curve.getG(), 
				curve.getN(), curve.getH());
		ECPrivateKeyParameters keyParameters = new ECPrivateKeyParameters(privateKey, ecSpec);

		DSAKCalculator kCalculator = new HMacDSAKCalculator(new SHA256Digest());
		ECDSASigner signer  = new ECDSASigner(kCalculator);
		signer.init(true, keyParameters);
		BigInteger[] signature = signer.generateSignature(data);
		
		return signature;
	}

	public static ECPublicKeyParameters toPublicKey(String privateKey) {
		BigInteger d = new BigInteger(privateKey, 16);
		ECPoint q = Domain.getG().multiply(d);
		ECPublicKeyParameters publicParams = new ECPublicKeyParameters(q, Domain);
		return publicParams;
	}
	
	public static boolean verifySignature(String privateKeyHex, BigInteger[] signature, byte[] msg) {
		DSAKCalculator kCalculator = new HMacDSAKCalculator(new SHA256Digest());
		ECDSASigner signer = new ECDSASigner(kCalculator);
		signer.init(false, pubKey);
		
		return signer.verifySignature(msg, signature[0], signature[1]);
	}
}
