package com.github.moreno.stephania.githubuser.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Representacion de la clase {@link Comments}
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class Comments implements Serializable {

    @SerializedName("href")
    @Expose
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
