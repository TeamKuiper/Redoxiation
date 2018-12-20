package teamKuiper.redoxiation.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import teamKuiper.redoxiation.Redoxiation;

public class ItemCommon extends ItemBase {
	
	public ItemCommon() {
		setRegistryName(Redoxiation.MODID, "common");
		setUnlocalizedName("common");
		setCreativeTab(Redoxiation.tabRedoxiationitems);
	}
	
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
    	items.addAll(variants);
    }
	
	public void postInit() {
		int index;
		
		//shard
		index = SHARD_START;
		calcite = addVariant(index, "calcite", "CaCO3", true); index++;
		saltChunk = addVariant(index, "saltChunk", "NaCl", true); index++;
		rawBauxite = addVariant(index, "rawBauxite", "Al(OH)3", true); index++;
		rawRutile = addVariant(index, "rawRutile", "Ti", true); index++;
		rawScheelite = addVariant(index, "rawScheelite", "CaWO4", true); index++;
		sulfurChunk = addVariant(index, "sulfurChunk", true); index++;
		dustSulfur = addVariant(index, "dustSulfur", true); index++;
		itemCryolite = addVariant(index, "itemCryolite", true); index++;
		dolomiteShard = addVariant(index, "dolomiteShard", true); index++;
		dolomitePowder = addVariant(index, "dolomitePowder", true);
		shards = new ItemStack[] { calcite, saltChunk, rawBauxite, rawRutile, rawScheelite, sulfurChunk, dustSulfur,
				itemCryolite, dolomiteShard, dolomitePowder};
		
		// CrushedOre
		index = CRUSHED_ORES_START;
		crushedOreIron = addVariant(index, "crushedOreIron", "Fe", true); index++;
		crushedOreGold = addVariant(index, "crushedOreGold", "Au", true); index++;
		crushedOreCopper = addVariant(index, "crushedOreCopper", "Cu", true); index++;
		crushedOreTin = addVariant(index, "crushedOreTin", "Sn", true); index++;
		crushedOreLead = addVariant(index, "crushedOreLead", "Pb", true); index++;
		crushedOreSilver = addVariant(index, "crushedOreSilver", "Ag", true); index++;
		crushedOreNickel = addVariant(index, "crushedOreNickel", "Ni", true); index++;
		crushedOrePlatinum = addVariant(index, "crushedOrePlatinum", "Pt", true); index++;
		crushedOreZinc = addVariant(index, "crushedOreZinc", "Zn", true); index++;
		crushedOreChromium = addVariant(index, "crushedOreChromium", "Cr", true);
		crushedOres = new ItemStack[] { crushedOreIron, crushedOreGold, crushedOreCopper, crushedOreTin, crushedOreLead,
				crushedOreSilver, crushedOreNickel, crushedOrePlatinum, crushedOreZinc, crushedOreChromium};
		
		// DustOre
		index = DUST_ORES_START;
		dustOreIron = addVariant(index, "dustOreIron", "Fe", true); index++;
		dustOreGold = addVariant(index, "dustOreGold", "Au", true); index++;
		dustOreCopper = addVariant(index, "dustOreCopper", "Cu", true); index++;
		dustOreTin = addVariant(index, "dustOreTin", "Sn", true); index++;
		dustOreLead = addVariant(index, "dustOreLead", "Pb", true); index++;
		dustOreSilver = addVariant(index, "dustOreSilver", "Ag", true); index++;
		dustOreNickel = addVariant(index, "dustOreNickel", "Ni", true); index++;
		dustOrePlatinum = addVariant(index, "dustOrePlatinum", "Pt", true); index++;
		dustOreZinc = addVariant(index, "dustOreZinc", "Zn", true); index++;
		dustOreCobalt = addVariant(index, "dustOreCobalt", "Co", true); index++;
		dustOreChromium = addVariant(index, "dustOreChromium", "Cr", true); index++;
		dustSalt = addVariant(index, "dustSalt", "NaCl", true); index++;
		dustBauxite = addVariant(index, "dustBauxite", "Al2O3", true); index++;
		dustRutile = addVariant(index, "dustRutile", "TiO2", true); index++;
		dustScheelite = addVariant(index, "dustScheelite", "WO3", true);
		dustOres = new ItemStack[] { dustOreIron, dustOreGold, dustOreCopper, dustOreTin, dustOreLead, dustOreSilver,
				dustOreNickel, dustOrePlatinum, dustOreZinc, dustOreCobalt, dustOreChromium, dustSalt, dustBauxite,
				dustRutile, dustScheelite };
		
		// Ingot
		index = INGOTS_START;
		ingotCopper = addVariant(index, "ingotCopper", "Cu", true); index++;
		ingotTin = addVariant(index, "ingotTin", "Sn", true); index++;
		ingotLead = addVariant(index, "ingotLead", "Pb", true); index++;
		ingotSilver = addVariant(index, "ingotSilver", "Ag", true); index++;
		ingotNickel = addVariant(index, "ingotNickel", "Ni", true); index++;
		ingotPlatinum = addVariant(index, "ingotPlatinum", "Pt", true); index++;
		ingotZinc = addVariant(index, "ingotZinc", "Zn", true); index++;
		ingotCobalt = addVariant(index, "ingotCobalt","Co", true); index++;
		ingotChromium = addVariant(index, "ingotChromium", "Cr", true); index++;
		ingotUranium = addVariant(index, "ingotUranium", "U", true); index++;
		ingotPlutonium = addVariant(index, "ingotPlutonium", "Pu", true); index++;
		ingotSteel = addVariant(index, "ingotSteel", true); index++;
		ingotAluminum = addVariant(index, "ingotAluminum", "Al2O3", true); index++;
		ingotTungsten = addVariant(index, "ingotTungsten", "WO3", true); index++;
		ingotTitanium = addVariant(index, "ingotTitanium", "TiO2", true);
		ingots = new ItemStack[] { ingotCopper, ingotTin, ingotLead, ingotSilver, ingotNickel, ingotPlatinum, ingotZinc,
				ingotCobalt, ingotChromium, ingotUranium, ingotPlutonium, ingotSteel, ingotAluminum, ingotTungsten,
				ingotTitanium, };
		
		// nugget
		index = NUGGETS_START;
		nuggetIron = addVariant(index, "nuggetIron", "Fe", true); index++;
		nuggetCopper = addVariant(index, "nuggetCopper", "Cu", true); index++;
		nuggetTin = addVariant(index, "nuggetTin", "Sn", true); index++;
		nuggetLead = addVariant(index, "nuggetLead", "Pb", true); index++;
		nuggetSilver = addVariant(index, "nuggetSilver", "Ag", true); index++;
		nuggetPlatinum = addVariant(index, "nuggetPlatinum", "Pt", true); index++;
		nuggetZinc = addVariant(index, "nuggetZinc", "Zn", true); index++;
		nuggetNickel = addVariant(index, "nuggetNickel", "Ni", true); index++;
		nuggetChromium = addVariant(index, "nuggetChromium", "Cr", true); index++;
		nuggetAluminum = addVariant(index, "nuggetAluminum", "Al2O3", true); index++;
		nuggetTitanium = addVariant(index, "nuggetTitanium", "TiO2", true); index++;
		nuggetTungsten = addVariant(index, "nuggetTungsten", "WO3", true); index++;
		nuggetSteel = addVariant(index, "nuggetSteel");
		nuggets = new ItemStack[] { nuggetIron, nuggetCopper, nuggetTin, nuggetLead, nuggetSilver, nuggetPlatinum,
				nuggetZinc, nuggetNickel, nuggetChromium, nuggetAluminum, nuggetTitanium, nuggetTungsten, nuggetSteel };
		
		// crashed
		index = CRASHED_BLOCKS_START;
		crashedNetherrack = addVariant(index, "crashedNetherrack"); index++;
		crashedObsidian = addVariant(index, "crashedObsidian");
		crashedBlocks = new ItemStack[] {crashedNetherrack, crashedObsidian};
	}

	public static final int SHARD_START = 0;
	public static final int CRUSHED_ORES_START = 21;
	public static final int DUST_ORES_START = 41;
	public static final int INGOTS_START = 71;
	public static final int NUGGETS_START = 101;
	public static final int CRASHED_BLOCKS_START = 131;

	public static ItemStack calcite, saltChunk, rawBauxite, rawRutile, rawScheelite, sulfurChunk, dustSulfur,
			itemCryolite, dolomiteShard, dolomitePowder,
			// CrushedOre
			crushedOreIron, crushedOreGold, crushedOreCopper, crushedOreTin, crushedOreLead, crushedOreSilver,
			crushedOreNickel, crushedOrePlatinum, crushedOreZinc, crushedOreChromium,
			// DustOre
			dustOreIron, dustOreGold, dustOreCopper, dustOreTin, dustOreLead, dustOreSilver, dustOreNickel,
			dustOrePlatinum, dustOreZinc, dustOreCobalt, dustOreChromium, dustSalt, dustBauxite, dustRutile,
			dustScheelite,
			// Ingot
			ingotCopper, ingotTin, ingotLead, ingotSilver, ingotNickel, ingotPlatinum, ingotZinc, ingotCobalt,
			ingotChromium, ingotUranium, ingotPlutonium, ingotSteel, ingotAluminum, ingotTungsten, ingotTitanium,
			// nugget
			nuggetIron, nuggetCopper, nuggetTin, nuggetLead, nuggetSilver, nuggetPlatinum, nuggetZinc, nuggetNickel,
			nuggetChromium, nuggetAluminum, nuggetTitanium, nuggetTungsten, nuggetSteel,
			// crashed
			crashedNetherrack, crashedObsidian;
	public static ItemStack[] shards, crushedOres, dustOres, ingots, nuggets, crashedBlocks;
}
