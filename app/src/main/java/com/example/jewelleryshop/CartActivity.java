package com.example.jewelleryshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Item> cartItems;
    CartAdapter cartAdapter;
    TextView totalPriceTextView;
    Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        checkoutButton = findViewById(R.id.checkoutButton);

        // Initialize views
        recyclerView = findViewById(R.id.cartRecyclerView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);

        // Get cart items from CartManager
        cartItems = CartManager.getInstance().getCartItems();

        // Initialize RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize cart adapter with cartItems
        cartAdapter = new CartAdapter(this, cartItems);

        // Set adapter to RecyclerView
        recyclerView.setAdapter(cartAdapter);

        // Calculate and display total price
        calculateTotalPrice();

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the checkout process and pass the total price as an extra
                Intent checkoutIntent = new Intent(CartActivity.this, Checkout.class);
                checkoutIntent.putExtra("totalPrice", totalPriceTextView.getText().toString());
                startActivity(checkoutIntent);
            }
        });
    }

    // Method to calculate total price of items in the cart
    private void calculateTotalPrice() {
        long totalPrice = 0;
        for (Item item : cartItems) {
            totalPrice += item.getPrice() * item.getQuantity(); // Multiply price by quantity
        }
        totalPriceTextView.setText("Total Price: $" + totalPrice); // Display total price in dollars
    }
}
