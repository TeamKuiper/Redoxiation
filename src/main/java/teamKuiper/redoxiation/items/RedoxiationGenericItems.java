package teamKuiper.redoxiation.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.GameData;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;

public class RedoxiationGenericItems extends Item {
	public static final RegistryNamespaced itemRegistry = GameData.getItemRegistry();
	private final String ITEMNAME;
	private final String INFORMATION;

	public static Item calcite, saltChunk, rawBauxite,
			rawRutile, rawScheelite, sulfurChunk,
			dustSulfur, itemCryolite, dolomiteShard,
			dolomitePowder,
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
	
	public static boolean calciteEnabled, saltChunkEnabled, rawBauxiteEnabled,
			rawRutileEnabled, rawScheeliteEnabled, dolomiteShardEnabled,
			//CrushOre
			crushedOreIronEnabled, crushedOreGoldEnabled, crushedOreCopperEnabled,
			crushedOreTinEnabled, crushedOreLeadEnabled, crushedOreSilverEnabled, 
			crushedOreNickelEnabled, crushedOrePlatinumEnabled, crushedOreZincEnabled,
			crushedOreChromiumEnabled,
			//DustOre
			dustOreIronEnabled, dustOreGoldEnabled, dustOreCopperEnabled,
			dustOreTinEnabled, dustOreLeadEnabled, dustOreSilverEnabled,
			dustOreNickelEnabled, dustOrePlatinumEnabled, dustOreZincEnabled,
			dustOreCobaltEnabled, dustOreChromiumEnabled, dustScheeliteEnabled,			
			dustSaltEnabled, dustBauxiteEnabled, dustRutileEnabled,
			//Ingot
			ingotCopperEnabled, ingotTinEnabled, ingotLeadEnabled,
			ingotSilverEnabled, ingotNickelEnabled,	ingotPlatinumEnabled,
			ingotZincEnabled, ingotCobaltEnabled, ingotChromiumEnabled,
			ingotUraniumEnabled, ingotPlutoniumEnabled, ingotSteelEnabled,
			ingotAluminumEnabled, ingotTungstenEnabled,	ingotTitaniumEnabled, 
			//Nugget
			nuggetIronEnabled, nuggetCopperEnabled, nuggetTinEnabled,
			nuggetLeadEnabled, nuggetSilverEnabled, nuggetPlatinumEnabled,
			nuggetZincEnabled, nuggetNickelEnabled, nuggetChromiumEnabled,
			nuggetAluminumEnabled, nuggetTitaniumEnabled, nuggetTungstenEnabled,
			nuggetSteelEnabled,
			//ETC
			sulfurChunkEnabled, dustSulfurEnabled, itemCryoliteEnabled,
			crashedNetherrackEnabled, crashedObsidianEnabled, dolomitePowderEnabled,
			//Fluid
			itemHotAirBucketEnabled, itemMoltenPigironBucketEnabled, itemSlagBucketEnabled;

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

	public static void registerItems(RegistryEvent.Register<Item> event) {
		if(calciteEnabled){
			calcite = new RedoxiationGenericItems("calcite", "CaCO3");
			event.getRegistry().register(calcite);
			OreDictionary.registerOre("materialcalcite", RedoxiationGenericItems.calcite);
    	}
		if(saltChunkEnabled){
			saltChunk = new RedoxiationGenericItems("saltChunk", "NaCl");
			event.getRegistry().register(saltChunk);
			OreDictionary.registerOre("lumpSalt", RedoxiationGenericItems.saltChunk);
		}
		if(rawBauxiteEnabled){
			rawBauxite = new RedoxiationGenericItems("rawBauxite", "Al(OH)3");
			event.getRegistry().register(rawBauxite);
			OreDictionary.registerOre("lumpBauxite", RedoxiationGenericItems.rawBauxite);
		}
		if(rawRutileEnabled){
			rawRutile = new RedoxiationGenericItems("rawRutile", "Ti");
			event.getRegistry().register(rawRutile);
			OreDictionary.registerOre("lumpRutile", RedoxiationGenericItems.rawRutile);
		}
		if(rawScheeliteEnabled){
			rawScheelite = new RedoxiationGenericItems("rawScheelite", "CaWO4");
			event.getRegistry().register(rawScheelite);
			OreDictionary.registerOre("lumpScheelite", RedoxiationGenericItems.rawScheelite);
		}
		if(dolomiteShardEnabled){
			dolomiteShard = new RedoxiationGenericItems("dolomiteShard");
			event.getRegistry().register(dolomiteShard);
		}
		if(crushedOreIronEnabled){
			crushedOreIron = new RedoxiationGenericItems("crushedOreIron", "Fe");
			event.getRegistry().register(crushedOreIron);
			OreDictionary.registerOre("crushedIron", RedoxiationGenericItems.crushedOreIron);
    	}
		if(crushedOreGoldEnabled){
			crushedOreGold = new RedoxiationGenericItems("crushedOreGold", "Au");
			event.getRegistry().register(crushedOreGold);
			OreDictionary.registerOre("crushedGold", RedoxiationGenericItems.crushedOreGold);
    	}
		if(crushedOreCopperEnabled){
			crushedOreCopper = new RedoxiationGenericItems("crushedOreCopper", "Cu");
			event.getRegistry().register(crushedOreCopper);
			OreDictionary.registerOre("crushedCopper", RedoxiationGenericItems.crushedOreCopper);
    	}
		if(crushedOreTinEnabled){
			crushedOreTin = new RedoxiationGenericItems("crushedOreTin", "Sn");
			event.getRegistry().register(crushedOreTin);
			OreDictionary.registerOre("crushedTin", RedoxiationGenericItems.crushedOreTin);
    	}
		if(crushedOreLeadEnabled){
			crushedOreLead = new RedoxiationGenericItems("crushedOreLead", "Pb");
			event.getRegistry().register(crushedOreLead);
			OreDictionary.registerOre("crushedLead", RedoxiationGenericItems.crushedOreLead);
    	}
		if(crushedOreSilverEnabled){
			crushedOreSilver = new RedoxiationGenericItems("crushedOreSilver", "Ag");
			event.getRegistry().register(crushedOreSilver);
			OreDictionary.registerOre("crushedSilver", RedoxiationGenericItems.crushedOreSilver);
		}
		if(crushedOrePlatinumEnabled){
			crushedOrePlatinum = new RedoxiationGenericItems("crushedOrePlatinum", "Pt");
			event.getRegistry().register(crushedOrePlatinum);
			OreDictionary.registerOre("crushedPlatinum", RedoxiationGenericItems.crushedOrePlatinum);
		}
		if(crushedOreZincEnabled){
			crushedOreZinc = new RedoxiationGenericItems("crushedOreZinc", "Zn");
			event.getRegistry().register(crushedOreZinc);
			OreDictionary.registerOre("crushedZinc", RedoxiationGenericItems.crushedOreZinc);
		}
		if(crushedOreNickelEnabled){
			crushedOreNickel = new RedoxiationGenericItems("crushedOreNickel", "Ni");
			event.getRegistry().register(crushedOreNickel);
			OreDictionary.registerOre("crushedNickel", RedoxiationGenericItems.crushedOreNickel);
		}
		if(crushedOreChromiumEnabled){
			crushedOreChromium = new RedoxiationGenericItems("crushedOreChromium", "Cr");
			event.getRegistry().register(crushedOreChromium);
			OreDictionary.registerOre("crushedChromium", RedoxiationGenericItems.crushedOreChromium);
		}
		if(dustOreIronEnabled){
			dustOreIron = new RedoxiationGenericItems("dustOreIron", "Fe");
			event.getRegistry().register(dustOreIron);
			OreDictionary.registerOre("dustOreIronRedox", RedoxiationGenericItems.dustOreIron);
		}
		if(dustOreGoldEnabled){
			dustOreGold = new RedoxiationGenericItems("dustOreGold", "Au");
			event.getRegistry().register(dustOreGold);
			OreDictionary.registerOre("dustOreGoldRedox", RedoxiationGenericItems.dustOreGold);
		}
		if(dustOreCopperEnabled){
			dustOreCopper = new RedoxiationGenericItems("dustOreCopper", "Cu");
			event.getRegistry().register(dustOreCopper);
			OreDictionary.registerOre("dustOreCopperRedox", RedoxiationGenericItems.dustOreCopper);
		}
		if(dustOreTinEnabled){
			dustOreTin = new RedoxiationGenericItems("dustOreTin", "Sn");
			event.getRegistry().register(dustOreTin);
			OreDictionary.registerOre("dustOreTinRedox", RedoxiationGenericItems.dustOreTin);
		}
		if(dustOreLeadEnabled){
			dustOreLead = new RedoxiationGenericItems("dustOreLead", "Pb");
			event.getRegistry().register(dustOreLead);
			OreDictionary.registerOre("dustOreLeadRedox", RedoxiationGenericItems.dustOreLead);
		}
		if(dustOreSilverEnabled){
			dustOreSilver = new RedoxiationGenericItems("dustOreSilver", "Ag");
			event.getRegistry().register(dustOreSilver);
			OreDictionary.registerOre("dustOreSilverRedox", RedoxiationGenericItems.dustOreSilver);
		}
		if(dustOrePlatinumEnabled){
			dustOrePlatinum = new RedoxiationGenericItems("dustOrePlatinum", "Pt");
			event.getRegistry().register(dustOrePlatinum);
			OreDictionary.registerOre("dustOrePlatinumRedox", RedoxiationGenericItems.dustOrePlatinum);
		}
    	if(dustOreZincEnabled){
			dustOreZinc = new RedoxiationGenericItems("dustOreZinc", "Zn");
			event.getRegistry().register(dustOreZinc);
			OreDictionary.registerOre("dustOreZincRedox", RedoxiationGenericItems.dustOreZinc);
    	}
    	if(dustOreNickelEnabled){
			dustOreNickel = new RedoxiationGenericItems("dustOreNickel", "Ni");
			event.getRegistry().register(dustOreNickel);
			OreDictionary.registerOre("dustOreNickelRedox", RedoxiationGenericItems.dustOreNickel);
    	}
    	if(dustOreChromiumEnabled){
			dustOreChromium = new RedoxiationGenericItems("dustOreChromium", "Cr");
			event.getRegistry().register(dustOreChromium);
			OreDictionary.registerOre("dustOreChromiumRedox", RedoxiationGenericItems.dustOreChromium);
    	}
    	if(dustSaltEnabled){
    		dustSalt = new RedoxiationGenericItems("dustSalt", "NaCl");
			event.getRegistry().register(dustSalt);
			OreDictionary.registerOre("dustOreSaltRedox", RedoxiationGenericItems.dustSalt);
    	}
    	if(dustBauxiteEnabled){
			dustBauxite = new RedoxiationGenericItems("dustBauxite", "Al2O3");
			event.getRegistry().register(dustBauxite);
			OreDictionary.registerOre("dustOreAluminumRedox", RedoxiationGenericItems.dustBauxite);
    	}
    	if(dustRutileEnabled){
			dustRutile = new RedoxiationGenericItems("dustRutile", "TiO2");
			event.getRegistry().register(dustRutile);
			OreDictionary.registerOre("dustOreTitaniumRedox", RedoxiationGenericItems.dustRutile);
    	}
    	if(dustScheeliteEnabled){
			dustScheelite = new RedoxiationGenericItems("dustScheelite", "WO3");
			event.getRegistry().register(dustScheelite);
			OreDictionary.registerOre("dustOreTungstenRedox", RedoxiationGenericItems.dustScheelite);
    	}
    	if(ingotCopperEnabled){
			ingotCopper = new RedoxiationGenericItems("ingotCopper", "Cu");
			event.getRegistry().register(ingotCopper);
			OreDictionary.registerOre("ingotCopper", RedoxiationGenericItems.ingotCopper);
    	}
    	if(ingotTinEnabled){
			ingotTin = new RedoxiationGenericItems("ingotTin", "Sn");
			event.getRegistry().register(ingotTin);
			OreDictionary.registerOre("ingotTin", RedoxiationGenericItems.ingotTin);
    	}
    	if(ingotLeadEnabled){
			ingotLead = new RedoxiationGenericItems("ingotLead", "Pb");
			event.getRegistry().register(ingotLead);
			OreDictionary.registerOre("ingotLead", RedoxiationGenericItems.ingotLead);
		}
    	if(ingotSilverEnabled){
			ingotSilver = new RedoxiationGenericItems("ingotSilver", "Ag");
			event.getRegistry().register(ingotSilver);
			OreDictionary.registerOre("ingotSilver", RedoxiationGenericItems.ingotSilver);
    	}
    	if(ingotPlatinumEnabled){
			ingotPlatinum = new RedoxiationGenericItems("ingotPlatinum", "Pt");	
			event.getRegistry().register(ingotPlatinum);
			OreDictionary.registerOre("ingotPlatinum", RedoxiationGenericItems.ingotPlatinum);
    	}
    	if(ingotZincEnabled){
			ingotZinc = new RedoxiationGenericItems("ingotZinc", "Zn");
			event.getRegistry().register(ingotZinc);
			OreDictionary.registerOre("ingotZinc", RedoxiationGenericItems.ingotZinc);
    	}
    	if(ingotNickelEnabled){
			ingotNickel = new RedoxiationGenericItems("ingotNickel", "Ni");
			event.getRegistry().register(ingotNickel);
			OreDictionary.registerOre("ingotNickel", RedoxiationGenericItems.ingotNickel);
    	}
    	if(ingotChromiumEnabled){
			ingotChromium = new RedoxiationGenericItems("ingotChromium", "Cr");
			event.getRegistry().register(ingotChromium);
			OreDictionary.registerOre("ingotChromium", RedoxiationGenericItems.ingotChromium);
    	}
    	if(ingotAluminumEnabled){
			ingotAluminum = new RedoxiationGenericItems("ingotAluminum", "Al2O3");
			event.getRegistry().register(ingotAluminum);
			OreDictionary.registerOre("ingotAluminumOort", RedoxiationGenericItems.ingotAluminum);
    	}
    	if(ingotTitaniumEnabled){
			ingotTitanium = new RedoxiationGenericItems("ingotTitanium", "TiO2");
			event.getRegistry().register(ingotTitanium);
			OreDictionary.registerOre("ingotTitaniumOort", RedoxiationGenericItems.ingotTitanium);
    	}
    	if(ingotTungstenEnabled){
			ingotTungsten = new RedoxiationGenericItems("ingotTungsten", "WO3");
			event.getRegistry().register(ingotTungsten);
			OreDictionary.registerOre("ingotTungstenOort", RedoxiationGenericItems.ingotTungsten);
		}
    	if(ingotCobaltEnabled){
			ingotCobalt = new RedoxiationGenericItems("ingotCobalt","Co");
			event.getRegistry().register(ingotCobalt);
			OreDictionary.registerOre("ingotCobalt", RedoxiationGenericItems.ingotCobalt);
    	}
    	if(ingotSteelEnabled){
			ingotSteel = new RedoxiationGenericItems("ingotSteel");
			event.getRegistry().register(ingotSteel);
			OreDictionary.registerOre("ingotSteelOort", RedoxiationGenericItems.ingotSteel);
    	}
    	if(nuggetIronEnabled){
    		nuggetIron = new RedoxiationGenericItems("nuggetIron", "Fe");
			event.getRegistry().register(nuggetIron);
			OreDictionary.registerOre("nuggetIron", RedoxiationGenericItems.nuggetIron);
    	}
    	if(nuggetCopperEnabled){
    		nuggetCopper = new RedoxiationGenericItems("nuggetCopper", "Cu");
			event.getRegistry().register(nuggetCopper);
			OreDictionary.registerOre("nuggetCopper", RedoxiationGenericItems.nuggetCopper);
    	}
    	if(nuggetTinEnabled){
			nuggetTin = new RedoxiationGenericItems("nuggetTin", "Sn");
			event.getRegistry().register(nuggetTin);
			OreDictionary.registerOre("nuggetTin", RedoxiationGenericItems.nuggetTin);
    	}
    	if(nuggetLeadEnabled){
    		nuggetLead = new RedoxiationGenericItems("nuggetLead", "Pb");
			event.getRegistry().register(nuggetLead);
			OreDictionary.registerOre("nuggetLead", RedoxiationGenericItems.nuggetLead);
    	}
    	if(nuggetSilverEnabled){
    		nuggetSilver = new RedoxiationGenericItems("nuggetSilver", "Ag");
			event.getRegistry().register(nuggetSilver);
			OreDictionary.registerOre("nuggetSilver", RedoxiationGenericItems.nuggetSilver);
    	}
    	if(nuggetPlatinumEnabled){
    		nuggetPlatinum = new RedoxiationGenericItems("nuggetPlatinum", "Pt");
			event.getRegistry().register(nuggetPlatinum);
			OreDictionary.registerOre("nuggetPlatinum", RedoxiationGenericItems.nuggetPlatinum);
    	}
    	if(nuggetZincEnabled){
    		nuggetZinc = new RedoxiationGenericItems("nuggetZinc", "Zn");
			event.getRegistry().register(nuggetZinc);
			OreDictionary.registerOre("nuggetZinc", RedoxiationGenericItems.nuggetZinc);
    	}
    	if(nuggetNickelEnabled){
    		nuggetNickel = new RedoxiationGenericItems("nuggetNickel", "Ni");
			event.getRegistry().register(nuggetNickel);
			OreDictionary.registerOre("nuggetNickel", RedoxiationGenericItems.nuggetNickel);
    	}
    	if(nuggetChromiumEnabled){
    		nuggetChromium = new RedoxiationGenericItems("nuggetChromium", "Cr");
			event.getRegistry().register(nuggetChromium);
			OreDictionary.registerOre("nuggetChromium", RedoxiationGenericItems.nuggetChromium);
    	}
    	if(nuggetAluminumEnabled){
    		nuggetAluminum = new RedoxiationGenericItems("nuggetAluminum", "Al2O3");
			event.getRegistry().register(nuggetAluminum);
			OreDictionary.registerOre("nuggetAluminum", RedoxiationGenericItems.nuggetAluminum);
    	}
    	if(nuggetTitaniumEnabled){
    		nuggetTitanium = new RedoxiationGenericItems("nuggetTitanium", "TiO2");
			event.getRegistry().register(nuggetTitanium);
			OreDictionary.registerOre("nuggetTitanium", RedoxiationGenericItems.nuggetTitanium);
		}
    	if(nuggetTungstenEnabled){
    		nuggetTungsten = new RedoxiationGenericItems("nuggetTungsten", "WO3");
			event.getRegistry().register(nuggetTungsten);
			OreDictionary.registerOre("nuggetTungsten", RedoxiationGenericItems.nuggetTungsten);
    	}
    	if(nuggetSteelEnabled){
    		nuggetSteel = new RedoxiationGenericItems("nuggetSteel");
			event.getRegistry().register(nuggetSteel);
			OreDictionary.registerOre("nuggetSteel", RedoxiationGenericItems.nuggetSteel);
    	}
    	if(sulfurChunkEnabled){
			sulfurChunk = new RedoxiationGenericItems("sulfurChunk");
			event.getRegistry().register(sulfurChunk);
			OreDictionary.registerOre("lumpSulfur", RedoxiationGenericItems.sulfurChunk);
    	}
    	if(dustSulfurEnabled){
    		dustSulfur = new RedoxiationGenericItems("dustSulfur");
			event.getRegistry().register(dustSulfur);
			OreDictionary.registerOre("dustOreSulfurOort", RedoxiationGenericItems.dustSulfur);
		}
    	if(dolomitePowderEnabled){
    		dolomitePowder = new RedoxiationGenericItems("dolomitePowder");
    		event.getRegistry().register(dolomitePowder);
    	}
    	if(itemCryoliteEnabled){
			itemCryolite = new RedoxiationGenericItems("itemCryolite");
			event.getRegistry().register(itemCryolite);
			OreDictionary.registerOre("materialCryolite", itemCryolite);
    	}
    	if(crashedNetherrackEnabled){
			crashedNetherrack = new RedoxiationGenericItems("crashedNetherrack");
    	}
    	if(crashedObsidianEnabled){
			crashedObsidian = new RedoxiationGenericItems("crashedObsidian");
    	}
        if(itemHotAirBucketEnabled) {
            itemHotAirBucket = new RedoxiationBucket(RedoxiationBlocks.hotAirBlock, "HotAirBucket");
            itemHotAirBucket.setUnlocalizedName(Redoxiation.MODID + "." + "HotAirBucket").setContainerItem(Items.bucket);
            event.getRegistry().register(itemHotAirBucket);
            FluidContainerRegistry.registerFluidContainer(RedoxiationBlocks.hotAir, new ItemStack(itemHotAirBucket), new ItemStack(Items.bucket));
            BucketHandler.INSTANCE.buckets.put(RedoxiationBlocks.hotAirBlock, itemHotAirBucket);
            MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
        }
        if(itemMoltenPigironBucketEnabled) {
            itemMoltenPigironBucket = new RedoxiationBucket(RedoxiationBlocks.moltenPigironBlock, "MoltenPigironBucket");
            itemMoltenPigironBucket.setUnlocalizedName(Redoxiation.MODID + "." + "MoltenPigironBucket").setContainerItem(Items.bucket);
            event.getRegistry().register(itemMoltenPigironBucket);
            FluidContainerRegistry.registerFluidContainer(RedoxiationBlocks.moltenPigiron, new ItemStack(itemMoltenPigironBucket), new ItemStack(Items.bucket));
            BucketHandler.INSTANCE.buckets.put(RedoxiationBlocks.moltenPigironBlock, itemMoltenPigironBucket);
            MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
        }
        if(itemSlagBucketEnabled) {
            itemSlagBucket = new RedoxiationBucket(RedoxiationBlocks.slagBlock, "SlagBucket");
            itemSlagBucket.setUnlocalizedName(Redoxiation.MODID + "." + "SlagBucket").setContainerItem(Items.bucket);
            event.getRegistry().register(itemSlagBucket);
            FluidContainerRegistry.registerFluidContainer(RedoxiationBlocks.slag, new ItemStack(itemSlagBucket), new ItemStack(Items.bucket));
            BucketHandler.INSTANCE.buckets.put(RedoxiationBlocks.slagBlock, itemSlagBucket);
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
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!(INFORMATION.equals("null"))) {
			list.add(INFORMATION);
		}
	}
}
