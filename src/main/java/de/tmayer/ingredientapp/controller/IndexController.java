package de.tmayer.ingredientapp.controller;

import de.tmayer.ingredientapp.model.Ingredient;
import de.tmayer.ingredientapp.model.Recipe;
import de.tmayer.ingredientapp.repository.RecipeRepository;
import de.tmayer.ingredientapp.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
public class IndexController {
    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    IngredientService ingredientService;

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView ();

        // Random recipe on main page.
        Random r = new Random ();
        int result = r.nextInt(1000-10) + 10;

        mav.addObject ("randomRecipe", result);
        mav.setViewName("index");
        return mav;
    }

    @GetMapping("/all-recipes")
    public ModelAndView allRecipes( Pageable pageable ){
        ModelAndView mav = new ModelAndView ();
        List<Recipe> allRecipes = recipeRepository.findAll();

        // Set up pagination using PageImpl and subList.
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allRecipes.size());
        Page<Recipe> allRecipesPage = new PageImpl<> (allRecipes.subList(start, end), pageable, allRecipes.size());
        long totalElems = allRecipes.size ();
        int totalPages = allRecipesPage.getTotalPages ();
        int currentPage = allRecipesPage.getNumber() + 1; // Start from 1.

        // Pass all recipes to template.
        mav.addObject("recipeList", allRecipesPage);
        mav.addObject("totalElems", totalElems);
        mav.addObject("totalPages", totalPages);
        mav.addObject("currentPage", currentPage);
        mav.setViewName("all-recipes");
        return mav;
    }

    @GetMapping("/ingredientsAutocomplete")
    @ResponseBody
    public List<String> ingredientsAutocomplete(@RequestParam(value="term", required = false, defaultValue = "") String term){
        List<String> ingredients = ingredientService.findIngredientsByIngredientName (term);
        return ingredients;
    }
}
