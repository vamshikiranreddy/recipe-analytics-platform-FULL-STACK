package com.securin.recipes.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cuisine;
    private String title;
    private Float rating;

    @Column(name = "prep_time")
    private Integer prepTime;

    @Column(name = "cook_time")
    private Integer cookTime;

    @Column(name = "total_time")
    private Integer totalTime;

    @Column(length = 1000)
    private String description;

    // ✅ JSONB fields stored as JsonNode
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private JsonNode nutrients;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private JsonNode ingredients;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private JsonNode instructions;

    private String serves;

    // Default constructor (required by JPA)
    public Recipe() {}

    // Constructor with parameters
    public Recipe(String cuisine, String title, Float rating, Integer prepTime,
                  Integer cookTime, Integer totalTime, String description,
                  JsonNode nutrients, JsonNode ingredients, JsonNode instructions, String serves) {
        this.cuisine = cuisine;
        this.title = title;
        this.rating = rating;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.totalTime = totalTime;
        this.description = description;
        this.nutrients = nutrients;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.serves = serves;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCuisine() { return cuisine; }
    public void setCuisine(String cuisine) { this.cuisine = cuisine; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Float getRating() { return rating; }
    public void setRating(Float rating) { this.rating = rating; }

    public Integer getPrepTime() { return prepTime; }
    public void setPrepTime(Integer prepTime) { this.prepTime = prepTime; }

    public Integer getCookTime() { return cookTime; }
    public void setCookTime(Integer cookTime) { this.cookTime = cookTime; }

    public Integer getTotalTime() { return totalTime; }
    public void setTotalTime(Integer totalTime) { this.totalTime = totalTime; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public JsonNode getNutrients() { return nutrients; }
    public void setNutrients(JsonNode nutrients) { this.nutrients = nutrients; }

    public JsonNode getIngredients() { return ingredients; }
    public void setIngredients(JsonNode ingredients) { this.ingredients = ingredients; }

    public JsonNode getInstructions() { return instructions; }
    public void setInstructions(JsonNode instructions) { this.instructions = instructions; }

    public String getServes() { return serves; }
    public void setServes(String serves) { this.serves = serves; }
}
