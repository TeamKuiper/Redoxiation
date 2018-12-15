package teamKuiper.redoxiation.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class ItemBase extends Item {

	protected final Map<Integer, String> NAME_MAP = new HashMap<Integer, String>();
	protected final Map<Integer, String> INFO_MAP = new HashMap<Integer, String>();
	
	protected List<ItemStack> variants = new ArrayList<ItemStack>();

	protected ItemStack addVariant(int metadata, String name) {
		return addVariant(metadata, name, false);
		
	}
	protected ItemStack addVariant(int metadata, String name, boolean registerOreDictionary) {
		return addVariant(metadata, name, "", registerOreDictionary);
	}
    
	protected ItemStack addVariant(int metadata, String name, String info) {
		return addVariant(metadata, name, info, false);
	}
    
	protected ItemStack addVariant(int metadata, String name, String info, boolean registerOreDictionary) {
		if(NAME_MAP.containsKey(metadata))
			return null;
		ItemStack stack = new ItemStack(this, 1, metadata);
		variants.add(stack);
		NAME_MAP.put(metadata, name);
		INFO_MAP.put(metadata, info);
		if(registerOreDictionary) {
			OreDictionary.registerOre(name, stack);	
		}
		
		return stack;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (INFO_MAP.containsKey(stack.getMetadata())) {
			tooltip.add(INFO_MAP.get(stack.getMetadata()));
		}
	}
	
	
	
}
