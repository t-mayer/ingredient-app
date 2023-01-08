package de.tmayer.ingredientapp.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import de.tmayer.ingredientapp.model.Recipe;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    List<Recipe> findByIngredientsIngredientNameContainingIgnoreCaseOrderByRecipeNameAsc( String ingredientName);

}