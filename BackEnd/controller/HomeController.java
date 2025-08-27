package com.securin.recipes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "🍲 Welcome to the Recipe API! 🚀 Try visiting /api/recipes to explore recipes.";
    }
}
