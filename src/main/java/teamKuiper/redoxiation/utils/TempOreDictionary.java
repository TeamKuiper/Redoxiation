package teamKuiper.redoxiation.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class TempOreDictionary {
	
	private static Map<String, Map<String, ItemStack>> oreDictionaryMap = new HashMap<String, Map<String, ItemStack>>();

	public static void tempRegister(String mapName, String name, ItemStack stack) {
		if(!oreDictionaryMap.containsKey(mapName)) {
			oreDictionaryMap.put(mapName, new HashMap<String, ItemStack>());
		}
		oreDictionaryMap.get(mapName).put(name, stack);
	}
	
	public static void register(String mapName) {
		if(oreDictionaryMap.containsKey(mapName)) {
			Map<String, ItemStack> map = oreDictionaryMap.get(mapName);
			Iterator<String> iter = map.keySet().iterator();
			while(iter.hasNext()) {
				String key = iter.next();
				System.out.println(key);
				OreDictionary.registerOre(key, map.get(key));
			}
			oreDictionaryMap.remove(mapName);
		}
	}
}
