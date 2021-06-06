package com.cryptogram.cryptogram.nft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NFTService {

    private final NFTRepository repository;

    @Autowired
    public NFTService(NFTRepository nftRepository) {
        this.repository = nftRepository;
    }


    public List<NFT> getNFTs(){
        return repository.findAll();
    }

    public Boolean checkNFTExistsByResourceUrl(String resourceUrl) {
        List<NFT> nfts = repository.findByResourceUrl(resourceUrl);
        return !nfts.isEmpty();
    }
}



