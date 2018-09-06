package com.github.moreno.stephania.githubuser.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Representacion de la clase {@link Base}
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class Base implements Serializable{

    @SerializedName("label")
    @Expose
    private String label;

    @SerializedName("ref")
    @Expose
    private String ref;

    @SerializedName("sha")
    @Expose
    private String sha;

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("repo")
    @Expose
    private Repositorio repo;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Repositorio getRepo() {
        return repo;
    }

    public void setRepo(Repositorio repo) {
        this.repo = repo;
    }
}
