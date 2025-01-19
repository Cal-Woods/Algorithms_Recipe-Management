package business;

import utils.Helpers;

/**
 *
 * @author michelle
 */
public class RecipeBook {
    private Recipe [] recipes;
    private int numRecipes;

    public RecipeBook(Recipe[] recipes) {
        this.recipes = recipes;
        // Assumes the array is full
        numRecipes = recipes.length;
    }
    
    // To be used where the array is not full, i.e. there are empty slots
    public RecipeBook(Recipe[] recipes, int numRecipes) {
        this.recipes = recipes;
        this.numRecipes = numRecipes;
    }
    
    public Recipe[] searchByIngredient(String ingredient){
        // Validation
        if(!Helpers.isValidString(ingredient))
            return null;

        //Create a Recipe[] to store Recipe objects that contain a specific ingredient
        Recipe[] ingredientMatches = new Recipe[0];

        //Initialise for loop to search recipes
        for (int i = 0; i < recipes.length; i++) {
            if(recipes[i].containsIngredient(ingredient)) {
            //Use resizeAddFilteredArray()
            ingredientMatches = resizeAddFilteredArray(ingredientMatches, recipes[i]);
            }
        }
        
        return ingredientMatches;
    }
    
    public Recipe[] getAllRecipes() {
        //Create a Recipe[] to store a copy of recipes
        Recipe[] recipesCopy = recipes.clone();
        return recipesCopy;
    }
    
    public Recipe findMostPopular(){
        // TODO: Add logic to find the most popular Recipe
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public void sortRecipesByMeal() {
        if (recipes == null){
            throw new IllegalArgumentException("recipes array must NOT be null.");
        }
        if (recipes.length <= 1){
            return;
        }

        //Implement the BubbleSort algorithm
        //Initialise for loop
        for(int eleCount = 0; eleCount < recipes.length; eleCount++) {
            //Create boolean swapped & set to false
            boolean swapped = false;

            //Initialise nested for loop to recipes.length-1-eleCount
            for (int i = 0; i < recipes.length-1-eleCount; i++) {
                //Check if recipes[i] . recipes[i+1]
                if(recipes[i].getMeal().compareToIgnoreCase(recipes[i+1].getMeal()) > 0) {
                    Recipe temp = recipes[i+1];
                    recipes[i+1] = recipes[i];
                    recipes[i] = temp;

                    //Set swapped to true
                    swapped = true;
                }
            }

            if(!swapped)
                break;
        }
    }

    public String findMostCommonMeal(){
        // TODO: Add logic to find the most common meal
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Recipe[] getRecipesByMeal(String meal) {
        if(!Helpers.isValidString(meal)){
            return new Recipe[0];
        }

        //SETUP
        //Call sortRecipesByMeal() method to sort array of Recipe objects
        sortRecipesByMeal();

        Recipe[] filtered = new Recipe[1];

        //LOGIC
        int start = 0;
        int end = recipes.length-1;

        while (start <= end) {
            int mid = start + (end-start)/2;

            if (recipes[mid].getMeal().equalsIgnoreCase(meal)) {
                filtered = resizeAddFilteredArray(filtered, recipes[mid]);
                return filtered;
            }
            else if(recipes[mid].getMeal().compareToIgnoreCase(meal) < 0) {
                start = mid+1;
            }

            else if(recipes[mid].getMeal().compareToIgnoreCase(meal) > 0) {
                end = mid-1;
            }
        }

        return filtered;
    }


    //Helper methods
    /** 
     * Grows a given array by 1.
     * 
     * @param original The given Recipe array
     * 
     * @return A new bigger array with an empty space at the last index.
     * 
     * @author Cal Woods
     * 
     * @see Note Only intended for use in getRecipesByMeal() method
     * 
    */
    private Recipe[] resizeAddFilteredArray(Recipe[] original, Recipe toAdd) {
        //Create a new Recipe array of size 1
        Recipe[] filtered = new Recipe[original.length+1];

        //Store all values from original array in filtered with a for loop
        for (int i = 0; i < original.length; i++) {
            filtered[i] = original[i];
        }

        //Place toAdd in filtered
        filtered[filtered.length-1] = toAdd;
        return filtered;
    }
}
