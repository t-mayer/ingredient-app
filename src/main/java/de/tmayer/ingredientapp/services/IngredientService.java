package de.tmayer.ingredientapp.services;

import de.tmayer.ingredientapp.model.Ingredient;
import de.tmayer.ingredientapp.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    @Autowired
    IngredientRepository ingredientRepository;

    public List<Ingredient> findIngredientsByIngredientName(String ingredientName) {

        // Get all ingredients and filter.
        List<Ingredient> allIngredients = ingredientRepository.findDistinctByIngredientNameContaining(ingredientName);


        return allIngredients;
    }
}
