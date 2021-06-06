package com.cryptogram.cryptogram;

import com.cryptogram.cryptogram.nft.NFT;
import com.cryptogram.cryptogram.nft.NFTRepository;
import com.cryptogram.cryptogram.nft.NFTService;
import com.cryptogram.cryptogram.nft.fetcher.NFTFetcher;
import com.cryptogram.cryptogram.nft.fetcher.bazaar.BazaarModel;
import com.cryptogram.cryptogram.nft.fetcher.hicetnunc.HicetnuncModel;
import com.cryptogram.cryptogram.nft.fetcher.kalamint.KalamintModel;
import com.cryptogram.cryptogram.nft.fetcher.mandala.MandalaModel;
import com.squareup.moshi.Moshi;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@SpringBootApplication()
@EnableScheduling
public class CryptogramApplication {
	private final NFTRepository repository;
	private final NFTService service;

	@Autowired
	public CryptogramApplication(NFTRepository repo, NFTService service){
		this.repository = repo;
		this.service = service;
	}

	public static void main(String[] args) {
		SpringApplication.run(CryptogramApplication.class, args);
	}

	// Runs every 15 minutes
	@Scheduled(fixedDelay = 1000 * 60 * 15)
	public void indexNFTs() throws IOException {
		try {
			OkHttpClient client = new OkHttpClient();
			Moshi moshi =  new Moshi.Builder().build();
			NFTFetcher fetcher = new NFTFetcher(client, moshi);
			List<NFT> nftsForDB = new ArrayList<>();

			for(HicetnuncModel nft: fetcher.fetchHicetnuncTokens(0)){
				NFT nftForDB  = nft.convertToNFT();
				boolean exists = service.checkNFTExistsByResourceUrl(nftForDB.getResourceUrl());
				if(!exists){
					nftsForDB.add(nftForDB);
				}
			}

			HashMap<String, Boolean> nftTracker = new HashMap<String, Boolean>();
			for (KalamintModel nft : fetcher.fetchKalamintTokens(0)) {
				var kmNFT = nft.convertToNFT();

				Boolean isNFTAlreadyAdded = nftTracker.getOrDefault("%s-%s".formatted(kmNFT.getName(), kmNFT.getResourceUrl()), false);
				if (!isNFTAlreadyAdded) {
					boolean exists = service.checkNFTExistsByResourceUrl(kmNFT.getResourceUrl());
					if(!exists){
						nftsForDB.add(kmNFT);
						nftTracker.put("%s-%s".formatted(kmNFT.getName(), kmNFT.getResourceUrl()), true);
					}
				}
			}
			nftTracker.clear();

			for (BazaarModel nft : fetcher.fetchBazaarTokens(0)) {
				if(nft.getDisplay_uri() == null) continue;
				NFT nftForDB = nft.convertToNFT();
				if(!service.checkNFTExistsByResourceUrl(nftForDB.getResourceUrl())){
					nftsForDB.add(nftForDB);
				}
			}

			for (MandalaModel nft : fetcher.fetchMandalaTokens(0)){
				NFT nftForDB = nft.convertToNFT();
				if(!service.checkNFTExistsByResourceUrl(nftForDB.getResourceUrl())) {
					nftsForDB.add(nft.convertToNFT());
				}
			}

			repository.saveAll(nftsForDB);


		} catch (IOException e) {
			e.printStackTrace();
		}



	}



}
