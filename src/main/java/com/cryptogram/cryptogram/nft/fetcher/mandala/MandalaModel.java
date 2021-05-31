package com.cryptogram.cryptogram.nft.fetcher.mandala;

import com.cryptogram.cryptogram.nft.NFT;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_ZONED_DATE_TIME;

public class MandalaModel {
    String token_id;
    String name;
    String timestamp;
    String thumbnail_uri;
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

    public String getThumbnail_uri() {
        return thumbnail_uri;
    }

    public void setThumbnail_uri(String thumbnail_uri) {
        this.thumbnail_uri = thumbnail_uri;
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
        return new NFT("Mandala-Art",
                this.getThumbnail_uri(),
                this.getToken_id(),
                this.convertTimestampToDateTime(),
                this.getUrlToPlatform(),
                this.getName());
    }

    @Override
    public String toString() {
        return "MandalaModel{\n" +
                "\ttoken_id = " + token_id +
                ",\n\tname = '" + name + '\'' +
                ",\n\ttimestamp = '" + timestamp + '\'' +
                ",\n\tthumbnail_uri = '" + thumbnail_uri + '\'' +
                ",\n\turlToPlatform = '" + urlToPlatform + '\'' +
                "\n}";
    }
}