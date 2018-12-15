package teamKuiper.redoxiation.utils;

import net.minecraft.item.ItemStack;

public class Utils {

	public static ItemStack newItemStack(ItemStack stack) {
		ItemStack newStack = new ItemStack(stack.getItem(), stack.getCount(), stack.getMetadata());
		newStack.setItemDamage(stack.getItemDamage());
		return newStack;
	}

	public static ItemStack newItemStack(ItemStack stack, int amount) {
		ItemStack newStack = new ItemStack(stack.getItem(), amount, stack.getMetadata());
		newStack.setItemDamage(stack.getItemDamage());
		return newStack;
	}
}
