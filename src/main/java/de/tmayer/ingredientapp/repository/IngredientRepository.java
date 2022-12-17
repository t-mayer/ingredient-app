package de.tmayer.ingredientapp.repository;

import de.tmayer.ingredientapp.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    List<Ingredient> findDistinctByIngredientNameContaining(String ingredientName);

}