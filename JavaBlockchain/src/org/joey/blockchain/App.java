package org.joey.blockchain;

public class App {
	public static void main(String[] args) {
		Blockchain blockchain = new Blockchain();
		Miner miner = new Miner();
		
		String previousHash = Constants.GENESIS_PREV_HASH;
		Block block0 = new Block(0, previousHash, "Transaction1");
		miner.mine(block0, blockchain);
		
		previousHash = blockchain.getBlockchain().get(blockchain.size()-1).getHash();
		Block block1 = new Block(1, previousHash, "Transcation2");
		miner.mine(block1, blockchain);
		
		previousHash = blockchain.getBlockchain().get(blockchain.size()-1).getHash();
		Block block2 = new Block(2, previousHash, "Transcation3");
		miner.mine(block2, blockchain);
		
		System.out.println("\n" + "BLOCKCHAIN:\n" + blockchain.toString());
		System.out.println("Miner's reward: " + miner.getReward());
	}
}
