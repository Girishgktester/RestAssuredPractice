package com.rest;

import java.util.Base64;

public class Base64Encoading {

	public static void main(String[] args) {

		String userNamecolonPassword = "myUserName:myPassword";

		String base64Encoded = Base64.getEncoder().encodeToString(userNamecolonPassword.getBytes());
		System.out.println(base64Encoded);
		byte[] decodedBytes = Base64.getDecoder().decode(base64Encoded);
		System.out.println("Decoded = " + new String(decodedBytes));

	}

}
