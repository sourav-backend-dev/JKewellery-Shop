package com.example.jewelleryshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private ArrayList<Item> cartItems;

    public CartAdapter(Context context, ArrayList<Item> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Item item = cartItems.get(position);
        holder.titleTextView.setText(item.getTitle());
        holder.priceTextView.setText("$" + item.getPrice() ); // Assuming price is stored in cents or smallest currency unit
        holder.quantityTextView.setText(String.valueOf(item.getQuantity()));
        // Load and display image using Picasso
        Picasso.get().load(item.getImageUrl()).placeholder(R.drawable.placeholder).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView priceTextView;
        TextView quantityTextView;
        ImageView imageView; // Add ImageView

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.cartItemTitleTextView);
            priceTextView = itemView.findViewById(R.id.cartItemPriceTextView);
            quantityTextView = itemView.findViewById(R.id.cartItemQuantityTextView);
            imageView = itemView.findViewById(R.id.cartItemImageView); // Initialize ImageView
        }
    }
}
