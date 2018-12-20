package teamKuiper.redoxiation.items;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class RedoxiationItems {
	
	public static ItemCommon itemCommon;
	
	public static void initItems() {
		itemCommon = new ItemCommon();
		itemCommon.postInit();
	}
	
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(itemCommon);
	}
}
