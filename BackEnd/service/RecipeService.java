package com.securin.recipes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.securin.recipes.entity.Recipe;
import com.securin.recipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Page<Recipe> getAllRecipes(Pageable pageable) {
        return recipeRepository.findAll(pageable);
    }

    // ✅ Updated to include calories filter
    public Page<Recipe> searchRecipes(String title,
                                      String cuisine,
                                      Float minRating,
                                      Float maxRating,
                                      Integer maxTotalTime,
                                      Integer maxCalories,
                                      Pageable pageable) {
        return recipeRepository.searchRecipes(
                title, cuisine, minRating, maxRating, maxTotalTime, maxCalories, pageable
        );
    }

    // ✅ For fetching a single recipe
    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    @PostConstruct
    public void importDataFromJson() {
        try {
            if (recipeRepository.count() == 0) {
                System.out.println("Loading initial recipe data...");

                ClassPathResource resource = new ClassPathResource("data/US_recipes_clean_fixed.json");

                if (!resource.exists()) {
                    System.out.println("⚠ Recipe import skipped: data/US_recipes_clean_fixed.json not found in resources.");
                    return;
                }

                String content = new String(resource.getInputStream().readAllBytes());
                ObjectMapper objectMapper = new ObjectMapper();

                List<Map<String, Object>> rawList = objectMapper.readValue(
                        content,
                        new TypeReference<List<Map<String, Object>>>() {}
                );

                List<Recipe> recipes = new ArrayList<>();

                for (Map<String, Object> raw : rawList) {
                    Recipe recipe = new Recipe();
                    recipe.setTitle((String) raw.get("title"));
                    recipe.setCuisine((String) raw.get("cuisine"));
                    recipe.setDescription((String) raw.get("description"));
                    recipe.setServes((String) raw.get("serves"));

                    // Numbers (null-safe)
                    recipe.setRating(raw.get("rating") != null ? Float.valueOf(raw.get("rating").toString()) : null);
                    recipe.setPrepTime(raw.get("prepTime") != null ? Integer.valueOf(raw.get("prepTime").toString()) : null);
                    recipe.setCookTime(raw.get("cookTime") != null ? Integer.valueOf(raw.get("cookTime").toString()) : null);
                    recipe.setTotalTime(raw.get("totalTime") != null ? Integer.valueOf(raw.get("totalTime").toString()) : null);

                    // ✅ Keep JSON nodes for nutrients/ingredients/instructions
                    recipe.setNutrients(raw.get("nutrients") != null ? objectMapper.valueToTree(raw.get("nutrients")) : null);
                    recipe.setIngredients(raw.get("ingredients") != null ? objectMapper.valueToTree(raw.get("ingredients")) : null);
                    recipe.setInstructions(raw.get("instructions") != null ? objectMapper.valueToTree(raw.get("instructions")) : null);

                    recipes.add(recipe);
                }

                recipeRepository.saveAll(recipes);
                System.out.println("✅ Data import completed. Loaded " + recipes.size() + " recipes.");
            } else {
                System.out.println("ℹ Database already contains data. Skipping import.");
            }
        } catch (Exception e) {
            System.err.println("❌ Error during data import: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
