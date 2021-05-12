package com.cryptogram.cryptogram.nft;

import com.cryptogram.cryptogram.nft.fetcher.NFTFetcher;
import com.cryptogram.cryptogram.nft.fetcher.bazaar.BazaarModel;
import com.cryptogram.cryptogram.nft.fetcher.hicetnunc.HicetnuncModel;
import com.cryptogram.cryptogram.nft.fetcher.kalamint.KalamintModel;
import com.cryptogram.cryptogram.nft.fetcher.mandala.MandalaModel;
import com.squareup.moshi.Moshi;
import okhttp3.OkHttpClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Configuration
public class NFTConfig {

    @Bean
    CommandLineRunner commandLineRunner(NFTRepository repository) throws IOException {

        return args -> {
            try{
                System.out.println("Run on config");
                OkHttpClient client = new OkHttpClient();
                Moshi moshi =  new Moshi.Builder().build();

                NFTFetcher fetcher = new NFTFetcher(client, moshi);

                List<NFT> nftsForDB = new ArrayList<>();


                for(HicetnuncModel nft: fetcher.fetchHicetnuncTokens(0)){
//                    nftsForDB.add(
//                            new NFT("Hicetnunc",
//                                    "https://cloudflare-ipfs.com/ipfs/%s".formatted(nft.getArtifact_uri().substring(7)),
//                                    nft.getToken_id(),
//                                    nft.convertTimestampToDateTime(),
//                                    nft.getUrlToPlatform(),
//                                    nft.getName())
//                    );
                    System.out.println(nft);
                }



                HashMap<String, Boolean> nftTracker = new HashMap<String, Boolean>();

                for (KalamintModel nft : fetcher.fetchKalamintTokens(0)) {
                    var kmNFT = new NFT("Kalamint",
                            "https://cloudflare-ipfs.com/ipfs/%s".formatted(nft.getDisplay_uri().substring(7)),
                            nft.getToken_id(),
                            nft.convertTimestampToDateTime(),
                            nft.getUrlToPlatform(),
                            nft.getName());
                    Boolean isNFTAlreadyAdded = nftTracker.getOrDefault("%s-%s".formatted(kmNFT.getName(), kmNFT.getResourceUrl()), false);
                    if (!isNFTAlreadyAdded) {
                        nftsForDB.add(kmNFT);
                        nftTracker.put("%s-%s".formatted(kmNFT.getName(), kmNFT.getResourceUrl()), true);
                    }
                    System.out.println(nft);
                }

                nftTracker.clear();


                for (BazaarModel nft : fetcher.fetchBazaarTokens(0)) {
                    if(nft.getDisplay_uri() == "null") continue;

//                nftsForDB.add(new NFT("BazaarMarket",
//                        "https://cloudflare-ipfs.com/ipfs/%s".formatted(nft.getDisplay_uri().substring(7)),
//                        nft.getToken_id(),
//                        nft.convertTimestampToDateTime(),
//                        nft.getUrlToPlatform(),
//                        nft.getName()));
                    System.out.println(nft.getDisplay_uri());
                }

                for (MandalaModel nft : fetcher.fetchMandalaTokens(0))
                    nftsForDB.add(new NFT("Mandala-Art",
                            "https://cloudflare-ipfs.com/ipfs/%s".formatted(nft.getThumbnail_uri().substring(7)),
                            nft.getToken_id(),
                            nft.convertTimestampToDateTime(),
                            nft.getUrlToPlatform(),
                            nft.getName()));


                repository.saveAll(nftsForDB);

            } catch (IOException e) {
                e.printStackTrace();
            }

        };
    }
}

