package org.example.exo.service;

import org.example.exo.dao.CartItemRepository;
import org.example.exo.entity.CartItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;

    public CartService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public void addToCart(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    public void removeFromCart(Long id) {
        cartItemRepository.deleteById(id);
    }

    public void clearCart() {
        cartItemRepository.deleteAll();
    }
}
