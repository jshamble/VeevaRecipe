# VeevaRecipe

### Usage:

Add all files to an eclipse project and run.

The PrintUserMenu is a function that you define, which contains sample use cases (call these functions within it).
A sample of possible methods to modify is provided below

### Adding More Menu Files

 Simply add more menu files to the ArrayList in the main function.
 
 Get more updated menus every week at 
 https://www.veeva.com/meet-veeva/careers/
 
 click the view menu button at the bottom of the webpage:

  OUR HEADQUARTERS
  Based in Pleasanton, CA, nestled between San Francisco and Silicon Valley, our office features a gourmet kitchen, free healthy lunches served daily, and onsite fitness classes.
  View Menu
  
  And add the pdf file to the veevaweeklymenus folder. Or modify the fucntion below to add them in manually

			List<String> filenames = new ArrayList<>();

			filenames.add("../veevaMenuPDFParser/veevaweeklymenus/Menu-Week-of-01.14-01.18.pdf");
			filenames.add("../veevaMenuPDFParser/veevaweeklymenus/Menu-Week-of-01.28-02.01.pdf");
			filenames.add("../veevaMenuPDFParser/veevaweeklymenus/Menu-Week-of-02.11-02.15.pdf");
			


### Print out most common ingredients

    IngredientsCount.entrySet().stream()
        .sorted(Map.Entry.comparingByValue())
        .forEach(System.out::println);

### printRecipeInfo / printRandomRecipeInfo

Given a recipe name, Prints the recipe, ingredients, and dish type (meat, vegetarian, main course or side dish).
printRandomRecipeInfo does the same thing, but pritns a randomly selected recipe from the map.

    printRecipeInfo("Tandoori Chicken");
        printRandomRecipeInfo();

### printRecipesThatContainIngredients / printRecipesThatContainALLIngredients

    Given a set of ingredients,
    prints the recipes that contain that set of ingredietns, also prints the missing ingredients.

    printRecipesThatContainIngredients Recipes onlyneed to contain one of the ingredients in the
    user provided ingreidients list instead of all)
    
    printRecipesThatContainALLIngredients Recipes must have ALL of the ingredients.

		Set<String> yourIngredientsList = new HashSet<>();
		
		yourIngredientsList.add("Salt");
		yourIngredientsList.add("Pepper");
		yourIngredientsList.add("Garlic");
		yourIngredientsList.add("Lime");
		yourIngredientsList.add("Olive Oil");
		
		printRecipesThatContainIngredients(yourIngredientsList);
		printRecipesThatContainAllIngredients(yourIngredientsList);
	
