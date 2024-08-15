package com.example.jewelleryshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ThankYou extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        Button backToProductButton = findViewById(R.id.backToProductButton);

        backToProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the Product page
                Intent intent = new Intent(ThankYou.this, Product.class);
                startActivity(intent);
                finish(); // Optional, if you don't want to keep this activity in the stack
            }
        });
    }
}
