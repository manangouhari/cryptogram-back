package com.cryptogram.cryptogram.nft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NFTRepository extends JpaRepository<NFT, Long> {
}
