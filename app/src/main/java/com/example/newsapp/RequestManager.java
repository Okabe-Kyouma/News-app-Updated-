package com.example.newsapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.example.newsapp.JsonStructure.ApiNews;
import com.example.newsapp.JsonStructure.ApiResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {

    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static String country_code = "us";

    public RequestManager(Context context){
        this.context = context;
    }


    public void getNewsResponse(OnFetchDataListener listener,String category,String query){

        CallNews callNews = retrofit.create(CallNews.class);
         Call<ApiResult> call = callNews.callApi(country_code,category,query,"185ff8c362174d8b82a8bcbc6468f816");

         try{

            call.enqueue(new Callback<ApiResult>() {
                @Override
                public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {

                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Error Occurred!!", Toast.LENGTH_SHORT).show();


                }
                    else {

                        listener.onFetchData(response.body().getArticles(), response.message());
                    }

                }
                @Override
                public void onFailure(Call<ApiResult> call, Throwable t) {

                    listener.onError("Error occurred!!");

                }
            });


         }
         catch (Exception e){
             e.printStackTrace();
         }

    }



  public interface CallNews{

        @GET("top-headlines")

        Call<ApiResult> callApi(

                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String query,
                @Query("apikey") String apikey

        );



    }


}


