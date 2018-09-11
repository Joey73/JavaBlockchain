package org.joey.blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
	public List<Block> blockchain;

	public Blockchain() {
		this.blockchain = new ArrayList<>();
	}

	public void addBlock(Block block) {
		this.blockchain.add(block);
	}

	public List<Block> getBlockchain() {
		return blockchain;
	}
	
	public int size() {
		return this.blockchain.size();
	}

	@Override
	public String toString() {
		String bc = "";
		for(Block block : this.blockchain) {
			bc += block.toString() + "\n";
		}
		return bc;
	}
}
