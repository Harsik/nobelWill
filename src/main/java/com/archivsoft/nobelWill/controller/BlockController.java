package com.archivsoft.nobelWill.controller;

import com.archivsoft.nobelWill.service.BlockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/block")
public class BlockController {

    private BlockService blockService;

    @GetMapping("/make")
    public String makeBlockChain(){
        return blockService.makeBlock();
    }

    @GetMapping("/getAll")
    public String findAllBlockChain(){
        return blockService.getBlockchain();
    }

    @GetMapping("/set")
    public String findAllBlockChain(@RequestParam String data){
        blockService.createBlockchain(data);
        return blockService.getBlockchain();
    }

    @GetMapping("/ge")
    public void genekey() throws IOException {
        blockService.genekey();
    }

}
