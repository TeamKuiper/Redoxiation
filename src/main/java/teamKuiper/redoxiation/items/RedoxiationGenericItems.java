package teamKuiper.redoxiation.items;

import java.util.List;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidContainerRegistry;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.RegistryNamespaced;
import net.minecraftforge.oredict.OreDictionary;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;

public class RedoxiationGenericItems extends Item {
	public static final RegistryNamespaced itemRegistry = GameData.getItemRegistry();
	private final String ITEMNAME;
	private final String INFORMATION;

	public static Item Calcite, SaltChunk, RawBauxite,
			RawRutile, RawScheelite, SulfurChunk,
			dustSulfur, ItemCryolite, DolomiteShard,
			DolomitePowder,
			//CrushedOre
			crushedOreIron, crushedOreGold, crushedOreCopper, crushedOreTin,
			crushedOreLead, crushedOreSilver, crushedOreNickel,
			crushedOrePlatinum,	crushedOreZinc, crushedOreChromium, 
			//DustOre
			dustOreIron, dustOreGold, dustOreCopper, dustOreTin, dustOreLead,
			dustOreSilver, dustOreNickel, dustOrePlatinum, dustOreZinc, dustOreCobalt,
			dustOreChromium, dustSalt, dustBauxite, dustRutile, dustScheelite,
			//Ingot
			ingotCopper, ingotTin, ingotLead, ingotSilver, ingotNickel,
			ingotPlatinum, ingotZinc, ingotCobalt, ingotChromium, ingotUranium,
			ingotPlutonium, ingotSteel, ingotAluminum, ingotTungsten, ingotTitanium, 
			//nugget
			nuggetIron, nuggetCopper, nuggetTin, nuggetLead,
			nuggetSilver, nuggetPlatinum, nuggetZinc, nuggetNickel,
			nuggetChromium, nuggetAluminum, nuggetTitanium, nuggetTungsten,
			nuggetSteel, 
			//crashed
			crashedNetherrack, crashedObsidian, 
			//Fluid
			itemHotAirBucket, itemMoltenPigironBucket, itemSlagBucket;
	
	public static boolean Calcite_enable, SaltChunk_enable, RawBauxite_enable,
			RawRutile_enable, RawScheelite_enable, DolomiteShard_enable,
			//CrushOre
			crushedOreIron_enable, crushedOreGold_enable, crushedOreCopper_enable,
			crushedOreTin_enable, crushedOreLead_enable, crushedOreSilver_enable, 
			crushedOreNickel_enable, crushedOrePlatinum_enable, crushedOreZinc_enable,
			crushedOreChromium_enable,
			//DustOre
			dustOreIron_enable, dustOreGold_enable, dustOreCopper_enable,
			dustOreTin_enable, dustOreLead_enable, dustOreSilver_enable,
			dustOreNickel_enable, dustOrePlatinum_enable, dustOreZinc_enable,
			dustOreCobalt_enable, dustOreChromium_enable, dustScheelite_enable,			
			dustSalt_enable, dustBauxite_enable, dustRutile_enable,
			//Ingot
			ingotCopper_enable, ingotTin_enable, ingotLead_enable,
			ingotSilver_enable, ingotNickel_enable,	ingotPlatinum_enable,
			ingotZinc_enable, ingotCobalt_enable, ingotChromium_enable,
			ingotUranium_enable, ingotPlutonium_enable, ingotSteel_enable,
			ingotAluminum_enable, ingotTungsten_enable,	ingotTitanium_enable, 
			//Nugget
			nuggetIron_enable, nuggetCopper_enable, nuggetTin_enable,
			nuggetLead_enable, nuggetSilver_enable, nuggetPlatinum_enable,
			nuggetZinc_enable, nuggetNickel_enable, nuggetChromium_enable,
			nuggetAluminum_enable, nuggetTitanium_enable, nuggetTungsten_enable,
			nuggetSteel_enable,
			//ETC
			SulfurChunk_enable, dustSulfur_enable, ItemCryolite_enable,
			crashedNetherrack_enable, crashedObsidian_enable, DolomitePowder_enable,
			//Fluid
			itemHotAirBucket_enable, itemMoltenPigironBucket_enable, itemSlagBucket_enable;

	public RedoxiationGenericItems(String name, String info) {
		super();
		setCreativeTab(Redoxiation.tabRedoxiationitems);
		INFORMATION = info;
		ITEMNAME = name;
	}

	public RedoxiationGenericItems(String name) {
		super();
		setCreativeTab(Redoxiation.tabRedoxiationitems);
		ITEMNAME = name;
		INFORMATION = "null";
	}

	public static void registerItems() {
		if(Calcite_enable){
			Calcite = new RedoxiationGenericItems("Calcite", "CaCO3");
			GameRegistry.registerItem(Calcite, "Calcite");
			OreDictionary.registerOre("materialCalcite", RedoxiationGenericItems.Calcite);
    	}
		if(SaltChunk_enable){
			SaltChunk = new RedoxiationGenericItems("SaltChunk", "NaCl");
			GameRegistry.registerItem(SaltChunk, "SaltChunk");
			OreDictionary.registerOre("lumpSalt", RedoxiationGenericItems.SaltChunk);
		}
		if(RawBauxite_enable){
			RawBauxite = new RedoxiationGenericItems("RawBauxite", "Al(OH)3");
			GameRegistry.registerItem(RawBauxite, "RawBauxite");
			OreDictionary.registerOre("lumpBauxite", RedoxiationGenericItems.RawBauxite);
		}
		if(RawRutile_enable){
			RawRutile = new RedoxiationGenericItems("RawRutile", "Ti");
			GameRegistry.registerItem(RawRutile, "RawRutile");
			OreDictionary.registerOre("lumpRutile", RedoxiationGenericItems.RawRutile);
		}
		if(RawScheelite_enable){
			RawScheelite = new RedoxiationGenericItems("RawScheelite", "CaWO4");
			GameRegistry.registerItem(RawScheelite, "RawScheelite");
			OreDictionary.registerOre("lumpScheelite", RedoxiationGenericItems.RawScheelite);
		}
		if(DolomiteShard_enable){
			DolomiteShard = new RedoxiationGenericItems("DolomiteShard");
			GameRegistry.registerItem(DolomiteShard, "DolomiteShard");
		}
		if(crushedOreIron_enable){
			crushedOreIron = new RedoxiationGenericItems("crushedOreIron", "Fe");
			GameRegistry.registerItem(crushedOreIron, "crushedOreIron");
			OreDictionary.registerOre("crushedIron", RedoxiationGenericItems.crushedOreIron);
    	}
		if(crushedOreGold_enable){
			crushedOreGold = new RedoxiationGenericItems("crushedOreGold", "Au");
			GameRegistry.registerItem(crushedOreGold, "crushedOreGold");
			OreDictionary.registerOre("crushedGold", RedoxiationGenericItems.crushedOreGold);
    	}
		if(crushedOreCopper_enable){
			crushedOreCopper = new RedoxiationGenericItems("crushedOreCopper", "Cu");
			GameRegistry.registerItem(crushedOreCopper, "crushedOreCopper");
			OreDictionary.registerOre("crushedCopper", RedoxiationGenericItems.crushedOreCopper);
    	}
		if(crushedOreTin_enable){
			crushedOreTin = new RedoxiationGenericItems("crushedOreTin", "Sn");
			GameRegistry.registerItem(crushedOreTin, "crushedOreTin");
			OreDictionary.registerOre("crushedTin", RedoxiationGenericItems.crushedOreTin);
    	}
		if(crushedOreLead_enable){
			crushedOreLead = new RedoxiationGenericItems("crushedOreLead", "Pb");
			GameRegistry.registerItem(crushedOreLead, "crushedOreLead");
			OreDictionary.registerOre("crushedLead", RedoxiationGenericItems.crushedOreLead);
    	}
		if(crushedOreSilver_enable){
			crushedOreSilver = new RedoxiationGenericItems("crushedOreSilver", "Ag");
			GameRegistry.registerItem(crushedOreSilver, "crushedOreSilver");
			OreDictionary.registerOre("crushedSilver", RedoxiationGenericItems.crushedOreSilver);
		}
		if(crushedOrePlatinum_enable){
			crushedOrePlatinum = new RedoxiationGenericItems("crushedOrePlatinum", "Pt");
			GameRegistry.registerItem(crushedOrePlatinum, "crushedOrePlatinum");
			OreDictionary.registerOre("crushedPlatinum", RedoxiationGenericItems.crushedOrePlatinum);
		}
		if(crushedOreZinc_enable){
			crushedOreZinc = new RedoxiationGenericItems("crushedOreZinc", "Zn");
			GameRegistry.registerItem(crushedOreZinc, "crushedOreZinc");
			OreDictionary.registerOre("crushedZinc", RedoxiationGenericItems.crushedOreZinc);
		}
		if(crushedOreNickel_enable){
			crushedOreNickel = new RedoxiationGenericItems("crushedOreNickel", "Ni");
			GameRegistry.registerItem(crushedOreNickel, "crushedOreNickel");
			OreDictionary.registerOre("crushedNickel", RedoxiationGenericItems.crushedOreNickel);
		}
		if(crushedOreChromium_enable){
			crushedOreChromium = new RedoxiationGenericItems("crushedOreChromium", "Cr");
			GameRegistry.registerItem(crushedOreChromium, "crushedOreChromium");
			OreDictionary.registerOre("crushedChromium", RedoxiationGenericItems.crushedOreChromium);
		}
		if(dustOreIron_enable){
			dustOreIron = new RedoxiationGenericItems("dustOreIron", "Fe");
			GameRegistry.registerItem(dustOreIron, "dustOreIron");
			OreDictionary.registerOre("dustOreIronRedox", RedoxiationGenericItems.dustOreIron);
		}
		if(dustOreGold_enable){
			dustOreGold = new RedoxiationGenericItems("dustOreGold", "Au");
			GameRegistry.registerItem(dustOreGold, "dustOreGold");
			OreDictionary.registerOre("dustOreGoldRedox", RedoxiationGenericItems.dustOreGold);
		}
		if(dustOreCopper_enable){
			dustOreCopper = new RedoxiationGenericItems("dustOreCopper", "Cu");
			GameRegistry.registerItem(dustOreCopper, "dustOreCopper");
			OreDictionary.registerOre("dustOreCopperRedox", RedoxiationGenericItems.dustOreCopper);
		}
		if(dustOreTin_enable){
			dustOreTin = new RedoxiationGenericItems("dustOreTin", "Sn");
			GameRegistry.registerItem(dustOreTin, "dustOreTin");
			OreDictionary.registerOre("dustOreTinRedox", RedoxiationGenericItems.dustOreTin);
		}
		if(dustOreLead_enable){
			dustOreLead = new RedoxiationGenericItems("dustOreLead", "Pb");
			GameRegistry.registerItem(dustOreLead, "dustOreLead");
			OreDictionary.registerOre("dustOreLeadRedox", RedoxiationGenericItems.dustOreLead);
		}
		if(dustOreSilver_enable){
			dustOreSilver = new RedoxiationGenericItems("dustOreSilver", "Ag");
			GameRegistry.registerItem(dustOreSilver, "dustOreSilver");
			OreDictionary.registerOre("dustOreSilverRedox", RedoxiationGenericItems.dustOreSilver);
		}
		if(dustOrePlatinum_enable){
			dustOrePlatinum = new RedoxiationGenericItems("dustOrePlatinum", "Pt");
			GameRegistry.registerItem(dustOrePlatinum, "dustOrePlatinum");
			OreDictionary.registerOre("dustOrePlatinumRedox", RedoxiationGenericItems.dustOrePlatinum);
		}
    	if(dustOreZinc_enable){
			dustOreZinc = new RedoxiationGenericItems("dustOreZinc", "Zn");
			GameRegistry.registerItem(dustOreZinc, "dustOreZinc");
			OreDictionary.registerOre("dustOreZincRedox", RedoxiationGenericItems.dustOreZinc);
    	}
    	if(dustOreNickel_enable){
			dustOreNickel = new RedoxiationGenericItems("dustOreNickel", "Ni");
			GameRegistry.registerItem(dustOreNickel, "dustOreNickel");
			OreDictionary.registerOre("dustOreNickelRedox", RedoxiationGenericItems.dustOreNickel);
    	}
    	if(dustOreChromium_enable){
			dustOreChromium = new RedoxiationGenericItems("dustOreChromium", "Cr");
			GameRegistry.registerItem(dustOreChromium, "dustOreChromium");
			OreDictionary.registerOre("dustOreChromiumRedox", RedoxiationGenericItems.dustOreChromium);
    	}
    	if(dustSalt_enable){
    		dustSalt = new RedoxiationGenericItems("dustSalt", "NaCl");
			GameRegistry.registerItem(dustSalt, "dustSalt");
			OreDictionary.registerOre("dustOreSaltRedox", RedoxiationGenericItems.dustSalt);
    	}
    	if(dustBauxite_enable){
			dustBauxite = new RedoxiationGenericItems("dustBauxite", "Al2O3");
			GameRegistry.registerItem(dustBauxite, "dustBauxite");
			OreDictionary.registerOre("dustOreAluminumRedox", RedoxiationGenericItems.dustBauxite);
    	}
    	if(dustRutile_enable){
			dustRutile = new RedoxiationGenericItems("dustRutile", "TiO2");
			GameRegistry.registerItem(dustRutile, "dustRutile");
			OreDictionary.registerOre("dustOreTitaniumRedox", RedoxiationGenericItems.dustRutile);
    	}
    	if(dustScheelite_enable){
			dustScheelite = new RedoxiationGenericItems("dustScheelite", "WO3");
			GameRegistry.registerItem(dustScheelite, "dustScheelite");
			OreDictionary.registerOre("dustOreTungstenRedox", RedoxiationGenericItems.dustScheelite);
    	}
    	if(ingotCopper_enable){
			ingotCopper = new RedoxiationGenericItems("ingotCopper", "Cu");
			GameRegistry.registerItem(ingotCopper, "ingotCopper");
			OreDictionary.registerOre("ingotCopper", RedoxiationGenericItems.ingotCopper);
    	}
    	if(ingotTin_enable){
			ingotTin = new RedoxiationGenericItems("ingotTin", "Sn");
			GameRegistry.registerItem(ingotTin, "ingotTin");
			OreDictionary.registerOre("ingotTin", RedoxiationGenericItems.ingotTin);
    	}
    	if(ingotLead_enable){
			ingotLead = new RedoxiationGenericItems("ingotLead", "Pb");
			GameRegistry.registerItem(ingotLead, "ingotLead");
			OreDictionary.registerOre("ingotLead", RedoxiationGenericItems.ingotLead);
		}
    	if(ingotSilver_enable){
			ingotSilver = new RedoxiationGenericItems("ingotSilver", "Ag");
			GameRegistry.registerItem(ingotSilver, "ingotSilver");
			OreDictionary.registerOre("ingotSilver", RedoxiationGenericItems.ingotSilver);
    	}
    	if(ingotPlatinum_enable){
			ingotPlatinum = new RedoxiationGenericItems("ingotPlatinum", "Pt");	
			GameRegistry.registerItem(ingotPlatinum, "ingotPlatinum");
			OreDictionary.registerOre("ingotPlatinum", RedoxiationGenericItems.ingotPlatinum);
    	}
    	if(ingotZinc_enable){
			ingotZinc = new RedoxiationGenericItems("ingotZinc", "Zn");
			GameRegistry.registerItem(ingotZinc, "ingotZinc");
			OreDictionary.registerOre("ingotZinc", RedoxiationGenericItems.ingotZinc);
    	}
    	if(ingotNickel_enable){
			ingotNickel = new RedoxiationGenericItems("ingotNickel", "Ni");
			GameRegistry.registerItem(ingotNickel, "ingotNickel");
			OreDictionary.registerOre("ingotNickel", RedoxiationGenericItems.ingotNickel);
    	}
    	if(ingotChromium_enable){
			ingotChromium = new RedoxiationGenericItems("ingotChromium", "Cr");
			GameRegistry.registerItem(ingotChromium, "ingotChromium");
			OreDictionary.registerOre("ingotChromium", RedoxiationGenericItems.ingotChromium);
    	}
    	if(ingotAluminum_enable){
			ingotAluminum = new RedoxiationGenericItems("ingotAluminum", "Al2O3");
			GameRegistry.registerItem(ingotAluminum, "ingotAluminum");
			OreDictionary.registerOre("ingotAluminumOort", RedoxiationGenericItems.ingotAluminum);
    	}
    	if(ingotTitanium_enable){
			ingotTitanium = new RedoxiationGenericItems("ingotTitanium", "TiO2");
			GameRegistry.registerItem(ingotTitanium, "ingotTitanium");
			OreDictionary.registerOre("ingotTitaniumOort", RedoxiationGenericItems.ingotTitanium);
    	}
    	if(ingotTungsten_enable){
			ingotTungsten = new RedoxiationGenericItems("ingotTungsten", "WO3");
			GameRegistry.registerItem(ingotTungsten, "ingotTungsten");
			OreDictionary.registerOre("ingotTungstenOort", RedoxiationGenericItems.ingotTungsten);
		}
    	if(ingotCobalt_enable){
			ingotCobalt = new RedoxiationGenericItems("ingotCobalt","Co");
			GameRegistry.registerItem(ingotCobalt, "ingotCobalt");
			OreDictionary.registerOre("ingotCobalt", RedoxiationGenericItems.ingotCobalt);
    	}
    	if(ingotSteel_enable){
			ingotSteel = new RedoxiationGenericItems("ingotSteel");
			GameRegistry.registerItem(ingotSteel, "ingotSteel");
			OreDictionary.registerOre("ingotSteelOort", RedoxiationGenericItems.ingotSteel);
    	}
    	if(nuggetIron_enable){
    		nuggetIron = new RedoxiationGenericItems("nuggetIron", "Fe");
			GameRegistry.registerItem(nuggetIron, "nuggetIron");
			OreDictionary.registerOre("nuggetIron", RedoxiationGenericItems.nuggetIron);
    	}
    	if(nuggetCopper_enable){
    		nuggetCopper = new RedoxiationGenericItems("nuggetCopper", "Cu");
			GameRegistry.registerItem(nuggetCopper, "nuggetCopper");
			OreDictionary.registerOre("nuggetCopper", RedoxiationGenericItems.nuggetCopper);
    	}
    	if(nuggetTin_enable){
			nuggetTin = new RedoxiationGenericItems("nuggetTin", "Sn");
			GameRegistry.registerItem(nuggetTin, "nuggetTin");
			OreDictionary.registerOre("nuggetTin", RedoxiationGenericItems.nuggetTin);
    	}
    	if(nuggetLead_enable){
    		nuggetLead = new RedoxiationGenericItems("nuggetLead", "Pb");
			GameRegistry.registerItem(nuggetLead, "nuggetLead");
			OreDictionary.registerOre("nuggetLead", RedoxiationGenericItems.nuggetLead);
    	}
    	if(nuggetSilver_enable){
    		nuggetSilver = new RedoxiationGenericItems("nuggetSilver", "Ag");
			GameRegistry.registerItem(nuggetSilver, "nuggetSilver");
			OreDictionary.registerOre("nuggetSilver", RedoxiationGenericItems.nuggetSilver);
    	}
    	if(nuggetPlatinum_enable){
    		nuggetPlatinum = new RedoxiationGenericItems("nuggetPlatinum", "Pt");
			GameRegistry.registerItem(nuggetPlatinum, "nuggetPlatinum");
			OreDictionary.registerOre("nuggetPlatinum", RedoxiationGenericItems.nuggetPlatinum);
    	}
    	if(nuggetZinc_enable){
    		nuggetZinc = new RedoxiationGenericItems("nuggetZinc", "Zn");
			GameRegistry.registerItem(nuggetZinc, "nuggetZinc");
			OreDictionary.registerOre("nuggetZinc", RedoxiationGenericItems.nuggetZinc);
    	}
    	if(nuggetNickel_enable){
    		nuggetNickel = new RedoxiationGenericItems("nuggetNickel", "Ni");
			GameRegistry.registerItem(nuggetNickel, "nuggetNickel");
			OreDictionary.registerOre("nuggetNickel", RedoxiationGenericItems.nuggetNickel);
    	}
    	if(nuggetChromium_enable){
    		nuggetChromium = new RedoxiationGenericItems("nuggetChromium", "Cr");
			GameRegistry.registerItem(nuggetChromium, "nuggetChromium");
			OreDictionary.registerOre("nuggetChromium", RedoxiationGenericItems.nuggetChromium);
    	}
    	if(nuggetAluminum_enable){
    		nuggetAluminum = new RedoxiationGenericItems("nuggetAluminum", "Al2O3");
			GameRegistry.registerItem(nuggetAluminum, "nuggetAluminum");
			OreDictionary.registerOre("nuggetAluminum", RedoxiationGenericItems.nuggetAluminum);
    	}
    	if(nuggetTitanium_enable){
    		nuggetTitanium = new RedoxiationGenericItems("nuggetTitanium", "TiO2");
			GameRegistry.registerItem(nuggetTitanium, "nuggetTitanium");
			OreDictionary.registerOre("nuggetTitanium", RedoxiationGenericItems.nuggetTitanium);
		}
    	if(nuggetTungsten_enable){
    		nuggetTungsten = new RedoxiationGenericItems("nuggetTungsten", "WO3");
			GameRegistry.registerItem(nuggetTungsten, "nuggetTungsten");
			OreDictionary.registerOre("nuggetTungsten", RedoxiationGenericItems.nuggetTungsten);
    	}
    	if(nuggetSteel_enable){
    		nuggetSteel = new RedoxiationGenericItems("nuggetSteel");
			GameRegistry.registerItem(nuggetSteel, "nuggetSteel");
			OreDictionary.registerOre("nuggetSteel", RedoxiationGenericItems.nuggetSteel);
    	}
    	if(SulfurChunk_enable){
			SulfurChunk = new RedoxiationGenericItems("SulfurChunk");
			GameRegistry.registerItem(SulfurChunk, "SulfurChunk");
			OreDictionary.registerOre("lumpSulfur", RedoxiationGenericItems.SulfurChunk);
    	}
    	if(dustSulfur_enable){
    		dustSulfur = new RedoxiationGenericItems("dustSulfur");
			GameRegistry.registerItem(dustSulfur, "dustSulfur");
			OreDictionary.registerOre("dustOreSulfurOort", RedoxiationGenericItems.dustSulfur);
		}
    	if(DolomitePowder_enable){
    		DolomitePowder = new RedoxiationGenericItems("DolomitePowder");
    		GameRegistry.registerItem(DolomitePowder, "DolomitePowder");
    	}
    	if(ItemCryolite_enable){
			ItemCryolite = new RedoxiationGenericItems("ItemCryolite");
			GameRegistry.registerItem(ItemCryolite, "ItemCryolite");
			OreDictionary.registerOre("materialCryolite", RedoxiationGenericItems.ItemCryolite);
    	}
    	if(crashedNetherrack_enable){
			crashedNetherrack = new RedoxiationGenericItems("crashedNetherrack");
    	}
    	if(crashedObsidian_enable){
			crashedObsidian = new RedoxiationGenericItems("crashedObsidian");
    	}
        if(itemHotAirBucket_enable) {
            itemHotAirBucket = new RedoxiationBucket(RedoxiationBlocks.HotAirBlock, "HotAirBucket");
            itemHotAirBucket.setUnlocalizedName(Redoxiation.MODID + "." + "HotAirBucket").setContainerItem(Items.bucket);
            GameRegistry.registerItem(itemHotAirBucket, "HotAirBucket");
            FluidContainerRegistry.registerFluidContainer(RedoxiationBlocks.HotAir, new ItemStack(itemHotAirBucket), new ItemStack(Items.bucket));
            BucketHandler.INSTANCE.buckets.put(RedoxiationBlocks.HotAirBlock, itemHotAirBucket);
            MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
        }
        if(itemMoltenPigironBucket_enable) {
            itemMoltenPigironBucket = new RedoxiationBucket(RedoxiationBlocks.MoltenPigironBlock, "MoltenPigironBucket");
            itemMoltenPigironBucket.setUnlocalizedName(Redoxiation.MODID + "." + "MoltenPigironBucket").setContainerItem(Items.bucket);
            GameRegistry.registerItem(itemMoltenPigironBucket, "MoltenPigironBucket");
            FluidContainerRegistry.registerFluidContainer(RedoxiationBlocks.MoltenPigiron, new ItemStack(itemMoltenPigironBucket), new ItemStack(Items.bucket));
            BucketHandler.INSTANCE.buckets.put(RedoxiationBlocks.MoltenPigironBlock, itemMoltenPigironBucket);
            MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
        }
        if(itemSlagBucket_enable) {
            itemSlagBucket = new RedoxiationBucket(RedoxiationBlocks.SlagBlock, "SlagBucket");
            itemSlagBucket.setUnlocalizedName(Redoxiation.MODID + "." + "SlagBucket").setContainerItem(Items.bucket);
            GameRegistry.registerItem(itemSlagBucket, "SlagBucket");
            FluidContainerRegistry.registerFluidContainer(RedoxiationBlocks.Slag, new ItemStack(itemSlagBucket), new ItemStack(Items.bucket));
            BucketHandler.INSTANCE.buckets.put(RedoxiationBlocks.SlagBlock, itemSlagBucket);
            MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
		}
		Redoxiation.logger.info("Item Registry Complete. Starting Tileentity proxy.");
	}

	@Override
	public String getUnlocalizedName() {
		return "item." + Redoxiation.MODID + "." + ITEMNAME;
	}

	@Override
	public String getUnlocalizedName(ItemStack item) {
		return "item." + Redoxiation.MODID + "." + ITEMNAME;
	}

	@Override
	public void registerIcons(IIconRegister reg) {
		this.itemIcon = reg.registerIcon(Redoxiation.MODID + ":" + ITEMNAME);
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player,
			List list, boolean par4) {
		if (!(INFORMATION.equals("null"))) {
			list.add(INFORMATION);
		}
	}
}
