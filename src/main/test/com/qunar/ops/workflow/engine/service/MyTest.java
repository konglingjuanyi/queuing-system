package com.qunar.ops.workflow.engine.service;

import org.apache.commons.codec.binary.Base64;

import com.sun.mail.util.BASE64DecoderStream;

public class MyTest {
	
	public static void main(String[] args) {
		byte[] decode = Base64.decodeBase64("eyJfcGVybWFuZW50Ijp0cnVlLCJydHhfaWQiOiJudWJ5LnpoYW5nIn0".getBytes());
		System.out.println(decode.toString());
	}

}
