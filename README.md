# VeevaRecipe

### Adding More Menu Files

 Simply add more menu files to the ArrayList in the main function.
 
 Get more updated menus every week at 
 https://www.veeva.com/meet-veeva/careers/
 
 click the view menu button at the bottom of the webpage:

  OUR HEADQUARTERS
  Based in Pleasanton, CA, nestled between San Francisco and Silicon Valley, our office features a gourmet kitchen, free healthy lunches served daily, and onsite fitness classes.
  View Menu

			List<String> filenames = new ArrayList<>();

			filenames.add("../veevaMenuPDFParser/veevaweeklymenus/Menu-Week-of-01.14-01.18.pdf");
			filenames.add("../veevaMenuPDFParser/veevaweeklymenus/Menu-Week-of-01.28-02.01.pdf");
			filenames.add("../veevaMenuPDFParser/veevaweeklymenus/Menu-Week-of-02.11-02.15.pdf");
			

### Usage:

The PrintUserMenu is a function that you define, which contains sample use cases (call these functions within it)

### Print out most common ingredients

    IngredientsCount.entrySet().stream()
        .sorted(Map.Entry.comparingByValue())
        .forEach(System.out::println);

### printRecipeInfo

Given a recipe name, Prints the recipe, ingredients, and dish type (meat, vegetarian, main course or side dish)

    printRecipeInfo("Tandoori Chicken");

### printRecipesThatContainIngredients

    Given a set of ingredients,
    also prints the missing ingredients.

    (may need to add a nother one that just has "contains one instead of contains all ingredients")

		Set<String> ingredientsOne = new HashSet<>();
		
		ingredientsOne.add("Salt");
		ingredientsOne.add("Pepper");
		ingredientsOne.add("Garlic");
		ingredientsOne.add("Lime");
		ingredientsOne.add("Olive Oil");
