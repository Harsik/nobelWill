package com.archivsoft.nobelWill.service;

import com.archivsoft.nobelWill.model.Block;
import com.archivsoft.nobelWill.model.Wlt;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class BlockService {

    public ArrayList<Block> blockchain = new ArrayList<Block>();
    public int difficulty = 1;
    public Wlt WltA;
    public Wlt WltB;

    public String makeBlock(){
        blockchain.add(new Block("Genesis block", "0"));
        System.out.println("\nTrying to Mine Genesis block!");
        blockchain.get(0).mineBlock(difficulty);

        for(int i = 1 ; i <= 10 ; i++){
            blockchain.add(new Block("block " + i, blockchain.get(blockchain.size()-1).hash));
            System.out.printf("\nTrying to Mine block #%d", i+1 );
            blockchain.get(i).mineBlock(difficulty);
        }

        System.out.println("\nBlockchain is Valid : " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nOpenchain Block list : ");
        System.out.println(blockchainJson);
        return blockchainJson;

    }

    public String getBlockchain(){
        return new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
    }

    public void createBlockchain(String data){
        blockchain.add(new Block(data, blockchain.get(blockchain.size()-1).hash));
    }

    public Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);

            if(!currentBlock.hash.equals(currentBlock.makeHashBlock()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }

            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }
    public void genekey() throws IOException {

        WltA = new Wlt();
        WltB = new Wlt();

        WltA.generateKeyPair();
        System.out.println("WltA.privateKey : " + WltA.privateKey.toString());
        System.out.println("WltA.publicKey : " + WltA.publicKey.toString());
        System.out.println("WltA.createPemPrivate : " + WltA.createPemPrivate());
        System.out.println("WltA.createPemPublic : " + WltA.createPemPublic());

        WltA.writePemFile(WltA.privateKey, "RSA PRIVATE KEY", "id_rsa");
        WltA.writePemFile(WltA.publicKey, "RSA PUBLIC KEY", "id_rsa.pub");
    }

}
