package org.joey.crypto.cryptocurrency;

import org.joey.crypto.blockchain.Block;
import org.joey.crypto.blockchain.Blockchain;
import org.joey.crypto.constants.Constants;

public class Miner {
	private double reward;

	public void mine(Block block, Blockchain blockchain) {
		System.out.println("\nMining...");
		while (this.notGoldenHash(block)) {
			block.generateHash();
			block.incrementNonce();
		}

		System.out.println(block + " has just mined...");
		System.out.println("Hash is: " + block.getHash());

		blockchain.addBlock(block);
		this.reward += Constants.MINER_REWARD;
	}

	private boolean notGoldenHash(Block block) {
		String leadingZeros = new String(new char[Constants.DIFFICULTY]).replace('\0', '0');
		return !block.getHash().substring(0, Constants.DIFFICULTY).equals(leadingZeros);
	}

	public double getReward() {
		return this.reward;
	}
}
