package com.cryptogram.cryptogram.nft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NFTRepository extends JpaRepository<NFT, Long> {
    public List<NFT> findByResourceUrl(String resourceUrl);
}
