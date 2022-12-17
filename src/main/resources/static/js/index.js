$(document).ready(function(){

    // Function to add ingredient as badge.
    var addIndex = 1;
    $("section.py-5.text-center.container").on('click', '.add-ingredient',function(){
    var ingredient = $('.add-ingredient-input').val();
    if(ingredient){
       var openingTag = '<span class="badge bg-secondary ingredient-' + addIndex + '">';
       var closingTag = '<span class="remove-ingredient-' + addIndex + '"> x </span></span>';
       $(".ingredients-list").append( openingTag + ingredient + closingTag);
       $(".search-recipes").append('<input class="input-ingredient-' + addIndex + '" type="hidden" value=' + ingredient + ' name="q"/>');
       addIndex ++;
       $('.submit-query').prop('disabled', false);
       $('.ingredients-form').trigger("reset");
    }
    return false;
    });

    // Function to remove ingredients.
    $("section.py-5.text-center.container").on('click', '[class^=remove-ingredient]', function(){
        var ingredientIndex = $(this).attr('class').split('-').pop();
        $(this).parent().remove();
        var selector = 'input.input-ingredient-' + ingredientIndex + '[type="hidden"][name="q"]';
        $(selector).remove();

        // Disable submit button if no queries present.
        var searchRecipesForm = $('.search-recipes');
        var inputIngredientsElem = $('input[class^="input-ingredient-"]');

        if (searchRecipesForm.find(inputIngredientsElem).length == 0) {
          $('.submit-query').prop('disabled', true);
        }
    });

    // Function for autocomplete.
    $(function() {
        $(".add-ingredient-input").autocomplete({
            source: "/ingredientsAutocomplete",
            minLength: 3
        });
    });
});
