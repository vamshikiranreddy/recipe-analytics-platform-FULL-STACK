package com.securin.recipes.repository;

import com.securin.recipes.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query(value = "SELECT * FROM recipes r " +
            "WHERE (:title IS NULL OR LOWER(r.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
            "AND (:cuisine IS NULL OR LOWER(r.cuisine) = LOWER(:cuisine)) " +
            "AND (:minRating IS NULL OR r.rating >= :minRating) " +
            "AND (:maxRating IS NULL OR r.rating <= :maxRating) " +
            "AND (:maxTotalTime IS NULL OR r.total_time <= :maxTotalTime) " +
            // ✅ Calories filter using JSONB (PostgreSQL)
            "AND (:maxCalories IS NULL OR " +
            "   CAST(NULLIF(regexp_replace(r.nutrients->>'calories', '[^0-9]', '', 'g'), '') AS INTEGER) <= :maxCalories)",
            countQuery = "SELECT count(*) FROM recipes r " +
                    "WHERE (:title IS NULL OR LOWER(r.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
                    "AND (:cuisine IS NULL OR LOWER(r.cuisine) = LOWER(:cuisine)) " +
                    "AND (:minRating IS NULL OR r.rating >= :minRating) " +
                    "AND (:maxRating IS NULL OR r.rating <= :maxRating) " +
                    "AND (:maxTotalTime IS NULL OR r.total_time <= :maxTotalTime) " +
                    "AND (:maxCalories IS NULL OR " +
                    "   CAST(NULLIF(regexp_replace(r.nutrients->>'calories', '[^0-9]', '', 'g'), '') AS INTEGER) <= :maxCalories)",
            nativeQuery = true)
    Page<Recipe> searchRecipes(String title,
                               String cuisine,
                               Float minRating,
                               Float maxRating,
                               Integer maxTotalTime,
                               Integer maxCalories,
                               Pageable pageable);
}
