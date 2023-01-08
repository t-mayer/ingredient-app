package de.tmayer.ingredientapp.controller;

import de.tmayer.ingredientapp.model.Recipe;
import de.tmayer.ingredientapp.repository.RecipeRepository;
import de.tmayer.ingredientapp.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Controller
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/recipe/{id}")
    public ModelAndView showRecipe(@PathVariable Integer id) {

        // Create new model and view for recipe detail page.
        ModelAndView mav = new ModelAndView ();
        var recipe = recipeRepository.getReferenceById(id);

        // Random recipe link in nav bar.
        Random r = new Random ();
        int result = r.nextInt(1000-10) + 10;
        mav.addObject ("randomRecipe", result);
        mav.addObject("recipe", recipe);
        mav.setViewName("recipe");
        return mav;
    }
}
