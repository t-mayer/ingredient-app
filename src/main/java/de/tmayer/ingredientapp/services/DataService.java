package de.tmayer.ingredientapp.services;

import de.tmayer.ingredientapp.model.Ingredient;
import de.tmayer.ingredientapp.model.Recipe;
import de.tmayer.ingredientapp.repository.RecipeRepository;
import de.tmayer.ingredientapp.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.*;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;


@Service
public class DataService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    public void readFile() {
        try {

            // Call buffered reader.
            BufferedReader bufferedReader = new BufferedReader (new FileReader ("src/main/resources/records.json"));

            // Read in file line by line.
            String line;
            while (( line = bufferedReader.readLine () ) != null) {

                // Extract recipe information.
                JSONObject json = new JSONObject (line);
                String recipeName = json.getString ("recipe_name");
                String recipeNameDecoded = URLDecoder.decode (recipeName, StandardCharsets.UTF_8.toString ());
                String recipeUrlId = json.getString ("recipe_url_id");
                String recipeUrl = json.getString ("recipe_url");
                Integer portionSize = Integer.parseInt(json.getString ("portion_size"));
                String ratingCount = json.getString ("rating_count");
                String ratingStars = json.getString ("rating_stars");

                // Catch duplicate recipes in file.
                try {
                    Recipe recipe = new Recipe ();
                    recipe.setUrlId (recipeUrlId);
                    recipe.setRecipeName (recipeNameDecoded);
                    recipe.setRecipeUrl (recipeUrl);
                    recipe.setPortionSize (portionSize);
                    recipe.setRatingCount (ratingCount);
                    recipe.setRatingStars (ratingStars);
                    recipeRepository.save (recipe);

                    // Extract ingredient information.
                    JSONArray ingredientJsonArr = json.getJSONArray ("ingredients");
                    for (int i = 0; i < ingredientJsonArr.length (); i++) {
                        JSONObject ingredientJsonObject = ingredientJsonArr.getJSONObject(i);
                        String ingredientName = ingredientJsonObject.keys().next();
                        String ingredientAmount = ingredientJsonObject.getString (ingredientName);

                        // Insert into db.
                        Ingredient ingredient = new Ingredient ();
                        ingredient.setIngredientName(ingredientName);
                        ingredient.setIngredientAmount (ingredientAmount);
                        ingredient.setRecipe (recipe);
                        ingredientRepository.save (ingredient);
                    }
                } catch (Exception ex) {

                    // Skip recipes with information missing.
                    continue;
                }

            }
            bufferedReader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
