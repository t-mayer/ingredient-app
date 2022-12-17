package de.tmayer.ingredientapp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "ingredient-app.initial", havingValue = "true")
public class InitialDataRunner implements CommandLineRunner {

    public final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    DataService dataService;

    @Override
    public void run ( String... args ) throws Exception {
        dataService.readFile ();
        logger.info ("Finished insert.");
    }
}
