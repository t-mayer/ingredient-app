package de.tmayer.ingredientapp.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class RecipeControllerAdvice {

    @ExceptionHandler
    public ModelAndView handleException(Exception ex) {
        ModelAndView mav = new ModelAndView ();
        mav.setViewName("error");
        return mav;
    }
}