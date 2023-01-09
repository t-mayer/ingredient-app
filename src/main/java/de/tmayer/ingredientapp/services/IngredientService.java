package de.tmayer.ingredientapp.services;

import de.tmayer.ingredientapp.model.Ingredient;
import de.tmayer.ingredientapp.repository.IngredientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredientService {
    @Autowired
    IngredientRepository ingredientRepository;

    public List<String> findIngredientsByIngredientName(String ingredientName) {

        // Get all ingredients.
        List<String> allIngredients = ingredientRepository.findDistinctByNameContaining(ingredientName);
        return allIngredients;
    }
}
