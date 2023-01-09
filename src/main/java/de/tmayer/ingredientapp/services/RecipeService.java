package de.tmayer.ingredientapp.services;

import de.tmayer.ingredientapp.model.Recipe;
import de.tmayer.ingredientapp.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;
    public List<Recipe> findAllRecipesByIngredientName( String ingredientName) {

        List<Recipe> foundRecipes = recipeRepository.findByIngredientsIngredientNameContainingIgnoreCaseOrderByRecipeNameAsc(ingredientName);
        return foundRecipes;
    }
}
