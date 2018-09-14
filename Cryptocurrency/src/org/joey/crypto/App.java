package org.joey.crypto;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class App {
	public static void main(String[] args) {
		// The security provider is Bouncy Castle. It is more flexible than JCE.
		Security.addProvider(new BouncyCastleProvider());

	}
}
