package com.github.moreno.stephania.githubuser.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/**
 * Representacion de la clase {@link Component}
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class Component implements Serializable {

    /** Cantidad de registros que contiene **/
    @SerializedName("total_count")
    @Expose
    private Integer totalCount;

    /** Respuesta  de error **/
    @SerializedName("incomplete_results")
    @Expose
    private Boolean incompleteResults;

    /** Lista de tipo Items **/
    @SerializedName("items")
    @Expose
    private List<Items> items = null;

    /** Get  **/
    public Integer getTotalCount() {
        return totalCount;
    }

    /** Set **/
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    /** Get  **/
    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    /** Set **/
    public void setIncompleteResults(Boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    /** Get  **/
    public List<Items> getItems() {
        return items;
    }

    /** Set **/
    public void setItems(List<Items> items) {
        this.items = items;
    }
}
