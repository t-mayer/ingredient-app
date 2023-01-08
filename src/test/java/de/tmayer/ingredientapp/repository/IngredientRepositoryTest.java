package de.tmayer.ingredientapp.repository;

import de.tmayer.ingredientapp.model.Ingredient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
class IngredientRepositoryTest {

    @Autowired
    private IngredientRepository ingredientRepositoryUnderTest;

    @AfterEach
    void tearDown() {
        ingredientRepositoryUnderTest.deleteAll ();
    }

    @Test
    void itShouldFindDistinctByNameContaining () {

        // Given.
        String exampleIngredientName = "Tofuwürstchen";
        Ingredient ingredient = new Ingredient ();
        ingredient.setIngredientName (exampleIngredientName);
        ingredientRepositoryUnderTest.save (ingredient);

        // When.
        List<String> exists = ingredientRepositoryUnderTest.findDistinctByNameContaining ("tofu");

        // Then.
        assertFalse(exists.isEmpty());
    }

    @Test
    void itShouldNotFindDistinctByNameContaining () {

        // Given.
        String toFind = "tofu";

        // When.
        List<String> exists = ingredientRepositoryUnderTest.findDistinctByNameContaining (toFind);

        // Then.
        assertThat(exists.isEmpty());
    }
}