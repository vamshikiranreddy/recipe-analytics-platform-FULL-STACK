package com.securin.recipes.controller;

import com.securin.recipes.entity.Recipe;
import com.securin.recipes.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    // ✅ Get all recipes (paginated)
    @GetMapping
    public ResponseEntity<Page<Recipe>> getAllRecipes(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("rating").descending());
        Page<Recipe> recipes = recipeService.getAllRecipes(pageable);
        return ResponseEntity.ok(recipes);
    }

    // ✅ Search recipes by filters (now includes calories)
    @GetMapping("/search")
    public ResponseEntity<Page<Recipe>> searchRecipes(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String cuisine,
            @RequestParam(required = false) Float minRating,
            @RequestParam(required = false) Float maxRating,
            @RequestParam(required = false) Integer maxTotalTime,
            @RequestParam(required = false) Integer maxCalories,   // ✅ NEW
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Recipe> recipes = recipeService.searchRecipes(
                title, cuisine, minRating, maxRating, maxTotalTime, maxCalories, pageable
        );
        return ResponseEntity.ok(recipes);
    }

    // ✅ Get recipe by ID
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
