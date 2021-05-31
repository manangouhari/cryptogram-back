package com.cryptogram.cryptogram.nft.fetcher.bazaar;

import com.cryptogram.cryptogram.nft.NFT;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_ZONED_DATE_TIME;

public class BazaarModel {
    String token_id;
    String name;
    String timestamp;
    String display_uri;
    String urlToPlatform;

    public String getToken_id() {
        return token_id;
    }

    public void setToken_id(String token_id) {
        this.token_id = token_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDisplay_uri() {
        return display_uri;
    }

    public void setDisplay_uri(String display_uri) {
        this.display_uri = display_uri;
    }

    public String getUrlToPlatform() {
        return urlToPlatform;
    }

    public void setUrlToPlatform(String urlToPlatform) {
        this.urlToPlatform = urlToPlatform;
    }

    public LocalDateTime convertTimestampToDateTime() {
        return LocalDateTime.parse(this.timestamp, ISO_ZONED_DATE_TIME);
    }

    public NFT convertToNFT(){
        return new NFT("BazaarMarket",
                "https://cloudflare-ipfs.com/ipfs/%s".formatted(this.getDisplay_uri().substring(7)),
                this.getToken_id(),
                this.convertTimestampToDateTime(),
                this.getUrlToPlatform(),
                this.getName());
    }

    @Override
    public String toString() {
        return "BazaarModel{" +
                "token_id=" + token_id +
                ", name='" + name + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", display_uri='" + display_uri + '\'' +
                ", urlToPlatform='" + urlToPlatform + '\'' +
                '}';
    }
}