package com.cryptogram.cryptogram.nft.fetcher;

import com.cryptogram.cryptogram.nft.fetcher.bazaar.BazaarModel;
import com.cryptogram.cryptogram.nft.fetcher.bazaar.BazaarService;
import com.cryptogram.cryptogram.nft.fetcher.hicetnunc.HicetnuncModel;
import com.cryptogram.cryptogram.nft.fetcher.hicetnunc.HicetnuncService;
import com.cryptogram.cryptogram.nft.fetcher.kalamint.KalamintModel;
import com.cryptogram.cryptogram.nft.fetcher.kalamint.KalamintService;
import com.cryptogram.cryptogram.nft.fetcher.mandala.MandalaModel;
import com.cryptogram.cryptogram.nft.fetcher.mandala.MandalaService;
import com.squareup.moshi.Moshi;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.util.List;

public class NFTFetcher {
    OkHttpClient client;
    Moshi moshi;

    public NFTFetcher(OkHttpClient client, Moshi moshi){
        this.client = client;
        this.moshi = moshi;
    }

    public List<HicetnuncModel> fetchHicetnuncTokens(int offset) throws IOException {
        HicetnuncService henService = new HicetnuncService(client, moshi);
        return henService.getLatestTokens(offset);
    }

    public List<MandalaModel> fetchMandalaTokens(int offset) throws IOException {
        MandalaService mandalaService = new MandalaService(client, moshi);
        return mandalaService.getLatestTokens(offset);
    }

    public List<KalamintModel> fetchKalamintTokens(int offset) throws IOException {
        KalamintService kalamintService = new KalamintService(client, moshi);
        return kalamintService.getLatestTokens(offset);
    }

    public List<BazaarModel> fetchBazaarTokens(int offset) throws IOException {
        BazaarService bazaarService = new BazaarService(client, moshi);
        return bazaarService.getLatestTokens(offset);
    }
}
