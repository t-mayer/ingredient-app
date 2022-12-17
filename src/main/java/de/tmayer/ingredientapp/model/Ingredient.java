package de.tmayer.ingredientapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_INGREDIENT")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COL_ID")
    private Integer id;

    @Column(name = "COL_INGREDIENT_NAME")
    private String ingredientName;

    @Column(name = "COL_INGREDIENT_AMOUNT")
    private String ingredientAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COL_RECIPE_ID")
    private Recipe recipe;

    public Integer getId () {
        return id;
    }

    public void setId ( Integer id ) {
        this.id = id;
    }

    public String getIngredientName () {
        return ingredientName;
    }

    public void setIngredientName ( String ingredientName ) {
        this.ingredientName = ingredientName;
    }

    public String getIngredientAmount () {
        return ingredientAmount;
    }

    public void setIngredientAmount ( String ingredientAmount ) {
        this.ingredientAmount = ingredientAmount;
    }

    public Recipe getRecipe () {
        return recipe;
    }

    public void setRecipe ( Recipe recipe ) {
        this.recipe = recipe;
    }

    @Override
    public String toString () {
        return "Ingredient{" +
                "id=" + id +
                ", ingredientName='" + ingredientName + '\'' +
                ", ingredientAmount='" + ingredientAmount + '\'' +
                ", recipe=" + recipe +
                '}';
    }
}
