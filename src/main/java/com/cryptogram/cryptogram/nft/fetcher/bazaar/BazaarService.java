package com.cryptogram.cryptogram.nft.fetcher.bazaar;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class BazaarService {
    String contract = "KT1PKvHNWuWDNVDtqjDha4AostLrGDu4G1jy";
    OkHttpClient client;
    JsonAdapter<List<BazaarModel>> adapter;

    public BazaarService(OkHttpClient client, Moshi moshi){
        this.client = client;

        Type type = Types.newParameterizedType(List.class, BazaarModel.class);
        this.adapter = moshi.adapter(type);

    }


    public List<BazaarModel> getLatestTokens(Long level) throws IOException {
        String API_URL = "https://api.better-call.dev/v1/contract/mainnet/" + this.contract+ "/tokens" + (level > 0? "?min_level=" + level: "");
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        List<BazaarModel> nfts;
        try(Response response = client.newCall(request).execute()) {
            nfts = adapter.fromJson(response.body().source());
//
            for(BazaarModel nft: nfts)
                nft.setUrlToPlatform("https://bazaarnft.xyz/collection/" + contract + "/token/" + nft.getToken_id());
        }

        return nfts;
    }
}
