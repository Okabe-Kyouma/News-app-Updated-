package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.newsapp.JsonStructure.ApiNews;
import com.example.newsapp.JsonStructure.ApiResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Settings extends AppCompatActivity implements sendDataListener{

    Spinner spinner;
    Button button;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.progress_dialog_box);

        if(dialog.getWindow()!=null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        button = findViewById(R.id.spinner_button);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.country_codes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               String s =  spinner.getSelectedItem().toString();

              int length = s.length();

              String sub = s.substring(length-3,length-1);

              button.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      dialog.show();
                      changeCountry(sub);
                      startActivity(new Intent(Settings.this,MainActivity.class));

                  }
              });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public void changeCountry(String country_code) {

        RequestManager.country_code = country_code;
        RequestManager manager = new RequestManager(this);
        manager.getNewsResponse(listener,"entertainment",null);
        dialog.dismiss();
        Toast.makeText(this,"Country changed!!",Toast.LENGTH_SHORT).show();

    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(List<ApiNews> arr, String message) {

            if(arr.isEmpty()){
                Toast.makeText(Settings.this, "Error", Toast.LENGTH_SHORT).show();
                //  dialog.dismiss();
            }
            else {

                MainActivity.callDisplayFunction(arr);

            }
        }

        @Override
        public void onError(String message) {


            Toast.makeText(Settings.this, "Please Check your Internet Connection!!", Toast.LENGTH_SHORT).show();
            // dialog.dismiss();

        }
    };

}