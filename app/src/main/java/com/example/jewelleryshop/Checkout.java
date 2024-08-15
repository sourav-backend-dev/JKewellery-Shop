package com.example.jewelleryshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.jewelleryshop.CartManager;


import androidx.appcompat.app.AppCompatActivity;

public class Checkout extends AppCompatActivity {

    EditText editTextFirstName, editTextLastName, editTextAddress, editTextEmail, editTextPhoneNumber;
    EditText editTextCardNumber, editTextCardExpiryDate, editTextCardCVV;
    Button submitOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextCardNumber = findViewById(R.id.editTextCardNumber);
        editTextCardExpiryDate = findViewById(R.id.editTextCardExpiryDate);
        editTextCardCVV = findViewById(R.id.editTextCardCVV);
        submitOrderButton = findViewById(R.id.submitOrderButton);

        submitOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    // Handle order submission logic here
                    // For demonstration purposes, let's assume the order is submitted successfully
                    // Clear the cart
                    clearCart();

                    // Navigate to ThankYouActivity
                    startActivity(new Intent(Checkout.this, ThankYou.class));
                    finish();
                }
            }
        });
    }

    private boolean validateFields() {
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String cardNumber = editTextCardNumber.getText().toString().trim();
        String cardExpiryDate = editTextCardExpiryDate.getText().toString().trim();
        String cardCVV = editTextCardCVV.getText().toString().trim();

        if (firstName.isEmpty()) {
            editTextFirstName.setError("First Name is required");
            return false;
        }
        if (lastName.isEmpty()) {
            editTextLastName.setError("Last Name is required");
            return false;
        }
        if (address.isEmpty()) {
            editTextAddress.setError("Address is required");
            return false;
        }
        if (email.isEmpty()) {
            editTextEmail.setError("Email Address is required");
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Invalid Email Address");
            return false;
        }
        if (phoneNumber.isEmpty()) {
            editTextPhoneNumber.setError("Phone Number is required");
            return false;
        }
        if (!android.util.Patterns.PHONE.matcher(phoneNumber).matches()) {
            editTextPhoneNumber.setError("Invalid Phone Number");
            return false;
        }
        if (cardNumber.isEmpty()) {
            editTextCardNumber.setError("Card Number is required");
            return false;
        }
        if (cardNumber.length() < 13 || cardNumber.length() > 19) {
            editTextCardNumber.setError("Invalid Card Number");
            return false;
        }
        if (cardExpiryDate.isEmpty()) {
            editTextCardExpiryDate.setError("Expiry Date is required");
            return false;
        }
        if (!cardExpiryDate.matches("\\d{2}/\\d{2}")) {
            editTextCardExpiryDate.setError("Invalid Expiry Date (MM/YY)");
            return false;
        }
        if (cardCVV.isEmpty()) {
            editTextCardCVV.setError("CVV is required");
            return false;
        }
        if (cardCVV.length() != 3) {
            editTextCardCVV.setError("Invalid CVV");
            return false;
        }

        return true;
    }

    private void clearCart() {
        CartManager.getInstance().clearCart();
    }
}
