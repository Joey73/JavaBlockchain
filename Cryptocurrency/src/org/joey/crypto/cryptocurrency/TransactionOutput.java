package org.joey.crypto.cryptocurrency;

import java.security.PublicKey;

public class TransactionOutput {
	// Identifier of the transaction output
	private String id;
	// Transaction id of the parent (so the transaction it was created in)
	private String parentTransactionId;
	// The new owner of the coin
	private PublicKey receiver;
	// Amount of coins
	private double amount;

	public TransactionOutput(PublicKey receiver, double amount, String parentTransactionId) {
		super();
		this.parentTransactionId = parentTransactionId;
		this.receiver = receiver;
		this.amount = amount;
		this.generateId();
	}

	private void generateId() {
		this.id = CryptographyHelper.generateHash(this.receiver.toString() + Double.toString(this.amount) + this.parentTransactionId);
	}

	public boolean isMine(PublicKey publicKey) {
		return publicKey == this.receiver;
	}

	public String getParentTransactionId() {
		return this.parentTransactionId;
	}

	public void setParentTransactionId(String parentTransactionId) {
		this.parentTransactionId = parentTransactionId;
	}

	public PublicKey getReceiver() {
		return this.receiver;
	}

	public void setReceiver(PublicKey receiver) {
		this.receiver = receiver;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getId() {
		return this.id;
	}
}
