package teamKuiper.redoxiation.recipes;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import teamKuiper.redoxiation.items.RedoxiationGenericItems;

public class RedoxiationRecipes extends RecipesMain{
	
	public static void RecipeLists() {
		GameRegistry.addRecipe(new ItemStack(Blocks.obsidian), new Object[]{
    		"AAA",
    		"AAA",
    		"AAA",
    		'A', Items.cookie
    	});
	}
}
