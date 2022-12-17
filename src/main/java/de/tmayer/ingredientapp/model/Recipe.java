package de.tmayer.ingredientapp.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TBL_RECIPE")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COL_ID")
    private Integer id;

    @Column(name = "COL_RECIPE_NAME", nullable = false)
    private String recipeName;

    @Column(name = "COL_RECIPE_URL_ID", nullable = false, unique = true)
    private String urlId;

    @Column(name = "COL_RECIPE_URL", nullable = false)
    private String recipeUrl;

    @Column(name = "COL_PORTION_SIZE")
    private Integer portionSize;

    @Column(name = "COL_RATING_STARS")
    private String ratingStars;
    @Column(name = "COL_RATING_COUNT")
    private String ratingCount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    public Integer getId () {
        return id;
    }

    public void setId ( Integer id ) {
        this.id = id;
    }

    public String getRecipeName () {
        return recipeName;
    }

    public void setRecipeName ( String recipeName ) {
        this.recipeName = recipeName;
    }

    public String getUrlId () {
        return urlId;
    }

    public void setUrlId ( String urlId ) {
        this.urlId = urlId;
    }

    public String getRecipeUrl () {
        return recipeUrl;
    }

    public void setRecipeUrl ( String recipeUrl ) {
        this.recipeUrl = recipeUrl;
    }

    public Integer getPortionSize () {
        return portionSize;
    }

    public void setPortionSize ( Integer portionSize ) {
        this.portionSize = portionSize;
    }

    public String getRatingCount () {
        return ratingCount;
    }

    public void setRatingCount ( String ratingCount ) {
        this.ratingCount = ratingCount;
    }

    public String getRatingStars () {
        return ratingStars;
    }

    public void setRatingStars ( String ratingStars ) {
        this.ratingStars = ratingStars;
    }


    public Set<Ingredient> getIngredients () {
        return ingredients;
    }

    public void setIngredients ( Set<Ingredient> ingredients ) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString () {
        return "Recipe{" +
                "id=" + id +
                ", recipeName='" + recipeName + '\'' +
                ", urlId='" + urlId + '\'' +
                ", recipeUrl='" + recipeUrl + '\'' +
                ", portionSize=" + portionSize +
                ", ratingStars=" + ratingStars +
                ", ratingCount=" + ratingCount +
                ", ingredients=" + ingredients +
                '}';
    }
}
