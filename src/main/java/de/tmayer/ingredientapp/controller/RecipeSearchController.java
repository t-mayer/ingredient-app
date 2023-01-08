package de.tmayer.ingredientapp.controller;

import de.tmayer.ingredientapp.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import de.tmayer.ingredientapp.model.Recipe;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Controller
public class RecipeSearchController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/search")
    public ModelAndView searchRecipes( @RequestParam(value="q") List<String> queries, Pageable pageable) {

        // Create new model and view for result page.
        ModelAndView mav = new ModelAndView ();

        // Get recipes by query, get intersections.
        List<Recipe> searchResults = new ArrayList<> ();
        for(String query : queries) {
            List<Recipe> searchResultRecipes = recipeService.findAllRecipesByIngredientName(query);
            if(searchResults.isEmpty ()) {
                searchResults.addAll (searchResultRecipes);
            } else {
                searchResults.retainAll (searchResultRecipes);
            }
        }

        // Set up pagination using PageImpl and subList.
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), searchResults.size());
        Page<Recipe> searchResultsPage = new PageImpl<>(searchResults.subList(start, end), pageable, searchResults.size());
        long totalElems = searchResults.size ();
        int totalPages = searchResultsPage.getTotalPages ();
        int currentPage = searchResultsPage.getNumber() + 1; // Start from 1.

        // Remove unnecessary commas in query.
        List<String> queriesList = new ArrayList<>();

        for (String query : queries) {
            queriesList.add(query.replaceAll(",", ""));
        }
        // Random recipe link in nav bar.
        Random r = new Random ();
        int result = r.nextInt(1000-10) + 10;

        // Pass necessary objects to template.
        mav.addObject("queries", queriesList);
        mav.addObject("searchResultRecipes", searchResultsPage);
        mav.addObject("totalElems", totalElems);
        mav.addObject("totalPages", totalPages);
        mav.addObject("currentPage", currentPage);
        mav.addObject ("randomRecipe", result);
        mav.setViewName("search-results");
        return mav;
    }
}
