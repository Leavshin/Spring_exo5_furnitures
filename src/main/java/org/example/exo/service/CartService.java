package org.example.exo.service;

import org.example.exo.dao.CartItemRepository;
import org.example.exo.dao.FurnitureRepository;
import org.example.exo.entity.CartItem;
import org.example.exo.entity.Furniture;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final FurnitureRepository furnitureRepository;

    public CartService(CartItemRepository cartItemRepository, FurnitureRepository furnitureRepository) {
        this.cartItemRepository = cartItemRepository;
        this.furnitureRepository = furnitureRepository;
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public void addToCart(Long furnitureId, int quantity) {
        Furniture furniture = furnitureRepository.findById(furnitureId).orElseThrow(() -> new RuntimeException("Furniture not found"));
        CartItem existingCartItem = cartItemRepository.findByFurnitureId(furnitureId);
        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
            cartItemRepository.save(existingCartItem);
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setFurnitureId(furnitureId);
            newCartItem.setQuantity(quantity);
            cartItemRepository.save(newCartItem);
        }
    }

    public void removeFromCart(Long id) {
        cartItemRepository.deleteById(id);
    }

    public void clearCart() {
        cartItemRepository.deleteAll();
    }
}
