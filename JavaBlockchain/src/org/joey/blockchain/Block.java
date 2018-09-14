package org.joey.blockchain;

import java.util.Date;

public class Block {
	private int id;
	private int nonce;
	private long timeStamp;
	private String hash;
	private String previousHash;
	private String transaction;

	public Block(int id, String previousHash, String transactions) {
		super();
		this.id = id;
		this.previousHash = previousHash;
		this.transaction = transactions;
		this.timeStamp = new Date().getTime();
		this.generateHash();
	}

	public void generateHash() {
		String dataToHash = Integer.toString(this.id) //
				+ Integer.toString(this.nonce) //
				+ Long.toString(this.timeStamp) //
				+ this.previousHash //
				+ this.transaction;
		String hashValue = SHA256Helper.generateHash(dataToHash);
		this.hash = hashValue;
	}

	public String getHash() {
		return this.hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPreviousHash() {
		return this.previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public void incrementNonce() {
		this.nonce++;
	}

	@Override
	public String toString() {
		return "Block [id=" + this.id + ", hash=" + this.hash + ", previousHash=" + this.previousHash + ", transaction=" + this.transaction + "]";
	}
}
