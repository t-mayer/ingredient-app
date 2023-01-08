package de.tmayer.ingredientapp.services;

import de.tmayer.ingredientapp.model.Recipe;
import de.tmayer.ingredientapp.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;

    public List<Recipe> findAllRecipesByIngredientName( String ingredientName) {

        List<Recipe> foundRecipes = recipeRepository.findByIngredientsIngredientNameContainingIgnoreCase(ingredientName);
        return foundRecipes;
    }
}
