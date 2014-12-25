package com.qunar.ops.oaengine.manager;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x509.RSAPublicKeyStructure;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Component
public class LoginManager {
	@Value("${login.apihost}")
	private String loginHost;
	@Value("${login.appkey}")
	private String key;
	public static final String LOCAL_URL = "http://l-backyard1.ops.dev.cn6.qunar.com:10088/api/v1/auth";
	
//	public static void main(String[] args) throws Exception {
//		LoginManager test = new LoginManager();
//		String p = "{\"p\": \"vfr4VFR$\", \"a\": \"testapp\", \"u\": \"yongnian.jiang\"}";
//		byte[] eRes1 = test.encryptWithPublicKey(p.getBytes(),test.getPemPublicKeyFromFile("D:\\pub_key"));
//		System.out.println(test.getPemPublicKeyFromFile("D:\\pub_key"));
//		test.post_invoke(LOCAL_URL, eRes1);
//	}
	
	public String login(String username, String password){
		String p = "{\"p\": \""+password+"\", \"a\": \""+key+"\", \"u\": \""+username+"\"}";
		byte[] eRes1;
		try {
			eRes1 = encryptWithPublicKey(p.getBytes(), getPemPublicKeyFromFile("/pub_key"));
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return this.post_invoke(username, loginHost, eRes1);
	}

	public String getPemPublicKeyFromFile(String filename) throws Exception {
		File f = new File(filename);
		FileInputStream fis = new FileInputStream(f);
		DataInputStream dis = new DataInputStream(fis);
		byte[] keyBytes = new byte[(int) f.length()];
		dis.readFully(keyBytes);
		dis.close();

		String temp = new String(keyBytes);
		String publicKeyPEM = temp.replace("-----BEGIN RSA PUBLIC KEY-----\n", "");
		publicKeyPEM = publicKeyPEM.replace("-----END RSA PUBLIC KEY-----", "");
		return publicKeyPEM;
	}

	public String getPemPrivateKeyFromFile(String filename) throws Exception {
		File f = new File(filename);
		FileInputStream fis = new FileInputStream(f);
		DataInputStream dis = new DataInputStream(fis);
		byte[] keyBytes = new byte[(int) f.length()];
		dis.readFully(keyBytes);
		dis.close();

		String temp = new String(keyBytes);
		String privateKeyPEM = temp.replace(
				"-----BEGIN RSA PRIVATE KEY-----\n", "");
		privateKeyPEM = privateKeyPEM.replace("-----END RSA PRIVATE KEY-----",
				"");
		return privateKeyPEM;
	}

	public String post_invoke(String username, String url, byte[] fileContent) {
		CloseableHttpClient  httpclient = HttpClients.createDefault();
		File file = null;
		try {
			HttpPost postMethod = new HttpPost(url);
			
			file = File.createTempFile("login", ".tmp");
			FileOutputStream writeFile = new FileOutputStream(file);
			writeFile.write(fileContent);
			writeFile.flush();
			writeFile.close();
			HttpEntity mre = MultipartEntityBuilder.create()
					.addPart("a", new StringBody(key, ContentType.TEXT_PLAIN))
					.addPart("uid", new StringBody(username, ContentType.TEXT_PLAIN))
					.addBinaryBody("p", file, ContentType.TEXT_PLAIN, "c")
					.build();//.addPart("p", contentBody).;
			postMethod.setEntity(mre);
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(5000)
	                .setConnectTimeout(5000)
	                .setConnectionRequestTimeout(5000)
	                .build();
			postMethod.setConfig(requestConfig);
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				public String handleResponse(final HttpResponse response)
						throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity): null;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}

			};
			String execute = httpclient.execute(postMethod, responseHandler);
			JSONObject jo = JSON.parseObject(execute);
			int status = (Integer)jo.get("status_id");
			String msg = (String)jo.get("msg");
			return status==0?null:msg;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			try {
				if(file != null){
					file.deleteOnExit();
				}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private byte[] encryptWithPublicKey(byte[] message, String publicKey)
			throws Exception {
		PublicKey apiPublicKey = getRSAPublicKeyFromString(publicKey);
		Cipher rsaCipher = Cipher
				.getInstance("RSA");
		rsaCipher.init(Cipher.ENCRYPT_MODE, apiPublicKey);
		return rsaCipher.doFinal(message);
	}

	public static byte[] decryptWithPrivateKey(byte[] message, String privateKey)
			throws Exception {
		PrivateKey pKey = getRSAPrivateKeyFromString(privateKey);
		Cipher rsaCipher = Cipher
				.getInstance("RSA");
		rsaCipher.init(Cipher.DECRYPT_MODE, pKey);
		return rsaCipher.doFinal(message);
	}

	public static PublicKey getRSAPublicKeyFromString(String apiKey)
			throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA", new BouncyCastleProvider());
		byte[] publicKeyBytes = Base64.decodeBase64(apiKey.getBytes("UTF-8"));
		ByteArrayInputStream bAIS = new ByteArrayInputStream(publicKeyBytes);
		ASN1InputStream ais = new ASN1InputStream(bAIS);
		Object asnObject = ais.readObject();
		ASN1Sequence sequence = (ASN1Sequence) asnObject;
		RSAPublicKeyStructure rsaPubStructure = new RSAPublicKeyStructure(sequence);
		RSAPublicKeySpec keySpec = new RSAPublicKeySpec(
				rsaPubStructure.getModulus(),
				rsaPubStructure.getPublicExponent());
		KeyFactory keyFact = KeyFactory.getInstance("RSA", new BouncyCastleProvider());
		return keyFact.generatePublic(keySpec);
	}

	public static PrivateKey getRSAPrivateKeyFromString(String key)
			throws Exception {
		byte[] clear = Base64.decodeBase64(key);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
		KeyFactory fact = KeyFactory.getInstance("RSA", new BouncyCastleProvider());
		PrivateKey priv = fact.generatePrivate(keySpec);
		Arrays.fill(clear, (byte) 0);
		return priv;
	}

	public static String stripPublicKeyHeaders(String key) {
		StringBuilder strippedKey = new StringBuilder();
		String lines[] = key.split("\n");
		for (String line : lines) {
			if (!line.contains("BEGIN RSA PUBLIC KEY")
					&& !line.contains("END RSA PUBLIC KEY")) {
				strippedKey.append(line.trim());
			}
		}
		return strippedKey.toString().trim();
	}
}
