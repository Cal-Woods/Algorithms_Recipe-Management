package app;

import java.util.Scanner;

import business.Recipe;
import business.RecipeBook;
import utils.RecipeFileUtils;

/**
 *
 * @author michelle
 * 
 * @see CoAuthors Cal Woods; Collins Igharo
 */
public class RecipeBookApp {

    public static void main(String[] args) {
        //Instantiate Scanner for reading user input
        Scanner userInput = new Scanner(System.in);

        //Print message
        System.out.println("Welcome to the Recipe Management app!\n\nThis app reads in recipes from a file and allows users to manage & manipulate those recipes.\n");

        //Create variable boolean running & set to true
        boolean running = true;

        //Create an array of recipes
        Recipe[] recipes = RecipeFileUtils.readRecipeFile("sampleRecipeInput.txt");

        //Create RecipeBook to hold recipes
        RecipeBook book1 = new RecipeBook(recipes);

        book1.sortRecipesByMeal();
        //Initialise infinite while loop for running program repeatedly
        while(running) {
            displayMenu();
            System.out.print("Please enter a number from one of the menu options: ");
            //If userInput Scanner object contains an int
            if(userInput.hasNextInt()) {
                //Check for specific input cases
                switch (userInput.nextInt()) {
                    //If 0
                    case 0:
                        //Set running to false, ending app loop
                        running = false;

                        //End case
                        break;
                
                    case 1:
                        System.out.println("Displaying Recipes from a RecipeBook.\n");
                        displayAllRecipes(book1);
                        break;

                    case 2:
                        for (int i = 0; i < recipes.length; i++) {
                            if(recipes[i].containsIngredient("Eggs")) {
                                System.out.println("Recipe name: "+recipes[i].getName());
                            }
                        }
                        break;

                    case 3:
                        System.out.println("\nDisplaying all recipes with a specific ingredient...");
                        for (int i = 0; i < recipes.length; i++) {
                            if(recipes[i].containsIngredient("Eggs")) {
                                System.out.println("Name of recipe: "+recipes[i].getName());
                                //Initialise nested for loop to list ingredients
                                //Display ingredient
                                System.out.print("Ingredients: ");
                                for (int j = 0; j < recipes[i].getIngredients().length; j++) {
                                    System.out.print(" "+recipes[i].getIngredients()[j]);
                                }
                                System.out.println();
                            }
                        }

                        case 4:
                            
                            break;

                        case 7:
                            book1.sortRecipesByMeal();
                            System.out.println("\nRecipes are now sorted.\n ");

                            displayAllRecipes(book1);
                            break;
                    //Else
                    default:
                        System.out.println("\nBad option! Please try again!\n\n");
                        break;
                }
            }
             else {
                //Print explanation
                System.out.println("\nMenu options must be integer numbers. Please try again.");

                //Clear Scanner buffer
                userInput.nextLine();
             }
        }
    }
        //Test new recipe class methods
    //     String[] testData = {"Algernon", "artificer", "aAa", "applek", "ACNE"};

    //     Recipe recipe = new Recipe("Testing", "Cal Woods", 5, "A test", testData);

    //     //Add ingredient
    //     recipe.addIngredient("Jelly Tot");
    //     recipe.addIngredient("algernhon");

    //     System.out.println("\nNew recipe array:");
    //     for (int i = 0; i < recipe.getIngredientCount(); i++) {
    //         System.out.println(recipe.getIngredients()[i]);
    //     }
    //     System.out.println("\n"+recipe.getIngredientCount());

    //     //Test Recipe sortIngredients() method
    //     recipe.sortIngredients();

    //     displayRecipeIngredients(recipe);

    //     //Test Recipe.conntainsIngredient()
    //     contains(recipe, "acne");

    //     System.out.println("\n");

    //     //Testing removeIngredient()
    //     recipe.removeIngredient("acne");
    //     recipe.removeIngredient("Algernhon");
    //     recipe.removeIngredient("applek");

    //     //Testing Recipe.addIngredient() with Recipe.removeIngredient()
    //     recipe.addIngredient("Lemon");
    //     recipe.addIngredient("Basil");

    //     //Test Recipe.addIngredient() with duplicate value
    //     recipe.sortIngredients();

    //     System.out.println(recipe.addIngredient("Basil"));
        
    //     displayRecipeIngredients(recipe);

    // }

    // private static void displayRecipeIngredients(Recipe recipe) {
    //     System.out.println("\nSorted recipe array:");
    //     for (int i = 0; i < recipe.getIngredientCount(); i++) {
    //         System.out.println(recipe.getIngredients()[i]);
    //     }
    //     System.out.println("\n"+recipe.getIngredientCount());
    // }

    // private static void contains(Recipe recipe, String ingredient) {
    //     if(recipe.containsIngredient(ingredient)) {
    //         System.out.println("Found!");
    //     }
    //     else {
    //         System.out.println("Not found!");
    //     }
    // }

    // //Application methods
    
    // /**
    //  * Displays program menu.
    //  * @author Cal Woods
    // **/
    public static void displayMenu() {
        System.out.println("\n1)Display all recipes\n2)Display all recipes with specified ingredient\n3)Display the most popular ingredient(in the most recipes)\n4)Display the most common meal\n5)Display all recipes for a specified meal\n6)Select a recipe from the list(Adds more options)\n7)Sort recipes");
    }

    /**
     * Displays all recipes in specific format, from a given RecipeBook.
     * @param book The RecipeBook to display
     * @return Nothing
     * 
     * @author Cal Woods
     */
    public static void displayAllRecipes(RecipeBook book) {
        //Initialise for loop
        for (int i = 0; i < book.getAllRecipes().length; i++) {
            System.out.println();

            System.out.println("Author: "+book.getAllRecipes()[i].getAuthor());
            System.out.print("Ingredients: ");
            //Inner-for loop to display each Recipe's ingredient
            for (int j = 0; j < book.getAllRecipes()[i].getIngredients().length; j++) {
                System.out.print(book.getAllRecipes()[i].getIngredients()[j]+" ");
            }

            System.out.println("\nNumber of ingredients: "+book.getAllRecipes()[i].getIngredientCount()+"");
            System.out.println("Meal: "+book.getAllRecipes()[i].getMeal());
            System.out.println("Name of recipe: "+book.getAllRecipes()[i].getName());
            System.out.println("Rating: "+book.getAllRecipes()[i].getRating());

            System.out.println();
        }
    }

    /**
     * Displays all recipes that have a matching meal, in specific format, from a given Recipe[].
     * @param filtered A filtered Recipe[]
     * @param meal The name of the meal
     * @return Nothing
     * 
     * @author Cal Woods
     */
    public static void displayAllRecipes(Recipe[] filtered, String meal) {
        //Initialise for loop
        for (int i = 0; i < filtered.length; i++) {
            System.out.println();
            
            System.out.println("Author: "+filtered[i].getAuthor());
            System.out.print("Ingredients: ");
            //Inner-for loop to display each Recipe's ingredient
            for (int j = 0; j < filtered[i].getIngredients().length; j++) {
                System.out.print(filtered[i].getIngredients()[j]+" ");
            }

            System.out.println("\nNumber of ingredients: "+filtered[i].getIngredientCount()+"");
            System.out.println("Meal: "+filtered[i].getMeal());
            System.out.println("Name of recipe: "+filtered[i].getName());
            System.out.println("Rating: "+filtered[i].getRating());

            System.out.println();
        }
    }
}
