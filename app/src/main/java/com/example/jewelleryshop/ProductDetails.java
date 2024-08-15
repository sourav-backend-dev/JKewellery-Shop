package com.example.jewelleryshop;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class ProductDetails extends AppCompatActivity {

    TextView titleTextView, descriptionTextView, priceTextView;
    ImageView imageView;
    Button addToCartButton, goToCartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        // Initialize views
        titleTextView = findViewById(R.id.titleTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        priceTextView = findViewById(R.id.priceTextView);
        imageView = findViewById(R.id.imageView);
        addToCartButton = findViewById(R.id.addToCartButton);
        goToCartButton = findViewById(R.id.goToCartButton);

        // Retrieve data passed from ProductAdapter
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String description = intent.getStringExtra("description");
            long price = intent.getLongExtra("price", 0);
            String imageUrl = intent.getStringExtra("imageUrl");

            // Set data to views
            titleTextView.setText(title);
            descriptionTextView.setText(description);
            priceTextView.setText(String.valueOf(price));

            // Load image using Picasso
            Picasso.get().load(imageUrl).placeholder(R.drawable.placeholder).into(imageView);
        }

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new Item object with details of the current product
                Item product = new Item();
                product.setTitle(titleTextView.getText().toString());
                product.setDescription(descriptionTextView.getText().toString());
                product.setPrice(Long.parseLong(priceTextView.getText().toString()));
                product.setQuantity(1); // Set default quantity
                String imageUrl = getIntent().getStringExtra("imageUrl");
                product.setImageUrl(imageUrl);

                // Add the product to the cart using CartManager
                CartManager.getInstance().addItemToCart(product);
            }
        });

        goToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to CartActivity
                Intent cartIntent = new Intent(ProductDetails.this, CartActivity.class);
                startActivity(cartIntent);
            }
        });

        // Show the back button on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu resource file
        getMenuInflater().inflate(R.menu.menu_product_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_cart) {// Navigate to CartActivity
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
