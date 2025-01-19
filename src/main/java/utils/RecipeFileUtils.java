package utils;

import business.Recipe;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author michelle
 */
public class RecipeFileUtils {
    public static Recipe [] readRecipeFile(String filename){
        ArrayList<Recipe> temp = new ArrayList();
        
        Scanner inputFile;
        try
        {
            inputFile = new Scanner(new FileReader(filename));
            while(inputFile.hasNextLine()){
                Recipe r = parseRecipe(inputFile.nextLine());
                if(r!= null){
                    temp.add(r);
                }
            }
        } catch (FileNotFoundException ex){
            // This is not the way to handle this issue in proper code!!
            // As you don't know how to recover from exceptions occurring yet, 
            // I just want you to see the error, then have the program end
            Logger.getLogger(RecipeFileUtils.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception occurred when reading from file: " + ex.getMessage());
            ex.printStackTrace();
            // We usually DO NOT WANT TO DO THIS
            System.exit(1);
        }
        Recipe [] text = new Recipe[temp.size()];
        return temp.toArray(text);
    }
    
    private static Recipe parseRecipe(String s){
        // Format: Name%%author%%rating%%meal%%ingredient1~~ingredient2~~ingredient3~~ etc
        // %% separates components of a recipe
        // ~~ separates ingredients
        Recipe r = null;
        String [] components = s.split("%%");
        if(components.length == 5){
            String name = components[0];
            String author = components[1];
            double rating;
            try{
                rating = Double.parseDouble(components[2]);
            }catch(NumberFormatException e){
                rating = 0;
            }
            String meal = components[3];
            String [] ingredientsList = components[4].split("~~");
            
            r = new Recipe(name, author, rating, meal, ingredientsList);
        }
        return r;
    }
    
    private static String formatRecipeForFile(Recipe r){
        // Format: Name%%author%%rating%%meal%%ingredient1~~ingredient2~~ingredient3~~ etc
        // %% separates components of a recipe
        // ~~ separates ingredients
        // Build start of String
        String output = r.getName()+"%%" + r.getAuthor()+"%%" + r.getRating()+"%%"+r.getMeal()+"%%";
        
        // Handle ingredients
        String [] ingredients = r.getIngredients();
        String ingredientsList;
        if(ingredients.length > 0){
            ingredientsList = ingredients[0];
            for(String s: ingredients){
                ingredientsList = ingredientsList + "~~" + s;
            }
        }else{
            ingredientsList = "";
        }
        
        output = output + ingredientsList;
        
        return output;
    }
    
    public static void main(String[] args) {
        Recipe [] recipes = readRecipeFile("sampleRecipeInput.txt");
        for(Recipe r: recipes){
            System.out.println(r);
            System.out.println("-----------------");
        }
    }
}
