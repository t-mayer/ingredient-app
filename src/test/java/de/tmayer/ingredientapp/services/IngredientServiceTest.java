package de.tmayer.ingredientapp.services;

import de.tmayer.ingredientapp.model.Ingredient;
import de.tmayer.ingredientapp.repository.IngredientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class IngredientServiceTest {

    @InjectMocks
    private IngredientService ingredientServiceUnderTest;
    @Mock
    private IngredientRepository ingredientRepository;
    @BeforeEach
    void setUp() {
        ingredientServiceUnderTest = new IngredientService (ingredientRepository);
    }

    @Test
    void itShouldFindIngredientsByIngredientName () {

        // Given.
        List<String> ingredientNameList = new ArrayList<> ();

        // When.
        Mockito.when(ingredientRepository.findDistinctByNameContaining (anyString ())).thenReturn(ingredientNameList);

        // Then.
        assertEquals(ingredientServiceUnderTest.findIngredientsByIngredientName (anyString()), ingredientNameList);
    }
}