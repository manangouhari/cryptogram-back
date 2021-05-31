package com.cryptogram.cryptogram.nft.fetcher.hicetnunc;

import com.cryptogram.cryptogram.nft.NFT;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_ZONED_DATE_TIME;

public class HicetnuncModel {
    String token_id;
    String name;
    String timestamp;
    String artifact_uri;
    List<fileType> formats;
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

    public String getArtifact_uri() {
        return artifact_uri;
    }

    public void setArtifact_uri(String artifact_uri) {
        this.artifact_uri = artifact_uri;
    }

    public List<fileType> getFormats() {
        return formats;
    }

    public void setFormats(List<fileType> formats) {
        this.formats = formats;
    }

    public String getMimeType(){
        return formats.get(0).getMimeType();
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

    public NFT convertToNFT() {
        return new NFT("Hicetnunc",
                        "https://cloudflare-ipfs.com/ipfs/%s".formatted(this.getArtifact_uri().substring(7)),
                        this.getToken_id(),
                        this.convertTimestampToDateTime(),
                        this.getUrlToPlatform(),
                        this.getName());
    }

    @Override
    public String toString() {
        return "HicetnuncModel{" +
                "token_id=" + token_id +
                ", name='" + name + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", artifact_uri='" + artifact_uri + '\'' +
                ", formats=" + formats +
                ", urlToPlatform='" + urlToPlatform + '\'' +
                '}';
    }
}

class fileType {
    String mimeType;

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Override
    public String toString() {
        return "fileType{" +
                "mimeType='" + mimeType + '\'' +
                '}';
    }
}