package org.example.exo.controller;

import org.example.exo.entity.CartItem;
import org.example.exo.entity.Furniture;
import org.example.exo.service.CartService;
import org.example.exo.service.FurnitureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final FurnitureService furnitureService;

    public CartController(CartService cartService, FurnitureService furnitureService) {
        this.cartService = cartService;
        this.furnitureService = furnitureService;
    }

    @GetMapping
    public String listCartItems(Model model) {
        List<CartItem> cartItems = cartService.getAllCartItems();
        model.addAttribute("cartItems", cartItems);
        return "cart/list";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long furnitureId, @RequestParam int quantity) {
        Optional<Furniture> furniture = furnitureService.getFurnitureById(furnitureId);
        if (furniture.isPresent()) {
            CartItem cartItem = new CartItem();
            cartItem.setFurnitureId(furnitureId);
            cartItem.setQuantity(quantity);
            cartService.addToCart(cartItem);
        }
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }
}
