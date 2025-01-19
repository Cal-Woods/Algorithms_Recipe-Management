package business;

import utils.Helpers;

/**
 *
 * @author michelle
 */
public class Recipe {
    private String name;
    private String author;
    private String meal;
    private String [] ingredients;
    private int ingredientCount;
    private double rating;
    
    public Recipe(String name, String author, double rating, String meal, String [] ingredients) {
        this.name = name;
        this.author = author;
        this.meal = meal;
        this.rating = rating;
        this.ingredients = ingredients;
        ingredientCount = ingredients.length;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getIngredients() {
        return ingredients;
    }
    
    // You may not add the setIngredients method

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getIngredientCount() {
        return ingredientCount;
    }
    
    /**
     * Adds an ingredient to the Recipe instance array 'ingredients'
     * @param ingredient The ingredient String to be added
     * @return A boolean indicating success or failure
     * 
     * @author Cal Woods
     */
    public boolean addIngredient(String ingredient){
        //Validation
        if(!validIngredient(ingredient))
            return false;
        
        // Using containsIngredient(), check If ingredient is already present in ingredients array
        if(containsIngredient(ingredient)) {
            //Exit method with a return value of false
            return false;
        }
        
        
        //If last ingredient in ingredients is not valid
        if(!validIngredient(ingredients[getIngredientCount()-1])) {
            //Overwrite last element with given ingredient
            ingredients[getIngredientCount()-1] = ingredient;
            //Exit method with a return value of true
            return true;
        }

        //Logic
        //Use resize() to reset ingredients array with a +1 bigger String array 
        resize();

        //Increment ingredientCount
        ingredientCount++;

        //Insert given ingredient at last element in ingredients array
        ingredients[getIngredientCount()-1] = ingredient;

        return true;
    }
    
    /**
     * Removes an ingredient from the Recipe instance ingredients array.
     * @param ingredient The ingredient to remove
     * @return A boolean indicating success or failure to remove element
     * 
     * @author Collins Igharo
     * 
     * @see Edited Edited to use containsIngredient() &  methods
     */
    public boolean removeIngredient(String ingredient){
        int indexIngredient = -1;

        for (int i = 0; i < ingredients.length; i++) {
            if(ingredients[i].equalsIgnoreCase(ingredient)){
                indexIngredient = i;
            }
        }

        //Check if indexIngredients is -1
        if(indexIngredient <= -1)
            return false;

        String [] IngredientsNew = new String[ingredients.length-1];
        int tracker = 0;

            for (int j = 0; j < ingredients.length; j++) {
                if (j != indexIngredient) { // Skip the index to be removed
                    IngredientsNew[tracker] = ingredients[j];
                    tracker++; // Only increment tracker when adding to IngredientsNew
                }
            }

            ingredients = IngredientsNew;

            ingredientCount--;

            return true;
        }

    /**
     * Searches a sorted array of ingredients only, in a Recipe instance for a specific value.
     * @param ingredient The String ingredient to search
     * @return A boolean indicating if the element was found
     * 
     * @throws IllegalArgumentException Thrown by Helpers.isValidString() method, if given String is null
     */
    public boolean containsIngredient(String ingredient) {
        if(!Helpers.isValidString(ingredient)) {
            return false;
        }

        //Implement binary search algorithm
        //Create start & end integers
        int start = 0, end = ingredients.length-1;

        //Initialise for loop for eleCount
        while(start <= end) {
            //Create an Integer mid for tracking midpoint in search area
            int mid = start+(end-start)/2;

            //Check if mid = ingredient
            if(ingredients[mid].equalsIgnoreCase(ingredient))
                return true;

            else if(ingredients[mid].compareToIgnoreCase(ingredient) < 0) {
                start = mid+1;
            }

            else if(ingredients[mid].compareToIgnoreCase(ingredient) > 0) {
                end = mid-1;
            }
        }

        return false;
    }

    /**
     * Sorts ingredients of a recipe, using the selectionSort algorithm
     * 
     * @author Cal Woods
     */
    public void sortIngredients() {
        if (ingredients == null){
            throw new IllegalArgumentException("Ingredients is null");
        }
        if (ingredients.length <= 1){
            return;
        }

        //Edited by Cal Woods
        //Create startPos & endPos trackers
        int startpos = 0, endPos = getIngredientCount()-1;

        while(startpos <= endPos) {
            int minPos = startpos;
            for (int i = startpos+1; i < ingredients.length; i++) {
                if (ingredients[i].compareToIgnoreCase(ingredients[minPos])<0) {
                    minPos = i;
                }
            }
            String temp = ingredients[startpos];
            ingredients[startpos] = ingredients[minPos];
            ingredients[minPos] = temp;

            startpos++;
        }
    }


    //Private methods

    /** 
     * Grows the ingredients array by 1, retaining all elements
     * 
     * @author Cal Woods
     * 
    */
    private void resize() {
        //Create a new String array of same size & same type as ingredients array for tracking old values from ingredients array
        String[] old = new String[getIngredientCount()];

        //Store all values from ingredients array in old with a for loop
        for (int i = 0; i < ingredients.length; i++) {
            old[i] = ingredients[i];
        }

        //Reset ingredients array to the same array with 1 extra element
        ingredients = new String[getIngredientCount()+1];

        //Add all elements from old to ingredients
        for (int i = 0; i < old.length; i++) {
            ingredients[i] = old[i];
        }
    }

    /**
     * @author Cal Woods
     * @param ingredient A given String representing an ingredient
     * @return A boolean true if given ingredient is valid, false otherwise
     */
    private boolean validIngredient(String ingredient) {
        //Validation
        if(ingredient == null)
            throw new IllegalArgumentException("Given ingredient must NOT be null.");
        if(ingredient.length() <= 1)
           return false;

        //Initialise for loop to iterate over the given String
        for (int i = 0; i < ingredient.length()-1; i++) {
            //Check if characters at i & i+1 are spaces
            if(ingredient.charAt(i) == ' ' && ingredient.charAt(i+1) == ' ') {
                return false;                    
            }
        }
        return true;
    }

    /**
     * Swaps the values of two ingredients in the Recipe ingredients instance array.
     * @param startPos The position of the first element to swap
     * @param minPos The position of the second element to swap
     * 
     * @return Nothing
     */
    private void swapIngredients(int startPos, int minPos) {
        //Create temp variable of same type as arguments
        String temp = ingredients[minPos];

        //Set data2 = data1
        ingredients[minPos] = ingredients[startPos];

        //Set data1 = temp
        ingredients[startPos] = temp;
    }
}