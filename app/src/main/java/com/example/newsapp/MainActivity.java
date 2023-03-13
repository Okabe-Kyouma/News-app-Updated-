package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.newsapp.JsonStructure.ApiNews;
import com.google.android.material.appbar.MaterialToolbar;

import java.security.Permission;
import java.security.Permissions;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener,View.OnClickListener  {

    RecyclerView recyclerView;
    ProgrammingAdapter adapter;
    Button b1,b2,b3,b4,b5,b6,b7;
    MaterialToolbar toolbar;
   public Dialog dialogBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.my_toolbar);
        toolbar = new MaterialToolbar(MainActivity.this);

        //toolbar =  new Toolbar(MainActivity.this);
        toolbar.setTitle("News");

        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar ab = getSupportActionBar();
        dialogBar = new Dialog(this);
        dialogBar.setContentView(R.layout.progress_dialog_box);
        if(dialogBar.getWindow()!=null){
            dialogBar.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        dialogBar.show();

        ab.hide();

        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);


        RequestManager manager = new RequestManager(this);
        manager.getNewsResponse(listener,"entertainment",null);

     //   cardView =  cardView.findViewById(R.id.card_view);

//        dialog = new ProgressDialog(this);
//        dialog.setTitle("Fetching All News Articles!!");
//        dialog.setContentView(R.layout.progress_dialog_box);
//        dialog.show();




    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(List<ApiNews> arr, String message) {

           if(arr.isEmpty()){
               Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    //  dialog.dismiss();
               dialogBar.dismiss();
           }
           else {
               displayList(arr);
               // dialog.dismiss();
                dialogBar.dismiss();
           }
        }

        @Override
        public void onError(String message) {


                Toast.makeText(MainActivity.this, "Please Check your Internet Connection!!", Toast.LENGTH_SHORT).show();
                dialogBar.dismiss();
                 // dialog.dismiss();

        }
    };

    public void displayList(List<ApiNews> arr) {
           recyclerView = findViewById(R.id.recycle_view);
           recyclerView.setHasFixedSize(true);
           recyclerView.setLayoutManager(new GridLayoutManager(this,1));
           adapter = new ProgrammingAdapter(this,arr,this);
           recyclerView.setAdapter(adapter);

    }

    @Override
    public void onNewsClicked(ApiNews news) {

       // dialog.setTitle("Opening Article!!");

           startActivity(new Intent(this,news_details.class)
                   .putExtra("news",news));


    }

    @Override
    public void onClick(View v) {

        Button button = (Button) v;

        String category = button.getText().toString();

        //dialog.setTitle("Fetching News Articles of "+ category);
        //dialog.show();

        dialogBar.show();

        RequestManager manager = new RequestManager(this);

        manager.getNewsResponse(listener,category,null);

    }

    public void setMenuClickListener(MenuItem item) {

        if(item.getItemId()==R.id.setting)
            startActivity(new Intent(MainActivity.this,Settings.class));

    }

    public static void callDisplayFunction(List<ApiNews> arr){



    }
}