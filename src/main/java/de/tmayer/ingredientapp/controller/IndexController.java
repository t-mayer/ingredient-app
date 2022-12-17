package de.tmayer.ingredientapp.controller;

import de.tmayer.ingredientapp.model.Ingredient;
import de.tmayer.ingredientapp.model.Recipe;
import de.tmayer.ingredientapp.repository.RecipeRepository;
import de.tmayer.ingredientapp.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ModelAndView allRecipes(){
        ModelAndView mav = new ModelAndView ();
        List<Recipe> allRecipes = recipeRepository.findAll();

        // Pass all recipes to template.
        mav.addObject("recipeList", allRecipes);
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
