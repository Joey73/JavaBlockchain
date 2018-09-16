package org.joey.crypto.cryptocurrency;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import org.joey.crypto.blockchain.Blockchain;

public class Transaction {
	// Id of the transaction is a hash
	private String transactionId;
	// Use the PublicKeys to reference the sender or receiver.
	private PublicKey sender;
	private PublicKey receiver;
	// Amount of coins the transaction sends to the receiver from the sender
	private double amount;
	// Make sure the transaction is signed to prevent anyone else from spending the coins
	private byte[] signature;

	// Every transaction has inputs and outputs
	public List<TransactionInput> inputs;
	public List<TransactionOutput> outputs;

	public Transaction(PublicKey sender, PublicKey receiver, double amount, List<TransactionInput> inputs) {
		super();
		this.inputs = new ArrayList<TransactionInput>();
		this.outputs = new ArrayList<TransactionOutput>();
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		this.inputs = inputs;
		this.calculateHash();
	}

	public boolean verifyTransaction() {
		if (!this.verifySignature()) {
			System.out.println("Invalid transaction because of invalid signature...");
			return false;
		}

		// Let's gather unspent transactions (we have to consider the inputs)
		for (TransactionInput transactionInput : this.inputs) {
			transactionInput.setUTXO(Blockchain.UTXOs.get(transactionInput.getTransactionOutputId()));
		}

		// Transactions have 2 parts: send an amount to the receiver + send the (balance-amount) back to the sender.
		this.outputs.add(new TransactionOutput(this.receiver, this.amount, this.transactionId)); // send value to recipient
		this.outputs.add(new TransactionOutput(this.sender, getInputsSum() - this.amount, this.transactionId)); //

		// Remove transaction inputs from blockchain's UTXOs list because they have been spent
		for (TransactionInput transactionInput : this.inputs) {
			if (transactionInput.getUTXO() != null) {
				Blockchain.UTXOs.remove(transactionInput.getUTXO().getId());
			}
		}

		// the outputs will be inputs for other transactions (so put them in the blockchain's UTXOs)
		for (TransactionOutput transactionOutput : this.outputs) {
			Blockchain.UTXOs.put(transactionOutput.getId(), transactionOutput);
		}

		return true;
	}

	public double getInputsSum() {
		double sum = 0;

		for (TransactionInput transactionInput : this.inputs) {
			if (transactionInput.getUTXO() != null) {
				sum += transactionInput.getUTXO().getAmount();
			}
		}
		return sum;
	}

	public void generateSignature(PrivateKey privateKey) {
		String data = this.sender.toString() + this.receiver.toString() + Double.toString(this.amount);
		this.signature = CryptographyHelper.applyECDSASignature(privateKey, data);
	}

	public boolean verifySignature() {
		String data = this.sender.toString() + this.receiver.toString() + Double.toString(this.amount);
		return CryptographyHelper.verifyECDSASignature(this.sender, data, this.signature);
	}

	private void calculateHash() {
		String hashData = this.sender.toString() + this.receiver.toString() + Double.toString(this.amount);
		this.transactionId = CryptographyHelper.generateHash(hashData);
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public PublicKey getSender() {
		return this.sender;
	}

	public void setSender(PublicKey sender) {
		this.sender = sender;
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

	public byte[] getSignature() {
		return this.signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	public List<TransactionInput> getInputs() {
		return this.inputs;
	}

	public void setInputs(List<TransactionInput> inputs) {
		this.inputs = inputs;
	}

	public List<TransactionOutput> getOutputs() {
		return this.outputs;
	}

	public void setOutputs(List<TransactionOutput> outputs) {
		this.outputs = outputs;
	}
}
