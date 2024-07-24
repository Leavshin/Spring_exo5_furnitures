package org.example.exo.controller;

import jakarta.validation.Valid;

import org.example.exo.entity.Furniture;
import org.example.exo.service.FurnitureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/furniture")
public class FurnitureController {

    private final FurnitureService furnitureService;

    public FurnitureController(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @GetMapping
    public String listFurniture(Model model) {
        List<Furniture> furnitureList = furnitureService.getAllFurniture();
        model.addAttribute("furnitureList", furnitureList);
        return "furniture/list";
    }

    @GetMapping("/add")
    public String addFurnitureForm(Model model) {
        model.addAttribute("furniture", new Furniture());
        return "furniture/form";
    }

    @PostMapping("/add")
    public String addFurniture(@Valid @ModelAttribute Furniture furniture, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "furniture/form";
        }
        furnitureService.saveFurniture(furniture);
        return "redirect:/furniture";
    }

    @GetMapping("/edit/{id}")
    public String editFurnitureForm(@PathVariable Long id, Model model) {
        Optional<Furniture> furniture = furnitureService.getFurnitureById(id);
        if (furniture.isPresent()) {
            model.addAttribute("furniture", furniture.get());
            return "furniture/form";
        }
        return "redirect:/furniture";
    }

    @PostMapping("/edit/{id}")
    public String updateFurniture(@PathVariable Long id, @Valid @ModelAttribute Furniture furniture, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "furniture/form";
        }
        furniture.setId(id);
        furnitureService.saveFurniture(furniture);
        return "redirect:/furniture";
    }

    @GetMapping("/delete/{id}")
    public String deleteFurniture(@PathVariable Long id) {
        furnitureService.deleteFurniture(id);
        return "redirect:/furniture";
    }
}
