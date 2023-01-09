package de.tmayer.ingredientapp.services;

import de.tmayer.ingredientapp.model.Ingredient;
import de.tmayer.ingredientapp.model.Recipe;
import de.tmayer.ingredientapp.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {
    @InjectMocks
    private RecipeService recipeServiceUnderTest;
    @Mock
    private RecipeRepository recipeRepository;
    @BeforeEach
    void setUp() {
        recipeServiceUnderTest = new RecipeService (recipeRepository);
    }
    @Test
    void itShouldFindAllRecipesByIngredientName () {

        // Given.
        List<Recipe> recipes = new ArrayList<> ();

        // When.
        Mockito.when(recipeRepository.findByIngredientsIngredientNameContainingIgnoreCaseOrderByRecipeNameAsc (anyString ())).thenReturn(recipes);

        // Then.
        assertEquals(recipeServiceUnderTest.findAllRecipesByIngredientName (anyString()),recipes);
    }
}