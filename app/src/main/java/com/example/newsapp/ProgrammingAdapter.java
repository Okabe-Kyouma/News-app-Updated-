package com.example.newsapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.JsonStructure.ApiNews;
import com.example.newsapp.JsonStructure.ApiResult;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.RecycleViewHolder> {

    private Context context;
    private List<ApiNews> arr;
    private SelectListener listener;
    ProgressDialog dialog;

    public ProgrammingAdapter(Context context, List<ApiNews> arr,SelectListener listener){
        this.context = context;
        this.arr = arr;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycle_view,parent,false);

        return new RecycleViewHolder(view);
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder,int position) {

        dialog = new ProgressDialog(context);


        holder.text_title.setText(arr.get(position).getTitle());
        holder.text_source.setText(arr.get(position).getSource().getName());

        if(arr.get(position).getUrlToImage()!=null)
            Picasso.get().load(arr.get(position).getUrlToImage()).into(holder.news_image);

                holder.news_image.setBackgroundDrawable(new ColorDrawable(0));


        holder.card_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.setTitle("Opening article");

                int poSi = holder.getAdapterPosition();

                   listener.onNewsClicked(arr.get(poSi));

            }
        });

    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public static class RecycleViewHolder extends RecyclerView.ViewHolder{

        public TextView text_title,text_source;
        public ImageView news_image;
        public CardView card_View;




        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            text_title = itemView.findViewById(R.id.text_title);
            text_source = itemView.findViewById(R.id.text_source);
           news_image = itemView.findViewById(R.id.news_image);
            card_View = itemView.findViewById(R.id.card_view);

        }
    }

}
