package com.cryptogram.cryptogram.nft.fetcher.kalamint;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class KalamintService {
    String contract = "KT1EpGgjQs73QfFJs9z7m1Mxm5MTnpC2tqse";
    OkHttpClient client;
    JsonAdapter<List<KalamintModel>> adapter;

    public KalamintService(OkHttpClient client, Moshi moshi){
        this.client = client;

        Type type = Types.newParameterizedType(List.class, KalamintModel.class);
        this.adapter = moshi.adapter(type);

    }


    public List<KalamintModel> getLatestTokens(Long level) throws IOException {
        String API_URL = "https://api.better-call.dev/v1/contract/mainnet/" + this.contract+ "/tokens" + (level > 0? "?min_level=" + level: "");
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        List<KalamintModel> nfts;
        try(Response response = client.newCall(request).execute()) {
            nfts = adapter.fromJson(response.body().source());
            for(KalamintModel nft: nfts)
                nft.setUrlToPlatform("https://kalamint.io/token/" + nft.getToken_id());
        }
        return nfts;
    }
}
