package org.joey.crypto.blockchain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.joey.crypto.cryptocurrency.TransactionOutput;

public class Blockchain {
	public static ArrayList<Block> blockchain;
	public static Map<String, TransactionOutput> UTXOs;

	public Blockchain() {
		Blockchain.UTXOs = new HashMap<String, TransactionOutput>();
		Blockchain.blockchain = new ArrayList<>();
	}

	public void addBlock(Block block) {
		Blockchain.blockchain.add(block);
	}

	public int size() {
		return Blockchain.blockchain.size();
	}

	@Override
	public String toString() {
		String blockchain = "";

		for (Block block : Blockchain.blockchain) {
			blockchain += block.toString() + "\n";
		}

		return blockchain;
	}
}
