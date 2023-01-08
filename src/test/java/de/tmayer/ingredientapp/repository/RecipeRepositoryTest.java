package de.tmayer.ingredientapp.repository;

import de.tmayer.ingredientapp.model.Ingredient;
import de.tmayer.ingredientapp.model.Recipe;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepositoryUnderTest;
    @Autowired
    private IngredientRepository ingredientRepositoryUnderTest;

    @AfterEach
    void tearDown() {
        recipeRepositoryUnderTest.deleteAll ();
        ingredientRepositoryUnderTest.deleteAll ();
    }

    @Test
    void itShouldFindByIngredientsIngredientNameContainingIgnoreCaseOrderByRecipeNameAsc () {

        // Given: ingredients and recipe.
        String ingredientName1 = "Lauch";
        String ingredientName2 = "Honig";
        Ingredient ingredient1 = new Ingredient ();
        Ingredient ingredient2 = new Ingredient ();
        ingredient1.setIngredientName (ingredientName1);
        ingredient2.setIngredientName (ingredientName2);
        Set<Ingredient> ingredientSet = new HashSet<> ();
        ingredientSet.add (ingredient1);
        ingredientSet.add (ingredient2);

        Recipe recipe = new Recipe ();
        recipe.setRecipeName ("Honig-Lauch-Auflauf");
        recipe.setIngredients (ingredientSet);
        recipe.setUrlId ("12345");
        recipe.setRecipeUrl ("www.recipe-test.");

        ingredient1.setRecipe (recipe);
        ingredient2.setRecipe (recipe);
        ingredientRepositoryUnderTest.save (ingredient1);
        ingredientRepositoryUnderTest.save (ingredient2);
        recipeRepositoryUnderTest.save (recipe);

        // When.
        List<Recipe> exists = recipeRepositoryUnderTest.findByIngredientsIngredientNameContainingIgnoreCaseOrderByRecipeNameAsc (ingredientName1);

        // Then.
        assertFalse(exists.isEmpty());

    }

    @Test
    void itShouldNotFindByIngredientsIngredientNameContainingIgnoreCaseOrderByRecipeNameAsc () {

        // Given: ingredients and recipe.
        String ingredientNameToTest = "Tofu";
        String ingredientName1 = "Lauch";
        String ingredientName2 = "Honig";
        Ingredient ingredient1 = new Ingredient ();
        Ingredient ingredient2 = new Ingredient ();
        ingredient1.setIngredientName (ingredientName1);
        ingredient2.setIngredientName (ingredientName2);
        Set<Ingredient> ingredientSet = new HashSet<> ();
        ingredientSet.add (ingredient1);
        ingredientSet.add (ingredient2);

        Recipe recipe = new Recipe ();
        recipe.setRecipeName ("Honig-Lauch-Auflauf");
        recipe.setIngredients (ingredientSet);
        recipe.setUrlId ("12345");
        recipe.setRecipeUrl ("www.recipe-test.");

        ingredient1.setRecipe (recipe);
        ingredient2.setRecipe (recipe);
        ingredientRepositoryUnderTest.save (ingredient1);
        ingredientRepositoryUnderTest.save (ingredient2);
        recipeRepositoryUnderTest.save (recipe);

        // When.
        List<Recipe> exists = recipeRepositoryUnderTest.findByIngredientsIngredientNameContainingIgnoreCaseOrderByRecipeNameAsc (ingredientNameToTest);

        // Then.
        assertThat(exists.isEmpty());
    }
}