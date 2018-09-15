package org.joey.crypto.blockchain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joey.crypto.constants.Constants;
import org.joey.crypto.cryptocurrency.CryptographyHelper;
import org.joey.crypto.cryptocurrency.Transaction;

public class Block {
	private int id;
	private int nonce;
	private long timeStamp;
	private String hash;
	private String previousHash;
	private List<Transaction> transactions;

	public Block(String previousHash) {
		super();
		this.transactions = new ArrayList<Transaction>();
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.generateHash();
	}

	public void generateHash() {
		String dataToHash = Integer.toString(this.id) //
				+ this.previousHash //
				+ Long.toString(this.timeStamp) //
				+ Integer.toString(this.nonce) //
				+ this.transactions.toString();
		String hashValue = CryptographyHelper.generateHash(dataToHash);
		this.hash = hashValue;
	}

	public void incrementNonce() {
		this.nonce++;
	}

	public String getHash() {
		return this.hash;
	}

	// check if the given transaction is valid or not
	public boolean addTransaction(Transaction transaction) {
		if(transaction == null) {
			return false;
		}
		
		// if the block is the genesis block we do not process
		if(!previousHash.equals(Constants.GENESIS_PREV_HASH)) {
			if(!transaction.verifyTransaction()) {
				System.out.println("Transaction is not valid...");
				return false;
			}
		}
		
		transactions.add(transaction);
		System.out.println("Transaction is valid and it's added to the block" + this);
		return true;
	}

	@Override
	public String toString() {
		return "Block [id=" + this.id + ", hash=" + this.hash + ", previousHash=" + this.previousHash + ", transaction=" + this.transactions.toString() + "]";
	}
}
