package com.cryptogram.cryptogram.nft.fetcher.mandala;

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

public class MandalaService {
    String contract = "KT1DKBvxiwDR7qazNuuxCxY2AaXnoytmDE7H";
    OkHttpClient client;
    JsonAdapter<List<MandalaModel>> adapter;

    public MandalaService(OkHttpClient client, Moshi moshi){
        this.client = client;

        Type type = Types.newParameterizedType(List.class, MandalaModel.class);
        this.adapter = moshi.adapter(type);

    }


    public List<MandalaModel> getLatestTokens(Long level) throws IOException {
        String API_URL = "https://api.better-call.dev/v1/contract/mainnet/" + this.contract+ "/tokens" + (level > 0? "?min_level=" + level: "");
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        List<MandalaModel> nfts;
        try(Response response = client.newCall(request).execute()) {
            nfts = adapter.fromJson(response.body().source());

            // remove tokens that have "favicon" in their thumbnail uri.
            nfts = nfts
                    .stream()
                    .filter(n -> n.getThumbnail_uri() != null && !n.getThumbnail_uri().contains("favicon"))
                    .collect(Collectors.toList());

            // set platform URL for each NFT, as Tezos Mandala doesn't have a dedicated page for each NFT.
            for(MandalaModel mandala: nfts){
                mandala.setUrlToPlatform("https://tezos-mandala.art/explore");
            }
        }

        return nfts;
    }
}
