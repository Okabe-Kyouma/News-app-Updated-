package com.example.newsapp.JsonStructure;

import java.io.Serializable;
import java.util.List;

public class ApiResult implements Serializable {

    public String status;
    public int totalResults;
    public List<ApiNews> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<ApiNews> getArticles() {
        return articles;
    }

    public void setArticles(List<ApiNews> articles) {
        this.articles = articles;
    }
}
