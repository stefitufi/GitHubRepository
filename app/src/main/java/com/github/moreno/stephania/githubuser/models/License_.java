package com.github.moreno.stephania.githubuser.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Representacion de la clase {@link License_}
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class License_ implements Serializable{

    @SerializedName("key")
    @Expose
    private String key;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("spdx_id")
    @Expose
    private String spdxId;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("node_id")
    @Expose
    private String nodeId;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpdxId() {
        return spdxId;
    }

    public void setSpdxId(String spdxId) {
        this.spdxId = spdxId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}
