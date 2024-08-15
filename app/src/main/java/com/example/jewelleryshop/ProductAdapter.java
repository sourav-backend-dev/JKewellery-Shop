package com.example.jewelleryshop;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder>{

    Context context;
    ArrayList<Item> productArrayList;

    public ProductAdapter(Context context, ArrayList<Item> productArrayList) {
        this.context = context;
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item = productArrayList.get(position);
        holder.price.setText(String.valueOf("$" +item.getPrice()));
//        holder.description.setText(item.description);
        holder.title.setText(item.getTitle());

        Log.d("ImageUrl", "ImageUrl: " + item.getImageUrl());
        Picasso.get().load(item.getImageUrl()).placeholder(R.drawable.placeholder).into(holder.imageView);

        // Set OnClickListener on itemView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open ProductDetails when itemView is clicked
                Intent intent = new Intent(context, ProductDetails.class);
                // Pass data to ProductDetails
                intent.putExtra("title", item.getTitle());
                intent.putExtra("description", item.getDescription());
                intent.putExtra("price", item.getPrice());
                intent.putExtra("imageUrl", item.getImageUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView description,price,title;
        ImageView imageView; // Add ImageView

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            title = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
