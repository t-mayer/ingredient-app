package de.tmayer.ingredientapp.controller;

import de.tmayer.ingredientapp.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import de.tmayer.ingredientapp.model.Recipe;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
public class RecipeSearchController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/search")
    public ModelAndView searchRecipes(@RequestParam(value="q") List<String> queries) {

        // Create new model and view for result page.
        ModelAndView mav = new ModelAndView ();

        // Get recipes by query, get intersections.
        List<Recipe> searchResults = new ArrayList<Recipe>();
        for(String query : queries) {
            List<Recipe> searchResultRecipes = recipeService.findAllRecipesByIngredientName(query);
            if(searchResults.isEmpty ()) {
                searchResults.addAll (searchResultRecipes);
            } else {
                searchResults.retainAll (searchResultRecipes);
            }
        }

        // Pass necessary objects to template.
        mav.addObject("queries", queries);
        mav.addObject("searchResultRecipes", searchResults);
        mav.setViewName("search-results");
        return mav;
    }
}
