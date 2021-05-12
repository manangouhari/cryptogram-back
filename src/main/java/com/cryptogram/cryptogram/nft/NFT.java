package com.cryptogram.cryptogram.nft;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        uniqueConstraints =
        @UniqueConstraint(columnNames={"tokenId", "platform"})
)
public class NFT {
    @Id
    @SequenceGenerator(
            name = "nft_sequence",
            sequenceName = "nft_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "nft_sequence"

    )
    private Long id;

    @Column(nullable = false)
    private String platform;

    @Column(nullable = false, unique = true)
    private String resourceUrl;

    @Column(nullable = false)
    private String tokenId;

    @Column(nullable = false)
    private LocalDateTime createdAt;


    private String urlToPlatform;

    private String name;


    public NFT() {}

    public NFT(Long id, String platform, String resourceUrl, String tokenId, LocalDateTime createdAt, String urlToPlatform, String name) {
        this.id = id;
        this.platform = platform;
        this.resourceUrl = resourceUrl;
        this.tokenId = tokenId;
        this.createdAt = createdAt;
        this.urlToPlatform = urlToPlatform;
        this.name = name;
    }

    public NFT(String platform, String resourceUrl, String tokenId, LocalDateTime createdAt, String urlToPlatform, String name) {
        this.platform = platform;
        this.resourceUrl = resourceUrl;
        this.tokenId = tokenId;
        this.createdAt = createdAt;
        this.urlToPlatform = urlToPlatform;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUrlToPlatform() {
        return urlToPlatform;
    }

    public void setUrlToPlatform(String urlToPlatform) {
        this.urlToPlatform = urlToPlatform;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NFT{" +
                "id=" + id +
                ", platform='" + platform + '\'' +
                ", resourceUrl='" + resourceUrl + '\'' +
                ", tokenId='" + tokenId + '\'' +
                ", createdAt=" + createdAt +
                ", urlToPlatform='" + urlToPlatform + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
