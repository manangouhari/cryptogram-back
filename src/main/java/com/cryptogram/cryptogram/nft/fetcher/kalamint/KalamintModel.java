package com.cryptogram.cryptogram.nft.fetcher.kalamint;

import com.cryptogram.cryptogram.nft.NFT;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.time.format.DateTimeFormatter.ISO_ZONED_DATE_TIME;

public class KalamintModel {
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

//    @Override
//    public boolean equals(Object obj){
//        if (!(obj instanceof KalamintModel)) {
//            return false;
//        }
//        KalamintModel nft = (KalamintModel) obj;
//        System.out.println(nft.name.equals(this.name) && nft.display_uri.equals(this.display_uri));
//        return nft.name.equals(this.name) && nft.display_uri.equals(this.display_uri);
//    }

    public NFT convertToNFT(){
        return new NFT("Kalamint",
                "https://cloudflare-ipfs.com/ipfs/%s".formatted(this.getDisplay_uri().substring(7)),
                this.getToken_id(),
                this.convertTimestampToDateTime(),
                this.getUrlToPlatform(),
                this.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KalamintModel)) return false;
        KalamintModel that = (KalamintModel) o;
        return Objects.equals(token_id, that.token_id) || (Objects.equals(name, that.name) && Objects.equals(display_uri, that.display_uri));
    }

    @Override
    public int hashCode() {
        return Objects.hash(token_id, name, timestamp, display_uri, urlToPlatform);
    }

    @Override
    public String toString() {
        return "KalamintModel{" +
                "token_id=" + token_id +
                ", name='" + name + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", display_uri='" + display_uri + '\'' +
                ", urlToPlatform='" + urlToPlatform + '\'' +
                '}';
    }

}