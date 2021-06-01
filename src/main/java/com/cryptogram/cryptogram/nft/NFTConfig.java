package com.cryptogram.cryptogram.nft;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class NFTConfig {

    @Bean
    CommandLineRunner commandLineRunner(NFTRepository repository) throws IOException {

        return args -> {
//            try{
//                System.out.println("Run on config");
//                OkHttpClient client = new OkHttpClient();
//                Moshi moshi =  new Moshi.Builder().build();
//
//                NFTFetcher fetcher = new NFTFetcher(client, moshi);
//
//                List<NFT> nftsForDB = new ArrayList<>();
//
//
//                for(HicetnuncModel nft: fetcher.fetchHicetnuncTokens(0)){
////                    nftsForDB.add(
////                            new NFT("Hicetnunc",
////                                    "https://cloudflare-ipfs.com/ipfs/%s".formatted(nft.getArtifact_uri().substring(7)),
////                                    nft.getToken_id(),
////                                    nft.convertTimestampToDateTime(),
////                                    nft.getUrlToPlatform(),
////                                    nft.getName())
////                    );
//                    nftsForDB.add(nft.convertToNFT());
//                }
//
//
//
//                HashMap<String, Boolean> nftTracker = new HashMap<String, Boolean>();
//
//                for (KalamintModel nft : fetcher.fetchKalamintTokens(0)) {
//                    var kmNFT = nft.convertToNFT();
//
//                    Boolean isNFTAlreadyAdded = nftTracker.getOrDefault("%s-%s".formatted(kmNFT.getName(), kmNFT.getResourceUrl()), false);
//                    if (!isNFTAlreadyAdded) {
//                        nftsForDB.add(kmNFT);
//                        nftTracker.put("%s-%s".formatted(kmNFT.getName(), kmNFT.getResourceUrl()), true);
//                    }
//                }
//
//                nftTracker.clear();
//
//
//                for (BazaarModel nft : fetcher.fetchBazaarTokens(0)) {
//                    if(nft.getDisplay_uri() == null) continue;
//                    nftsForDB.add(nft.convertToNFT());
//                }
//
//                for (MandalaModel nft : fetcher.fetchMandalaTokens(0)){
//                    nftsForDB.add(nft.convertToNFT());
//                }
//
//
//
////                repository.saveAll(nftsForDB);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        };
    }
}

