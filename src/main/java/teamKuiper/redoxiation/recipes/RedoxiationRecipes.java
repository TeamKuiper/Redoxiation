package teamKuiper.redoxiation.recipes;

public class RedoxiationRecipes {

	public static int i = 100;
	public static int large = 9;
	public static int fc = 0;
	public static int ioCount = 0;

	public static String arrCount[] = new String[i];
	public static Object output[] = new Object[i];
	public static Object input[][] = new Object[i][large];
	
	public static void RecipesMember() {
		/*//example
		input[fc][0] = Blocks.stone;
		output[fc] = Items.apple;
		arrCount[fc] = "aaa:aaa:aa :a";
		fc++;
		
		input[fc][0] = Blocks.sand;
		input[fc][1] = Items.arrow;
		output[fc] = Blocks.cobblestone;
		arrCount[fc] = "aaa:aaa:aab:a:b";
		fc++;
		
		input[fc][0] = Items.clay_ball;
		input[fc][1] = Blocks.bookshelf;
		input[fc][2] = Items.bone;
		output[fc] = Items.flint;
		arrCount[fc] = "aaa:abc:abc:a:b:c";
		fc++;
		*/
		RecipesSort();
	}

	private static void RecipesSort() {
		for (int j=0;j<fc;j++) {
			int inCount = 0;
		for (int k=0;k<large;k++) {
			if (input[j][k] != null) { inCount++; }	
		}
		do {
				if (inCount == 1) {
				RecipesMain.RecipesMain1(arrCount[j], input[j][0], output[j]);
				ioCount++;
					} else if (inCount == 2) {
						RecipesMain.RecipesMain2(arrCount[j], input[j][0], input[j][1],
								output[j]);
					} else if (inCount == 3) {
						RecipesMain.RecipesMain3(arrCount[j], input[j][0], input[j][1],
								input[j][2], output[j]);
					}
				}
			while (fc == ioCount);
		}
	}
}