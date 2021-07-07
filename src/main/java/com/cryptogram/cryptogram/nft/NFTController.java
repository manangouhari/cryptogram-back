package com.cryptogram.cryptogram.nft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/nfts")
public class NFTController {
    private final NFTService nftService;

    @Autowired
    public NFTController(NFTService nftService) {
        this.nftService = nftService;
    }

    @GetMapping
    public List<NFT> getNFTs() {

        List<NFT> nfts = nftService.getNFTs();
        Collections.shuffle(nfts);
        return nfts;
    }
}
