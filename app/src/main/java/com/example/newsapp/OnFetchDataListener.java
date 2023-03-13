package com.example.newsapp;

import com.example.newsapp.JsonStructure.ApiNews;

import java.util.List;

public interface OnFetchDataListener {

    void onFetchData(List<ApiNews>  arr,String message);

    void onError(String message);

}
