package de.tmayer.ingredientapp.repository;

import de.tmayer.ingredientapp.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import de.tmayer.ingredientapp.model.Recipe;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    List<Recipe> findByIngredientsIngredientNameContainingIgnoreCase(String ingredientName);

}