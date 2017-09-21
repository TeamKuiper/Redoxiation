package teamKuiper.redoxiation.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesMain {
	
	public static void RecipesMain1(String ar, Object in, Object out) {
		String[] arr = ar.split(":");
		char num = arr[3].charAt(0);
		if (out instanceof Block) {
			GameRegistry.addRecipe(new ItemStack((Block)out), new Object[] {
				arr[0], arr[1], arr[2], num, in});
		} else if (out instanceof Item) {
			GameRegistry.addRecipe(new ItemStack((Item)out), new Object[] {
				arr[0], arr[1], arr[2], num, in});	
		}
	}
	public static void RecipesMain2(String ar, Object in1, Object in2, Object out) {
		String[] arr = ar.split(":");
		char num1 = arr[3].charAt(0);
		char num2 = arr[4].charAt(0);
		if (out instanceof Block) {
			GameRegistry.addRecipe(new ItemStack((Block)out), new Object[] {
				arr[0], arr[1], arr[2], num1, in1, num2, in2});
		} else if (out instanceof Item) {
			GameRegistry.addRecipe(new ItemStack((Item)out), new Object[] {
				arr[0], arr[1], arr[2], num1, in1, num2, in2});	
		}
	}
	public static void RecipesMain3(String ar, Object in1, Object in2, Object in3, Object out) {
		String[] arr = ar.split(":");
		char num1 = arr[3].charAt(0);
		char num2 = arr[4].charAt(0);
		char num3 = arr[5].charAt(0);
		if (out instanceof Block) {
			GameRegistry.addRecipe(new ItemStack((Block)out), new Object[] {
				arr[0], arr[1], arr[2], num1, in1, num2, in2, num3, in3});
		} else if (out instanceof Item) {
			GameRegistry.addRecipe(new ItemStack((Item)out), new Object[] {
				arr[0], arr[1], arr[2], num1, in1, num2, in2, num3, in3});	
		}
	}
}