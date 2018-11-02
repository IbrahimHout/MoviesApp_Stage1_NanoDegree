package com.example.ibrahimelhout.moviesapp.Models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieReview {
    private Integer id;
    private Integer page;

    @SerializedName("results")
    private List<MovieReviewResults> reviews;
    private Integer totalPages;
    private Integer totalResults;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<MovieReviewResults> getReviews() {
        return reviews;
    }

    public void setReviews(List<MovieReviewResults> reviews) {
        this.reviews = reviews;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
}
