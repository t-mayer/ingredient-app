package de.tmayer.ingredientapp.repository;

import de.tmayer.ingredientapp.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;


import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    @Query("SELECT DISTINCT i.ingredientName FROM Ingredient i WHERE LOWER(i.ingredientName) LIKE LOWER((CONCAT('%', :name, '%')))")
    List<String> findDistinctByNameContaining(@Param("name") String name);

}