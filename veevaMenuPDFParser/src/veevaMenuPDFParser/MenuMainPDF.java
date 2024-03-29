package veevaMenuPDFParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;


public class MenuMainPDF {
	
	private final static String longDashString = "------------------------------------------------------------------------------------------------------------------------";

	public static void printUserMenu()
	{
		//Don't forget you can download more veeva menus weekely at https://www.veeva.com/meet-veeva/careers/ => View menu
		
        System.out.println("\nMost Common Ingredients:\n");

        IngredientsCount.entrySet().stream()
        .sorted(Map.Entry.comparingByValue())
        .forEach(System.out::println);
        
        /*IngredientsCount.entrySet().stream()
        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
        .forEach(System.out::println);*/

        System.out.println(longDashString);
        
        printRecipeInfo("Tandoori Chicken");
        printRandomRecipeInfo();
        
		Set<String> yourIngredientList = new HashSet<>();
		
		//yourIngredientList.add("Salt");
		//yourIngredientList.add("Pepper");
		//yourIngredientList.add("Lime");
		yourIngredientList.add("Garlic");
		yourIngredientList.add("Olive Oil");
		
	    //printRecipesThatContainIngredients(yourIngredientList);
	    printRecipesThatContainAllIngredients(yourIngredientList);
	}
	
	static Map<String,Integer> IngredientsCount = new HashMap<>();
	static Map<String,Recipe> recipeAndIngredients = new HashMap<>();
    static List<String> ingredientsListArray = new ArrayList<String>();
	//actually, make a recipe Object:, with a boolean isvegetarain, which checks if a recipe is vegetarian or not.

    //O(1) efficient
    public static void printRecipeInfo(String ... recipes)
    {
    	for(String recipeName : recipes)
    	{
    		System.out.println("Recipe Name: " + recipeName);

    		if(recipeAndIngredients.get(recipeName) != null)
    		{
	    		System.out.println("Recipe Type: " + recipeAndIngredients.get(recipeName).type);//meat main, vegetarian main, vegetarian side,
	    		System.out.print("Recipe Ingredient List: ");//meat main, vegetarian main, vegetarian side,
	    		
	    		for(String ingredient : recipeAndIngredients.get(recipeName).Ingredients)
	    		{
	    			System.out.print(ingredient+",");
	    		}
	    		System.out.println();
	    		System.out.println(longDashString);
    		}
    	}
    }
    
    public static void printRandomRecipeInfo()
    {

			Random     random    = new Random();
			List<String> keys      = new ArrayList<String>(recipeAndIngredients.keySet());
			String      recipeName  = keys.get( random.nextInt(keys.size()) );
    		    	
    		System.out.println("Recipe Name: " + recipeName);

    		if(recipeAndIngredients.get(recipeName) != null)
    		{
	    		System.out.println("Recipe Type: " + recipeAndIngredients.get(recipeName).type);//meat main, vegetarian main, vegetarian side,
	    		System.out.print("Recipe Ingredient List: ");//meat main, vegetarian main, vegetarian side,
	    		
	    		for(String ingredient : recipeAndIngredients.get(recipeName).Ingredients)
	    		{
	    			System.out.print(ingredient+",");
	    		}
	    		System.out.println();
	    		System.out.println(longDashString);
    		}
    }
    
    
    //rank and print recipes that have the least amoutn of ingredients missing at the top
    public static void printRecipesThatContainIngredients(Set<String> ingredients)
    {
    	
    	Map<Integer,ArrayList<String>> recipesWithLeastAmountOfIngredients = new TreeMap<>();
    	
    	for(Map.Entry<String, Recipe> recipes : recipeAndIngredients.entrySet())
    	{
    		for(String listIngredient : ingredients)
    		{
    			if(recipes.getValue().Ingredients.contains(listIngredient))
	    		{
    	    		if(recipeAndIngredients.get(recipes.getKey()) != null)
    	    		{

    		    		Set<String> recipeIngredientsClone = new HashSet<>(recipeAndIngredients.get(recipes.getKey()).Ingredients);
    		    		recipeIngredientsClone.removeAll(ingredients);
    		    		
    	    			ArrayList<String> recipeNames = recipesWithLeastAmountOfIngredients.getOrDefault(recipeIngredientsClone.size(), new ArrayList<String>());
    	    			recipeNames.add(recipes.getKey());
    	    			recipesWithLeastAmountOfIngredients.put(recipeIngredientsClone.size(),recipeNames);
    	    		}
    	    		break;
	    		} 	
    		}
    	}
    	
    	Map<Integer,ArrayList<String>> recipesWithLeastAmountOfIngredientsReversed = new TreeMap<>(Collections.reverseOrder());
    	recipesWithLeastAmountOfIngredientsReversed.putAll(recipesWithLeastAmountOfIngredients);
    	
    	for(Map.Entry<Integer,ArrayList<String>> recipes : recipesWithLeastAmountOfIngredientsReversed.entrySet())
    	{
    		for(String recipeName : recipes.getValue())
    		{
	    		System.out.println("Recipe Name: " + recipeName);
	
	    		if(recipeAndIngredients.get(recipeName) != null)
	    		{
		    		System.out.println("Recipe Type: " + recipeAndIngredients.get(recipeName).type);//meat main, vegetarian main, vegetarian side,
		    		System.out.print("Recipe Ingredient List: ");//meat main, vegetarian main, vegetarian side,
		    		
		    		for(String ingredient : recipeAndIngredients.get(recipeName).Ingredients)
		    		{
		    			System.out.print(ingredient+",");
		    		}
		    		System.out.println();
		    		
		    		Set<String> recipeIngredientsClone = new HashSet<>(recipeAndIngredients.get(recipeName).Ingredients);
		    		
		    		recipeIngredientsClone.removeAll(ingredients);
		    		
		    		System.out.print("Missing Ingredients: " + recipeIngredientsClone.size() + "/" + recipeAndIngredients.get(recipeName).Ingredients.size() + " (" + (int)((recipeIngredientsClone.size()/(float)recipeAndIngredients.get(recipeName).Ingredients.size())*100.0) + "%)" + " ");
	
		    		for(String ingredient : recipeIngredientsClone)
		    		{
		    			System.out.print(ingredient+",");
		    		}
		    		System.out.println();
		    		System.out.println(longDashString);
	    		}
    		}
    	}
    }
    

    
    public static void printRecipesThatContainAllIngredients(Set<String> ingredients)
    {
    	Map<Integer,ArrayList<String>> recipesWithLeastAmountOfIngredients = new TreeMap<>();
    	
    	for(Map.Entry<String, Recipe> recipes : recipeAndIngredients.entrySet())
    	{
    			if(recipes.getValue().Ingredients.containsAll(ingredients))
	    		{
    	    		if(recipeAndIngredients.get(recipes.getKey()) != null)
    	    		{

    		    		Set<String> recipeIngredientsClone = new HashSet<>(recipeAndIngredients.get(recipes.getKey()).Ingredients);
    		    		recipeIngredientsClone.removeAll(ingredients);
    		    		
    	    			ArrayList<String> recipeNames = recipesWithLeastAmountOfIngredients.getOrDefault(recipeIngredientsClone.size(), new ArrayList<String>());
    	    			recipeNames.add(recipes.getKey());
    	    			recipesWithLeastAmountOfIngredients.put(recipeIngredientsClone.size(),recipeNames);
    	    		}
	    		} 	
    	}
    	
    	Map<Integer,ArrayList<String>> recipesWithLeastAmountOfIngredientsReversed = new TreeMap<>(Collections.reverseOrder());
    	recipesWithLeastAmountOfIngredientsReversed.putAll(recipesWithLeastAmountOfIngredients);
    	
    	for(Map.Entry<Integer,ArrayList<String>> recipes : recipesWithLeastAmountOfIngredientsReversed.entrySet())
    	{
    		for(String recipeName : recipes.getValue())
    		{
	    		System.out.println("Recipe Name: " + recipeName);
	
	    		if(recipeAndIngredients.get(recipeName) != null)
	    		{
		    		System.out.println("Recipe Type: " + recipeAndIngredients.get(recipeName).type);//meat main, vegetarian main, vegetarian side,
		    		System.out.print("Recipe Ingredient List: ");//meat main, vegetarian main, vegetarian side,
		    		
		    		for(String ingredient : recipeAndIngredients.get(recipeName).Ingredients)
		    		{
		    			System.out.print(ingredient+",");
		    		}
		    		System.out.println();
		    		
		    		Set<String> recipeIngredientsClone = new HashSet<>(recipeAndIngredients.get(recipeName).Ingredients);
		    		
		    		recipeIngredientsClone.removeAll(ingredients);
		    		
		    		System.out.print("Missing Ingredients: " + recipeIngredientsClone.size() + "/" + recipeAndIngredients.get(recipeName).Ingredients.size() + " (" + (int)((recipeIngredientsClone.size()/(float)recipeAndIngredients.get(recipeName).Ingredients.size())*100.0) + "%)" + " ");
	
		    		for(String ingredient : recipeIngredientsClone)
		    		{
		    			System.out.print(ingredient+",");
		    		}
		    		System.out.println();
		    		System.out.println(longDashString);
	    		}
    		}
    	}
    }
    
	public static void main(String[] args) 
	{
			//1) DONE parse PDF files, splitting the bold "recipes", and "Ingredients"
			//2) DONE global Ingredient count HashMap,
			//3) DONE recipeToIngredients HashMap (1) String Name, 2) Set
			//4) DONE printRecipeInfo() function (access the recipe by map) -> Prints out Name, meat main or vegetarian main or side.
			//5) DONE GetRecipesThatContainIngredients //(can pre-count on initialization? NO, have to count all)
			//6) DONE, just add files as needed Iterate though all files in the directory, not just the one pdf file...
			//7) DONE add spacing for dishes with newline separators, i.e.  Grilled FlankSteak should be => Grilled Flank Steak (et. al)
			//8) DONE Add meat main course, vegetarian main course, vegetarian side dish to Type vegetarian options, and cleanup the text i.e. mon tues weds SUccess, but sometimes parser doesnt work, and sometimes parser doesn't display full list, like in the case of Sugar?	
			//9) DONE Push to github + add readme! To download more veeva menus, please go to this webpage.
		
			List<String> filenames = new ArrayList<>();


			File folder = new File("../veevaMenuPDFParser/veevaweeklymenus/");
			File[] listOfFiles = folder.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) {
				  	if(listOfFiles[i].getName().contains(".pdf"))
				  	{
				  		//System.out.println(listOfFiles[i].getPath());
				  		filenames.add(listOfFiles[i].getPath());
				  	}
			  }
			}
			
			//filenames.add("../veevaMenuPDFParser/veevaweeklymenus/Menu-Week-of-01.14-01.18.pdf");
			//filenames.add("../veevaMenuPDFParser/veevaweeklymenus/Menu-Week-of-01.28-02.01.pdf");
			//filenames.add("../veevaMenuPDFParser/veevaweeklymenus/Menu-Week-of-02.11-02.15.pdf");
			
			for(String filename : filenames)
			{
				ingredientsListArray.clear();
				//NOT THREAD SAFE, DO INGREDIENTS LIST CALCULATION AFTER.
				parsePDF(filename);
				
				String currentRecipe = "";
				String LastRecentRecipe = "";
				String lastKnownRecipeType = "unknown";
				
				boolean StartOfRecipe = true;
	            
	            for(String s : ingredientsListArray)
	            {
	            	s = s.replaceAll("-", "");
	            	s = s.trim();
	            	
	            	if(!s.equals(""))
	            	{
	            		//System.out.println(ingredientsList[j]);
	            		if(s.contains("<"))
	            		{
	            			currentRecipe += currentRecipe + s.replaceAll("<", "").trim();
	            			StartOfRecipe = true;

	            			if(s.contains(">"))
	                		{
	                			currentRecipe = currentRecipe.replaceAll(">", "").trim();
	                			StartOfRecipe = false;

		            			LastRecentRecipe = currentRecipe.trim();
		            			currentRecipe = "";
	                		}
	            			//System.out.println(currentRecipe);
	            		}
	            		else if(s.contains(">"))
	            		{
	            			//System.out.println(ingredientsList[j]);
	            			currentRecipe += " " + s.replaceAll(">", "").trim();
	            			StartOfRecipe = false;
	            			LastRecentRecipe = currentRecipe.trim();
	            			currentRecipe = "";
	            			
	            			//this if-else block may need to be moved to beneath the LONe else statement blow
	            			if(LastRecentRecipe.contains("Menu Monday Tuesday Wednesday Thursday Friday Meat Fish and Seafood Mains "))
	            			{
	            				LastRecentRecipe = LastRecentRecipe.replaceAll("Menu Monday Tuesday Wednesday Thursday Friday Meat Fish and Seafood Mains ", "");
	            				lastKnownRecipeType = "Meat Main Course";
	            			}
	            			else if(LastRecentRecipe.contains("Vegetable Mains "))
	            			{
	            				LastRecentRecipe = LastRecentRecipe.replaceAll("Vegetable Mains ", "");
	            				lastKnownRecipeType = "Vegetarian Main Course";
	            			}
	            			else if(LastRecentRecipe.contains("Vegetable Side "))
	            			{
	            				LastRecentRecipe = LastRecentRecipe.replaceAll("Vegetable Side ", "");
	            				lastKnownRecipeType = "Vegetarian Side dish";
	            			}
	            			
	            			//System.out.println(currentRecipe); 
	            			
	            		}
	            		else if(StartOfRecipe)
	            		{
	            			currentRecipe += " " + s.replaceAll(">", "").trim();
	            			//System.out.println(ingredientsList[j]);
	            		}
	            		else
	            		{	  
	            			//System.out.println(LastRecentRecipe);
	            			Recipe recipe = recipeAndIngredients.getOrDefault(LastRecentRecipe, new Recipe());
	            			recipe.type = lastKnownRecipeType; // VEGETARIAN OR MEAT
	            			recipe.Ingredients.add(s);
	            			recipeAndIngredients.put(LastRecentRecipe, recipe);
	            			IngredientsCount.put(s,IngredientsCount.getOrDefault(s,0)+1);
	            		}
	            		
	            		
	            	}
	            }
			}
			
			printUserMenu();
	}
	
	public static void parsePDF(String fileName) {

	    //PDFParser parser = null;
	    PDDocument pdDoc = null;
	    COSDocument cosDoc = null;
	    PDFTextStripper pdfStripper;

	    String parsedText;
	    File file = new File(fileName);
	    try { 
	    	

	        //parser = new PDFParser( new FileInputStream(file));
	        //parser.parse();
	        //cosDoc = parser.getDocument();
	    	
	        pdfStripper = new PDFTextStripper() {
	            String prevBaseFont = "";
                
                boolean startOfIngredients = false;

	            protected void writeString(String text, List<TextPosition> textPositions) throws IOException
	            {
	                StringBuilder builder = new StringBuilder();
	                StringBuilder ingredients = new StringBuilder();

	    	        String currentRecipe = "";
	    	        boolean StartOfRecipe = true;
	    	    	
	                
	                for (TextPosition position : textPositions)
	                {
	                    String baseFont = position.getFont().getName();
	                    if (baseFont != null && !baseFont.equals(prevBaseFont))
	                    {
	                        builder.append('[').append(baseFont).append(']');
	                        prevBaseFont = baseFont;
	                    }
	                    
	                    if( prevBaseFont.equals("Arial-BoldMT") )
	                    {
	                    	if(!startOfIngredients && position.getUnicode() != " ")
	                    	{
	                    		startOfIngredients = true;	
	                    		ingredients.append("<");
	                    		//System.out.println( position.getUnicode() );
	                    	}
	                    	ingredients.append(position.getUnicode());
	                    }
	                    else if( prevBaseFont.equals("ArialMT"))
	                    {
	                    	//System.out.println(position.getUnicode());
	                    	if(startOfIngredients && position.getUnicode() != " " && position.getUnicode() != ",")
	                    	{
	                    		startOfIngredients = false;	
	                    		ingredients.append(">");
	                    	}
	                    	//System.out.print(position.getUnicode());
	                    	ingredients.append(position.getUnicode());
	                    }
	                    
	                    builder.append(position.getUnicode());
	                }

	                writeString(builder.toString());

	                String wholeIngredients = ingredients.toString();
	                
	                String[] ingredientsList = wholeIngredients.split(",");
	                for(int j = 0; j < ingredientsList.length; j++)
	                {
	                	ingredientsListArray.add(ingredientsList[j]);
	                }

	                /*for(int j = 0; j < ingredientsList.length; j++)
	                {
                		System.out.println(ingredientsList[j]);

	                }*/

	                
	                
	            }
	        };
	        
	        pdDoc = PDDocument.load(new File(fileName));
	        parsedText = pdfStripper.getText(pdDoc);
	        
	        //System.out.println(parsedText);
	        
	        /*for(Map.Entry<String,Integer> entry : IngredientsCount.entrySet())
	        {
	        	System.out.println(entry);
	        }*/

            pdDoc.close();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        try {
	            if (pdDoc != null)
	                pdDoc.close();
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        } 
	    }
	}

}
