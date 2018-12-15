package teamKuiper.redoxiation.items.temp;

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

public class RedoxiationItems extends Item {
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

	public RedoxiationItems(String name, String info) {
		super();
		setCreativeTab(Redoxiation.tabRedoxiationitems);
		INFORMATION = info;
		ITEMNAME = name;
	}

	public RedoxiationItems(String name) {
		super();
		setCreativeTab(Redoxiation.tabRedoxiationitems);
		ITEMNAME = name;
		INFORMATION = "null";
	}

	public static void registerItems(RegistryEvent.Register<Item> event) {
		if(calciteEnabled){
			calcite = new RedoxiationItems("calcite", "CaCO3");
			event.getRegistry().register(calcite);
			OreDictionary.registerOre("materialcalcite", RedoxiationItems.calcite);
    	}
		if(saltChunkEnabled){
			saltChunk = new RedoxiationItems("saltChunk", "NaCl");
			event.getRegistry().register(saltChunk);
			OreDictionary.registerOre("lumpSalt", RedoxiationItems.saltChunk);
		}
		if(rawBauxiteEnabled){
			rawBauxite = new RedoxiationItems("rawBauxite", "Al(OH)3");
			event.getRegistry().register(rawBauxite);
			OreDictionary.registerOre("lumpBauxite", RedoxiationItems.rawBauxite);
		}
		if(rawRutileEnabled){
			rawRutile = new RedoxiationItems("rawRutile", "Ti");
			event.getRegistry().register(rawRutile);
			OreDictionary.registerOre("lumpRutile", RedoxiationItems.rawRutile);
		}
		if(rawScheeliteEnabled){
			rawScheelite = new RedoxiationItems("rawScheelite", "CaWO4");
			event.getRegistry().register(rawScheelite);
			OreDictionary.registerOre("lumpScheelite", RedoxiationItems.rawScheelite);
		}
		if(dolomiteShardEnabled){
			dolomiteShard = new RedoxiationItems("dolomiteShard");
			event.getRegistry().register(dolomiteShard);
		}
		if(crushedOreIronEnabled){
			crushedOreIron = new RedoxiationItems("crushedOreIron", "Fe");
			event.getRegistry().register(crushedOreIron);
			OreDictionary.registerOre("crushedIron", RedoxiationItems.crushedOreIron);
    	}
		if(crushedOreGoldEnabled){
			crushedOreGold = new RedoxiationItems("crushedOreGold", "Au");
			event.getRegistry().register(crushedOreGold);
			OreDictionary.registerOre("crushedGold", RedoxiationItems.crushedOreGold);
    	}
		if(crushedOreCopperEnabled){
			crushedOreCopper = new RedoxiationItems("crushedOreCopper", "Cu");
			event.getRegistry().register(crushedOreCopper);
			OreDictionary.registerOre("crushedCopper", RedoxiationItems.crushedOreCopper);
    	}
		if(crushedOreTinEnabled){
			crushedOreTin = new RedoxiationItems("crushedOreTin", "Sn");
			event.getRegistry().register(crushedOreTin);
			OreDictionary.registerOre("crushedTin", RedoxiationItems.crushedOreTin);
    	}
		if(crushedOreLeadEnabled){
			crushedOreLead = new RedoxiationItems("crushedOreLead", "Pb");
			event.getRegistry().register(crushedOreLead);
			OreDictionary.registerOre("crushedLead", RedoxiationItems.crushedOreLead);
    	}
		if(crushedOreSilverEnabled){
			crushedOreSilver = new RedoxiationItems("crushedOreSilver", "Ag");
			event.getRegistry().register(crushedOreSilver);
			OreDictionary.registerOre("crushedSilver", RedoxiationItems.crushedOreSilver);
		}
		if(crushedOrePlatinumEnabled){
			crushedOrePlatinum = new RedoxiationItems("crushedOrePlatinum", "Pt");
			event.getRegistry().register(crushedOrePlatinum);
			OreDictionary.registerOre("crushedPlatinum", RedoxiationItems.crushedOrePlatinum);
		}
		if(crushedOreZincEnabled){
			crushedOreZinc = new RedoxiationItems("crushedOreZinc", "Zn");
			event.getRegistry().register(crushedOreZinc);
			OreDictionary.registerOre("crushedZinc", RedoxiationItems.crushedOreZinc);
		}
		if(crushedOreNickelEnabled){
			crushedOreNickel = new RedoxiationItems("crushedOreNickel", "Ni");
			event.getRegistry().register(crushedOreNickel);
			OreDictionary.registerOre("crushedNickel", RedoxiationItems.crushedOreNickel);
		}
		if(crushedOreChromiumEnabled){
			crushedOreChromium = new RedoxiationItems("crushedOreChromium", "Cr");
			event.getRegistry().register(crushedOreChromium);
			OreDictionary.registerOre("crushedChromium", RedoxiationItems.crushedOreChromium);
		}
		if(dustOreIronEnabled){
			dustOreIron = new RedoxiationItems("dustOreIron", "Fe");
			event.getRegistry().register(dustOreIron);
			OreDictionary.registerOre("dustOreIronRedox", RedoxiationItems.dustOreIron);
		}
		if(dustOreGoldEnabled){
			dustOreGold = new RedoxiationItems("dustOreGold", "Au");
			event.getRegistry().register(dustOreGold);
			OreDictionary.registerOre("dustOreGoldRedox", RedoxiationItems.dustOreGold);
		}
		if(dustOreCopperEnabled){
			dustOreCopper = new RedoxiationItems("dustOreCopper", "Cu");
			event.getRegistry().register(dustOreCopper);
			OreDictionary.registerOre("dustOreCopperRedox", RedoxiationItems.dustOreCopper);
		}
		if(dustOreTinEnabled){
			dustOreTin = new RedoxiationItems("dustOreTin", "Sn");
			event.getRegistry().register(dustOreTin);
			OreDictionary.registerOre("dustOreTinRedox", RedoxiationItems.dustOreTin);
		}
		if(dustOreLeadEnabled){
			dustOreLead = new RedoxiationItems("dustOreLead", "Pb");
			event.getRegistry().register(dustOreLead);
			OreDictionary.registerOre("dustOreLeadRedox", RedoxiationItems.dustOreLead);
		}
		if(dustOreSilverEnabled){
			dustOreSilver = new RedoxiationItems("dustOreSilver", "Ag");
			event.getRegistry().register(dustOreSilver);
			OreDictionary.registerOre("dustOreSilverRedox", RedoxiationItems.dustOreSilver);
		}
		if(dustOrePlatinumEnabled){
			dustOrePlatinum = new RedoxiationItems("dustOrePlatinum", "Pt");
			event.getRegistry().register(dustOrePlatinum);
			OreDictionary.registerOre("dustOrePlatinumRedox", RedoxiationItems.dustOrePlatinum);
		}
    	if(dustOreZincEnabled){
			dustOreZinc = new RedoxiationItems("dustOreZinc", "Zn");
			event.getRegistry().register(dustOreZinc);
			OreDictionary.registerOre("dustOreZincRedox", RedoxiationItems.dustOreZinc);
    	}
    	if(dustOreNickelEnabled){
			dustOreNickel = new RedoxiationItems("dustOreNickel", "Ni");
			event.getRegistry().register(dustOreNickel);
			OreDictionary.registerOre("dustOreNickelRedox", RedoxiationItems.dustOreNickel);
    	}
    	if(dustOreChromiumEnabled){
			dustOreChromium = new RedoxiationItems("dustOreChromium", "Cr");
			event.getRegistry().register(dustOreChromium);
			OreDictionary.registerOre("dustOreChromiumRedox", RedoxiationItems.dustOreChromium);
    	}
    	if(dustSaltEnabled){
    		dustSalt = new RedoxiationItems("dustSalt", "NaCl");
			event.getRegistry().register(dustSalt);
			OreDictionary.registerOre("dustOreSaltRedox", RedoxiationItems.dustSalt);
    	}
    	if(dustBauxiteEnabled){
			dustBauxite = new RedoxiationItems("dustBauxite", "Al2O3");
			event.getRegistry().register(dustBauxite);
			OreDictionary.registerOre("dustOreAluminumRedox", RedoxiationItems.dustBauxite);
    	}
    	if(dustRutileEnabled){
			dustRutile = new RedoxiationItems("dustRutile", "TiO2");
			event.getRegistry().register(dustRutile);
			OreDictionary.registerOre("dustOreTitaniumRedox", RedoxiationItems.dustRutile);
    	}
    	if(dustScheeliteEnabled){
			dustScheelite = new RedoxiationItems("dustScheelite", "WO3");
			event.getRegistry().register(dustScheelite);
			OreDictionary.registerOre("dustOreTungstenRedox", RedoxiationItems.dustScheelite);
    	}
    	if(ingotCopperEnabled){
			ingotCopper = new RedoxiationItems("ingotCopper", "Cu");
			event.getRegistry().register(ingotCopper);
			OreDictionary.registerOre("ingotCopper", RedoxiationItems.ingotCopper);
    	}
    	if(ingotTinEnabled){
			ingotTin = new RedoxiationItems("ingotTin", "Sn");
			event.getRegistry().register(ingotTin);
			OreDictionary.registerOre("ingotTin", RedoxiationItems.ingotTin);
    	}
    	if(ingotLeadEnabled){
			ingotLead = new RedoxiationItems("ingotLead", "Pb");
			event.getRegistry().register(ingotLead);
			OreDictionary.registerOre("ingotLead", RedoxiationItems.ingotLead);
		}
    	if(ingotSilverEnabled){
			ingotSilver = new RedoxiationItems("ingotSilver", "Ag");
			event.getRegistry().register(ingotSilver);
			OreDictionary.registerOre("ingotSilver", RedoxiationItems.ingotSilver);
    	}
    	if(ingotPlatinumEnabled){
			ingotPlatinum = new RedoxiationItems("ingotPlatinum", "Pt");	
			event.getRegistry().register(ingotPlatinum);
			OreDictionary.registerOre("ingotPlatinum", RedoxiationItems.ingotPlatinum);
    	}
    	if(ingotZincEnabled){
			ingotZinc = new RedoxiationItems("ingotZinc", "Zn");
			event.getRegistry().register(ingotZinc);
			OreDictionary.registerOre("ingotZinc", RedoxiationItems.ingotZinc);
    	}
    	if(ingotNickelEnabled){
			ingotNickel = new RedoxiationItems("ingotNickel", "Ni");
			event.getRegistry().register(ingotNickel);
			OreDictionary.registerOre("ingotNickel", RedoxiationItems.ingotNickel);
    	}
    	if(ingotChromiumEnabled){
			ingotChromium = new RedoxiationItems("ingotChromium", "Cr");
			event.getRegistry().register(ingotChromium);
			OreDictionary.registerOre("ingotChromium", RedoxiationItems.ingotChromium);
    	}
    	if(ingotAluminumEnabled){
			ingotAluminum = new RedoxiationItems("ingotAluminum", "Al2O3");
			event.getRegistry().register(ingotAluminum);
			OreDictionary.registerOre("ingotAluminumOort", RedoxiationItems.ingotAluminum);
    	}
    	if(ingotTitaniumEnabled){
			ingotTitanium = new RedoxiationItems("ingotTitanium", "TiO2");
			event.getRegistry().register(ingotTitanium);
			OreDictionary.registerOre("ingotTitaniumOort", RedoxiationItems.ingotTitanium);
    	}
    	if(ingotTungstenEnabled){
			ingotTungsten = new RedoxiationItems("ingotTungsten", "WO3");
			event.getRegistry().register(ingotTungsten);
			OreDictionary.registerOre("ingotTungstenOort", RedoxiationItems.ingotTungsten);
		}
    	if(ingotCobaltEnabled){
			ingotCobalt = new RedoxiationItems("ingotCobalt","Co");
			event.getRegistry().register(ingotCobalt);
			OreDictionary.registerOre("ingotCobalt", RedoxiationItems.ingotCobalt);
    	}
    	if(ingotSteelEnabled){
			ingotSteel = new RedoxiationItems("ingotSteel");
			event.getRegistry().register(ingotSteel);
			OreDictionary.registerOre("ingotSteelOort", RedoxiationItems.ingotSteel);
    	}
    	if(nuggetIronEnabled){
    		nuggetIron = new RedoxiationItems("nuggetIron", "Fe");
			event.getRegistry().register(nuggetIron);
			OreDictionary.registerOre("nuggetIron", RedoxiationItems.nuggetIron);
    	}
    	if(nuggetCopperEnabled){
    		nuggetCopper = new RedoxiationItems("nuggetCopper", "Cu");
			event.getRegistry().register(nuggetCopper);
			OreDictionary.registerOre("nuggetCopper", RedoxiationItems.nuggetCopper);
    	}
    	if(nuggetTinEnabled){
			nuggetTin = new RedoxiationItems("nuggetTin", "Sn");
			event.getRegistry().register(nuggetTin);
			OreDictionary.registerOre("nuggetTin", RedoxiationItems.nuggetTin);
    	}
    	if(nuggetLeadEnabled){
    		nuggetLead = new RedoxiationItems("nuggetLead", "Pb");
			event.getRegistry().register(nuggetLead);
			OreDictionary.registerOre("nuggetLead", RedoxiationItems.nuggetLead);
    	}
    	if(nuggetSilverEnabled){
    		nuggetSilver = new RedoxiationItems("nuggetSilver", "Ag");
			event.getRegistry().register(nuggetSilver);
			OreDictionary.registerOre("nuggetSilver", RedoxiationItems.nuggetSilver);
    	}
    	if(nuggetPlatinumEnabled){
    		nuggetPlatinum = new RedoxiationItems("nuggetPlatinum", "Pt");
			event.getRegistry().register(nuggetPlatinum);
			OreDictionary.registerOre("nuggetPlatinum", RedoxiationItems.nuggetPlatinum);
    	}
    	if(nuggetZincEnabled){
    		nuggetZinc = new RedoxiationItems("nuggetZinc", "Zn");
			event.getRegistry().register(nuggetZinc);
			OreDictionary.registerOre("nuggetZinc", RedoxiationItems.nuggetZinc);
    	}
    	if(nuggetNickelEnabled){
    		nuggetNickel = new RedoxiationItems("nuggetNickel", "Ni");
			event.getRegistry().register(nuggetNickel);
			OreDictionary.registerOre("nuggetNickel", RedoxiationItems.nuggetNickel);
    	}
    	if(nuggetChromiumEnabled){
    		nuggetChromium = new RedoxiationItems("nuggetChromium", "Cr");
			event.getRegistry().register(nuggetChromium);
			OreDictionary.registerOre("nuggetChromium", RedoxiationItems.nuggetChromium);
    	}
    	if(nuggetAluminumEnabled){
    		nuggetAluminum = new RedoxiationItems("nuggetAluminum", "Al2O3");
			event.getRegistry().register(nuggetAluminum);
			OreDictionary.registerOre("nuggetAluminum", RedoxiationItems.nuggetAluminum);
    	}
    	if(nuggetTitaniumEnabled){
    		nuggetTitanium = new RedoxiationItems("nuggetTitanium", "TiO2");
			event.getRegistry().register(nuggetTitanium);
			OreDictionary.registerOre("nuggetTitanium", RedoxiationItems.nuggetTitanium);
		}
    	if(nuggetTungstenEnabled){
    		nuggetTungsten = new RedoxiationItems("nuggetTungsten", "WO3");
			event.getRegistry().register(nuggetTungsten);
			OreDictionary.registerOre("nuggetTungsten", RedoxiationItems.nuggetTungsten);
    	}
    	if(nuggetSteelEnabled){
    		nuggetSteel = new RedoxiationItems("nuggetSteel");
			event.getRegistry().register(nuggetSteel);
			OreDictionary.registerOre("nuggetSteel", RedoxiationItems.nuggetSteel);
    	}
    	if(sulfurChunkEnabled){
			sulfurChunk = new RedoxiationItems("sulfurChunk");
			event.getRegistry().register(sulfurChunk);
			OreDictionary.registerOre("lumpSulfur", RedoxiationItems.sulfurChunk);
    	}
    	if(dustSulfurEnabled){
    		dustSulfur = new RedoxiationItems("dustSulfur");
			event.getRegistry().register(dustSulfur);
			OreDictionary.registerOre("dustOreSulfurOort", RedoxiationItems.dustSulfur);
		}
    	if(dolomitePowderEnabled){
    		dolomitePowder = new RedoxiationItems("dolomitePowder");
    		event.getRegistry().register(dolomitePowder);
    	}
    	if(itemCryoliteEnabled){
			itemCryolite = new RedoxiationItems("itemCryolite");
			event.getRegistry().register(itemCryolite);
			OreDictionary.registerOre("materialCryolite", itemCryolite);
    	}
    	if(crashedNetherrackEnabled){
			crashedNetherrack = new RedoxiationItems("crashedNetherrack");
    	}
    	if(crashedObsidianEnabled){
			crashedObsidian = new RedoxiationItems("crashedObsidian");
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
