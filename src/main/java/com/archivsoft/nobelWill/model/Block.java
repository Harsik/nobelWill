package com.archivsoft.nobelWill.model;

import com.archivsoft.nobelWill.util.StrUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class Block {

	public String hash;
	public String previousHash;
	private String data;
	private LocalDateTime timestamp;
	private int nonce;
	private String target = "00";
	private int targetDepth = 2;

	public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timestamp = LocalDateTime.now();
		mineNewBlock();
	}

	private void mineNewBlock(){
		while(hash == null || !hash.substring(0, targetDepth).equals(target)) {
			nonce ++;
			hash = makeHashBlock();
		}
	}

	public String makeHashBlock() {
		return StrUtil.applySha256(
				previousHash +
						data +
						timestamp +
						Integer.toString(nonce)
		);
	}
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0');
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = makeHashBlock();
		}
	}
}