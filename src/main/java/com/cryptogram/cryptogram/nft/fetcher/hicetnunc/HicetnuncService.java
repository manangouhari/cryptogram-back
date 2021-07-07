package com.cryptogram.cryptogram.nft.fetcher.hicetnunc;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class HicetnuncService {
    String contract = "KT1RJ6PbjHpwc3M5rw5s2Nbmefwbuwbdxton";
    OkHttpClient client;
    JsonAdapter<List<HicetnuncModel>> adapter;

    public HicetnuncService(OkHttpClient client, Moshi moshi) {
        this.client = client;

        Type type = Types.newParameterizedType(List.class, HicetnuncModel.class);
        this.adapter = moshi.adapter(type);


    }


    public List<HicetnuncModel> getLatestTokens(Long level) throws IOException {
        String API_URL = "https://api.better-call.dev/v1/contract/mainnet/" + this.contract + "/tokens" + (level  > 0 ? "?min_level=" + level : "");
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        List<HicetnuncModel> nfts;
        try (Response response = client.newCall(request).execute()) {
            nfts = adapter.fromJson(response.body().source());

            nfts = nfts
                    .stream()
                    .filter(nft -> {
                        String type = nft.getMimeType();
                        boolean isJPEGorPNG = type.contains("jpeg") || type.contains("png");
                        return isJPEGorPNG;
                    })
                    .collect(Collectors.toList());

            for (HicetnuncModel nft : nfts)
                nft.setUrlToPlatform("https://www.hicetnunc.xyz/objkt/" + nft.getToken_id());

        }
        return nfts;
    }
}