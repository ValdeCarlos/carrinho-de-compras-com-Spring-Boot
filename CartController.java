package com.example.ecommerce.controller;

import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/")
    public String showCart(Model model) {
        List<CartItem> cartItems = cartRepository.findAll();

        // Calcular o total do carrinho
        double total = cartItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        // Aplicar desconto progressivo
        double discount = 0;
        if (total > 100) {
            discount = 0.1; // 10% de desconto para compras acima de 100
        } else if (total > 50) {
            discount = 0.05; // 5% de desconto para compras acima de 50
        }

        double totalWithDiscount = total * (1 - discount);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);
        model.addAttribute("discount", discount);
        model.addAttribute("totalWithDiscount", totalWithDiscount);

        return "cart";
    }
}