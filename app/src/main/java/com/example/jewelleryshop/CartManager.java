package com.example.jewelleryshop;

import java.util.ArrayList;

public class CartManager {
    private static CartManager instance;
    private ArrayList<Item> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addItemToCart(Item item) {
        // Check if the item is already in the cart
        boolean itemExists = false;
        for (Item cartItem : cartItems) {
            if (cartItem.getTitle().equals(item.getTitle())) {
                // If the item exists, update the quantity
                cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
                itemExists = true;
                break;
            }
        }
        if (!itemExists) {
            cartItems.add(item);
        }
    }

    public ArrayList<Item> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
    }
}
