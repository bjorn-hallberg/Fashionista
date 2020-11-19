package com.example.Fashionista;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    protected List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public int numberOfItems() {
        int count = 0;
        for (CartItem item : cartItems) {
            count += item.quantity;
        }
        return count;
    }

    public double totalAmount() {
        double totalAmount = 0;
        for (CartItem item : cartItems) {
            totalAmount += item.product.price * item.quantity;
        }
        return totalAmount;
    }

    public void addItem(Product product, int quantity) {
        boolean exists = false;

        for (CartItem item : cartItems) {
            if (item.product.id == product.id) {
                item.quantity += quantity;
                exists = true;
                break;
            }
        }

        if (!exists) {
            cartItems.add(new CartItem(product, quantity));
        }
    }

    public void removeItem(int row) {
        cartItems.remove(row);
    }

}
