package de.tmayer.ingredientapp.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@ControllerAdvice
public class RecipeControllerAdvice {

    @ExceptionHandler
    public ModelAndView handleException(Exception ex) {
        ModelAndView mav = new ModelAndView ();

        // Random recipe link in nav bar.
        Random r = new Random ();
        int result = r.nextInt(1000-10) + 10;
        mav.addObject ("randomRecipe", result);
        mav.setViewName("error");
        return mav;
    }
}