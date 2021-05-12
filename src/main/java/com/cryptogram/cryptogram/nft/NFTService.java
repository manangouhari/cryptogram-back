package com.cryptogram.cryptogram.nft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
}



